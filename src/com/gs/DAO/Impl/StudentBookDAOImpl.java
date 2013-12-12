package com.gs.DAO.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.gs.DAO.BookDAO;
import com.gs.DAO.StudentBookDAO;
import com.gs.model.Book;
import com.gs.model.Student;
import com.gs.model.StudentBooks;

@Component("studentBookDAO")
public class StudentBookDAOImpl implements StudentBookDAO {
	private boolean addBookFail;
	private BookDAO bookDAO;
	private boolean hasBeenBorrow;
	private HibernateTemplate hibernateTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gs.DAO.Impl.StudentBookDAO#addBook(com.gs.model.StudentBooks,
	 * int)
	 */
	public void addBook(StudentBooks sb, int id) {
		String str = load(sb.getStudent_userName()).getBook_id();
		if (checkBookNumber(sb.getStudent_userName()) == 0) {// 没书
			str = Integer.toString(id) + ",";
		} else if (checkBookNumber(sb.getStudent_userName()) < 2
				&& checkBookNumber(sb.getStudent_userName()) != 0) {// 有书
			str = str + Integer.toString(id) + ",";
		} else if (checkBookNumber(sb.getStudent_userName()) == 2) {
			str = str + Integer.toString(id);
		} else {
			addBookFail = true;
		}
		StudentBooks sbn = new StudentBooks();
		sbn.setBook_id(str);
		sbn.setStudent_userName(sb.getStudent_userName());
		hibernateTemplate.merge(sbn);
	}

	public void addStudentBooks(String s){
		StudentBooks studentBooks = new StudentBooks();
		studentBooks.setStudent_userName(s);
		hibernateTemplate.merge(studentBooks);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gs.DAO.Impl.StudentBookDAO#borrowBook(com.gs.model.Student,
	 * java.lang.String)
	 */
	public void borrowBook(Student s, String bid) throws Exception { // studentbook表中插入数据，Book表中的isBorrow设为true
		Book b = bookDAO.loadBook(bid);
		if (b.isBrrowed()) {
			hasBeenBorrow = true;
			return;
		}
		if (checkUserByName(s.getUserName())) { // 数据库中存在此学生
			addBook(load(s.getUserName()), b.getId());
		} else {// 数据库中无此人
			StudentBooks studentBooks = new StudentBooks();
			studentBooks.setStudent_userName(s.getUserName());
			hibernateTemplate.save(studentBooks);
			addBook(load(s.getUserName()), b.getId());
		}
		bookDAO.borrowBook(b);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gs.DAO.Impl.StudentBookDAO#checkBookNumber(com.gs.model.StudentBooks)
	 */
	public int checkBookNumber(String s) {
		StudentBooks sb = load(s);
		String str = load(sb.getStudent_userName()).getBook_id();
		Book[] books = new Book[5];
		String[] ids = str.split(",");
		if (ids[0].equals(""))
			return 0;
		else
			return ids.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gs.DAO.Impl.StudentBookDAO#checkUserByName(java.lang.String)
	 */
	public boolean checkUserByName(String student_userName) {
		Session s = hibernateTemplate.getSessionFactory().openSession();
		s.beginTransaction();
		long count = (Long) s
				.createQuery(
						"select count(*) from StudentBooks studentbooks where student_userName = :student_userName")
				.setString("student_userName", student_userName).uniqueResult();
		s.getTransaction().commit();
		if (count > 0) {
			return true;
		} else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gs.DAO.Impl.StudentBookDAO#getBookDAO()
	 */
	public BookDAO getBookDAO() {
		return bookDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gs.DAO.Impl.StudentBookDAO#getHibernateTemplate()
	 */
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public List<StudentBooks> getStudentBooks() {
		return (List<StudentBooks>) this.hibernateTemplate.find("from StudentBooks");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gs.DAO.Impl.StudentBookDAO#isAddBookFail()
	 */
	public boolean isAddBookFail() {
		return addBookFail;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gs.DAO.Impl.StudentBookDAO#isHasBeenBorrow()
	 */
	public boolean isHasBeenBorrow() {
		return hasBeenBorrow;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gs.DAO.Impl.StudentBookDAO#load(java.lang.String)
	 */
	public StudentBooks load(String username) {
		StudentBooks studentBooks = (StudentBooks) hibernateTemplate.load(
				StudentBooks.class, username);
		return studentBooks;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gs.DAO.Impl.StudentBookDAO#loadBooksFromStudent(com.gs.model.Student)
	 */
	public String[] loadBooksFromStudent(Student s) {
		String str = load(s.getUserName()).getBook_id();
		Book[] books = new Book[5];
		String[] ids = str.split(",");
		return ids;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gs.DAO.Impl.StudentBookDAO#returnBook(com.gs.model.Student,
	 * java.lang.String)
	 */
	public int returnBook(Student s, String id) {
		Book b = bookDAO.loadBook(id);
		String[] bid = loadBooksFromStudent(s);
		for (int i = 0; i < bid.length; i++) {
			if (bid[i].equals(id)) {
				bid[i] = "-1";
				break;
			}
		}
		String str = new String();
		for (int i = 0; i < bid.length; i++) {
			if (!bid[i].equals("-1")) {
				str = str + (bid[i] + ",");
			}
		}
		StudentBooks sb = load(s.getUserName());
		sb.setBook_id(str);
		hibernateTemplate.update(sb);
		return bookDAO.returnBook(b);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gs.DAO.Impl.StudentBookDAO#setBookDAO(com.gs.DAO.BookDAO)
	 */
	@Resource
	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gs.DAO.Impl.StudentBookDAO#setHibernateTemplate(org.springframework
	 * .orm.hibernate3.HibernateTemplate)
	 */
	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}
