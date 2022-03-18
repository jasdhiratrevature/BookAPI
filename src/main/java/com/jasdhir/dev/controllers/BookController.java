package com.jasdhir.dev.controllers;

import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jasdhir.dev.entities.Book;
import com.jasdhir.dev.exceptions.InvalidUpdateException;
import com.jasdhir.dev.services.BookService;
import io.javalin.http.Handler;

public class BookController {
	 private Gson gson = new  GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss").create();
	    private BookService bserv;

	    public BookController(BookService bookService){
	        this.bserv = bookService;
	    }

	    public Handler getAllBooks = (ctx) ->{
	    	String title=ctx.queryParam("title");
	    	
	        if(title==null){
	            Set<Book> books = this.bserv.getAllBooks();
	            String booksJSON = this.gson.toJson(books);
	            ctx.result(booksJSON);
	        }else{
	            Set<Book> books = this.bserv.getBooksByTitle(title);
	            String booksJSON = this.gson.toJson(books);
	            ctx.result(booksJSON);
	        }

	    };
	    
	    public Handler getBookById = (ctx) ->{
	        int id = Integer.parseInt(ctx.pathParam("id"));
	        Book book = this.bserv.getBookById(id);
	        String bookJSON = this.gson.toJson(book);
	        ctx.result(bookJSON);
	    };
	    
	    public Handler createBook = (ctx) -> {
	        Book book = this.gson.fromJson(ctx.body(), Book.class);
	        System.out.println(book);
	        book = this.bserv.registerBook(book);
	        String bookJSON = gson.toJson(book);
	        ctx.status(201);
	        ctx.result(bookJSON);
	    };
	    
	    public Handler updateBook = (ctx) ->{
	        int id = Integer.parseInt(ctx.pathParam("id"));
	        Book book = this.gson.fromJson(ctx.body(), Book.class);
	        book.setBookId(id);
	        try{
	            this.bserv.updateBook(book);
	            String bookJSON = this.gson.toJson(book);
	            ctx.result(bookJSON);
	        }catch (InvalidUpdateException e){
	            ctx.status(400);
	            ctx.result("Books cannot be upgraded in quality");
	        }

	    };
	    
	    public Handler deleteBook = (ctx) -> {
	        int id = Integer.parseInt(ctx.pathParam("id"));
	        boolean result = this.bserv.deleteBookById(id);
	        if(result){
	            ctx.status(200);
	        }else{
	            ctx.status(404);
	        }
	    };
}
