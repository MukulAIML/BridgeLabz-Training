package com.bridgelabz.addressbook.controller;

import java.io.File;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.bridgelabz.addressbook.model.Person;
import com.bridgelabz.addressbook.repository.AddressBookRepository;
import com.bridgelabz.addressbook.repository.CsvDataSource;
import com.bridgelabz.addressbook.repository.DataSourceFactory;
import com.bridgelabz.addressbook.repository.DatabaseDataSource;
import com.bridgelabz.addressbook.repository.IAddressBookRepository;
import com.bridgelabz.addressbook.repository.JsonDataSource;
import com.bridgelabz.addressbook.service.AddressBookService;
import com.bridgelabz.addressbook.serviceimpl.AddressBookServiceImpl;
import com.bridgelabz.addressbook.util.Utility;

public class AddressBookController {
	static Utility utility = new Utility();
	static AddressBookService bookService = new AddressBookServiceImpl();
	static AddressBookRepository bookRepository = new AddressBookRepository();
	static String name;
	static IAddressBookRepository dataSource;
	
	public static void main(String[] args) {
		
		AddressBookController.addressBookMenu();
		//AddressBookController.personMenu();
		
	}

	
	public static void addressBookMenu() {
		System.out.println("1. Add Address Book");
		System.out.println("2. Delete Address Book");
		System.out.println("3. Select Address Book");
		System.out.println("4. Data Source Operations");
		System.out.println("5. Quit");
		String select = Utility.inputString();
		if(Utility.stringChecker(select)) {
			switch (select) {
			case "1":
				System.out.println("Enter Address Book Name");
				name = Utility.inputString();
				bookService.addAddressBook(name);
				addressBookMenu();
				break;
			case "2":
				System.out.println("Enter Address Book Name");
				name = Utility.inputString();
				bookService.deleteAddressBook(name);
				addressBookMenu();
				break;
			case "3":
				File file = bookService.listOfFiles();
				//System.out.println("Controller :"+file);
				personMenu(file);
			break;
			case "4":
				dataSourceMenu();
				addressBookMenu();
				break;
			case "5":
				System.out.println("Thanks!!!");
				break;
			default:
				break;
			}
		}
	}
	
