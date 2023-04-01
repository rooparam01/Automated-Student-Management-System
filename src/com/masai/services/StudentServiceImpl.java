package com.masai.services;

import java.util.Map;
import java.util.Scanner;

import com.masai.entities.Batch;
import com.masai.entities.Courses;
import com.masai.entities.Student;

public class StudentServiceImpl implements StudentService {

	@Override
	public void studentSignUpFunctionality(Scanner sc, Map<Integer, Courses> courses, Map<Integer, Batch> batches,
			Map<Integer, Student> students) {
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.address = address;
//		this.mobileNumber = mobileNumber;
//		this.email = email;
//		this.password = password;
		System.out.println("---------------------------------------");
		String avi = sc.nextLine();
		System.out.println("Please Enter Your First Name");
		String fName=sc.nextLine();
		System.out.println("Please Enter Your Last Name");
		String lName=sc.nextLine();
		System.out.println("Please Enter Your Address");
		String address=sc.nextLine();
		
		System.out.println("Please Enter Your 10 digit Mobile number");
		long mNumber=sc.nextLong();
		System.out.println("Please Enter Your Email");
		String emailAddress=sc.next();
		for(Map.Entry<Integer, Student> mp:students.entrySet()) {
			if(mp.getValue().getEmail().equals(emailAddress)) {
				throw new IllegalArgumentException("You have already registered");
			}
		}
		System.out.println("Please Enter Your Password");
		String password=sc.next();
		Student std = new Student(fName,lName,address,mNumber,emailAddress,password);
		students.put(std.getId(), std);
		System.out.println("Your Registration Successfully Completed");
		System.out.println("---------------------------------------");
	}

	@Override
	public void seeDetailOfStudent(String sEmail, Map<Integer, Student> students, Map<Integer, Batch> batches,
			Map<Integer, Courses> courses) {
		System.out.println("---------------------------------------");
		for(Map.Entry<Integer, Student> std:students.entrySet()) {
			if(std.getValue().getEmail().equals(sEmail)) {
				Student curr = std.getValue();
				System.out.println("Student Name :- "+std.getValue().getFirstName()+" "+std.getValue().getLastName());
				System.out.println("Student Email :- "+curr.getEmail());
				System.out.println("Student mobile Number :- "+curr.getMobileNumber());
				System.out.println("Student Roll no(id) :- "+curr.getId());
				System.out.println("Student address :- "+curr.getAddress());
				for(Map.Entry<Integer, Batch> bt:batches.entrySet()) {
					if(bt.getValue().getId()==curr.getBatchid()) {
						System.out.println("Student Batch :- "+bt.getValue());
					}
				}
				
			}
		}
		System.out.println("---------------------------------------");
		
	}

	@Override
	public void SeeStudentBatchWise(Scanner sc, Map<Integer, Batch> batches, BatchService batcServicec,
			StudentService stdService, Map<Integer, Student> students, Map<Integer, Courses> courses) {
		System.out.println("---------------------------------------");
		for(Map.Entry<Integer, Batch> bt: batches.entrySet()) {
			System.out.println("Batch Id:-"+bt.getValue().getId()+"  Batch Name:-"+bt.getValue().getName());
		}
		System.out.println("Please Enter batch id from above list");
		int searchId = sc.nextInt();
		boolean flag=true;
		for(Map.Entry<Integer, Student> st: students.entrySet()) {
			if(st.getValue().getBatchid()==searchId) {
				flag=false;
				System.out.println(st.getValue());	
			}
		}
		if(flag==true) {
			System.out.println("There is no student availble in this batch");
		}
		System.out.println("---------------------------------------");
	}

	@Override
	public void SeeAllStudent(Scanner sc, Map<Integer, Batch> batches, BatchService batcServicec,
			StudentService stdService, Map<Integer, Student> students, Map<Integer, Courses> courses) {
		System.out.println("---------------------------------------");
		for(Map.Entry<Integer, Student> st:students.entrySet()) {
			System.out.println(st.getValue());
		}
		System.out.println("---------------------------------------");
		
	}

}
