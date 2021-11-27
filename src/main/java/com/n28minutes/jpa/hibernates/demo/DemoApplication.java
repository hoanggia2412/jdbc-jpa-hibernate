package com.n28minutes.jpa.hibernates.demo;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.Transactional;

import com.n28minutes.jpa.hibernates.demo.entity.Course;
import com.n28minutes.jpa.hibernates.demo.entity.FullTimeEmployee;
import com.n28minutes.jpa.hibernates.demo.entity.PartTimeEmployee;
import com.n28minutes.jpa.hibernates.demo.entity.Passport;
import com.n28minutes.jpa.hibernates.demo.entity.Review;
import com.n28minutes.jpa.hibernates.demo.entity.Student;
import com.n28minutes.jpa.hibernates.demo.repository.CourseRepository;
import com.n28minutes.jpa.hibernates.demo.repository.CourseSpringDataRepository;
import com.n28minutes.jpa.hibernates.demo.repository.EmployeeRepository;
import com.n28minutes.jpa.hibernates.demo.repository.StudentRepository;

@SpringBootApplication
@EnableCaching  
public class DemoApplication implements CommandLineRunner{
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	CourseSpringDataRepository repository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	StudentRepository studentRepository;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Course course = repository.findById(10001L);
//		logger.info("Course 1001 -> {}", course);
//		repository.deleteById(10001L);
//		repository.save(new Course("Microservices in 100 steps"));
//		repository.playWithEntityManager();
//		studentRepository.saveStudentWithPassport();
//		studentRepository.playWithEntityManager();
//		List<Review> reviews = Arrays.asList( new Review[]{new Review("5", "Great Hands-on stuff."), new Review("5", "Hatsoff.")});
//		courseRepository.addReviewsForCourse(10001L,reviews);
//		studentRepository.insertStudentAndCourse(new Student("Eren"), new Course("Microservices"));
//		employeeRepository.insertEmployee(new FullTimeEmployee("Eren", new BigDecimal(10000)));
//		employeeRepository.insertEmployee(new PartTimeEmployee("Malee", new BigDecimal(50)));
//		employeeRepository.insertEmployee(new FullTimeEmployee("Misth", new BigDecimal(20000)));
//		logger.info("list od employees -> {}",employeeRepository.retrieveAllEmployees());
		
		logger.info("List without deleted elements -> {}",repository.queryStringName());
	}

	
}
