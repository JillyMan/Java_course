package com.bookshop.dao.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bookshop.core.model.Author;
import com.bookshop.core.model.Book;
import com.textfileworker.FileUtil;
import com.textfileworker.FileWorker;
import com.textfileworker.TextFileWorker;

public class BookFileUtil implements FileUtil<Book> {
	
	private final FileWorker fileWorker;
	private final DateFormat dateFormat = new SimpleDateFormat("d MMMM, yyyy");
	
	public BookFileUtil (String path) { 
		fileWorker = new TextFileWorker(path);
	}
	
	public List<Book> readFromFile() {
		
		List<String> lines = fileWorker.readFromFile();
		
		if (lines == null || lines.size() == 0) {
			throw new IllegalArgumentException();
		}

		final List<Book> result = new ArrayList<Book>();

		for(String line : lines) { 
			result.add(fromLine(line));
		}

		return result;
	}

	public void writeToFile(Collection<Book> values) {
		if(values == null || values.size() == 0) { 
			throw new IllegalArgumentException();
		}
		
		final List<String> result = new ArrayList<String>();

		for(Book book : values) { 
			result.add(toLine(book));
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
		Book result = null;
		try {
			result = new Book();
			result.setId(Integer.valueOf(parts[0]));
			result.setPrice(Integer.valueOf(parts[1]));
			result.setAuthor(Author.valueOf(parts[2])); 
			result.setTitle(parts[3]);			
			result.setDateReceipt(dateFormat.parse(parts[4]));
			result.setDataRelease(dateFormat.parse(parts[5]));			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
