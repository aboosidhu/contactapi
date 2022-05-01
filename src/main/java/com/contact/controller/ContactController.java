package com.contact.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contact.model.Contact;
import com.contact.model.ContactResponse;
import com.contact.service.ContactService;

@RestController
@RequestMapping("contact")
@CrossOrigin("http://localhost:4200")
public class ContactController {

	@Autowired
	ContactService contactService;

	List<Contact> contactList = new ArrayList<>();

	@PostConstruct
	List<Contact> generateContactList() {
		contactList=contactService.readContactsFromCsv();
		return contactList;
	}

	@GetMapping("/list/{skipCount}/{limitCount}")
	public ResponseEntity<ContactResponse>getContactList(@PathVariable("skipCount") int skipCount,
			@PathVariable("limitCount") int limitCount) {
		return ResponseEntity.ok(contactService.getContacts(contactList,skipCount,limitCount));
	}

	@GetMapping("/search/{limitCount}/{searchKeyWord}")
	public ResponseEntity<ContactResponse> searchContact(@PathVariable("limitCount") int limitCount,
			@PathVariable("searchKeyWord") String searchKeyWord) {
		return ResponseEntity.ok(contactService.searchContact(contactList, searchKeyWord, limitCount));
	}

}
