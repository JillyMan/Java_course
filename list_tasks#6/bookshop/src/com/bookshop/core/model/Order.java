package com.bookshop.core.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.bookshop.dao.Storable;
import com.bookshop.dao.StorageException;
import com.bookshop.dao.StorageFactory;

import java.util.Set;


public class Order implements Identified<Integer>, Externalizable, Cloneable {	
	
	private static final long serialVersionUID = -4013503901685628461L;

	public enum Status {
		AWAITING("AWAITING", 1),
		COMPLETED("COMPLETED", 2),
		CANCALED("CANCALED", 3);
		
		private final String name;
		private final Integer value;
		
		private Status(String str, Integer value) { 
			this.name = str;
			this.value = value;
		}
			
		public String toString() { 
			return this.name;
		}		
		
		public Integer toInt() { 
			return value;
		}
	}
	
	private Integer id;
	private Date dateOrder;
	private Date dateRelease;
	transient private Map<Book, Integer> booksCount;
	private Status status;
	
	private final DateFormat dateFormat = new SimpleDateFormat("d MMMM, yyyy");
	
	public Order() {}
	
	public Order(Integer id, Date dateOrder, Date dateRelease, Map<Book, Integer> books) {
		this.id = id;
		this.dateOrder = dateOrder;
		this.dateRelease = dateRelease;
		this.booksCount = books;
	}
	
	public Order(Integer id, Date dateOrder, Date dateRelease, Map<Book, Integer> books, Status status) {
		this(id, dateOrder, dateRelease, books);
		this.status = status;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateOrder() {
		return dateOrder;
	}
	
	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}
	
	public Date getDateRelease() {
		return dateRelease;
	}
	
	public void setDateRelease(Date dateRelease) {
		this.dateRelease = dateRelease;
	}
	
	public Integer getPrice() {
		int result = 0;
		if(booksCount != null) { 
			result = booksCount.keySet().stream()
					.mapToInt(book -> book.getPrice())
					.sum();
		}
		return result;
	}
	
	public void setIdCountBooks(Map<Book, Integer> map) { 
		booksCount = map;
	}
	
	public Map<Book, Integer> getBooksCount() {
		return booksCount;
	}
	
	public Set<Book> getBooks() { 
		return booksCount.keySet();
	}
	
	public void setBook(Book book, int count) {
		if(book == null || count < 0) { 
			throw new IllegalArgumentException();
		}
		
		if(booksCount == null) {
			booksCount = new HashMap<Book, Integer>();
		}
		
		if(count <= 0) {
			booksCount.remove(book);
		} else {
			booksCount.put(book, count);			
		}
	}
		
	public void addBooks(Book book, int count) {
		if(book == null || count <= 0) { 
			throw new IllegalArgumentException();
		}
		
		if(booksCount == null) {
			booksCount = new HashMap<Book, Integer>();
		}
		
		if(booksCount.containsKey(book)) { 
			booksCount.compute(book, (key, value) -> value + count);
		} else { 
			booksCount.put(book, count);
		}
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}	
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOrder == null) ? 0 : dateOrder.hashCode());
		result = prime * result + ((dateRelease == null) ? 0 : dateRelease.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (dateOrder == null) {
			if (other.dateOrder != null)
				return false;
		} else if (!dateOrder.equals(other.dateOrder))
			return false;
		if (dateRelease == null) {
			if (other.dateRelease != null)
				return false;
		} else if (!dateRelease.equals(other.dateRelease))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object clone() throws CloneNotSupportedException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		try (ObjectOutput out = new ObjectOutputStream(bos)) {			
			out.writeObject(booksCount);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] bytesBooksCount = bos.toByteArray();		
		ByteArrayInputStream bis = new ByteArrayInputStream(bytesBooksCount);
		Map<Book, Integer> newBooksCount = null;

		try(ObjectInputStream oi = new ObjectInputStream(bis)){
			newBooksCount = (Map<Book, Integer>)oi.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if (newBooksCount == null) {
			throw new CloneNotSupportedException();
		}		
		
		Order obj = new Order(id, (Date)dateOrder.clone(), (Date)dateRelease.clone(), newBooksCount, status);
		return obj;
	}

	public String toString() {
		return "Order [ID=" + id + ", DataOrder=" + dateFormat.format(dateOrder) + ", DateRelease=" + dateFormat.format(dateRelease) + 
				", Price=" + getPrice() + ", IdCountBooks=" + booksCount.toString() + ", Status=" + status.toString() + "]";
	}

	private static class Pair<K, V> implements Serializable {		
		private static final long serialVersionUID = 1L;

		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		private K key;
		private V value;

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(id);
		out.writeObject(dateOrder);
		out.writeObject(dateRelease);
		List<Pair<Integer, Integer>> idsCount = new ArrayList<Pair<Integer, Integer>>();
		for(Entry<Book, Integer> bc : booksCount.entrySet()) {
			idsCount.add(new Pair<Integer, Integer>(bc.getKey().getId(), bc.getValue()));
		}
		out.writeObject(idsCount);	
		out.writeObject(status);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		final Storable<Book> bookStore = StorageFactory.getInstance().getBookStorage();
		this.id = (Integer)(in.readObject());
		this.dateOrder = (Date)(in.readObject());
		this.dateRelease = (Date)(in.readObject());
			
		@SuppressWarnings("unchecked")
		List<Pair<Integer, Integer>> idsCount = (List<Pair<Integer, Integer>>)in.readObject();
		booksCount = new HashMap<Book, Integer>();
		for (Pair<Integer, Integer> pair : idsCount) {
			try {
				booksCount.put(bookStore.getById(pair.getKey()), pair.getValue());
			} catch (StorageException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		this.status = (Status)(in.readObject());
	}
}