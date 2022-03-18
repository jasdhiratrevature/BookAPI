package com.jasdhir.dev.entities;

import java.util.Date;

public class Book {

    private int bookId;
    private String title;
    private String author;
    private boolean available;
    private Date returnDate;
	public Book() {
		super();
	}
	public Book(int bookId, String title, String author, boolean available, Date returnDate) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.available = available;
		this.returnDate = returnDate;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author + ", available=" + available
				+ ", returnDate=" + returnDate + "]";
	}

   
}