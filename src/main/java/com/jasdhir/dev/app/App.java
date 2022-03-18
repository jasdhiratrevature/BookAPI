package com.jasdhir.dev.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jasdhir.dev.controllers.BookController;
import com.jasdhir.dev.daos.BookDAO;
import com.jasdhir.dev.daos.PostgresBookDAO;
import com.jasdhir.dev.services.BookService;
import com.jasdhir.dev.services.BookServiceImpl;

import io.javalin.Javalin;

public class App {

	static Logger logger = LogManager.getLogger(App.class.getName());

	public static void main(String[] args) {
		Javalin app = Javalin.create();

		BookDAO bdao = new PostgresBookDAO();
		BookService bserv = new BookServiceImpl(bdao);
		BookController bookController = new BookController(bserv);

		app.get("/books", bookController.getAllBooks);

		app.get("/books/{id}", bookController.getBookById);
		
		app.post("/books",bookController.createBook);
		
		app.put("/books/{id}", bookController.updateBook);
		
		app.delete("/books/{id}", bookController.deleteBook);

		app.start();
	}
}
