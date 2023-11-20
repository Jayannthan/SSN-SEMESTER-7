// Product.java
package com.example.products;

public class Product {
	private int id;
	private String name;
	private String brand;
	private String description;
	private double price;

	public Product(int id, String name, String brand, String description, double price) {
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getBrand() {
		return brand;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}
}
