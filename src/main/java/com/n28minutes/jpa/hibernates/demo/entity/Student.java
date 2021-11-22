package com.n28minutes.jpa.hibernates.demo.entity;


import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;


@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String name;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Passport passport;
	
	@Embedded
	private Address address;
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Collection<Course> getCourses() {
		return courses;
	}
	public void addCourses(Course courses) {
		this.courses.add(courses);
	}

	@ManyToMany
	@JoinTable(name = "student_course",
	joinColumns = @JoinColumn(name = "student_id"),
	inverseJoinColumns = @JoinColumn(name = "course_id")
			)
	private Collection<Course> courses = new ArrayList<>();
	
//	@UpdateTimestamp
//	private LocalDateTime lastUpdatedDate;
//
//	@CreationTimestamp
//	private LocalDateTime createdDate;
//	
	public Student() {
		
	}
	public Student(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	public Passport getPassport() {
		return passport;
	}
	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}
	
}
