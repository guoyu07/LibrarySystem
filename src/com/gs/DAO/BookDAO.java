package com.gs.DAO;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.gs.model.Book;

public interface BookDAO {

	public abstract HibernateTemplate getHibernateTemplate();

	@Resource
	public abstract void setHibernateTemplate(
			HibernateTemplate hibernateTemplate);

	public abstract void save(Book b) throws Exception;

	public abstract void delete(Book b);

	public abstract void update(Book b);

	public abstract boolean checkBookWithName(String bookname);

	public abstract List<Book> getBooks();

	public abstract Book loadBook(String id);

	public abstract void borrowBook(Book b);

	public abstract int returnBook(Book b);

}