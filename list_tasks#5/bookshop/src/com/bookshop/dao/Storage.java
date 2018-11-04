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
	
	public void add(T item) {
		Set<T> set = new HashSet<T>(getAll());
		if(set.add(item)) {
			fileUtil.writeToFile(set);	
		}
	}

	public void update(T item) {
		if(item != null) {			
			Set<T> set = new HashSet<T>(getAll()); 
			T element = set.stream()
				.filter(x -> x.getId() == item.getId())
				.findFirst()
				.orElseThrow(() -> new  RuntimeException("Item: " + item + " not found"));
			set.remove(element);
			if(set.add(item)) { 
				fileUtil.writeToFile(set);											
			}
		} else {
			throw new IllegalArgumentException("Item == null");
		}
	}
	
	public boolean delete(T item) {
		if(item == null) {
			throw new IllegalArgumentException("Item == null");
		}
		List<T> list = new ArrayList<T>(getAll());
		boolean result = list.remove(item);
		if(result) { 
			fileUtil.writeToFile(list);			
		} 
		return result;
	}

	public List<T> getAll() {
		return fileUtil.readFromFile();
	}

	public T getById(Integer id) {
		return 	getAll().stream()
				.filter(item -> item.getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new  RuntimeException("Item with id = " + id + " not found"));
	}
	
}
