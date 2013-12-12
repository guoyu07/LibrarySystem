package com.gs.DAO.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.gs.DAO.BookDAO;
import com.gs.model.Book;
import com.gs.model.Student;

@Component("bookDAO")
public class BookDAOImpl implements BookDAO {

	private HibernateTemplate hibernateTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gs.DAO.Impl.BookDAO#getHibernateTemplate()
	 */
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gs.DAO.Impl.BookDAO#setHibernateTemplate(org.springframework.orm.
	 * hibernate3.HibernateTemplate)
	 */
	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gs.DAO.Impl.BookDAO#save(com.gs.model.Book)
	 */
	public void save(Book b) throws Exception {
		hibernateTemplate.save(b);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gs.DAO.Impl.BookDAO#delete(com.gs.model.Book)
	 */
	public void delete(Book b) {
		hibernateTemplate.delete(b);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gs.DAO.Impl.BookDAO#update(com.gs.model.Book)
	 */
	public void update(Book b) {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gs.DAO.Impl.BookDAO#checkBookWithName(java.lang.String)
	 */
	public boolean checkBookWithName(String bookname) {
		Session s = hibernateTemplate.getSessionFactory().openSession();
		s.beginTransaction();
		long count = (Long) s
				.createQuery(
						"select count(*) from Book book where bookname = :bookname")
				.setString("bookname", bookname).uniqueResult();
		s.getTransaction().commit();
		if (count > 0) {
			return true;
		} else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gs.DAO.Impl.BookDAO#getBooks()
	 */
	public List<Book> getBooks() {
		return (List<Book>) this.hibernateTemplate.find("from Book");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gs.DAO.Impl.BookDAO#loadBook(java.lang.String)
	 */
	public Book loadBook(String id) {
		Book b = (Book) this.hibernateTemplate.get(Book.class,
				Integer.parseInt(id));
		return b;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gs.DAO.Impl.BookDAO#borrowBook(com.gs.model.Book)
	 */
	public void borrowBook(Book b) {
		b.setBrrowed(true);
		Date d = new Date();
		b.setStartDay(d.getTime());
		hibernateTemplate.update(b);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gs.DAO.Impl.BookDAO#returnBook(com.gs.model.Book)
	 */
	public int returnBook(Book b) {
		b.setBrrowed(false);
		Date d = new Date();
		int time = (int)((b.getStartDay()-d.getTime())/(1000*60*60*24));
		hibernateTemplate.update(b);
		b.setStartDay(-1);
		return time;
	}
}
