package com.masai.services;

import java.util.Map;
import java.util.Scanner;

import com.masai.entities.Batch;
import com.masai.entities.Courses;
import com.masai.exception.CourseException;

public interface BatchService {
	public String adminCreateBatch(Scanner sc, Map<Integer, Courses> courses, Map<Integer, Batch> batches, CourseService courService) throws CourseException;

public void PrintAllBatches(Map<Integer, Batch> batches);
}
