package com.jasdhir.dev.daos;

import java.util.Set;

import com.jasdhir.dev.entities.Book;

public interface BookDAO {

    //CREATE
    Book createBook(Book book);

    //READ
    Set<Book> getAllBooks();
    Book getBookById(int id);

    //UPDATE
    Book updateBook(Book book);

    //DELETE
    boolean deleteBookById(int id);
}
