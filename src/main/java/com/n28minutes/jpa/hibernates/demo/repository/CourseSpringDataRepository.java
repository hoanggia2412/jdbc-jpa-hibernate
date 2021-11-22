package com.n28minutes.jpa.hibernates.demo.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.n28minutes.jpa.hibernates.demo.entity.Course;

@RepositoryRestResource(collectionResourceRel = "course")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {
	Collection<Course> findByNameAndId(String name, String id);
	Collection<Course> findByName(String name);
	Collection<Course> findByNameOrderByIdDesc(String name);
	
	
	// JPQL
	@Query("Select c from Course c where name like '%:name%'")
	Collection<Course> courseLikeNameWithJPQL(String name);
	
	//Native query
	@Query(value =  "Select * from Course c where c.name like '%:name%'", nativeQuery = true)
	Collection<Course> courseWithLikeNameWithNative(String name);
	
	//NamedQuery By JPQL
	@Query(name = "query_all_courses")
	Collection<Course> queryAllCourseByNamedQuery();
}
