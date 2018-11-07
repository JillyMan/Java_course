package com.bookshop.dao.util;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookshop.core.model.Book;
import com.bookshop.core.model.Order;
import com.bookshop.dao.Storable;
import com.bookshop.dao.StorageException;
import com.bookshop.dao.StorageFactory;
import com.textfileworker.FileUtil;
import com.textfileworker.FileWorker;
import com.textfileworker.TextFileWorker;

public class OrderFileUtil implements FileUtil<Order> {

	private final FileWorker fileWorker;
	private final DateFormat dateFormat = new SimpleDateFormat("d MMMM, yyyy");

	public OrderFileUtil(String path) { 
		fileWorker = new TextFileWorker(path);
	}

	public List<Order> readFromFile() {
		
		List<String> lines = fileWorker.readFromFile();
		
		if (lines == null || lines.size() == 0) {
			throw new IllegalArgumentException();
		}

		final List<Order> result = new ArrayList<Order>();

		for(String line : lines) { 
			result.add(fromLine(line));
		}

		return result;
	}

	public void writeToFile(Collection<Order> values) {
		if(values == null || values.size() == 0) { 
			throw new IllegalArgumentException();
		}
		
		final List<String> result = new ArrayList<String>();

		for(Order order: values) { 
			result.add(toLine(order));
		}		
		
		fileWorker.writeToFile(result);
	}

	public String toLine(Order entity) {
		if(entity == null) { 
			throw new IllegalArgumentException();
		}
		
		Map<Integer, Integer> idBookCount = new HashMap<Integer, Integer>();	
		entity.getBooksCount().forEach((book, count) -> idBookCount.put(book.getId(), count));
		
		final String[] array = new String[] {
			String.valueOf(entity.getId()),
			dateFormat.format(entity.getDateOrder()),
			dateFormat.format(entity.getDateRelease()),
			idBookCount.toString(),
			String.valueOf(entity.getStatus())
		};
		
		return String.join(";", array);
	}

	public Order fromLine(String line) {
		if(line == null || line.length() == 0) { 
			throw new IllegalArgumentException();
		}
		
		String[] parts = line.split(";");
		
		Order result = null;
		Map<Book, Integer> map = new HashMap<Book, Integer>();
		String[] mapValue = parts[3].split("\\D");
		
		Storable<Book> storeBook = StorageFactory.getInstance().getBookStorage();
		
		for(int i = 0; i < mapValue.length - 1; i++) { 
			if(!mapValue[i].equals("")) {
				Book book = null;
				try {
					book = storeBook.getById(Integer.valueOf(mapValue[i]));
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(book == null) { 
					throw new RuntimeException("Book by id = " + mapValue[i] + " not found");
				}
				map.put(book, Integer.valueOf(mapValue[i+1]));				
				i++;
			}
		}
		
		try {
			result = new Order();			
			result.setId(Integer.valueOf(parts[0]));
			result.setDateOrder(dateFormat.parse(parts[1]));
			result.setDateRelease(dateFormat.parse(parts[2]));
			result.setIdCountBooks(map);
			result.setStatus(Order.Status.valueOf(parts[4]));
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return result;
	}
	
}
