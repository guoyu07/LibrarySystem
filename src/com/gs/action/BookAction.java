package com.gs.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gs.model.Book;
import com.gs.model.Student;
import com.gs.service.BookService;
import com.opensymphony.xwork2.ActionSupport;

@Component("bookAction")
@Scope("prototype")
public class BookAction extends ActionSupport {

	private Book book;
	private String bookname;
	
	private List<Book> books;
	private BookService bookService;

	private String id;

	@Override
	public String execute() throws Exception {
		Book b = new Book();
		b.setBookname(bookname);
		b.setBrrowed(false);
		if (bookService.checkBookWithName(bookname))
			return "fail";
		else {
			bookService.save((Book) b);
			return "success";
		}
	}

	public Book getBook() {
		return book;
	}

	public String getBookname() {
		return bookname;
	}

	public List<Book> getBooks() {
		return books;
	}

	public BookService getBookService() {
		return bookService;
	}

	public String getId() {
		return id;
	}

	public String list() {

		this.books = this.bookService.getBooks();
		return "list";

	}

	public String loadBook() {
		book = bookService.loadBook(id);
		return "loadBook";
	}
	public void setBook(Book book) {
		this.book = book;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	@Resource
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public void setId(String id) {
		this.id = id;
	}
}
