package com.bookshop.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bookshop.core.model.Identified;
import com.textfileworker.FileUtil;

public class Storage<T extends Identified<Integer>> implements Storable<T> {
	
	private FileUtil<T> fileUtil;
	
	public Storage(FileUtil<T> connector) { 
		this.fileUtil = connector;		
	}
	
	public void add(T item) throws StorageException  {
		if(item == null) { 
			throw new IllegalArgumentException("Item == null");
		}
		Set<T> set = new HashSet<T>(getAll());
		if(set.add(item)) {
			fileUtil.writeToFile(set);
		} else {
			throw new StorageException("Item already located in storage: " + item);
		}
	}

	public void update(T item) throws StorageException {
		if(item == null) {			
			throw new IllegalArgumentException("Item == null");
		}

		Set<T> set = new HashSet<T>(getAll()); 
		T element = set.stream()
			.filter(x -> x.getId() == item.getId())
			.findFirst()
			.orElseThrow(() -> new StorageException("Item not found: " + item));
		
		if(set.remove(element) && set.add(item)) { 
			fileUtil.writeToFile(set);											
		} else {
			throw new StorageException("Can't update item: " + item);
		}
	}
	
	public void delete(T item) throws StorageException {
		if(item == null) {
			throw new IllegalArgumentException("Item == null");
		}
		List<T> list = new ArrayList<T>(getAll());
		boolean result = list.remove(item);
		if(result) { 
			fileUtil.writeToFile(list);			
		} else { 
			throw new StorageException("Item have not been removed: " + item);
		}
	}

	public List<T> getAll() {
		return fileUtil.readFromFile();
	}

	public T getById(Integer id) throws StorageException {
		return 	getAll().stream()
				.filter(item -> item.getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new  StorageException(this.getClass().getName() + " with id = " + id + " not found"));
	}	
}