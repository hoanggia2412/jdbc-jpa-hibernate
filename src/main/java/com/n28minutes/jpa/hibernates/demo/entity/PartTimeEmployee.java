package com.n28minutes.jpa.hibernates.demo.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class PartTimeEmployee extends Employee {
	
	private BigDecimal hourlyWage;
	
	protected PartTimeEmployee() {
		
	}
	public PartTimeEmployee(String name, BigDecimal hourlyWage) {
		super(name);
		this.hourlyWage = hourlyWage;
	}
	public void setHourlyWage(BigDecimal hourlyWage) {
		this.hourlyWage = hourlyWage;
	}
	public BigDecimal getHourlyWage() {
		return this.hourlyWage;
	}
}
