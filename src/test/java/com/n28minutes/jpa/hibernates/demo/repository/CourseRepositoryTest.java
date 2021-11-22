package com.n28minutes.jpa.hibernates.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.n28minutes.jpa.hibernates.demo.DemoApplication;
import com.n28minutes.jpa.hibernates.demo.entity.Course;
import com.n28minutes.jpa.hibernates.demo.entity.Review;
import com.n28minutes.jpa.hibernates.demo.entity.Student;
@SpringBootTest(classes = DemoApplication.class)
class CourseRepositoryTest {
	@Autowired
	CourseRepository repository;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	EntityManager em;
	
//	@Test
//	@DirtiesContext
//	public void deleteById_basicTest() {
//		repository.deleteById(10002L);
//		assertNull(repository.findById(10002L));
//	}
//	@Test
//	@DirtiesContext
//	public void save_basicTest() {
//		//get a course
//		Course course = repository.findById(10002L);
//		assertEquals("Spring in 100 steps", course.getName());
//		
//		//update
//		course.setName("Spring in 50 steps - Updated");
//		repository.save(course);		
//		Course course1 = repository.findById(10002L);
//		assertEquals("Spring in 50 steps - Updated", course1.getName());
//	}
//	
//	
//	@Test
//	public void findById_basicTest() {
//		Course course = repository.findById(10002L);
//		assertEquals("Spring in 100 steps", course.getName());
//	}
	
	@Test
	@Transactional
	public void retrieveReviewsForCourse() {
		Course course =  repository.findById(10001L);
		logger.info("Get reviews for course = 10001 -> {}",course.getReviews());
	}
	
	@Test
	@Transactional
	public void retrieveCourseForReview() {
		Review review = em.find(Review.class,40002L);
		logger.info("Get reviews for course = 10001 -> {}",review.getCourse());
	}
	@Test
	@Transactional
	public void retrieveStudentAndCourses() {
		Student student = em.find(Student.class, 20001L);
		logger.info("Student -> {}", student);
		logger.info("Courses -> {}", student.getCourses());
	}
	
//	@Test
//	@DirtiesContext
//	public void playWithEntityManager() {
//		repository.playWithEntityManager();
//	}
	
}
