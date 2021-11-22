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
import com.n28minutes.jpa.hibernates.demo.entity.Address;
import com.n28minutes.jpa.hibernates.demo.entity.Course;
import com.n28minutes.jpa.hibernates.demo.entity.Passport;
import com.n28minutes.jpa.hibernates.demo.entity.Student;
@SpringBootTest(classes = DemoApplication.class)
class StudentRepositoryTest {
	@Autowired
	StudentRepository repository;
	@Autowired
	EntityManager em;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void happensAtTransaction() {
//		Passport passport = new Passport("N40004");
		Student student = em.find(Student.class, 20001L);
		student.setName("Eren-Updated");
//		student.setPassport(passport);
		
//		logger.info("Student id=20001 -> {}",student);
//		logger.info("passport -> {}",student.getPassport());
	}
//	
	@Test
	@Transactional
	public void setAddressDetails() {
		Student student = em.find(Student.class,20001L);
		student.setAddress(new Address("No1", "Some Street", "Hyperabad"));
		em.flush();
		logger.info("Student id=20001 -> {}",student);
		logger.info("address -> {}",student.getAddress());
	}
	
//	@Test
//	@Transactional
//	public void retrievePassportAndAssociatedStudent() {
//		Passport passport = em.find(Passport.class, 30001L);
//		logger.info("Passport id=30001 -> {}", passport);
//		logger.info("student -> {}", passport.getStudent());
//	}
	
}
