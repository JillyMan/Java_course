package com.bookshop.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bookshop.core.model.Book;
import com.bookshop.core.model.Order;
import com.bookshop.core.model.Order.Status;
import com.bookshop.core.model.RequestsBook;
import com.bookshop.dao.Storable;
import com.bookshop.dao.StorageFactory;

public class ServiceOrder {
	
	private final Storable<Order> connector = StorageFactory.getInstance().getOrderStorage();
	
	public ServiceOrder() { 
		
	}
	
	public void add(Order order) {
		if(order == null) { 
			throw new IllegalArgumentException("Order has value null");			
		}
		order.setStatus(Order.Status.AWAITING);
		connector.add(order);	
		
		ServiceRequestBook reqSevice = new ServiceRequestBook();
		Map<Book, Integer> bookCount = order.getBooksCount();
		bookCount.forEach((book, count) -> reqSevice.makeRequest(book, bookCount.get(book)));
	}
		
	public void cancel(Order order) {
		if(order == null) {
			throw new IllegalArgumentException("Order has value null");
		} else if(!order.getStatus().equals(Status.AWAITING)) {
			System.err.println("Order now" + order.getStatus() + " !!!!");
			throw new RuntimeException("Order has status " + order.getStatus());
		}

		order.setStatus(Order.Status.CANCALED);

		ServiceRequestBook reqSevice = new ServiceRequestBook();
		Map<Book, Integer> bookCount = order.getBooksCount();
		bookCount.forEach((book, count) -> reqSevice.removeRequest(book, bookCount.get(book)));
		
		connector.update(order);
	}

	public boolean equip(Order order) { 
		boolean result = false;
		if(!order.getStatus().equals(Status.AWAITING)) { 
			throw new IllegalArgumentException();
		}
		
		Storable<RequestsBook> requestConnector = StorageFactory.getInstance().getRequestBookStorage();
		ServiceRequestBook serviceReq = new ServiceRequestBook();
		Map<Book, Integer> booksCount = order.getBooksCount();	
		List<RequestsBook> requests = new ArrayList<RequestsBook>();
		
		booksCount.keySet().forEach(book -> requests.add(requestConnector.getById(book.getId())));
				
		if(booksCount.size() == requests.size()) { 			
			result = requests.stream()
				.allMatch((req) ->  {
					return req.getBooksOnStorage() >= booksCount.get(req.getBook());
				});
			if(result) { 
				requests.forEach((req) -> {
					int needBook = booksCount.get(req.getBook());
					serviceReq.deregisterBook(req.getBook(), needBook);
					serviceReq.removeRequest(req.getBook(), needBook);					
				});
				order.setStatus(Order.Status.COMPLETED);
				connector.update(order);
			}
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
		if(min == null || max == null) { 
			throw new IllegalArgumentException();
		}
		List<Order> result = connector.getAll();
		result.removeIf((Order o) -> { 	
			if(o.getStatus() == Order.Status.COMPLETED) { 
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
