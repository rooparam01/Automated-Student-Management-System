package com.masai.services;

import java.util.Map;

import com.masai.entities.Courses;
import com.masai.exception.CourseException;


public class CourseServiceImpl implements CourseService {

	@Override
	public String addCoures(Courses cor, Map<Integer, Courses> courses) {
		
		courses.put(cor.getId(), cor);

		return "Course added successfully";
		
	}

	@Override
	public void viewAllCourses(Map<Integer, Courses> courses) throws CourseException {
		System.out.println("---------------------------------------");
		if (courses != null && courses.size() > 0) {
			for (Map.Entry<Integer, Courses> me : courses.entrySet()) {
				System.out.println(me.getValue());
			}

		} else {
			throw new CourseException("Course List is empty");
		}
		System.out.println("---------------------------------------");
	}

	@Override
	public String UpdateCoures(Courses cor, Map<Integer, Courses> courses) {
		courses.put(cor.getId(), cor);

		return "Course Updated successfully";
	}

}
