package com.n28minutes.jpa.hibernates.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.n28minutes.jpa.hibernates.demo.DemoApplication;
import com.n28minutes.jpa.hibernates.demo.entity.Course;
import com.n28minutes.jpa.hibernates.demo.entity.Student;

@SpringBootTest(classes = DemoApplication.class)
public class JPQLTest {
	
	@Autowired
	private EntityManager em;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void jpql_course_without_student() {
		Query query = em.createQuery("Select c from Course c where c.students is empty");
		List resultList = query.getResultList();
		logger.info("Select c from Course c where c.id not in -> {}",resultList);
		assertEquals(2, resultList.size());
	}
	@Test
	public void jpql_course_atleast_2students() {
		Query query = em.createQuery("Select c from Course c where size(c.students) >= 2");
		List resultList = query.getResultList();
		logger.info("Select c from Course c where c.students >= 2 -> {}",resultList);
	}
	@Test
	public void jpql_student_with_passport_in_a_certain_pattern() {
		TypedQuery<Student> query = em.createQuery("Select s from Student s where s.passport.number like '%E123%'",Student.class);
		List resultList = query.getResultList();
		logger.info("Select s from Student s where s.passport.number like '%E123%' -> {}",resultList);
	}
	@Test
	public void jpql_course_ordered_by_students() {
		Query query = em.createQuery("Select c from Course c order by size(c.students) desc");
		List resultList = query.getResultList();
		logger.info("Select c from Course c order by size(c.students) -> {}",resultList);
	}
	
	//JOIN -> select c, s from  Course c JOIN c.students s
	//LEFTJOIN
	//CROSSJOIN
	@Test
	public void jpql_join() {
		// Query query = em.createQuery("Select c, s from Course c JOIN c.students s");
		 Query query = em.createQuery("Select c, s from Course c, Student s");
		//Query query = em.createQuery("Select c, s from Course c LEFT JOIN c.students s");
		List<Object[]> resultList = query.getResultList();
		for (Object[] result : resultList) {
			//Object[0] - course
			//Object[1] - student
			logger.info("Courses -> {}, Students -> {}",result[0],result[1]);
		}
	}
	
	@Test
	public void jpql_basic() {
		Query query = em.createNamedQuery("query_all_courses");
		List resultList = query.getResultList();
		logger.info("Select c from Course c -> {}",resultList);
	}
	
	@Test
	public void jpql_typed() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c",Course.class);
		List resultList = query.getResultList();
		logger.info("Select c from Course c typed -> {}",resultList);
	}
	
	
//	@Test
//	public void jpql_where() {
//		TypedQuery<Course> query = em.createQuery("Select c from Course c where name like '%50 steps'",Course.class);
//		List resultList = query.getResultList();
//		logger.info("Select c from Course c where name like '%100 steps' -> {}",resultList);
//	}
	
	@Test
	public void jpql_where() {
		TypedQuery<Course> query = em.createNamedQuery("query_get_100_steps_courses",Course.class);
		List resultList = query.getResultList();
		logger.info("Select c from Course c where name like '%100 steps' -> {}",resultList);
	}
}
