package com.n28minutes.jpa.hibernates.demo.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String line1;
	private String line2;
	private String line3;
	public Address(String line1, String line2, String line3) {
		super();
		this.line1 = line1;
		this.line2 = line2;
		this.line3 = line3;
	}
	protected Address() {
		
	}
	
}
