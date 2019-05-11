package com.bookshop.dao.util;

import java.util.Collection;

public interface FileUtil<T> {
	Collection<T> readFromFile();
	void writeToFile(final Collection<T> values);
}
