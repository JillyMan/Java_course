package com.bookshop.dao.util;

import com.senla.training.FileWorker;
import com.senla.training.TextFileWorker;
import com.senla.training.example.FileUtil;
import com.bookshop.core.model.Order;

public class OrderFileUtil implements FileUtil<Order> {

	private final FileWorker fileWorker;
	
	public OrderFileUtil(String path) { 
		fileWorker = new TextFileWorker(path);
	}

	public Order[] readFromFile() {
		return null;
	}

	public void writeToFile(Order[] values) {		
	}

	public String toLine(Order entity) {
		return null;
	}

	public Order fromLine(String line) {
		return null;
	}
	
}
