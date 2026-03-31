package com.addressbook;

/**
 * Exception thrown when a contact already exists
 */
public class ContactAlreadyExistsException extends Exception {
    public ContactAlreadyExistsException(String message) {
        super(message);
    }
}
