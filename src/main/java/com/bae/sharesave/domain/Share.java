package com.bae.sharesave.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Share {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private double amount;
	private double price;
	private double value;

	public Share(Long id, String name, double amount, double price, double value) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.price = price;
		this.value = value;
	}

	public Share(String name, double amount, double price, double value) {
		super();
		this.name = name;
		this.amount = amount;
		this.price = price;
		this.value = value;
	}

	public Share() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
