package com.contact.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.contact.model.Contact;
import com.contact.model.ContactResponse;

@Service
public class ContactService {

	@Value("${csv.file}")
	String csvFileName;

	public List<Contact> readContactsFromCsv() {
		List<Contact> contactList = new ArrayList<>();
		try {
			File file = new ClassPathResource(csvFileName).getFile();
			contactList = readLine(file).lines().skip(1).map(c -> generateContact(c))
					.sorted((c1, c2) -> c1.getName().compareTo(c2.getName())).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contactList;
	}

	private BufferedReader readLine(File file) throws FileNotFoundException {
		return new BufferedReader(new FileReader(file));
	}

	Contact generateContact(String line) {
		String[] values = line.split(",");
		Contact contact = new Contact();
		contact.setName(values[0]);
		contact.setImageUrl(values[1]);
		return contact;
	}

	public ContactResponse getContacts(List<Contact> contactList, int skipCount, int limitCount) {
		ContactResponse contactResponse = new ContactResponse();
		contactResponse.setTotalCount(contactList.size());
		contactResponse.setContactList(contactList.stream().skip(skipCount).limit(limitCount)
				.collect(Collectors.toList()));
		return contactResponse;
	}
	
	public ContactResponse searchContact(List<Contact> contactList,String searchKeyWord, int limitCount) {
		ContactResponse contactResponse = new ContactResponse();
		List<Contact> filteredContactList = contactList.stream()
				.filter(c -> c.getName().toLowerCase().contains(searchKeyWord.toLowerCase())).skip(0).limit(limitCount)
				.toList();

		contactResponse.setTotalCount(filteredContactList.size());
		contactResponse.setContactList(filteredContactList);
		return contactResponse;
	}

}
