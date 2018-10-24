package com.bookshop.dao;

import java.util.Arrays;
import java.util.List;

import com.bookshop.core.model.Identified;
import com.senla.training.example.FileUtil;

public class Storage<T extends Identified<Integer>> implements Storable<T> {
	
	private FileUtil<T> connector;
	
	public Storage(FileUtil<T> connector) { 
		this.connector = connector;		
	}
	
	@SuppressWarnings("unchecked")
	public void add(T item) {
		List<T> list = getAll(); 
		list.add(item);
		connector.writeToFile((T[])list.toArray()); // :)
	}

	@SuppressWarnings("unchecked")
	public void update(T item) {
		List<T> list = getAll(); 
		list.set(list.indexOf(item), item);
		connector.writeToFile((T[])list.toArray()); // :)
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

	public T getById(Integer id) {
		List<T> list = getAll();
		return 	list.stream()
				.filter((T t) -> t.getId().equals(id))
				.findFirst().get();
	}
	
}
