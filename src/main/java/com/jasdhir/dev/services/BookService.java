package com.jasdhir.dev.services;

import java.util.Set;

import com.jasdhir.dev.entities.Book;

public interface BookService {

    //CREATE
    Book registerBook(Book book);

    //READ
    Set<Book> getAllBooks();
    Set<Book> getBooksByTitle(String title);
    Book getBookById(int id);

    //UPDATE
    Book updateBook(Book book);

    //DELETE
    boolean deleteBookById(int id);

}