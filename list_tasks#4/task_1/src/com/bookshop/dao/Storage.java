package com.bookshop.dao;

import java.util.HashSet;
import java.util.List;

import com.bookshop.core.model.Identified;
import com.textfileworker.FileUtil;

public class Storage<T extends Identified<Integer>> implements Storable<T> {
	
	private FileUtil<T> fileUtil;
	
	public Storage(FileUtil<T> connector) { 
		this.fileUtil = connector;		
	}
	
	public void add(T item) {
		HashSet<T> set = new HashSet<T>(getAll());
		if(set.add(item)) {
			fileUtil.writeToFile(set);	
		}
	}

	public void update(T item) {
		List<T> list = getAll(); 
		int index = list.indexOf(item);
		if(index >= 0) { 
			list.set(index, item);			
		}
		fileUtil.writeToFile(list);
	}
	
	public boolean delete(T item) {
		List<T> list = getAll();
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
		List<T> list = getAll();
		return 	list.stream()
				.filter((T t) -> t.getId().equals(id))
				.findFirst().get();
	}
	
}
