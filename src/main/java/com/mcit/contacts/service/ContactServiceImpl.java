package com.mcit.contacts.service;

import java.util.List;

import com.mcit.contacts.Constants;
import com.mcit.contacts.pojo.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.mcit.contacts.repository.ContactRepository;

@Service
//@ConditionalOnProperty(name = "server.port", havingValue = "8080")
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;


    public Contact getContact(int index){
        return contactRepository.getContact(index);
    }
    public int getIndexOfId(String id){
        for(int i = 0; i<getAllContacts().size(); i++){
            if(getContact(i).getId().equals(id))
                return i;
        }
        return Constants.Not_Found;
    }

    @Override
    public Contact getContactById(String id) {
            return contactRepository.getContact(getIndexOfId(id));
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.getContacts();
    }

    @Override
    public void addContact(Contact contact) {
        contactRepository.saveContact(contact);
    }

    @Override
    public void updateContact(String id, Contact contact) {
        contactRepository.updateContact(getIndexOfId(id), contact);
    }

    @Override
    public void deleteContact(String id) {
        contactRepository.deleteContact(getIndexOfId(id));
    }
}
