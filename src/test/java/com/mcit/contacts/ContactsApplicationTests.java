package com.mcit.contacts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcit.contacts.pojo.Contact;
import com.mcit.contacts.repository.ContactRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ContactsApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private ObjectMapper objectMapper;

	private Contact[] contacts = new Contact[]{
			new Contact("123","Ahmad","8475111212"),
			new Contact("125","Mahmood","9584222222"),
			new Contact("127","Mohammad","7333254444")
	};
	@BeforeEach
	void setup(){
		for (int i = 0; i< contacts.length; i++){
			contactRepository.saveContact(contacts[i]);
		}
	}
	@AfterEach
	void clear(){
		contactRepository.getContacts().clear();
	}
	@Test
	public void getContactByIdTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/contact/123");

		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.name").value("Ahmad"))
				.andExpect(jsonPath("$.phoneNumber").value(contacts[0].getPhoneNumber()));
	}
	@Test
	public void getAllContactsTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/contacts");

		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.size()").value(contacts.length))
				.andExpect(jsonPath("$.[?(@.id == \"123\" && @.name == \"Ahmad\" && @.phoneNumber == \"8475111212\")]").exists());
	}
	@Test
	public void contactNotFoundTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/contact/129");

		mockMvc.perform(request)
				.andExpect(status().isNotFound());
	}
	@Test
	public void validContactCreation() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/contact")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(new Contact("555","Karim","8541225777")));

		mockMvc.perform(request)
				.andExpect(status().isCreated());
	}
	@Test
	public void invalidContactCreation() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/contact")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(new Contact("   ","    ","    ")));

		mockMvc.perform(request)
				.andExpect(status().isBadRequest());
	}

}
