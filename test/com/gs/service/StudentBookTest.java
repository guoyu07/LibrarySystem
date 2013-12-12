package com.gs.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gs.DAO.StudentBookDAO;
import com.gs.action.BorrowAction;
import com.gs.action.UserAction;
import com.gs.model.Book;
import com.gs.model.Student;
import com.gs.model.StudentBooks;

public class StudentBookTest {


	@Test
	public void testBorrowBook() throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		StudentBookDAO sbdao = (StudentBookDAO) ctx.getBean("studentBookDAO");
		Student s = new Student();
		s.setUserName("gsh");
		sbdao.borrowBook(s, "10");
	}

	@Test
	public void testReturnBook() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		StudentBookDAO sbdao = (StudentBookDAO) ctx.getBean("studentBookDAO");
		Student s = new Student();
		s.setUserName("gsh");
		sbdao.returnBook(s, "10");
	}

	@Test
	public void testDontHaveBook() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testCheckName() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		StudentBookDAO sb = (StudentBookDAO) ctx.getBean("studentBookDAO");
		System.out.println(sb.checkUserByName("bbb"));
	}
	
	@Test
	public void testAddBook() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		StudentBookDAO dao = (StudentBookDAO) ctx.getBean("studentBookDAO");
		StudentBooks sb = dao.load("gsh");
		dao.addBook(sb, 5);
	}
	
	@Test
	public void testAction() throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		BorrowAction ba = (BorrowAction) ctx.getBean("borrowAction");
		ba.setBookID("10");
		ba.setUserName("gsh");
		System.out.println(ba.execute());
	}
	
	@Test
	public void testUpdate() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		StudentBookService ss = (StudentBookService) ctx.getBean("studentBookService");
		ss.updateStudents();
	}

}
