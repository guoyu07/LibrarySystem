package com.gs.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gs.DAO.BookDAO;
import com.gs.model.Book;
import com.gs.model.Student;


@Transactional
@Component("bookService")
public class BookService {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private BookDAO bookDAO;

	public BookDAO getBookDAO() {
		return bookDAO;
	}

	@Resource
	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	public boolean checkBookWithName(String bookname) throws ClassNotFoundException, SQLException {
		return bookDAO.checkBookWithName(bookname);

	}

	public void save(Book b) throws Exception {
		bookDAO.save(b);

	}
	public void delete(Book b){
		bookDAO.delete(b);
	}
	
	public List<Book> getBooks() {
		return this.bookDAO.getBooks();
	}
	public Book loadBook(String id){
		return this.bookDAO.loadBook(id);
	}
	
	public void delete(String s){
		bookDAO.delete(loadBook(s));
	}
}
