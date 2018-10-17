package com.bookShop.serviceObject;

import java.util.Comparator;
import java.util.List;

public interface Sortable<T> {
	List<T> sortBy(Comparator<T> comp);
}
