package com.n28minutes.jpa.hibernates.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Review {
	@Id
	@GeneratedValue
	private Long id;
	private String description;

	@Enumerated
	private ReviewRating rating;
	
	@ManyToOne
	private Course course;
	
	public Review(ReviewRating rating, String description) {
		super();
		this.description = description;
		this.rating = rating;
	}
	public Review() {
	}
	public String getDescription() {
		return description;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	@Override
	public String toString() {
		return "{id=" + id + ", description=" + description + ", rating=" + rating + "}";
	}
	public ReviewRating getRating() {
		return rating;
	}
	public void setRating(ReviewRating rating) {
		this.rating = rating;
	}
	
}
