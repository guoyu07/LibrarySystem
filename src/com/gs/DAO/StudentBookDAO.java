package com.gs.DAO;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.gs.DAO.BookDAO;
import com.gs.model.Student;
import com.gs.model.StudentBooks;

public interface StudentBookDAO {

	public abstract void addBook(StudentBooks sb, int id);

	public abstract void borrowBook(Student s, String bid) throws Exception;

	public abstract int checkBookNumber(String s);

	public abstract boolean checkUserByName(String student_userName);



	public abstract BookDAO getBookDAO();

	public abstract boolean isAddBookFail();

	public abstract boolean isHasBeenBorrow();

	public abstract HibernateTemplate getHibernateTemplate();

	public abstract StudentBooks load(String username);

	public abstract String[] loadBooksFromStudent(Student s);

	public abstract int returnBook(Student s, String id);

	@Resource
	public abstract void setBookDAO(BookDAO bookDAO);

	@Resource
	public abstract void setHibernateTemplate(
			HibernateTemplate hibernateTemplate);

	public List<StudentBooks> getStudentBooks();
	public void addStudentBooks(String s);

}