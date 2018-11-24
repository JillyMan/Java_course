package com.bookshop.dao.util;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.bookshop.core.model.RequestsBook;

public class RequestBookFileUtil implements FileUtil<RequestsBook>{

	private static final Logger log = Logger.getLogger(RequestBookFileUtil.class.getName());
	
	private String path;
	
	public RequestBookFileUtil (String path) { 
		this.path = path;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<RequestsBook> readFromFile() {
		Collection<RequestsBook> result = null;
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
			result = (Collection<RequestsBook>)ois.readObject();
		} catch (FileNotFoundException e) {
			log.log(Level.INFO, "File not fount", e);
		} catch (IOException e) {
			log.log(Level.INFO, "IO Exception", e);
		} catch (ClassNotFoundException e) {
			log.log(Level.INFO, "Class not found", e);
		}
		
		return result;
	}

	public void writeToFile(Collection<RequestsBook> values) {
		if(values == null || values.size() == 0) { 
			throw new IllegalArgumentException();
		}

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path, false))) {
			oos.writeObject(values);
		} catch (FileNotFoundException e) {
			log.log(Level.INFO, "File not fount", e);
		} catch (IOException e) {
			log.log(Level.INFO, "IO Exception", e);
		}
	}
}
