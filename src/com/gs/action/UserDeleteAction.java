package com.gs.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gs.model.Book;
import com.gs.model.Student;
import com.gs.service.BookService;
import com.gs.service.StudentService;
import com.gs.vo.UserRegisterVO;
import com.opensymphony.xwork2.ActionSupport;

@Component("userDeleteAction")
@Scope("prototype")
public class UserDeleteAction extends ActionSupport {

	private StudentService ss;

	private String username;

	@Override
	public String execute() throws Exception {
		ss.delete(username);
		return "del";
	}

	public StudentService getStudentService() {
		return ss;
	}

	public String getUsername() {
		return username;
	}

	@Resource
	public void setStudentService(StudentService ss) {
		this.ss = ss;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
