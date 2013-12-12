package com.gs.DAO;

import java.util.List;

import com.gs.model.Student;

public interface UserDAO {
	public void save(Student u) throws Exception;
	public void delete(Student u);
	public void update(Student u);
	public boolean checkUserWithName(String username);
	public List<Student> getStudents(); 
	public Student loadStudent(String id);
}
