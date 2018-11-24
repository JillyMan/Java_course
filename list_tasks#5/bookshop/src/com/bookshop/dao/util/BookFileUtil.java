package com.bookshop.dao.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;

import com.bookshop.core.model.Book;

public class BookFileUtil implements FileUtil<Book> {
	
	private String path;
	
	public BookFileUtil (String path) { 
		this.path = path;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Book> readFromFile() {
		Collection<Book> result = null;
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
			result = (Collection<Book>)ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}

	public void writeToFile(Collection<Book> values) {
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