package com.bookshop.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.senla.training.example.FileUtil;

public class Storage<T> implements Storable<T> {
	
	private FileUtil<T> connector;
	
	public Storage(FileUtil<T> connector) { 
		this.connector = connector;		
	}
	
	@SuppressWarnings("unchecked")
	public void add(T item) {
		List<T> a = new ArrayList<>(); 
		a.add(item);
		connector.writeToFile((T[])a.toArray()); // :)
	}

	public void update(T item) {
		
	}
	
	@SuppressWarnings("unchecked")
	public boolean delete(T item) {
		List<T> list = getAll();
		boolean result = list.remove(item);
		connector.writeToFile((T[])list.toArray());
		return result;
	}

	public List<T> getAll() {
		return Arrays.asList(connector.readFromFile());
	}
	
}
