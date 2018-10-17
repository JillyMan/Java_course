package com.bookShop.dataLayer;

import com.bookShop.businessObject.Book;
import com.senla.training.FileWorker;
import com.senla.training.TextFileWorker;
import com.senla.training.example.FileUtil;

public class BookFileUtil implements FileUtil<Book> {
	
	private final FileWorker fileWorker;
	
	public BookFileUtil (String path) { 
		fileWorker = new TextFileWorker(path);
	}
	
	public Book[] readFromFile() {
		return null;
	}

	public void writeToFile(Book[] values) {

	}

	public String toLine(Book entity) {
		return null;
	}

	public Book fromLine(String line) {
		if (line == null || line.isEmpty()) { 
			return null;
		}
				
		return null;
	}
}
