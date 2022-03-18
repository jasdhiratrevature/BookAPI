package com.jasdhir.tests.servicetests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jasdhir.dev.daos.BookDAO;
import com.jasdhir.dev.entities.Book;
import com.jasdhir.dev.services.BookService;
import com.jasdhir.dev.services.BookServiceImpl;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {

    @Mock
    BookDAO mockBookDAO = null;
    BookService bookService = null;

    @BeforeEach
    void bookDaoSetUp(){
        Book greatGatsby = new Book(1,"The Great Gatsby","F. Scott Fitzgerald",true,new java.util.Date());
        Book tomSawyer = new Book(2,"The Adventures of Tom Sawyer","Mark Twain",true,new java.util.Date());
        Book theHobbit = new Book(3,"The Hobbit","J.R. Tolkien",true,new java.util.Date());
        Book chamberOfSecrets = new Book(4,"Harry Potter and the Chamber of Secrets","J.K. Rowling",true,new java.util.Date());
        Book deathlyHallows = new Book(5,"Harry Potter and the Deathly Hallows","J.K. Rowling",true,new java.util.Date());
        Set<Book> allBooks = new HashSet<Book>();
        allBooks.add(greatGatsby);
        allBooks.add(tomSawyer);
        allBooks.add(theHobbit);
        allBooks.add(chamberOfSecrets);
        allBooks.add(deathlyHallows);
        Mockito.when(mockBookDAO.getAllBooks()).thenReturn(allBooks);
        bookService = new BookServiceImpl(mockBookDAO);
    }

    @Test
    void book_titles_containing_search(){
        Set<Book> books = this.bookService.getBooksByTitle("harry");
        Assertions.assertEquals(2,books.size());
    }

}
