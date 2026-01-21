package com.bridgelabz.addressbook.repository;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.bridgelabz.addressbook.model.Person;

/**
 * JSON file implementation of IAddressBookRepository.
 * Stores and retrieves contacts from JSON files.
 */
public class JsonDataSource implements IAddressBookRepository {

    private String filePath;
    private JSONParser jsonParser;

    public JsonDataSource() {
        this.filePath = "addressbook.json";
        this.jsonParser = new JSONParser();
    }

    public JsonDataSource(String filePath) {
        this.filePath = filePath;
        this.jsonParser = new JSONParser();
    }

    @Override
    public void saveContacts(List<Person> contacts) {
        JSONObject jsonObject = new JSONObject();
        JSONObject addressBook = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Person person : contacts) {
            JSONObject personJson = new JSONObject();
            personJson.put("FirstName", person.getFirstName());
            personJson.put("LastName", person.getLastName());
            personJson.put("City", person.getCity());
            personJson.put("State", person.getState());
            personJson.put("Address", person.getAddress());
            personJson.put("Zip", person.getZip());
            personJson.put("Mobile", person.getPhonenumber());
            jsonArray.add(personJson);
        }

        addressBook.put("Address Book", jsonArray);
        jsonObject.put("Address Book", addressBook);

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
            System.out.println("Contacts saved to JSON file successfully!");
        } catch (IOException e) {
            System.err.println("Error saving to JSON file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> loadContacts() {
        List<Person> contacts = new ArrayList<>();

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("JSON file not found. Returning empty list.");
                return contacts;
            }

            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(file));
            JSONObject addressBook = (JSONObject) jsonObject.get("Address Book");
            JSONArray jsonArray = (JSONArray) addressBook.get("Address Book");

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
        } catch (IOException | ParseException e) {
            System.err.println("Error loading from JSON file: " + e.getMessage());
            e.printStackTrace();
        }

        return contacts;
    }

    @Override
    public String getDataSourceType() {
        return "JSON File";
    }
}

