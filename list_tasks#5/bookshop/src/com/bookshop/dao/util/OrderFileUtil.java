package com.bookshop.dao.util;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;

import com.bookshop.core.model.Order;

public class OrderFileUtil implements FileUtil<Order> {
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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
