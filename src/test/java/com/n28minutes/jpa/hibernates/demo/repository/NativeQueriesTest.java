package com.n28minutes.jpa.hibernates.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.n28minutes.jpa.hibernates.demo.DemoApplication;
import com.n28minutes.jpa.hibernates.demo.entity.Course;

@SpringBootTest(classes = DemoApplication.class)
public class NativeQueriesTest {
	
	@Autowired
	private EntityManager em;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
//	@Test
//	public void jpql_basic() {
//		Query query = em.createNativeQuery("select * from course");
//		List resultList = query.getResultList();
//		logger.info("Select * from course -> {}",resultList);
//	}
//	@Test
//	public void jpql_where_with_parameter() {
//		Query query = em.createNativeQuery("select * from Course where id=?");
//		query.setParameter(1, 10001L);
//		List resultList = query.getResultList();
//		logger.info("select * from Course where id=10001L -> {}",resultList);
//	}
	@Test
	public void jpql_where_with_parameter() {
		Query query = em.createNativeQuery("select * from Course where id=:id");
		query.setParameter("id", 10001L);
		List<Course> resultList = query.getResultList();
		logger.info("select * from Course where id=10001L -> {}",resultList);
	}
	
	@Test
	@Transactional
	public void native_queries_to_update() {
		Query query = em.createNativeQuery("Update course set last_updated_date=sysdate() where id=?");
		query.setParameter(1, 10001L);
		int noRowUpdated = query.executeUpdate();
		logger.info("noRowUpdated -> {}", noRowUpdated);
	}
	
}
