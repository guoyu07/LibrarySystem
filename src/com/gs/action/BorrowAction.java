package com.gs.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gs.DAO.StudentBookDAO;
import com.gs.service.BookService;
import com.gs.service.StudentBookService;
import com.opensymphony.xwork2.ActionSupport;

@Component("borrowAction")
@Scope("prototype")
public class BorrowAction extends ActionSupport {
	private BookService bookService;
	private String bookID;
	private String userName;
	private StudentBookService studentBookService;

	public BookService getBookService() {
		return bookService;
	}
	@Resource
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public String getBookID() {
		return bookID;
	}
	public void setBookID(String bookID) {
		this.bookID = bookID;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public StudentBookService getStudentBookService() {
		return studentBookService;
	}
	@Resource
	public void setStudentBookService(StudentBookService studentBookService) {
		this.studentBookService = studentBookService;
	}
	@Override
	public String execute() throws Exception {
		studentBookService.borrowBook(userName, bookID);
		if(studentBookService.isBeenBorrow()) return "beenBorrowed";
		else return SUCCESS;
	}

}
