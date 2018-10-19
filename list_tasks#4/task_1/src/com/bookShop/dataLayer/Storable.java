package com.bookShop.dataLayer;

import java.util.List;

public interface Storable<TItem> {
	void add(TItem item);
	boolean delete(TItem item);
	List<TItem> getAll();
}
