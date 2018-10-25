package com.bookshop.dao.util;

import com.senla.training.FileWorker;
import com.senla.training.TextFileWorker;
import com.senla.training.example.FileUtil;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

		DateFormat dateFormat = new SimpleDateFormat("d MMMM, yyyy");

		BookFileUtil bookUtil = new BookFileUtil("data/null.txt");
		List<Book> books = entity.getBooks();
		String[] strbook = new String[books.size()];
		for(int i = 0; i < books.size(); ++i) { 
			strbook[i] = bookUtil.toLine(books.get(i));
		}
		String lineBook = String.join("#", strbook);
		
		final String[] array = new String[] {
			String.valueOf(entity.getId()),
			dateFormat.format(entity.getDateOrder()),
			dateFormat.format(entity.getDateRelease()),
			lineBook,
			String.valueOf(entity.getStatus())
		};
		
		return String.join(";", array);
	}

	public Order fromLine(String line) {
		if(line == null || line.length() == 0) { 
			throw new IllegalArgumentException();
		}
		
		String[] parts = line.split(";");
		DateFormat dateFormat = new SimpleDateFormat("d MMMM, yyyy");
		
		BookFileUtil bookUtil = new BookFileUtil("data/null.txt");		
		String[] lineBook = parts[3].split("#");
		List<Book> books = new ArrayList<Book>();
		for(int i = 0; i < lineBook.length; ++i) { 
			books.add(bookUtil.fromLine(lineBook[i]));	
		}		
		
		Order result = null;
		try {
			result = new Order(
					Integer.valueOf(parts[0]),
					dateFormat.parse(parts[1]),
					dateFormat.parse(parts[2]),
					books,
					Order.Status.valueOf(parts[4]));

		} catch (NumberFormatException | ParseException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
