package com.bridgelabz.addressbook.serviceimpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.bridgelabz.addressbook.model.Person;
import com.bridgelabz.addressbook.repository.AddressBookRepository;
import com.bridgelabz.addressbook.service.AddressBookService;
import com.bridgelabz.addressbook.util.Utility;

public class AddressBookServiceImpl implements AddressBookService {

	JSONObject firstName = new JSONObject();
	JSONObject jsonFinalObject = new JSONObject();
	static JSONArray jsonArray = new JSONArray();
	File file;
	String path = "JsonFile/";

	@SuppressWarnings("unchecked")
	@Override
	public void store(Person person) {
		JSONObject json = AddressBookRepository.readData();
		System.out.println("From file :" + json);
		json = (JSONObject) json.get("Address Book");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("FirstName", person.getFirstName());
		jsonObject.put("LastName", person.getLastName());
		jsonObject.put("City", person.getCity());
		jsonObject.put("State", person.getState());
		jsonObject.put("Address", person.getAddress());
		jsonObject.put("Zip", person.getZip());
		jsonObject.put("Mobile", person.getPhonenumber());

		jsonArray.add(jsonObject);

		json.put(person.getFirstName(), jsonArray);

		jsonFinalObject.put("Address Book", json);

		System.out.println(jsonFinalObject);

		AddressBookRepository.writeData(jsonFinalObject);

	}

	@SuppressWarnings("unchecked")
	@Override
	public void edit(File file, String name) {

		JSONObject json = new JSONObject();
		JSONObject temp = new JSONObject();
		jsonFinalObject = AddressBookRepository.readDataNew(file);
		json = (JSONObject) jsonFinalObject.get("Address Book");
		jsonArray = (JSONArray) json.get(name);
		//System.out.println(name + " Details ::\n" + jsonArray);

		System.out.println("Please select what you want to update ");
		Iterator<?> iterator = jsonArray.iterator();

		while (iterator.hasNext()) {
			JSONObject object = (JSONObject) iterator.next();

			object.forEach((k, v) -> {
				
					System.out.println(k + " " + v);
					System.out.println("Do u want to update " + k + "\nenter Y for yes otherwise enter N");
					String updatedvalue = Utility.inputString().toUpperCase();
					if (!k.equals("FirstName")) {
					if (updatedvalue.equals("Y")) {
						System.out.println("Please enter updated value for " + k);
						updatedvalue = Utility.inputString();
						v = updatedvalue;
					}
					temp.put(k, v);
				} else {
					temp.put(k, v);
					System.out.println("Sorry you can't change your First Name");
				}
			});
			jsonArray.remove(object);
			jsonArray.add(temp);

		}
		System.out.println("Updated value " + jsonFinalObject.toJSONString());
		AddressBookRepository.writeDataNew(jsonFinalObject, file);

	}

	@Override
	public void delete(File file,String name) {
		JSONObject json = new JSONObject();
		jsonFinalObject = AddressBookRepository.readDataNew(file);
		//System.out.println("Before Deleted :" + jsonFinalObject);
		json = (JSONObject) jsonFinalObject.get("Address Book");
		json.remove(name);
		//System.out.println("After Deleted :" + jsonFinalObject);

		AddressBookRepository.writeDataNew(jsonFinalObject, file);
	}

	/*
	 * Method will search weather the details is present in the Address Book or not
	 * 
	 * @param-type: String
	 * 
	 * @return-type: JSONArray
	 * 
	 */

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject search(File file,String name) {
		JSONObject json = new JSONObject();
		jsonFinalObject = AddressBookRepository.readDataNew(file);
		json = (JSONObject) jsonFinalObject.get("Address Book");

		jsonArray = (JSONArray) json.get(name);

		jsonArray.forEach(person -> AddressBookServiceImpl.displayPersonDetails((JSONObject) person));
		return json;

	}

	/*
	 * Method will display the Details
	 * 
	 * @param-type: JSONObject
	 * 
	 * @return-type: Doesn't return anything
	 * 
	 */
	public static void displayPersonDetails(JSONObject person) {

		System.out.println("----------Person Details----------\n");
		System.out.println("Name :" + person.get("FirstName") + " " + person.get("LastName"));
		System.out.println("\nAddress :" + person.get("Address"));
		System.out.println("\nCity :" + person.get("City"));
		System.out.println("\nState :" + person.get("State"));
		System.out.println("\nZip :" + person.get("Zip"));
		System.out.println("\nMobile :" + person.get("Mobile"));
	}

