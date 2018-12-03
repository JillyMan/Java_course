package com.bookshop.dao;

import java.util.Collection;

public interface Storable<T> {
	void add(T item) throws StorageException;
	void update(T item) throws StorageException;
	void delete(T item) throws StorageException;
	Collection<T> getAll();
	T getById(Integer id) throws StorageException;
}
