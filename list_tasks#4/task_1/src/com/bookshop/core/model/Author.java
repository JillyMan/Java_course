package com.bookshop.core.model;

public class Author {
	private String firstName;
	private String lastName;
	
	public Author(String firstName, String lastName) { 
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public static Author valueOf(String line) { 
		String[] result = line.split(":");	
		return new Author(result[0], result[1]);
	}
	
	public String toString() {
		return firstName + ":" + lastName;
	}
	
}
