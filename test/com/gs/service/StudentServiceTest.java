package com.gs.service;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gs.action.UserAction;
import com.gs.action.UserDeleteAction;
import com.gs.model.Student;
import com.gs.model.User;
import com.gs.util.CookieUtils;
import com.gs.vo.UserRegisterVO;

public class StudentServiceTest {


	@Test
	public void testSave() throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		UserAction ua = (UserAction) ctx.getBean("userAction");
		UserRegisterVO userRegisterVO = new UserRegisterVO();
		userRegisterVO.setUsername("ty");
		userRegisterVO.setPassword("ty");
		userRegisterVO.setPassword2("ty");
		ua.setUserRegisterVO(userRegisterVO);
		ua.execute();
		
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
	
	@Test
	public void testGetStudents() throws Exception {
		ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
		UserAction ua = (UserAction)act.getBean("userAction");
		ua.loadStudent();
		
	}
	
	@Test
	public void testDelete() throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		UserDeleteAction uda = (UserDeleteAction) ctx.getBean("userDeleteAction");
		uda.execute();
		
	}
	
	

}
