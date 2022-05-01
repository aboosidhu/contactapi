package com.contact.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.contact.model.Contact;

@SpringBootTest
class ContactServiceTest {

	@InjectMocks
	ContactService contactService;

	@Test
	void testGetContacts() {
		List<Contact> contactList = generateContactList();
		assertThat(contactService.getContacts(contactList, 0, 1).getTotalCount() == 1).isTrue();
	}

	@Test
	void testSeachContacts() {
		List<Contact> contactList = generateContactList();
		assertThat(contactService.searchContact(contactList, "A", 1).getTotalCount() == 1).isTrue();
	}

	List<Contact> generateContactList() {
		List<Contact> contactList = new ArrayList<>();
		Contact contact = new Contact();
		contact.setName("JHON");
		contact.setImageUrl("imageurl");
		Contact contact1 = new Contact();
		contact1.setName("ABS");
		contact1.setImageUrl("imageurl");
		contactList.add(contact1);
		return contactList;
	}
}
