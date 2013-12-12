package com.gs.service;

import java.sql.*;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gs.DAO.UserDAO;
import com.gs.DAO.Impl.UserDAOImpl;
import com.gs.model.Student;
import com.gs.model.User;
@Transactional
@Component("studentService")
public class StudentService {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	@Resource
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public boolean checkUserWithName(String username)
			throws ClassNotFoundException, SQLException {
		return userDAO.checkUserWithName(username);

	}

	public void save(User u) throws Exception {
		userDAO.save((Student) u);

	}

	public List<Student> getStudents() {
		return this.userDAO.getStudents();
	}
	public Student loadStudent(String id){
		return this.userDAO.loadStudent(id);
	}
	
	public void delete(String s){
		userDAO.delete(loadStudent(s));
	}
	
}
