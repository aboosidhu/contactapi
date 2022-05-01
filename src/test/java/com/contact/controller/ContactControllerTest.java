package com.contact.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.contact.model.Contact;
import com.contact.model.ContactResponse;
import com.contact.service.ContactService;

@SpringBootTest
class ContactControllerTest {
	
	@InjectMocks
	ContactController contactController;

	@Mock
	ContactService contactService;

	@Test
	void testGetContactList() {
		MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));

		ContactResponse contactResponse=new ContactResponse();
		List<Contact> contactList=new ArrayList<>();
		Contact contact=new Contact();
		contact.setName("JHON");
		contact.setImageUrl("imageurl");
		contactList.add(contact);
		contactResponse.setContactList(new ArrayList<Contact>());
		contactResponse.setTotalCount(1);
		when(contactService.getContacts(new ArrayList<Contact>(), 0, 0)).thenReturn(contactResponse);

		ResponseEntity<ContactResponse> responseEntity = contactController.getContactList(0, 0);
		
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		assertThat(responseEntity.getBody()).isEqualTo(contactResponse);
	}
	
	@Test
	void searchContact() {
		MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));

		ContactResponse contactResponse=new ContactResponse();
		List<Contact> contactList=new ArrayList<>();
		Contact contact=new Contact();
		contact.setName("BDMMMM");
		contact.setImageUrl("imageurl");
		contactList.add(contact);
		contactResponse.setContactList(new ArrayList<Contact>());
		contactResponse.setTotalCount(1);
		when(contactService.searchContact(new ArrayList<Contact>(), "A", 0)).thenReturn(contactResponse);

		ResponseEntity<ContactResponse> responseEntity = contactController.searchContact(0,"A");
		
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		assertThat(responseEntity.getBody()).isEqualTo(contactResponse);
	}

}
