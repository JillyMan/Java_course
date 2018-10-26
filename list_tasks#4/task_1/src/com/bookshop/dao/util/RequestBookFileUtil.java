package com.bookshop.dao.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bookshop.core.model.Book;
import com.bookshop.core.model.RequestsBook;
import com.textfileworker.FileUtil;
import com.textfileworker.FileWorker;
import com.textfileworker.TextFileWorker;

public class RequestBookFileUtil implements FileUtil<RequestsBook>{

	private final FileWorker fileWorker;

	public RequestBookFileUtil(String path) { 
		fileWorker = new TextFileWorker(path);
	}
	
	public List<RequestsBook> readFromFile() {
		
		List<String> lines = fileWorker.readFromFile();
		
		if (lines == null || lines.size() == 0) {
			throw new IllegalArgumentException();
		}

		final List<RequestsBook> result = new ArrayList<RequestsBook>();

		for(String line : lines) { 
			result.add(fromLine(line));
		}

		return result;
	}

	public void writeToFile(Collection<RequestsBook> values) {
		if(values == null || values.size() == 0) { 
			throw new IllegalArgumentException();
		}
		
		final List<String> result = new ArrayList<String>();

		for(RequestsBook req: values) { 
			result.add(toLine(req));
		}		
		
		fileWorker.writeToFile(result);
	}

	public String toLine(final RequestsBook entity) {
		if(entity == null ) { 
			throw new IllegalArgumentException();
		}

		BookFileUtil bookUtil = new BookFileUtil("data/null.txt");
		Book book = entity.getBook();
		String line = bookUtil.toLine(book);
				
		final String[] array = new String[] { 
			line,
			String.valueOf(entity.getQueryOnBook()),
			String.valueOf(entity.getBooksOnStorage())
		};
		
		return String.join(";", array);
	}

	public RequestsBook fromLine(String line) {
		if(line == null || line.isEmpty()) { 
			throw new IllegalArgumentException();
		}
		
		BookFileUtil bookUtil = new BookFileUtil("data/null.txt");
		final String[] parts = line.split(";");

		final RequestsBook result = new RequestsBook(
				bookUtil.fromLine(parts[0]),
				Integer.valueOf(parts[1]), 
				Integer.valueOf(parts[2]));
		return result;
	}

}
