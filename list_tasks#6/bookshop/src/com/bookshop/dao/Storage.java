package com.bookshop.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import com.bookshop.core.model.Identified;
import com.bookshop.dao.util.FileUtil;

public class Storage<T extends Identified<Integer>> implements Storable<T> {
	
	private FileUtil<T> fileUtil;
	private static final Logger log = Logger.getLogger(Storage.class.getName());
	
	public Storage(FileUtil<T> connector) { 
		this.fileUtil = connector;		
	}
	
	public void add(T item) throws StorageException  {
		if(item == null) { 
			log.info("IllegalArgumentException " + item);
			throw new IllegalArgumentException("Item == null");
		}
		Set<T> set = new HashSet<T>(getAll());
		if(set.add(item)) {
			fileUtil.writeToFile(set);
		} else {
			log.info("Item already located in storage: " + item);
			throw new StorageException("Item already located in storage: " + item);
		}
	}

	public void update(T item) throws StorageException {
		if(item == null) {			
			log.info("IllegalArgumentException " + item);
			throw new IllegalArgumentException("Item == null");
		}

		Set<T> set = new HashSet<T>(getAll()); 
		T element = set.stream()
			.filter(x -> x.getId().equals(item.getId()))
			.findFirst()
			.orElseThrow(() -> { 
				log.info("Item not found: " + item);
				return new StorageException("Item not found: " + item);	
			});
		
		if(set.remove(element) && set.add(item)) { 
			fileUtil.writeToFile(set);											
		} else {
			log.info("Can't update item: " + item);
			throw new StorageException("Can't update item: " + item);
		}
	}
	
	public void delete(T item) throws StorageException {
		if(item == null) {
			log.info("IllegalArgumentException " + item);
			throw new IllegalArgumentException("Item == null");
		}
		List<T> list = new ArrayList<T>(getAll());
		boolean result = list.remove(item);
		if(result) { 
			fileUtil.writeToFile(list);			
		} else { 
			log.info(item.getClass().getName() + " have not been removed:" + item);
			throw new StorageException("Item have not been removed: " + item);
		}
	}

	public Collection<T> getAll() {
		return fileUtil.readFromFile();
	}

	public T getById(Integer id) throws StorageException {
		return 	getAll().stream()
				.filter(item -> item.getId().equals(id))
				.findFirst()
				.orElseThrow(() ->  {
					return new StorageException("Item with id = " + id + " not found");
				});
	}	
}