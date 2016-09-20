package com.mfg.entity;

import java.sql.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Good {
	private String id;
	private String name;
	private int age;
	private Date productionDate;
	
	public Good() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public Good(String id, String name, int age, Date productionDate) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.productionDate = productionDate;
	}
	
	
	
}
