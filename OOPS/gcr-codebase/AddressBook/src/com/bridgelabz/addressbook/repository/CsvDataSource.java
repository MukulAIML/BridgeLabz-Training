package com.bridgelabz.addressbook.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bridgelabz.addressbook.model.Person;

/**
 * CSV file implementation of IAddressBookRepository.
 * Stores and retrieves contacts from CSV files.
 */
public class CsvDataSource implements IAddressBookRepository {

    private String filePath;
    private static final String CSV_HEADER = "FirstName,LastName,City,State,Address,Zip,Mobile";

    public CsvDataSource() {
        this.filePath = "addressbook.csv";
    }

    public CsvDataSource(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void saveContacts(List<Person> contacts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(CSV_HEADER);
            writer.newLine();

            for (Person person : contacts) {
                String line = String.format("%s,%s,%s,%s,%s,%d,%d",
                        escapeCsvField(person.getFirstName()),
                        escapeCsvField(person.getLastName()),
                        escapeCsvField(person.getCity()),
                        escapeCsvField(person.getState()),
                        escapeCsvField(person.getAddress()),
                        person.getZip(),
                        person.getPhonenumber());
                writer.write(line);
                writer.newLine();
            }

            System.out.println("Contacts saved to CSV file successfully!");
        } catch (IOException e) {
            System.err.println("Error saving to CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> loadContacts() {
        List<Person> contacts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // Skip header

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 7) {
                    Person person = new Person();
                    person.setFirstName(fields[0]);
                    person.setLastName(fields[1]);
                    person.setCity(fields[2]);
                    person.setState(fields[3]);
                    person.setAddress(fields[4]);
                    person.setZip(Integer.parseInt(fields[5]));
                    person.setPhonenumber(Long.parseLong(fields[6]));
                    contacts.add(person);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading from CSV file: " + e.getMessage());
            e.printStackTrace();
        }

        return contacts;
    }

    /**
     * Escapes special characters in CSV fields
     * @param field The field value to escape
     * @return Escaped field value
     */
    private String escapeCsvField(String field) {
        if (field == null) return "";
        if (field.contains(",") || field.contains("\"") || field.contains("\n")) {
            return "\"" + field.replace("\"", "\"\"") + "\"";
        }
        return field;
    }

    @Override
    public String getDataSourceType() {
        return "CSV File";
    }
}

