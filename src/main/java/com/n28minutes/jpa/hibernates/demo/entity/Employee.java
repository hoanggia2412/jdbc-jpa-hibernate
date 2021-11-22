package com.n28minutes.jpa.hibernates.demo.entity;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

//@Entity
//@NamedQueries(value =  {
//		@NamedQuery(name = "query_all_employees",query = "select p from Employee p")
//		
//		})
@MappedSuperclass
//@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "type")
public abstract class Employee {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	protected Employee(String name) {
		this.name = name;
	}
	protected Employee() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	@Override
	public String toString() {
		return "{id=" + id + ", name=" + name + "}";
	}
	
}
