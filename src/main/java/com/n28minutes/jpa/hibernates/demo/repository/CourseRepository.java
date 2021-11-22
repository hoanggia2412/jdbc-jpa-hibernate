package com.n28minutes.jpa.hibernates.demo.repository;

import java.util.Collection;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.n28minutes.jpa.hibernates.demo.entity.Course;
import com.n28minutes.jpa.hibernates.demo.entity.Review;
import com.n28minutes.jpa.hibernates.demo.entity.ReviewRating;

@Repository
@Transactional
public class CourseRepository {
	@Autowired
	EntityManager em;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public Course findById(Long id) {
		return em.find(Course.class, id);
	}
	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}
	//insert or update
	public Course save(Course course) {
		if(course.getId() == null) {
			em.persist(course);
		} else {
			em.merge(course);
		}
		return course;
	}
	public void playWithEntityManager() {
		Course course = new Course("Web Services in 100 steps");
		em.persist(course);
		
		Course course2 = findById(10001L);
		course2.setName("JPA in 50 steps - Updated");
		
	}
	public void addReviewsForCourse() {
		//get the course 10003
		Course course = findById(10003L);
		logger.info("Course.getReviews() -> {}",course.getReviews());
		
		//add 2 reviews to it
		Review review1 = new Review(ReviewRating.FIVE, "Great Hands-on stuff.");
		Review review2 = new Review(ReviewRating.FIVE, "Hatsoff.");
		
		//setting the relationship
		course.addReviews(review1);
		review1.setCourse(course);
		
		course.addReviews(review2);
		review2.setCourse(course);
		
		//save it to database
		em.persist(review1);
		em.persist(review2);
	}

	public void addReviewsForCourse(Long courseId, Collection<Review> reviews) {
		//get the course 10003
		Course course = findById(courseId);
		logger.info("Course.getReviews() -> {}",course.getReviews());
		
		for (Review review : reviews) {
			course.addReviews(review);
			review.setCourse(course);
			em.persist(review);
		}
	
	}
}
