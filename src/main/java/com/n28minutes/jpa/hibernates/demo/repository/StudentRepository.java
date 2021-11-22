package com.n28minutes.jpa.hibernates.demo.repository;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.n28minutes.jpa.hibernates.demo.entity.Course;
import com.n28minutes.jpa.hibernates.demo.entity.Passport;
import com.n28minutes.jpa.hibernates.demo.entity.Student;

@Repository
@Transactional
public class StudentRepository {
	@Autowired
	EntityManager em;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public Student findById(Long id) {
		return em.find(Student.class, id);
	}
	public void deleteById(Long id) {
		Student student = findById(id);
		em.remove(student);
	}
	//insert or update
	public Student save(Student student) {
		if(student.getId() == null) {
			em.persist(student);
		} else {
			em.merge(student);
		}
		return student;
	}
	public void playWithEntityManager() {
		
		Student student = em.find(Student.class, 20001L);
		Passport passport = student.getPassport();
		logger.info("passport is -> {}",passport);
	}
	
	public void saveStudentWithPassport() {
		Passport passport = new Passport("Z123456");
		em.persist(passport);
		Student student = new Student("Mien");
		em.persist(student);
		student.setPassport(passport);
		
	}
	public void insertStudentAndCourse(Student student, Course course) {
		course.addStudents(student);
		course.setName("Microservices in 100 steps - Updated");
		student.addCourses(course);
		em.persist(course);
		em.persist(student);
	}
}
