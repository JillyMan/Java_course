package com.bookshop.dao.util;

import com.senla.training.FileWorker;
import com.senla.training.TextFileWorker;
import com.senla.training.example.FileUtil;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bookshop.core.model.Book;
import com.bookshop.core.model.Order;
import com.bookshop.core.model.Order.Status;

public class OrderFileUtil implements FileUtil<Order> {

	private final FileWorker fileWorker;
	
	public OrderFileUtil(String path) { 
		fileWorker = new TextFileWorker(path);
	}

	public Order[] readFromFile() {
		final String[] lines = fileWorker.readFromFile();
		
		if (lines == null || lines.length == 0) {
			throw new IllegalArgumentException();
		}
		
		final Order[] result = new Order[lines.length];

		for (int i = 0; i < lines.length; i++) {
			result[i] = fromLine(lines[i]);
		}
		
		return result;
	}

	public void writeToFile(Order[] values) {		
		if(values == null || values.length == 0) { 
			throw new IllegalArgumentException();
		}
		
		final String[] lines = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			lines[i] = toLine(values[i]);
		}
		fileWorker.writeToFile(lines);
	}

	public String toLine(Order entity) {
		if(entity == null) { 
			throw new IllegalArgumentException();
		}
/*
		private Integer id;
		private Date dateOrder;
		private Date dateRelease;
		private List<Book> books;
		private Status status;
*/		

		DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
		final String[] parts = new String[] {
			String.valueOf(entity.getId()),
			dateFormat.format(entity.getDateOrder()),
			dateFormat.format(entity.getDateRelease()),
			
		};
		
		return null;
	}

	public Order fromLine(String line) {
		return null;
	}
	
}