	public static void personMenu(File file) {
		System.out.println("\nSelect following option to perform some action..\n");
		System.out.println("1. Add a person");
		System.out.println("2. Edit");
		System.out.println("3. Delete a person");
		System.out.println("4. Search a person");
		System.out.println("5. Sort contacts");
		System.out.println("6. Save to Data Source");
		System.out.println("7. Load from Data Source");
		System.out.println("8. Quit");
		String select = Utility.inputString();
		if(Utility.stringChecker(select)) {
			switch (select) {
			case "1":
				//AddressBookController.addUser();
				JSONObject temp = AddressBookServiceImpl.addUser(file);
				AddressBookRepository.writeDataNew(temp, file);
				personMenu(file);
				break;
			case "2":
				System.out.println("Enter the first name :");
				name = Utility.inputString();
				bookService.edit(file, name);
				personMenu(file);
				break;
			case "3":
				System.out.println("Enter first name :");
				name = Utility.inputString();
				bookService.delete(file,name);
				personMenu(file);
				break;
			case "4":
				System.out.println("Enter first name :");
				name = Utility.inputString();
				bookService.search(file,name);
				personMenu(file);
				break;
			case "5":
				sortMenu(file);
				personMenu(file);
				break;
			case "6":
				saveToDataSource(file);
				personMenu(file);
				break;
			case "7":
				loadFromDataSource(file);
				personMenu(file);
				break;
			case "8":
				System.out.println("Thanks!!!");
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Submenu for sorting options (UC10 and UC11)
	 */
	public static void sortMenu(File file) {
		System.out.println("\nSelect sorting option:\n");
		System.out.println("1. Sort by Name");
		System.out.println("2. Sort by City");
		System.out.println("3. Sort by State");
		System.out.println("4. Sort by Zip");
		System.out.println("5. Back");
		
		String select = Utility.inputString();
		if (Utility.stringChecker(select)) {
			switch (select) {
			case "1":
				sortAndDisplay(file, "name");
				break;
			case "2":
				sortAndDisplay(file, "city");
				break;
			case "3":
				sortAndDisplay(file, "state");
				break;
			case "4":
				sortAndDisplay(file, "zip");
				break;
			case "5":
				// Go back to person menu
				break;
			default:
				System.out.println("Invalid option!");
			}
		}
	}

	/**
	 * Loads contacts from file, sorts them, and displays the result
	 */
	private static void sortAndDisplay(File file, String sortBy) {
		JSONObject json = AddressBookRepository.readDataNew(file);
		json = (JSONObject) json.get("Address Book");
		
		// Get all contacts from the JSON structure
		List<Person> contacts = new java.util.ArrayList<>();
		for (Object key : json.keySet()) {
			String personName = (String) key;
			JSONArray personArray = (JSONArray) json.get(personName);
			contacts.addAll(AddressBookServiceImpl.jsonArrayToPersonList(personArray));
		}
		
		if (contacts.isEmpty()) {
			System.out.println("No contacts found in this address book.");
			return;
		}
		
		// Sort based on user's choice
		switch (sortBy) {
		case "name":
			bookService.sortByName(contacts);
			break;
		case "city":
			bookService.sortByCity(contacts);
			break;
		case "state":
			bookService.sortByState(contacts);
			break;
		case "zip":
			bookService.sortByZip(contacts);
			break;
		}
		
		// Display sorted contacts using toString()
		System.out.println("\n=== Sorted Contacts by " + sortBy.toUpperCase() + " ===\n");
		for (Person person : contacts) {
			System.out.println(person.toString());
		}
	}

	/**
	 * Menu for data source operations (UC17)
	 */
	public static void dataSourceMenu() {
		System.out.println("\n=== Data Source Menu (UC17) ===\n");
		System.out.println("1. Use JSON File Data Source");
		System.out.println("2. Use CSV File Data Source");
		System.out.println("3. Use SQLite Database Data Source");
		System.out.println("4. Configure MySQL Data Source");
		System.out.println("5. Configure PostgreSQL Data Source");
		System.out.println("6. Save All Contacts to Current Data Source");
		System.out.println("7. Load Contacts from Current Data Source");
		System.out.println("8. Display Current Data Source Info");
		System.out.println("9. Back");
		
		String select = Utility.inputString();
		if (Utility.stringChecker(select)) {
			switch (select) {
			case "1":
				dataSource = new JsonDataSource();
				System.out.println("Data source set to JSON File.");
				break;
			case "2":
				dataSource = new CsvDataSource();
				System.out.println("Data source set to CSV File.");
				break;
			case "3":
				dataSource = new DatabaseDataSource();
				System.out.println("Data source set to SQLite Database.");
				break;
			case "4":
				System.out.println("Enter connection info (host|database|user|password):");
				String mysqlInfo = Utility.inputString();
				dataSource = DataSourceFactory.createDataSource("mysql", mysqlInfo);
				System.out.println("Data source set to MySQL Database.");
				break;
			case "5":
				System.out.println("Enter connection info (host|database|user|password):");
				String pgInfo = Utility.inputString();
				dataSource = DataSourceFactory.createDataSource("postgresql", pgInfo);
				System.out.println("Data source set to PostgreSQL Database.");
				break;
			case "6":
				if (dataSource != null) {
					// Load all contacts from files and save to data source
					List<Person> allContacts = loadAllContacts();
					if (!allContacts.isEmpty()) {
						dataSource.saveContacts(allContacts);
					} else {
						System.out.println("No contacts to save.");
					}
				} else {
					System.out.println("Please select a data source first.");
				}
				break;
			case "7":
				if (dataSource != null) {
					List<Person> contacts = dataSource.loadContacts();
					System.out.println("Loaded " + contacts.size() + " contacts from " + dataSource.getDataSourceType());
					for (Person person : contacts) {
						System.out.println(person.toString());
					}
				} else {
					System.out.println("Please select a data source first.");
				}
				break;
			case "8":
				if (dataSource != null) {
					System.out.println("Current Data Source: " + dataSource.getDataSourceType());
				} else {
					System.out.println("No data source selected.");
				}
				break;
			case "9":
				// Back to main menu
				break;
			default:
				System.out.println("Invalid option!");
			}
		}
	}

	/**
	 * Saves contacts from the current file to the configured data source
	 */
	private static void saveToDataSource(File file) {
		if (dataSource == null) {
			System.out.println("No data source configured. Please select one from Data Source Menu first.");
			return;
		}

		JSONObject json = AddressBookRepository.readDataNew(file);
		json = (JSONObject) json.get("Address Book");
		
		List<Person> contacts = new java.util.ArrayList<>();
		for (Object key : json.keySet()) {
			String personName = (String) key;
			JSONArray personArray = (JSONArray) json.get(personName);
			contacts.addAll(AddressBookServiceImpl.jsonArrayToPersonList(personArray));
		}
		
		if (contacts.isEmpty()) {
			System.out.println("No contacts in this address book.");
			return;
		}
		
		dataSource.saveContacts(contacts);
	}

	/**
	 * Loads contacts from the configured data source and adds them to the current file
	 */
	private static void loadFromDataSource(File file) {
		if (dataSource == null) {
			System.out.println("No data source configured. Please select one from Data Source Menu first.");
			return;
		}

		List<Person> contacts = dataSource.loadContacts();
		if (contacts.isEmpty()) {
			System.out.println("No contacts found in data source.");
			return;
		}
		
		System.out.println("Loaded contacts from " + dataSource.getDataSourceType() + ":");
		for (Person person : contacts) {
			System.out.println(person.toString());
		}
	}

	/**
	 * Loads all contacts from all address book files
	 */
	private static List<Person> loadAllContacts() {
		List<Person> allContacts = new java.util.ArrayList<>();
		String path = "JsonFile/";
		
		java.io.File folder = new java.io.File(path);
		if (folder.exists() && folder.isDirectory()) {
			for (java.io.File file : folder.listFiles()) {
				if (file.getName().endsWith(".json")) {
					JSONObject json = AddressBookRepository.readDataNew(file);
					if (json != null) {
						json = (JSONObject) json.get("Address Book");
						for (Object key : json.keySet()) {
							JSONArray personArray = (JSONArray) json.get(key);
							allContacts.addAll(AddressBookServiceImpl.jsonArrayToPersonList(personArray));
						}
					}
				}
			}
		}
		
		return allContacts;
	}

	public static void addUser() {
		String firstName,lastName,address,city,state;
		String zip;
		String mobile;
		
		@SuppressWarnings("static-access")
		JSONObject jsonObject = bookRepository.readData();
		jsonObject = (JSONObject) jsonObject.get("Address Book");
		
		
		Person person = new Person();
		System.out.println("Enter first Name :");
		firstName = Utility.getString(false);
		
		
		System.out.println("Enter Last Name :");
		lastName = Utility.getString(false);
		
		System.out.println("Enter Address :");
		address = Utility.getString(false);
		
		System.out.println("Enter city Name :");
		city = Utility.getString(false);
		
		System.out.println("Enter State Name :");
		state = Utility.getString(false);

		System.out.println("Enter Zip Code:");
		zip = Utility.inputString();
		if (Utility.stringChecker(zip)) {
			person.setZip(Integer.parseInt(zip));
		}
		
		System.out.println("Enter 10 digit Phone Number :");
		mobile = Utility.inputString();
		if (Utility.stringChecker(zip)) {
			person.setPhonenumber(Long.parseLong(mobile));
		}
		
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setCity(city);
		person.setAddress(address);
		person.setState(state);
		
		bookService.store(person);
		personMenu(null);
		
	}
}

