package com.addressbook;

import java.util.*;

/**
 * AddressBook service class managing contacts
 */
public class AddressBook {
    private Map<String, Contact> contacts;
    private List<Contact> contactList;
    
    public AddressBook() {
        contacts = new HashMap<>();
        contactList = new ArrayList<>();
    }
    
    /**
     * Add a new contact to the address book
     */
    public void addContact(Contact contact) throws ContactAlreadyExistsException {
        if (contacts.containsKey(contact.getContactId())) {
            throw new ContactAlreadyExistsException("Contact with ID " + contact.getContactId() + " already exists!");
        }
        contacts.put(contact.getContactId(), contact);
        contactList.add(contact);
    }
    
    /**
     * Update an existing contact
     */
    public void updateContact(String contactId, Contact updatedContact) throws ContactNotFoundException {
        if (!contacts.containsKey(contactId)) {
            throw new ContactNotFoundException("Contact with ID " + contactId + " not found!");
        }
        
        Contact oldContact = contacts.get(contactId);
        int index = contactList.indexOf(oldContact);
        
        contacts.put(contactId, updatedContact);
        contactList.set(index, updatedContact);
    }
    
    /**
     * Delete a contact by ID
     */
    public void deleteContact(String contactId) throws ContactNotFoundException {
        if (!contacts.containsKey(contactId)) {
            throw new ContactNotFoundException("Contact with ID " + contactId + " not found!");
        }
        
        Contact contact = contacts.remove(contactId);
        contactList.remove(contact);
    }
    
    /**
     * Search contact by ID
     */
    public Contact searchById(String contactId) throws ContactNotFoundException {
        Contact contact = contacts.get(contactId);
        if (contact == null) {
            throw new ContactNotFoundException("Contact with ID " + contactId + " not found!");
        }
        return contact;
    }
    
    /**
     * Search contacts by name (partial match)
     */
    public List<Contact> searchByName(String name) {
        List<Contact> results = new ArrayList<>();
        for (Contact contact : contactList) {
            if (contact.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(contact);
            }
        }
        return results;
    }
    
    /**
     * Get all contacts
     */
    public List<Contact> getAllContacts() {
        return new ArrayList<>(contactList);
    }
    
    /**
     * Get total number of contacts
     */
    public int getTotalContacts() {
        return contacts.size();
    }
    
    /**
     * Clear all contacts
     */
    public void clearAllContacts() {
        contacts.clear();
        contactList.clear();
    }
    
    /**
     * Sort contacts by name
     */
    public List<Contact> getSortedContactsByName() {
        List<Contact> sorted = new ArrayList<>(contactList);
        sorted.sort(Comparator.comparing(Contact::getName));
        return sorted;
    }
}
