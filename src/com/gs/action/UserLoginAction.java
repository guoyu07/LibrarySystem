package com.gs.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gs.model.Student;
import com.gs.service.StudentService;
import com.gs.util.CookieUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

@Component("userLoginAction")
@Scope("prototype")
public class UserLoginAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, SessionAware {
	private String passWord;
	private StudentService studentService;
	private String userName;
	public static final String USER_SESSION = "user.session";  
	private CookieUtils cookieUtils = new CookieUtils(); 
	private HttpServletResponse response;  
    private HttpServletRequest request;  
    private Map<String, Object> session;  

	@Override
	public String execute() throws Exception {
		if (studentService.loadStudent(userName).getPassWord().equals(passWord)) {
			Student user = studentService.loadStudent(userName);
			Cookie cookie = cookieUtils.addCookie(user);  
            response.addCookie(cookie);
			return SUCCESS;
		} else
			return "fail";
	}
	
	public String logout() {  
        HttpSession session = request.getSession(false);  
        if (session != null)  
            session.removeAttribute(USER_SESSION);  
        Cookie cookie = cookieUtils.delCookie(request);  
        if (cookie != null)  
            response.addCookie(cookie);  
        return "login";  
    }  

	public String getPassWord() {
		return passWord;
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public String getUserName() {
		return userName;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}

	@Resource
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}

}
