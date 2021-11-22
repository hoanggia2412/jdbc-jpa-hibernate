package com.n28minutes.jpa.hibernates.demo.repository;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.n28minutes.jpa.hibernates.demo.DemoApplication;
import com.n28minutes.jpa.hibernates.demo.entity.Course;

@SpringBootTest(classes = DemoApplication.class)
public class CriteriaQueryTest {
	
	@Autowired
	private EntityManager em;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void criteria_basic() {
		//select c from Course c
		
		//1. Use Criteria Builder to create a Criteria Query returning the expect result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		//3. Define Predicates etc using Criteria Builder
		
		//4. Add Predicates etc to the Criteria Query
		
		//5. Build the TypedQuery using the entity manager and criteria query 
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		Collection<Course> resultList = query.getResultList();
		logger.info("Typed Query -> {}",resultList);
	}
	
	@Test
	public void all_courses_having_100steps() {
		//select c from Course c where name like '%100 steps'
		
		//1. Use Criteria Builder to create a Criteria Query returning the expect result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		//3. Define Predicates etc using Criteria Builder
		Predicate like100Steps = cb.like(courseRoot.get("name"), "%100 steps");
				
		//4. Add Predicates etc to the Criteria Query
		cq.where(like100Steps);
			
		//5. Build the TypedQuery using the entity manager and criteria query 
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		Collection<Course> resultList = query.getResultList();
		logger.info("Typed Query %100 steps -> {}",resultList);
	}
	
	@Test
	public void all_courses_without_student_and_like_100steps() {
		//select c from Course c where c.students is empty
		
		//1. Use Criteria Builder to create a Criteria Query returning the expect result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		//3. Define Predicates etc using Criteria Builder
		Predicate withoutStudent = cb.isEmpty(courseRoot.get("students"));
		Predicate like100Steps = cb.like(courseRoot.get("name"), "%100 steps");
		
		//4. Add Predicates etc to the Criteria Query
		cq.where(cb.and(withoutStudent,like100Steps));
				
		//5. Build the TypedQuery using the entity manager and criteria query 
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		Collection<Course> resultList = query.getResultList();
		logger.info("Typed Query without student -> {}",resultList);
	}
	
	@Test
	public void join() {
		//select c from Course c join c.students s
		
		//1. Use Criteria Builder to create a Criteria Query returning the expect result object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		//3. Define Predicates etc using Criteria Builder
		courseRoot.join("students",JoinType.LEFT);
		
		//4. Add Predicates etc to the Criteria Query
		cq.distinct(true);
				
		//5. Build the TypedQuery using the entity manager and criteria query 
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		Collection<Course> resultList = query.getResultList();
		logger.info("Typed Query join student -> {}",resultList);
	}
}
