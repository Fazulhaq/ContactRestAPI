package com.mcit.contacts.web;

import com.mcit.contacts.pojo.Contact;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.mcit.contacts.service.ContactService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Contact Controller", description = "Create and retrieve contacts")
public class ContactController {
    
    @Autowired
    private ContactService contactService;
    @Operation(summary = "Get contact by Id", description = "You can pass the id and it returns the data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Id not found in the list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class)))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of contact", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Contact.class))))
    })
    @GetMapping(value = "/contact/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> getContactById(@PathVariable String id){
        Contact contact = contactService.getContactById(id);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }
    @Operation(summary = "Get all contacts", description = "Get all the contact in the list")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of contacts", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Contact.class))))
    @GetMapping(value = "/contacts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contact>> getAllContacts(){
        List<Contact>  contacts = contactService.getAllContacts();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }
    @Operation(summary = "Add a contact", description = "Pass a complete contact details and it stores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact successfully adds"),
            @ApiResponse(responseCode = "400", description = "Unsuccessful operation on adding contact", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exception.class))))
    })
    @PostMapping("/contact")
    public ResponseEntity<HttpStatus> createContact(@RequestBody Contact contact){
        contactService.addContact(contact);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @Operation(summary = "Edit a contact", description = "Give the id and data and it will update based on that id")
    @PutMapping(value = "/updatecontact/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> updateContact(@PathVariable String id, @RequestBody Contact contact){
        contactService.updateContact(id, contact);
        return new ResponseEntity<>(contactService.getContactById(id), HttpStatus.OK);
    }
    @Operation(summary = "Delete a contact", description = "Pass the id and it will get removed")
    @DeleteMapping("/deletecontact/{id}")
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable String id){
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
