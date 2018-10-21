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
		return null;
	}

	public void writeToFile(RequestsBook[] values) {
		
	}

	public String toLine(RequestsBook entity) {
		return null;
	}

	public RequestsBook fromLine(String line) {
		return null;
	}

}
