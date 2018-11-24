package com.bookshop.core.model;

import java.io.Serializable;

public class Author implements Serializable {
	private static final long serialVersionUID = -1131462630675635864L;

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
		String[] result = line.split(" ");	
		return new Author(result[0], result[1]);
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	public String toString() {
		return firstName + " " + lastName;
	}
	
}
