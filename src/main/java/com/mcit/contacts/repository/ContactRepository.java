package com.mcit.contacts.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mcit.contacts.pojo.Contact;

@Repository
public class ContactRepository {
    
    private List<Contact> contacts = Arrays.asList(
            new Contact("123","Ahmad","785422215"),
            new Contact("125","Mahmood","700814752"),
            new Contact("127","Karim","798521452")
    );

    public List<Contact> getContacts() {
        return contacts;
    }

    public Contact getContact(int index) {
        return contacts.get(index);
    }

    public void saveContact(Contact contact) {
        contacts.add(contact);
    }

    public void updateContact(int index, Contact contact) { 
        contacts.set(index, contact); 
    }
    
    public void deleteContact(int index) {
        contacts.remove(index);
    }

}
