package com.addressbook;

/**
 * Exception thrown when a contact is not found
 */
public class ContactNotFoundException extends Exception {
    public ContactNotFoundException(String message) {
        super(message);
    }
}
