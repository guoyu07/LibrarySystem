package com.gs.service;

import java.util.Iterator;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gs.DAO.StudentBookDAO;
import com.gs.DAO.UserDAO;
import com.gs.model.Student;
import com.gs.model.StudentBooks;
@Component("studentBookService")
@Transactional
public class StudentBookService {
	private boolean beenBorrow;
	private SessionFactory sessionFactory;
	private StudentBookDAO studentBookDAO;
	private UserDAO userDAO;
	public void borrowBook(String n,String bid) throws Exception{ //before call this method must check the beenBorrow,otherwise it won't throw any exception
		Student s = userDAO.loadStudent(n);
		if(studentBookDAO.isHasBeenBorrow()) {beenBorrow = true;return;}
		studentBookDAO.borrowBook(s, bid);
	}

	public int checkBookNumber(String s){
		return studentBookDAO.checkBookNumber(s);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public StudentBookDAO getStudentBookDAO() {
		return studentBookDAO;
	}
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public boolean isBeenBorrow() {
		return beenBorrow;
	}

	public int returnBook(String sn,String id) {
		Student s = userDAO.loadStudent(sn);
		return studentBookDAO.returnBook(s, id);
	}
	
	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Resource
	public void setStudentBookDAO(StudentBookDAO studentBookDAO) {
		this.studentBookDAO = studentBookDAO;
	}
	
	@Resource
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void updateStudents(){
		Iterator si = userDAO.getStudents().iterator();
		Iterator sbi = studentBookDAO.getStudentBooks().iterator();
		boolean same = false;
		for(;si.hasNext();){
			Student s = (Student) si.next();
			for(;sbi.hasNext();){
				StudentBooks sb = (StudentBooks) sbi.next();
				if(sb.getStudent_userName().equals(s.getUserName())) {same = true;break;}
			}
			if(same)break;
			studentBookDAO.addStudentBooks(s.getUserName());
		}
		
	}
}
