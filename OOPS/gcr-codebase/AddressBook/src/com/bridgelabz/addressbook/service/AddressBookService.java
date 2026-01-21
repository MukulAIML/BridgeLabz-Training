package com.bridgelabz.addressbook.service;

import java.io.File;
import java.util.List;

import org.json.simple.JSONObject;

import com.bridgelabz.addressbook.model.Person;


public interface AddressBookService {

	void store(Person person);

	void edit(File file, String name);

	void delete(File file, String name);

	JSONObject search(File file, String name);

	void addAddressBook(String name);

	void deleteAddressBook(String name);

	File listOfFiles();

	// UC10 - Sorting by person's name
	void sortByName(List<Person> contacts);

	// UC11 - Sorting by City, State, Zip
	void sortByCity(List<Person> contacts);
	void sortByState(List<Person> contacts);
	void sortByZip(List<Person> contacts);

}
