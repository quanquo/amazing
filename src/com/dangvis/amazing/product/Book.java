package com.dangvis.amazing.product;

public class Book extends Product {
	private String isbn_10;
	
	public Book(String bookID) {
		super(bookID);		
	}
	
	public Book(String bookID, String description ) {
		super(bookID, description);
	}
	
	public Book(String bookID, String description, String isbn_10) {
		super(bookID, description);
		this.setIsbn_10(isbn_10);
	}

	public String getIsbn_10() {
		return isbn_10;
	}

	public void setIsbn_10(String isbn_10) {
		this.isbn_10 = isbn_10;
	};	
	
}
