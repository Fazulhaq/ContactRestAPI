package com.mcit.contacts.web;

import com.mcit.contacts.pojo.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.mcit.contacts.service.ContactService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {
    
    @Autowired
    private ContactService contactService;

    @GetMapping("/contact/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable String id){
        Contact contact = contactService.getContactById(id);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }
    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> getAllContacts(){
        List<Contact>  contacts = contactService.getAllContacts();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }
    @PostMapping("/contact")
    public ResponseEntity<HttpStatus> createContact(@RequestBody Contact contact){
        contactService.addContact(contact);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/updatecontact/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable String id, @RequestBody Contact contact){
        contactService.updateContact(id, contact);
        return new ResponseEntity<>(contactService.getContactById(id), HttpStatus.OK);
    }
    @DeleteMapping("/deletecontact/{id}")
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable String id){
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
