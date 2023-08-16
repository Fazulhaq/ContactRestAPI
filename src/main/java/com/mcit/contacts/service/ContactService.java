package com.mcit.contacts.service;

import com.mcit.contacts.pojo.Contact;

import java.util.List;

public interface ContactService {
    Contact getContactById(String id);

    List<Contact> getAllContacts();

    void addContact(Contact contact);

    void updateContact(String id, Contact contact);

    void deleteContact(String id);
}
