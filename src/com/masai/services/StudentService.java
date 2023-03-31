package com.masai.services;

import java.util.Map;
import java.util.Scanner;

import com.masai.entities.Batch;
import com.masai.entities.Courses;
import com.masai.entities.Student;

public interface StudentService {
	public String studentSignUpFunctionality(Scanner sc, Map<Integer, Courses> courses,
			Map<Integer, Batch> batches, Map<Integer, Student> students);
}
