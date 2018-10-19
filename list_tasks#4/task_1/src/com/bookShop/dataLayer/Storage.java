package com.bookShop.dataLayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.senla.training.example.FileUtil;

public class Storage<TItem> implements Storable<TItem> {
	
	private FileUtil<TItem> connector;
	
	public Storage(FileUtil<TItem> connector) { 
		this.connector = connector;		
	}
	
	@SuppressWarnings("unchecked")
	public void add(TItem item) {
		List<TItem> a = new ArrayList<>(); 
		a.add(item);
		connector.writeToFile((TItem[])a.toArray()); // :)
	}

	public boolean delete(TItem item) {
		return getAll().remove(item);
	}

	public List<TItem> getAll() {
		return Arrays.asList(connector.readFromFile());
	}
	
}
