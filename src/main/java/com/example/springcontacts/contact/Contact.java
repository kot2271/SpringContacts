package com.example.springcontacts.contact;

import org.springframework.stereotype.Component;

@Component
public class Contact {
    private String fullName;
    private String phoneNumber;
    private String email;


    public String getFullName() {
        return fullName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return fullName + ";" + phoneNumber + ";" + email;
    }
}
