package com.mcit.contacts.pojo;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class Contact {
    @NotBlank(message = "Id should not be null")
    private String id;
    @NotBlank(message = "Name should not be null")
    private String name;
    @NotBlank(message = "Phone Number should not be null")
    private String phoneNumber;


    public Contact() {
        this.id = UUID.randomUUID().toString();
    }

    public Contact(String id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
