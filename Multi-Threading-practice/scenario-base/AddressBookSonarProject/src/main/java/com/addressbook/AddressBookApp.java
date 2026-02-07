package com.addressbook;

import java.util.*;

/**
 * Main application class for Address Book Management System
 */
public class AddressBookApp {
    private AddressBook addressBook;
    private Scanner scanner;
    
    public AddressBookApp() {
        addressBook = new AddressBook();
        scanner = new Scanner(System.in);
    }
    
    public void displayMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   ADDRESS BOOK MANAGEMENT SYSTEM      ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("1. Add New Contact");
        System.out.println("2. Update Contact");
        System.out.println("3. Delete Contact");
        System.out.println("4. Search Contact by ID");
        System.out.println("5. Search Contacts by Name");
        System.out.println("6. Display All Contacts");
        System.out.println("7. Display Sorted Contacts");
        System.out.println("8. Display Total Contacts");
        System.out.println("9. Exit");
        System.out.print("Choose option: ");
    }
    
    public void addNewContact() {
        try {
            System.out.println("\n--- Add New Contact ---");
            System.out.print("Enter Contact ID: ");
            String id = scanner.nextLine();
            
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter Phone Number: ");
            String phone = scanner.nextLine();
            
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            
            System.out.print("Enter Address: ");
            String address = scanner.nextLine();
            
            Contact contact = new Contact(id, name, phone, email, address);
            addressBook.addContact(contact);
            System.out.println("✓ Contact added successfully!");
            
        } catch (ContactAlreadyExistsException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    public void updateContact() {
        try {
            System.out.println("\n--- Update Contact ---");
            System.out.print("Enter Contact ID to update: ");
            String id = scanner.nextLine();
            
            System.out.print("Enter New Name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter New Phone Number: ");
            String phone = scanner.nextLine();
            
            System.out.print("Enter New Email: ");
            String email = scanner.nextLine();
            
            System.out.print("Enter New Address: ");
            String address = scanner.nextLine();
            
            Contact updatedContact = new Contact(id, name, phone, email, address);
            addressBook.updateContact(id, updatedContact);
            System.out.println("✓ Contact updated successfully!");
            
        } catch (ContactNotFoundException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    public void deleteContact() {
        try {
            System.out.println("\n--- Delete Contact ---");
            System.out.print("Enter Contact ID to delete: ");
            String id = scanner.nextLine();
            
            addressBook.deleteContact(id);
            System.out.println("✓ Contact deleted successfully!");
            
        } catch (ContactNotFoundException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    public void searchById() {
        try {
            System.out.println("\n--- Search Contact by ID ---");
            System.out.print("Enter Contact ID: ");
            String id = scanner.nextLine();
            
            Contact contact = addressBook.searchById(id);
            System.out.println("\n" + contact);
            
        } catch (ContactNotFoundException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    public void searchByName() {
        System.out.println("\n--- Search Contacts by Name ---");
        System.out.print("Enter Name to search: ");
        String name = scanner.nextLine();
        
        List<Contact> results = addressBook.searchByName(name);
        
        if (results.isEmpty()) {
            System.out.println("No contacts found with name: " + name);
        } else {
            System.out.println("\nFound " + results.size() + " contact(s):");
            for (Contact contact : results) {
                System.out.println(contact);
            }
        }
    }
    
    public void displayAllContacts() {
        System.out.println("\n--- All Contacts ---");
        List<Contact> allContacts = addressBook.getAllContacts();
        
        if (allContacts.isEmpty()) {
            System.out.println("No contacts available.");
        } else {
            for (Contact contact : allContacts) {
                System.out.println(contact);
            }
        }
    }
    
    public void displaySortedContacts() {
        System.out.println("\n--- Sorted Contacts (by Name) ---");
        List<Contact> sortedContacts = addressBook.getSortedContactsByName();
        
        if (sortedContacts.isEmpty()) {
            System.out.println("No contacts available.");
        } else {
            for (Contact contact : sortedContacts) {
                System.out.println(contact);
            }
        }
    }
    
    public void displayTotalContacts() {
        int total = addressBook.getTotalContacts();
        System.out.println("\nTotal Contacts: " + total);
    }
    
    public void run() {
        boolean running = true;
        
        while (running) {
            displayMenu();
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1:
                        addNewContact();
                        break;
                    case 2:
                        updateContact();
                        break;
                    case 3:
                        deleteContact();
                        break;
                    case 4:
                        searchById();
                        break;
                    case 5:
                        searchByName();
                        break;
                    case 6:
                        displayAllContacts();
                        break;
                    case 7:
                        displaySortedContacts();
                        break;
                    case 8:
                        displayTotalContacts();
                        break;
                    case 9:
                        System.out.println("\nThank you for using Address Book System!");
                        running = false;
                        break;
                    default:
                        System.out.println("✗ Invalid option! Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("✗ Invalid input! Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
        
        scanner.close();
    }
    
    public static void main(String[] args) {
        AddressBookApp app = new AddressBookApp();
        app.run();
    }
}
