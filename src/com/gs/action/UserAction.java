package com.gs.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gs.model.Student;
import com.gs.service.StudentBookService;
import com.gs.service.StudentService;
import com.gs.vo.UserRegisterVO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Component("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven {

	private static final long serialVersionUID = 1L;
	private String username;
	private StudentService ss;
	private Student student;
	private List<Student> students;

	private UserRegisterVO userRegisterVO = new UserRegisterVO();
	private StudentBookService studentBookService;

	@Override
	public String execute() throws Exception {
		if (userRegisterVO.isDif())
			return "dif";
		Student u = new Student();
		u.setUserName(userRegisterVO.getUsername());
		u.setPassWord(userRegisterVO.getPassword());
		if (ss.checkUserWithName(userRegisterVO.getUsername())) {
			return "fail";
		} else {
			ss.save((Student) u);
			return "success";
		}
	}

	public Object getModel() {
		return userRegisterVO;
	}

	

	public Student getStudent() {
		return student;
	}

	public List<Student> getStudents() {
		this.students = this.ss.getStudents();
		return students;
	}

	public StudentService getStudentService() {
		return ss;
	}

	public UserRegisterVO getUserRegisterVO() {
		return userRegisterVO;
	}

	public String list() {

		this.students = this.ss.getStudents();
		return "list";

	}

	public String loadStudent() {
		student = ss.loadStudent(username);
		return "loadStudent";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Resource
	public void setStudentService(StudentService ss) {
		this.ss = ss;
	}

	public void setUserRegisterVO(UserRegisterVO userRegisterVO) {
		this.userRegisterVO = userRegisterVO;
	}
	
	public void updateStudentBooks(){
		studentBookService.updateStudents();
	}

	public StudentBookService getStudentBookService() {
		return studentBookService;
	}
	@Resource
	public void setStudentBookService(StudentBookService studentBookService) {
		this.studentBookService = studentBookService;
	}
}
