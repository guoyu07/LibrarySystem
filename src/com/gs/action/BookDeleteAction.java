package com.gs.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gs.model.Book;
import com.gs.service.BookService;
import com.opensymphony.xwork2.ActionSupport;

@Component("bookDeleteAction")
@Scope("prototype")
public class BookDeleteAction extends ActionSupport {

	private BookService bookService;
	private String bookname;
	
	

	public BookService getBookService() {
		return bookService;
	}
	@Resource
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	private String book;

	@Override
	public String execute() throws Exception {
		bookService.delete(bookname);
		return SUCCESS;
		
	}
}
		
