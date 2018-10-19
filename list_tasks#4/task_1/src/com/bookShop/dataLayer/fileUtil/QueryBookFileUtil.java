package com.bookShop.dataLayer.fileUtil;

import com.bookShop.businessObject.QueryBook;
import com.senla.training.FileWorker;
import com.senla.training.TextFileWorker;
import com.senla.training.example.FileUtil;

public class QueryBookFileUtil implements FileUtil<QueryBook>{

	private final FileWorker fileWorker;

	public QueryBookFileUtil(String path) { 
		fileWorker = new TextFileWorker(path);
	}
	
	public QueryBook[] readFromFile() {
		return null;
	}

	public void writeToFile(QueryBook[] values) {
		
	}

	public String toLine(QueryBook entity) {
		return null;
	}

	public QueryBook fromLine(String line) {
		return null;
	}

}
