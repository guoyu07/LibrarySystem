package com.gs.DAO.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.gs.DAO.UserDAO;
import com.gs.model.Student;
import com.gs.util.HibernateUtil;

@Component("userDAO")
public class UserDAOImpl implements UserDAO {

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void save(Student u)  {
			hibernateTemplate.save(u);
	}

	public void delete(Student u) {
		hibernateTemplate.delete(u);
	}

	public void update(Student u) {
		// TODO Auto-generated method stub

	}

	public boolean checkUserWithName(String username) {
		Session s = hibernateTemplate.getSessionFactory().openSession();
		s.beginTransaction();
		long count = (Long) s
				.createQuery(
						"select count(*) from Student student where username = :username")
				.setString("username", username).uniqueResult();
		s.getTransaction().commit();
		if (count > 0) {
			return true;
		} else
			return false;
	}

	public List<Student> getStudents() {
		return (List<Student>) this.hibernateTemplate.find("from Student");
	}

	public Student loadStudent(String sid) {
		Student s = (Student)this.hibernateTemplate.load(Student.class, sid);
		return s;
	}

}
