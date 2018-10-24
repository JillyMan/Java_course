package com.bookshop.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.bookshop.core.model.Book;
import com.bookshop.core.model.Order;
import com.bookshop.core.model.RequestsBook;
import com.bookshop.dao.FactoryStorage;
import com.bookshop.dao.Storable;

public class ServiceOrder {
	
	private final Storable<Order> storage = FactoryStorage.getOrderStorage();
	
	public ServiceOrder() { 
		
	}
	
	public void add(Order order) { 
		order.setStatus(Order.Status.AWAITING);
		storage.add(order);	
		
		Storable<RequestsBook> reqStore = FactoryStorage.getRequestBookStorage();			

		List<RequestsBook> requests = reqStore.getAll()
			.stream()
			.filter((RequestsBook req) -> {
				for(Book book : order.getBooks()) {
					if(book.getId() == req.getId()) {
						return true;
					}
				}
				return false;
			})
			.collect(Collectors.toList());

		if(order.getBooks().size() == requests.size()) { 
			requests.forEach((RequestsBook req) -> {
				req.setQueryOnBook(req.getQueryOnBook() + 1);
				reqStore.update(req);
			});
		}
	}
	
	public void close(Order order) {
		order.setStatus(Order.Status.CANCALED);
		storage.update(order);
		
		Storable<RequestsBook> reqStore = FactoryStorage.getRequestBookStorage();	
		
		List<RequestsBook> requests = reqStore.getAll()
		.stream()
		.filter((RequestsBook req) -> {
			for(Book book : order.getBooks()) {
				if(book.getId() == req.getId()) {
					return true;
				}
			}
			return false;
		})
		.collect(Collectors.toList());
	
		if(order.getBooks().size() == requests.size()) { 
			requests.forEach((RequestsBook req) -> {
				req.setQueryOnBook(req.getQueryOnBook() - 1);
				reqStore.update(req);
			});
		}		
	}
		
	public boolean equip(Order order) { 
		boolean result = false;
		Storable<RequestsBook> reqStore = FactoryStorage.getRequestBookStorage();	
		
		List<RequestsBook> requests = reqStore.getAll()
			.stream()
			.filter((RequestsBook req) -> {
				for(Book book : order.getBooks()) {
					if(book.getId() == req.getId() && req.getBooksOnStorage() > 0) {
						return true;
					}
				}
				return false;
			}).collect(Collectors.toList());

		if(order.getBooks().size() == requests.size()) { 
			result = true;
			requests.forEach((RequestsBook req) -> {
				req.setQueryOnBook(req.getQueryOnBook() - 1);
				req.setBooksOnStorage(req.getBooksOnStorage() - 1);			
				reqStore.update(req);
			});
			order.setStatus(Order.Status.COMPLEATE);
			storage.update(order);
		}
	
		return result;
	}
	
	public List<Order> getAwaiting() { 
		return storage.getAll()
				.stream()
				.filter((Order o) -> o.getStatus() == Order.Status.AWAITING)
				.collect(Collectors.toList());
	}
	
	public List<Order> getCompleateForPeriod(Date min, Date max) { 
		List<Order> result = storage.getAll();
		result.removeIf((Order o) -> { 	
			if(o.getStatus() == Order.Status.COMPLEATE) { 
				return !(o.getDateRelease().before(min) || o.getDateRelease().after(max));				
			}
			return false;
		});
		return result;
	}	
	
	public int getEarnedMoney(Date min, Date max) { 
		List<Order> result = getCompleateForPeriod(min, max);
		return result.stream().mapToInt(o -> o.getPrice()).sum();
	}

	public int getCountCompleateForPeriod(Date min, Date max) { 
		return getCompleateForPeriod(min, max).size();
	}
	
	public List<Order> sortBy(Comparator<Order> comparator) { 
		List<Order> result = storage.getAll();
		result.sort(comparator);
		return result;
	}

}
