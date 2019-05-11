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

import com.bookshop.core.model.Order;

public class OrderFileUtil implements FileUtil<Order> {

	private static final Logger log = Logger.getLogger(OrderFileUtil.class.getName());
	
	private String path;
	
	public OrderFileUtil (String path) { 
		this.path = path;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Order> readFromFile() {
		Collection<Order> result = null;
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
			result = (Collection<Order>)ois.readObject();
		} catch (FileNotFoundException e) {
			log.log(Level.INFO, "File not fount", e);
		} catch (IOException e) {
			log.log(Level.INFO, "IO Exception", e);
		} catch (ClassNotFoundException e) {
			log.log(Level.INFO, "Class not found", e);
		}

		return result;
	}

	public void writeToFile(Collection<Order> values) {
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
