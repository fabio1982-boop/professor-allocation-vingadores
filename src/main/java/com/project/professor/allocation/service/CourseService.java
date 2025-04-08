package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.repository.CourseRepository;

@Service

public class CourseService {
	private final CourseRepository repository;
	private final CourseService courseService;

	public CourseService(CourseRepository repository, CourseService courseService) {
		this.repository = repository;
		this.courseService = courseService;
	}

	public Course findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public void deleteById(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		}
	}

	public void deleteAll() {
		repository.deleteAllInBatch();

	}

	public Course save(Course course) {

		course.setId(null);

		return saveInternal(course);
	}

	public Course update(Course course) {
		if (repository.existsById(course.getId())) {
			return saveInternal(course);
		} else {
			return null;
		}
	}

	private Course saveInternal(Course course) {
		course = repository.save(course);

		course = courseService.findById(course.getCourse().getId());

		course.setCourse(course);

		return course;
	}

	public List<Course> findAll(String name) {
		return repository.findAll();
	}

}
