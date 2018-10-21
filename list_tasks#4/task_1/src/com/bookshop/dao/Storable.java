package com.bookshop.dao;

import java.util.List;

public interface Storable<T> {
	void add(T item);
	void update(T item);
	boolean delete(T item);
	List<T> getAll();
}
