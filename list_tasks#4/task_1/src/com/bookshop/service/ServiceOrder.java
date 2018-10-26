package com.bookshop.service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.bookshop.core.model.Book;
import com.bookshop.core.model.Order;
import com.bookshop.core.model.Order.Status;
import com.bookshop.core.model.RequestsBook;
import com.bookshop.dao.FactoryStorage;
import com.bookshop.dao.Storable;

public class ServiceOrder {
	
	private final Storable<Order> connector = FactoryStorage.getOrderStorage();
	
	public ServiceOrder() { 
		
	}
	
	public void add(Order order) { 
		order.setStatus(Order.Status.AWAITING);
		connector.add(order);	
		
		Storable<RequestsBook> reqStore = FactoryStorage.getRequestBookStorage();			
		List<RequestsBook> requests = getRequestsByOrder(order, reqStore);

		if(order.getBooks().size() == requests.size()) { 
			requests.forEach((RequestsBook req) -> {
				req.setQueryOnBook(req.getQueryOnBook() + 1);
				reqStore.update(req);
			});
		}
	}
		
	public void close(Order order) {
		if(order.getStatus().equals(Status.COMPLEATE)) {
			System.err.println("Order compleate!!!");
			return;
		}
		order.setStatus(Order.Status.CANCALED);
		connector.update(order);

		Storable<RequestsBook> reqStore = FactoryStorage.getRequestBookStorage();	
		List<RequestsBook> requests = getRequestsByOrder(order, reqStore);
		
		if(order.getBooks().size() == requests.size()) { 
			requests.forEach((RequestsBook req) -> {
				req.setQueryOnBook(req.getQueryOnBook() - 1);
				reqStore.update(req);
			});
		}		
	}
	
	private List<RequestsBook> getRequestsByOrder(Order order, Storable<RequestsBook> reqStore) { 
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
		return requests;
	}

	
	public boolean equip(Order order) { 
		boolean result = false;
		if(!order.getStatus().equals(Status.AWAITING)) { 
			System.err.println("Order not awaiting!!!");
			return result;
		}
		Storable<RequestsBook> reqStore = FactoryStorage.getRequestBookStorage();	
		
		List<RequestsBook> requests = reqStore.getAll();
		requests = requests.stream()
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
			connector.update(order);
		}
	
		return result;
	}
	
	public List<Order> getAwaiting() { 
		return connector.getAll()
				.stream()
				.filter((Order o) -> o.getStatus() == Order.Status.AWAITING)
				.collect(Collectors.toList());
	}
	
	public List<Order> getCompleateForPeriod(Date min, Date max) { 
		List<Order> result = connector.getAll();
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
		List<Order> result = connector.getAll();
		result.sort(comparator);
		return result;
	}

}
