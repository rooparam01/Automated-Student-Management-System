package com.masai.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

import com.masai.entities.Batch;
import com.masai.entities.Courses;
import com.masai.exception.CourseException;
import com.masai.utility.IDGeneration;

public class BatchServiceImpl implements BatchService {

	@Override
	public String adminCreateBatch(Scanner sc, Map<Integer, Courses> courses, Map<Integer, Batch> batches,
			CourseService courService) throws CourseException {
		System.out.println("Please Enter the name of Batch");
		String name=sc.next();
		System.out.println("Please Enter the Strength of Batch");
		int strength=sc.nextInt();
		courService.viewAllCourses(courses);
		System.out.println("Please Enter the Course id for Batch");
		int cCode = sc.nextInt();
		Courses courseBatch = null;
		boolean cheak=false;
		for(Map.Entry<Integer,Courses> mp:courses.entrySet()) {
			if(mp.getValue().getId()==cCode) {
				courseBatch=mp.getValue();
				cheak=true;
			}
		}
		if(cheak==false) {
			return "Invalid Course Selection";
		}
		System.out.println("Please Enter the Date of Start(DD/MM/YYYY) Batch");
		String startInput = sc.next();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate startDate = LocalDate.parse(startInput, formatter);
		//System.out.println("Please Enter the Date of End(DD/MM/YYYY) Batch");
		//String endInput=sc.next();
		LocalDate endDate = startDate.plusMonths(courseBatch.getDuration());
		Batch newBatch = new Batch(name,strength,cCode,startDate,endDate);
		batches.put(newBatch.getId(), newBatch);
		return "Batch Created Succesfully";
	}

	@Override
	public void PrintAllBatches(Map<Integer, Batch> batches) {
		for(Map.Entry<Integer, Batch>  mp: batches.entrySet()) {
			System.out.println(mp.getValue().toString());
		}
		
	}

}
