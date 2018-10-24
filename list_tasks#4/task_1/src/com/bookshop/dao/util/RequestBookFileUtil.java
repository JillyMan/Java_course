package com.bookshop.dao.util;

import com.senla.training.FileWorker;
import com.senla.training.TextFileWorker;
import com.senla.training.example.FileUtil;
import com.bookshop.core.model.RequestsBook;

public class RequestBookFileUtil implements FileUtil<RequestsBook>{

	private final FileWorker fileWorker;

	public RequestBookFileUtil(String path) { 
		fileWorker = new TextFileWorker(path);
	}
	
	public RequestsBook[] readFromFile() {
		
		final String[] lines = fileWorker.readFromFile();
		
		if (lines == null || lines.length == 0) {
			throw new IllegalArgumentException();
		}

		final RequestsBook[] result = new RequestsBook[lines.length];
		
		for(int i = 0; i < result.length; ++i) { 
			result[i] = fromLine(lines[i]);
		}
		
		return result;
	}

	public void writeToFile(RequestsBook[] values) {
		if(values == null || values.length == 0) { 
			throw new IllegalArgumentException();
		}
		
		final String[] lines = new String[values.length];
		for(int i = 0; i < lines.length; ++i) { 
			lines[i] = toLine(values[i]);
		}
		fileWorker.writeToFile(lines);
	}

	public String toLine(final RequestsBook entity) {
		if(entity == null ) { 
			throw new IllegalArgumentException();
		}

		final String[] array = new String[] { 
			String.valueOf(entity.getId()),
			String.valueOf(entity.getBooksOnStorage()),
			String.valueOf(entity.getQueryOnBook())
		};
		
		return String.join(";", array);
	}

	public RequestsBook fromLine(String line) {
		if(line == null || line.isEmpty()) { 
			throw new IllegalArgumentException();
		}
		final String[] parts = line.split(";");
		final RequestsBook result = new RequestsBook(
				Integer.valueOf(parts[0]), 
				Integer.valueOf(parts[1]), 
				Integer.valueOf(parts[2]));
		return result;
	}

}