	@Override
	public void addAddressBook(String name) {
		file = new File(path+name+".json");
		try(FileWriter fileWriter = new FileWriter(file);) {
			System.out.println(file.getName()+" Created Successfully\n");
			if(file.length() == 0) {
				//System.out.println("Coming inside the if block");
				String defaultValue = "{\n" + "  \"Address Book\" :{\n" + "  }\n" + "}";
				fileWriter.write(defaultValue);
				fileWriter.flush();
			}
				JSONObject temp = addUser(file);
				System.out.println("Result :"+temp);
				
				AddressBookRepository.writeDataNew(temp,file);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAddressBook(String name) {
		file = new File("JsonFile/"+name+".json");
		if(file.exists()) {
			file.delete();
			System.out.println(name+" Deleted Successfully...\n");
		}
		else {
			System.out.println("The file you try to delete is not exist\n");
		}
	}

	@Override
	public File listOfFiles() {
		//File folder = new File(path);
		int i=0;
		String select;
		Stream<Path> files;
		try {
			files = Files.walk(Paths.get(path));
			List<String> fileList = files.filter(Files::isRegularFile).map(path-> path.getFileName().toString()).collect(Collectors.toList());
			System.out.println("List of File");
			for(String f : fileList) {
				System.out.println(++i+"."+ f);
			}
			System.out.println("Please select a File");
			select = Utility.inputString();
			String fileName = fileList.get(Integer.parseInt(select)-1);
			String[] name = fileName.split("\\.");
			String fname="";
			for(int j=0;j<name.length;j++) {
				//System.out.println(name[j]);
				fname = name[0];
			}
			//System.out.println("Final :"+fname);
			file = new File(path+fname+".json");
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return file;
	}

	public static JSONObject addUser(File file2) {
		String firstName,lastName,address,city,state;
		String zip;
		String mobile;
		
		//JSONObject jsonObject = AddressBookRepository.readData();
		//jsonObject = (JSONObject) jsonObject.get("Address Book");
		
		
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
		
		//bookService.store(person);
		//personMenu();
		return storeFile(person,file2);
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject storeFile(Person person, File file2) {
		
		JSONObject baseJson = AddressBookRepository.readDataNew(file2);
		//System.out.println("From file :" + baseJson);
		JSONObject json = (JSONObject) baseJson.get("Address Book");
		//System.out.println("After reading file :" + json);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("FirstName", person.getFirstName());
		jsonObject.put("LastName", person.getLastName());
		jsonObject.put("City", person.getCity());
		jsonObject.put("State", person.getState());
		jsonObject.put("Address", person.getAddress());
		jsonObject.put("Zip", person.getZip());
		jsonObject.put("Mobile", person.getPhonenumber());

		jsonArray.add(jsonObject);

		json.put(person.getFirstName(), jsonArray);
		System.out.println(baseJson);

		//System.out.println(baseJson);
		return baseJson;
	}

	// UC10 - Sorting by person's name using Collections.sort with Comparator
	@Override
	public void sortByName(List<Person> contacts) {
		if (contacts == null || contacts.isEmpty()) {
			System.out.println("No contacts to sort.");
			return;
		}
		
		Collections.sort(contacts, new Comparator<Person>() {
			@Override
			public int compare(Person p1, Person p2) {
				// First compare by first name
				int firstNameCompare = p1.getFirstName().compareToIgnoreCase(p2.getFirstName());
				if (firstNameCompare != 0) {
					return firstNameCompare;
				}
				// If first names are same, compare by last name
				return p1.getLastName().compareToIgnoreCase(p2.getLastName());
			}
		});
		
		System.out.println("Contacts sorted by name successfully!");
	}

	// UC11 - Sorting by City
	@Override
	public void sortByCity(List<Person> contacts) {
		if (contacts == null || contacts.isEmpty()) {
			System.out.println("No contacts to sort.");
			return;
		}
		
		Collections.sort(contacts, new Comparator<Person>() {
			@Override
			public int compare(Person p1, Person p2) {
				return p1.getCity().compareToIgnoreCase(p2.getCity());
			}
		});
		
		System.out.println("Contacts sorted by city successfully!");
	}

	// UC11 - Sorting by State
	@Override
	public void sortByState(List<Person> contacts) {
		if (contacts == null || contacts.isEmpty()) {
			System.out.println("No contacts to sort.");
			return;
		}
		
		Collections.sort(contacts, new Comparator<Person>() {
			@Override
			public int compare(Person p1, Person p2) {
				return p1.getState().compareToIgnoreCase(p2.getState());
			}
		});
		
		System.out.println("Contacts sorted by state successfully!");
	}

	// UC11 - Sorting by Zip
	@Override
	public void sortByZip(List<Person> contacts) {
		if (contacts == null || contacts.isEmpty()) {
			System.out.println("No contacts to sort.");
			return;
		}
		
		Collections.sort(contacts, new Comparator<Person>() {
			@Override
			public int compare(Person p1, Person p2) {
				return Integer.compare(p1.getZip(), p2.getZip());
			}
		});
		
		System.out.println("Contacts sorted by zip successfully!");
	}
	
	/**
	 * Converts JSONArray to List of Person objects
	 * @param jsonArray containing person data
	 * @return List of Person objects
	 */
	public static List<Person> jsonArrayToPersonList(JSONArray jsonArray) {
		List<Person> contacts = new java.util.ArrayList<>();
		if (jsonArray == null) return contacts;
		
		for (Object obj : jsonArray) {
			JSONObject personJson = (JSONObject) obj;
			Person person = new Person();
			person.setFirstName((String) personJson.get("FirstName"));
			person.setLastName((String) personJson.get("LastName"));
			person.setCity((String) personJson.get("City"));
			person.setState((String) personJson.get("State"));
			person.setAddress((String) personJson.get("Address"));
			person.setZip(((Long) personJson.get("Zip")).intValue());
			person.setPhonenumber((Long) personJson.get("Mobile"));
			contacts.add(person);
		}
		return contacts;
	}
}
