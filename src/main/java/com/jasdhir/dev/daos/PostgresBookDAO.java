package com.jasdhir.dev.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jasdhir.dev.entities.Book;
import com.jasdhir.dev.utils.ConnectionUtil;



public class PostgresBookDAO implements BookDAO {
	
	static Logger logger = LogManager.getLogger(PostgresBookDAO.class.getName());

	@Override
	public Book createBook(Book book) {
		  try(Connection conn = ConnectionUtil.createConnection()){
	            String sql = "insert into book (bookid,title,author,available,returndate) values (?,?,?,?,?) ";
	            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	           ps.setInt(1, book.getBookId());
	            ps.setString(2,book.getTitle());
	            ps.setString(3,book.getAuthor());
	            ps.setBoolean(4,book.isAvailable());
	           // Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss").create();
	            
	         //   ps.setDate(5, book.getReturnDate());
	            ps.setDate(5, new java.sql.Date(book.getReturnDate().getTime()));

	            ps.execute();

	            ResultSet rs = ps.getGeneratedKeys();
	            rs.next();
	            int key = rs.getInt("bookid");
	            book.setBookId(key);

	            return book;

	        } catch (SQLException sqlException) {
	            sqlException.printStackTrace();
	            logger.error("could not create book",sqlException);
	        }
	        return null;
	}

	@Override
	public Set<Book> getAllBooks() {
		 try(Connection conn = ConnectionUtil.createConnection()){
	            String sql = "select * from book ";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();

	            Set<Book> books = new HashSet<Book>();

	            while(rs.next()){
	                Book book = new Book();
	                book.setBookId(rs.getInt("bookid"));
	                book.setTitle(rs.getString("title"));
	                book.setAuthor(rs.getString("author"));
	                book.setAvailable(rs.getBoolean("available"));
	                book.setReturnDate(rs.getDate("returndate"));
	                books.add(book);
	            }

	            return books;

	        } catch (SQLException sqlException) {
	            sqlException.printStackTrace();
	            logger.error("could not retrieve book",sqlException);
	            return null;
	        }
	}

	@Override
	public Book getBookById(int id) {
		 try(Connection conn = ConnectionUtil.createConnection()){
	            String sql = "select * from book where bookid = ? ";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setInt(1,id);
	            ResultSet rs = ps.executeQuery();
	            rs.next();

	            Book book = new Book();
	            book.setBookId(rs.getInt("bookid"));
	            book.setTitle(rs.getString("title"));
	            book.setAuthor(rs.getString("author"));
	            book.setAvailable(rs.getBoolean("available"));
	            book.setReturnDate(rs.getDate("returndate"));

	            return book;

	        } catch (SQLException sqlException) {
	            sqlException.printStackTrace();
	            logger.error("could not retrieve book",sqlException);
	            return null;
	        }
	}

	@Override
	public Book updateBook(Book book) {
		 try(Connection conn = ConnectionUtil.createConnection()){
	            String sql = "update book set title = ?, author = ? , available = ?,returndate = ? where bookid =? ";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setInt(5, book.getBookId());
	            ps.setString(1,book.getTitle());
	            ps.setString(2,book.getAuthor());
	            ps.setBoolean(3,book.isAvailable());
	            ps.setDate(4, new java.sql.Date(book.getReturnDate().getTime()));
	            ps.setInt(5, book.getBookId());
	            ps.executeUpdate();

	            // SQL INJECTION VERSION
	            // DO NOT USE
	            // 1. Looks terrible
	            // 2. Leaves you open databaase open to malicious attacks
//	            String injection = "update book set title = " + "'" +book.getTitle()+"'"  +
//	                    ", author = " + "'" + book.getAuthor() + "'"  +
//	                    ", book_condition = " +book.getCondition() +
//	                    ", available = " + book.getAvailable() +
//	                    ", return_date = " + book.getReturnDate() +
//	                    " where book_id = " + book.getBookId();
//	            System.out.println(injection);
//	            Statement statement = conn.createStatement();
//	            statement.execute(injection);
	            //

	            return book;

	        } catch (SQLException sqlException) {
	            sqlException.printStackTrace();
	            logger.error("could not update book",sqlException);
	            return null;
	        }
	}

	@Override
	public boolean deleteBookById(int id) {
		try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "delete from book  where bookid =? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();

            return true;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            logger.error("could not delete book",sqlException);
            return false;
        }

	}

}
