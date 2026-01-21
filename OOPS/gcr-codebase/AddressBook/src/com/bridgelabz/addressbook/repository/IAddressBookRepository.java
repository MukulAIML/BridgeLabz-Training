package com.bridgelabz.addressbook.repository;

import java.util.List;

import com.bridgelabz.addressbook.model.Person;

/**
 * Interface for data source implementations following the Open-Close Principle.
 * New data sources can be added without modifying existing code.
 */
public interface IAddressBookRepository {

    /**
     * Saves a list of contacts to the data source
     * @param contacts List of Person objects to save
     */
    void saveContacts(List<Person> contacts);

    /**
     * Loads contacts from the data source
     * @return List of Person objects
     */
    List<Person> loadContacts();

    /**
     * Returns the type of data source
     * @return String representing the data source type
     */
    String getDataSourceType();
}

