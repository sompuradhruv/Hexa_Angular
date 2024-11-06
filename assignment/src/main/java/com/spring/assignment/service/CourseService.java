package com.spring.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.assignment.model.Course;
import com.spring.assignment.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	public List<Course> fetchAllCourses() {
		 
		return courseRepository.fetchAllCourses();
	}

	public List<Course> getEnrolledCourses(Object usernameObj) {
		String username = (String) usernameObj;
		
		return courseRepository.fetchAllEnrolledCourses(username);
	}

	public void softDelete(String cid) {
		courseRepository.softDelete(Integer.parseInt(cid));
		
	}

}