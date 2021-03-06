package com.bae.sharesave.domain;

public class Share {

	private String name;
	private double amount;
	private double price;
	private double value;

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

}
