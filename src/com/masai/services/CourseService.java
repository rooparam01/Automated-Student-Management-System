package com.masai.services;

import java.util.Map;

import com.masai.entities.Courses;
import com.masai.exception.CourseException;


public interface CourseService {
	public String addCoures(Courses cor, Map<Integer, Courses> courses);
	public String UpdateCoures(Courses cor, Map<Integer, Courses> courses);

	public void viewAllCourses(Map<Integer, Courses> courses) throws CourseException;
}
