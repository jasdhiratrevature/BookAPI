package com.jasdhir.tests.daotests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;


import com.jasdhir.dev.daos.BookDAO;
import com.jasdhir.dev.daos.PostgresBookDAO;
import com.jasdhir.dev.entities.Book;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookDaoTests {

    private static BookDAO bdao = new PostgresBookDAO();
    static Logger logger = LogManager.getLogger(BookDaoTests.class.getName());

    private static Book testBook = null;

    @Test
    @Order(1)
    void create_book(){
        Book hp = new Book(0,"Harry Potter and the Chamber of Secrets","J.K. Rowling",true,new java.util.Date());
        bdao.createBook(hp);
        BookDaoTests.testBook = hp;
        System.out.println(hp);
        Assertions.assertNotEquals(0,hp.getBookId());
    }

    @Order(2)
    @Test
    void get_book(){
        Book hp = bdao.getBookById(testBook.getBookId());
        Assertions.assertEquals("Harry Potter and the Chamber of Secrets",hp.getTitle());
    }

    @Order(3)
    @Test
    void update_book(){
        Book hp = bdao.getBookById(testBook.getBookId());
      //  hp.setCondition(3);
        bdao.updateBook(hp);
        Book updatedBook = bdao.getBookById(hp.getBookId());
        //Assertions.assertEquals(3,updatedBook.getAuthor());
        Assertions.assertEquals(3,updatedBook.getBookId());
    }

    @Order(4)
    @Test
    void delete_book(){
        Assertions.assertTrue(bdao.deleteBookById(testBook.getBookId()));
    }

    


}
