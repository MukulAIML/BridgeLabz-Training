package com.bridgelabz.addressbook.model;

public class Person {

	String firstName;
	String lastName;
	String city;
	String state;
	String address;

	int zip;
	long phonenumber;
	//Address address;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public long getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Returns full name of the person
	 * @return full name as firstName + lastName
	 */
	public String getFullName() {
		return firstName + " " + lastName;
	}
	
	/**
	 * Formats person details for console output
	 * @return formatted string representation
	 */
	@Override
	public String toString() {
		return "----------Person Details----------\n" +
		       "Name     : " + firstName + " " + lastName + "\n" +
		       "Address  : " + address + "\n" +
		       "City     : " + city + "\n" +
		       "State    : " + state + "\n" +
		       "Zip      : " + zip + "\n" +
		       "Mobile   : " + phonenumber + "\n";
	}
}
