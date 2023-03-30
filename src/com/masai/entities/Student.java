package com.masai.entities;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {
//first name, last name, address, mobile number, email, and password should be taken.
	private String firstName;
	private String lastName;
	private String address;
	private int mobileNumber;
	private String email;
	private String password;
	private int id;
	private int batchid;
	public Student(String firstName, String lastName, String address, int mobileNumber, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getAddress() {
		return address;
	}
	public int getMobileNumber() {
		return mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public int getId() {
		return id;
	}
	public int getBatchid() {
		return batchid;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setBatchid(int batchid) {
		this.batchid = batchid;
	}
	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", mobileNumber="
				+ mobileNumber + ", email=" + email +", id=" + id + ", batchid=" + batchid
				+ "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, batchid, email, firstName, id, lastName, mobileNumber, password);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(address, other.address) && batchid == other.batchid && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName) && mobileNumber == other.mobileNumber
				&& Objects.equals(password, other.password);
	}
	
	
}
