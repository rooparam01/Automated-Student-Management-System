package com.masai.services;

import java.util.Map;
import java.util.Scanner;

import com.masai.entities.Batch;
import com.masai.entities.Courses;
import com.masai.entities.Student;

public class StudentServiceImpl implements StudentService {

	@Override
	public String studentSignUpFunctionality(Scanner sc, Map<Integer, Courses> courses, Map<Integer, Batch> batches,
			Map<Integer, Student> students) {
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.address = address;
//		this.mobileNumber = mobileNumber;
//		this.email = email;
//		this.password = password;
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
		System.out.println(std);
		return null;
	}

}
