# Implementation Plan for Address Book Application

## UC10 - Sorting by Person's Name
- [x] Add toString() method to Person.java for formatted output
- [x] Add sortByName() method to AddressBookService interface
- [x] Implement sortByName() in AddressBookServiceImpl using Collections.sort() with Comparator
- [x] Add menu option in AddressBookController for sorting by name

## UC11 - Sorting by City, State, Zip
- [x] Add sortByCity() method to AddressBookService interface
- [x] Add sortByState() method to AddressBookService interface
- [x] Add sortByZip() method to AddressBookService interface
- [x] Implement all three sorting methods in AddressBookServiceImpl
- [x] Add menu options in AddressBookController for sorting by location fields

## UC17 - Save to Database with Open-Close Principle
- [x] Create IAddressBookRepository interface with saveContacts() and loadContacts() methods
- [x] Create JsonDataSource class implementing IAddressBookRepository for JSON file storage
- [x] Create CsvDataSource class implementing IAddressBookRepository for CSV file storage
- [x] Create DatabaseDataSource class implementing IAddressBookRepository using JDBC
- [x] Create DataSourceFactory for creating data source instances
- [x] Modify AddressBookController to use the interface for data operations
- [x] Add database table creation SQL script

## Additional Improvements
- [ ] Create Contact class (alias for Person or refactor Person to Contact)
- [ ] Add comprehensive error handling
- [ ] Add unit tests for sorting functionality
- [ ] Add unit tests for data source implementations

