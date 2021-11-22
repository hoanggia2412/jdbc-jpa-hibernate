package com.n28minutes.jpa.hibernates.demo.repository;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.n28minutes.jpa.hibernates.demo.entity.Employee;

@Repository
@Transactional
public class EmployeeRepository {

	@Autowired
	EntityManager em;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertEmployee(Employee employee) {
		em.persist(employee);
	}
	
	public Collection<Employee> retrieveAllEmployees(){
		//TypedQuery<Employee> query = em.createNamedQuery("query_all_employees",Employee.class);
		TypedQuery<Employee> query = em.createQuery("select e from Employee e",Employee.class);
		return query.getResultList();
	}
}
