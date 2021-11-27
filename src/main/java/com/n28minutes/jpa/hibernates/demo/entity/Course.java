package com.n28minutes.jpa.hibernates.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Cacheable;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.SqlResultSetMapping;

import org.hibernate.FetchMode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@NamedQueries(value = {@NamedQuery(name = "query_all_courses",
						query = "Select c from Course c"),
					   @NamedQuery(name = "query_all_courses_join_fetch",
						query = "Select c from Course c JOIN FETCH c.students"),
                       @NamedQuery(name = "query_get_100_steps_courses",
                       	query = "Select c from Course c where name like '%100 steps'")})
@NamedNativeQuery(name = "query_name_for_courses",
						query = "Select * from Course",resultSetMapping = "course-name")
@Cacheable
@SqlResultSetMapping(name = "course-name",
					columns = {@ColumnResult(name="name",type = String.class)})
@SQLDelete(sql="update course set is_deleted=true where id=?")
@Where(clause = "is_deleted =false")
public class Course {
	private static Logger LOGGER = LoggerFactory.getLogger(Course.class);
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String name;
	
	private boolean isDeleted;
	
	@PreRemove
	public void preRemove() {
		LOGGER.info("Setting isDeleted = true");
		this.isDeleted = true;
	}
	
	@OneToMany(mappedBy = "course",fetch = FetchType.LAZY)
	private Collection<Review> reviews = new ArrayList<>();
	
	@JsonIgnore
	@Fetch(org.hibernate.annotations.FetchMode.SELECT)
	@ManyToMany(mappedBy = "courses")
	private Collection<Student> students = new ArrayList<>();
	
	public Collection<Student> getStudents() {
		return students;
	}
	public void addStudents(Student students) {
		this.students.add(students);
	}

	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	
	public Collection<Review> getReviews() {
		return reviews;
	}
	public void addReviews(Review review) {
		this.reviews.add(review);
	}
	
	public void removeReviews(Review review) {
		this.reviews.remove(review);
	}
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	public Course(String name) {
		this.name = name;
	}
	protected Course() {
		
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
