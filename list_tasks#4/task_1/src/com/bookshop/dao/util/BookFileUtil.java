package com.bookshop.dao.util;

import com.senla.training.FileWorker;
import com.senla.training.TextFileWorker;
import com.senla.training.example.FileUtil;
import com.senla.training.example.Man;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bookshop.core.model.Author;
import com.bookshop.core.model.Book;
import com.bookshop.core.model.Order;

public class BookFileUtil implements FileUtil<Book> {
	
	private final FileWorker fileWorker;
	
	public BookFileUtil (String path) { 
		fileWorker = new TextFileWorker(path);
	}
	
	public Book[] readFromFile() {
		
		final String[] lines = fileWorker.readFromFile();
		
		if (lines == null || lines.length == 0) {
			throw new IllegalArgumentException();
		}

		final Book[] result = new Book[lines.length];

		for (int i = 0; i < lines.length; i++) {
			result[i] = fromLine(lines[i]);
		}
		
		return result;
	}

	public void writeToFile(Book[] values) {
		if(values == null || values.length == 0) { 
			throw new IllegalArgumentException();
		}
		
		final String[] result = new String[values.length];
		
		for(int i = 0; i < result.length; ++i) { 
			result[i] = toLine(values[i]);
		}
		
		fileWorker.writeToFile(result);
	}
	
	public String toLine(final Book book) {
		if (book == null) {
			throw new IllegalArgumentException();
		}
		DateFormat dateFormat = new SimpleDateFormat("d MMMM, yyyy");
		
		final String[] array = new String[] { 
				String.valueOf(book.getId()), 
				String.valueOf(book.getPrice()),
				String.valueOf(book.getAuthor()),
				book.getTitle(),
				dateFormat.format(book.getDateReceipt()),
				dateFormat.format(book.getDateRelease()),
		};

		return String.join("@", array);
	}

	public Book fromLine(String line) {
		if (line == null || line.isEmpty()) { 
			return null;
		}

		final String[] parts = line.split("@");
		DateFormat dateFormat = new SimpleDateFormat("d MMMM, yyyy");
		Book result = null;
		try {
			result = new Book(
				Integer.valueOf(parts[0]),
				Integer.valueOf(parts[1]),
				Author.valueOf(parts[2]), 
				parts[3],			
				dateFormat.parse(parts[4]),
				dateFormat.parse(parts[5]));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
