package com.addressbook;

/**
 * Contact class representing a person in the address book
 */
public class Contact {
    private String contactId;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    
    public Contact(String contactId, String name, String phoneNumber, String email, String address) {
        this.contactId = contactId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }
    
    public String getContactId() {
        return contactId;
    }
    
    public void setContactId(String contactId) {
        this.contactId = contactId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Override
    public String toString() {
        return "Contact{" +
                "ID='" + contactId + '\'' +
                ", Name='" + name + '\'' +
                ", Phone='" + phoneNumber + '\'' +
                ", Email='" + email + '\'' +
                ", Address='" + address + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contact contact = (Contact) obj;
        return contactId.equals(contact.contactId);
    }
    
    @Override
    public int hashCode() {
        return contactId.hashCode();
    }
}
