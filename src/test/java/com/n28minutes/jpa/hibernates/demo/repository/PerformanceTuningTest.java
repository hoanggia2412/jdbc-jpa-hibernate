package com.n28minutes.jpa.hibernates.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;
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
class PerformanceTuningTest {
	@Autowired
	CourseRepository repository;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	EntityManager em;
	
	@Test
	@Transactional
	public void creatingNPlusOneProblems() {
		List<Course> courses = em
						.createNamedQuery("query_all_courses",Course.class)
						.getResultList();
		for (Course course : courses) {
			logger.info("Course -> {} Student -> {}",course,course.getStudents());
		}
	}
	
//	@Test
//	@Transactional
//	public void solvingNPlusOneProblems_EntityGraph() {
//		
//		EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
//		Subgraph<Object> subGraph = entityGraph.addSubgraph("students");
//		
//		List<Course> courses = em
//						.createNamedQuery("query_all_courses",Course.class)
//						.setHint("javax.persistence.loadgraph", entityGraph)
//						.getResultList();
//		for (Course course : courses) {
//			logger.info("Course solving -> {} Student -> {}",course,course.getStudents());
//		}
//	}
	
	@Test
	@Transactional
	public void solvingNPlusOneProblems_JoinFetch() {
		
		List<Course> courses = em
						.createNamedQuery("query_all_courses_join_fetch",Course.class)						
						.getResultList();
		for (Course course : courses) {
			logger.info("Course solving -> {} Student -> {}",course,course.getStudents());
		}
	}
}
