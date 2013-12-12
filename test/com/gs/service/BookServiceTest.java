package com.gs.service;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gs.action.UserAction;
import com.gs.model.Book;
import com.gs.model.Student;
import com.gs.model.User;

public class BookServiceTest {

	@Test
	public void testSave() throws Exception {
		Book b = new Book();
		b.setBookname("hhh");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
BookService bs = (BookService) ctx.getBean("bookService");
		//BookService bookService = new BookService();
		bs.save(b);
	}
	
	@Test
	public void testExsit() throws ClassNotFoundException, SQLException {
		User u = new Student();
		u.setUserName("Nike2");
		u.setPassWord("Nike2");
		/*ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"beans.xml");
		StudentService ss = (StudentService) ctx.getBean("studentService");*/
		StudentService ss = new StudentService();
		System.out.println(ss.checkUserWithName(u.getUserName()));
	}
	
	/*@Test
	public void testAction() throws Exception {
		UserAction userAction = new UserAction();
		userAction.setUsername("Mike1");
		userAction.setPassword("Mike1");
		String s = userAction.execute();
		assertEquals("success",s);
		
		
	}
*/
}
