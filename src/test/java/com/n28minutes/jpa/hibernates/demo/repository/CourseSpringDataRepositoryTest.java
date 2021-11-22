package com.n28minutes.jpa.hibernates.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;

import com.n28minutes.jpa.hibernates.demo.DemoApplication;
import com.n28minutes.jpa.hibernates.demo.entity.Course;
import com.n28minutes.jpa.hibernates.demo.entity.Review;
import com.n28minutes.jpa.hibernates.demo.entity.Student;
@SpringBootTest(classes = DemoApplication.class)
class CourseSpringDataRepositoryTest {
	@Autowired
	CourseSpringDataRepository repository;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	EntityManager em;
	
//	@Test
//	public void findById() {
//		Optional<Course> courseOptional = repository.findById(10001L);
//		logger.info("findById {}",courseOptional.isPresent());
//		assertTrue(courseOptional.isPresent());
//	}
//	@Test
//	public void findAllByNamedQuery() {
//		Iterable<Course> courses= repository.queryAllCourseByNamedQuery();
//		logger.info("findAllByNamedQuery {}",courses);
//		
//	}
//	
//	
//	@Test
//	public void sort() {
//		Sort sort = Sort.by(Sort.Direction.DESC, "name");
//		Iterable<Course> resultList = repository.findAll(sort);
//		logger.info("Sorted Course -> {}",resultList);
//	}
//	
//	@Test
//	public void pagination() {
//		PageRequest pageRequest = PageRequest.of(0,3);
//		Page<Course> page1 = repository.findAll(pageRequest);
//		logger.info("First page -> {}",page1.getContent());
//		
//		Pageable secondPageable = page1.nextPageable();
//		Page<Course> secondPage = repository.findAll(secondPageable);
//		logger.info("Second page -> {}",secondPage.getContent());
//	}
//	
	@Test
	@org.springframework.transaction.annotation.Transactional
	public void updateWithoutSave() {
		Course course = new Course("Without update");
		course = repository.save(course);
		course.setName("Updated");
		Optional<Course> result = repository.findById(1L);
		assertEquals(result.get().getName(), "Updated");
	}
	
}
