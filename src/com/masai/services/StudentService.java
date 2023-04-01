package com.masai.services;

import java.util.Map;
import java.util.Scanner;

import com.masai.entities.Batch;
import com.masai.entities.Courses;
import com.masai.entities.Student;

public interface StudentService {
	public void studentSignUpFunctionality(Scanner sc, Map<Integer, Courses> courses,
			Map<Integer, Batch> batches, Map<Integer, Student> students);

	public void seeDetailOfStudent(String sEmail, Map<Integer, Student> students, Map<Integer, Batch> batches,
			Map<Integer, Courses> courses);

	public void SeeStudentBatchWise(Scanner sc, Map<Integer, Batch> batches, BatchService batcServicec,
			StudentService stdService, Map<Integer, Student> students, Map<Integer, Courses> courses);

	public void SeeAllStudent(Scanner sc, Map<Integer, Batch> batches, BatchService batcServicec,
			StudentService stdService, Map<Integer, Student> students, Map<Integer, Courses> courses);
}
