package com.jasdhir.dev.services;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jasdhir.dev.daos.BookDAO;
import com.jasdhir.dev.entities.Book;



public class BookServiceImpl implements BookService{
	static Logger logger = LogManager.getLogger(BookServiceImpl.class.getName());
	
	 private BookDAO bdao;
	    public BookServiceImpl(BookDAO bookDAO){
	        this.bdao = bookDAO;
	    }

	@Override
	public Book registerBook(Book book) {
		
		this.bdao.createBook(book);
        return book;
	}

	@Override
	public Set<Book> getAllBooks() {
		Set<Book> books = this.bdao.getAllBooks();
        return books;
	}

	@Override
	public Set<Book> getBooksByTitle(String title) {
		 Set<Book> books = this.bdao.getAllBooks();
	        Set<Book> selectedBooks = new HashSet<Book>();

	        for(Book b : books){
	            if(b.getTitle().toLowerCase().contains(title.toLowerCase())){
	                selectedBooks.add(b);
	            }
	        }

	        return selectedBooks;
	}

	@Override
	public Book getBookById(int id) {
		Book b = this.bdao.getBookById(id);
        return b;
	}

	@Override
	public Book updateBook(Book book) {
		this.bdao.updateBook(book);
        return book;
	}

	@Override
	public boolean deleteBookById(int id) {
		 boolean result = this.bdao.deleteBookById(id);
	        return result;
	}
}
