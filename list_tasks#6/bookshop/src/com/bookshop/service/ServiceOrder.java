package com.bookshop.service;

import java.util.ArrayList;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bookshop.core.comparator.OrderComparators;
import com.bookshop.core.model.Book;
import com.bookshop.core.model.Order;
import com.bookshop.core.model.Order.Status;
import com.bookshop.core.model.RequestsBook;
import com.bookshop.dao.Storable;
import com.bookshop.dao.StorageException;
import com.bookshop.dao.StorageFactory;
import com.bookshop.service.exception.ServiceOrderException;

public class ServiceOrder {
	
	private final Storable<Order> connector = StorageFactory.getInstance().getOrderStorage();
	
	public ServiceOrder() { 
		
	}
	
	public void add(Order order) throws StorageException {
		if(order == null) { 
			throw new IllegalArgumentException("Order has value null");			
		}
		order.setStatus(Order.Status.AWAITING);
		
		ServiceRequestBook reqSevice = new ServiceRequestBook();
		Map<Book, Integer> bookCount = order.getBooksCount();
		for(Book book : bookCount.keySet()) { 
			reqSevice.makeRequest(book, bookCount.get(book));
		}

		connector.add(order);
	}
		
	public void cancel(Order order) throws StorageException {
		if(order == null) {
			throw new IllegalArgumentException("Order has value null");
		} else if(!order.getStatus().equals(Status.AWAITING)) {
			System.err.println("Order now" + order.getStatus() + " !!!!");
			throw new RuntimeException("Order has status " + order.getStatus());
		}

		order.setStatus(Order.Status.CANCALED);

		ServiceRequestBook reqSevice = new ServiceRequestBook();
		Map<Book, Integer> bookCount = order.getBooksCount();

		for(Book book : bookCount.keySet()) { 
			reqSevice.removeRequest(book, bookCount.get(book));
		}
		
		connector.update(order);
	}

	public boolean equip(Order order) throws StorageException, ServiceOrderException { 
		boolean result = false;
		if(!order.getStatus().equals(Status.AWAITING)) { 
			throw new IllegalArgumentException();
		}
		
		Storable<RequestsBook> requestConnector = StorageFactory.getInstance().getRequestBookStorage();
		ServiceRequestBook serviceReq = new ServiceRequestBook();
		Map<Book, Integer> booksCount = order.getBooksCount();	
		List<RequestsBook> requests = new ArrayList<RequestsBook>();
		
		for(Book book : booksCount.keySet()) { 
			requests.add(requestConnector.getById(book.getId()));			
		}
	
		if(booksCount.size() == requests.size()) { 			
			result = requests.stream()
							.allMatch(req -> req.getBooksOnStorage() >= booksCount.get(req.getBook()));
			if(result) { 
				for(RequestsBook req : requests) {
					int needBook = booksCount.get(req.getBook());
					serviceReq.deregisterBook(req.getBook(), needBook);
					serviceReq.removeRequest(req.getBook(), needBook);
				}
				order.setDateRelease(new Date());
				order.setStatus(Order.Status.COMPLETED);
				connector.update(order);
			}
			else {
				throw new ServiceOrderException("Not enough book on storage.");
			}
		} else {
			throw new ServiceOrderException("Book in order no exist in storage.");
		}
		
		return result;
	}
	
	public List<Order> getAwaiting() { 
		return connector.getAll()
				.stream()
				.filter((Order o) -> o.getStatus() == Order.Status.AWAITING)
				.collect(Collectors.toList());
	}
	
	private List<Order> getCompleateForPeriod(Date min, Date max) {
		if(min == null || max == null) { 
			throw new IllegalArgumentException();
		}
		List<Order> result = new ArrayList<Order>(connector.getAll());
		result.removeIf((Order o) -> { 	
			if(o.getStatus() == Order.Status.COMPLETED) {
				return (o.getDateRelease().before(min) || o.getDateRelease().after(max));				
			}
			return true;
		});
		return result;
	}	
	
	public List<Order> getCompleateForPeriod(Date min, Date max, OrderComparators.Type type) {
		List<Order> list = getCompleateForPeriod(min, max);
		list.sort(OrderComparators.getComparator(type));
		return list;
	}

	public int getEarnedMoney(Date min, Date max) { 
		List<Order> result = getCompleateForPeriod(min, max);
		return result.stream().mapToInt(o -> o.getPrice()).sum();
	}

	public int getCountCompleateForPeriod(Date min, Date max) { 
		return getCompleateForPeriod(min, max).size();
	}
	
	public List<Order> sortBy(Comparator<Order> comparator) {
		List<Order> result = new ArrayList<Order>(connector.getAll());
		result.sort(comparator);
		return result;
	}

}
