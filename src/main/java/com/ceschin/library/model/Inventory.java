package com.ceschin.library.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Inventory implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int quantity;
	private String location;
	
	public Inventory() {}

	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
