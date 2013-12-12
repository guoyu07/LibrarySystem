package com.gs.util;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.gs.DAO.UserDAO;
import com.gs.action.UserLoginAction;
import com.gs.model.Student;


/**
 * cookie的增加、删除、查询
 */
@Component("cookiesUtils")
public class CookieUtils {
	public static final String USER_COOKIE = "user.cookie";
	private UserDAO userDAO;
	private boolean ishavecookie;
	public UserDAO getUserDAO() {
		return userDAO;
	}
	@Resource
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	// 添加一个cookie
	public Cookie addCookie(Student user) {
		Cookie cookie = new Cookie(USER_COOKIE, user.getUserName() + ","
				+ user.getPassWord());
		System.out.println("添加cookie");
		cookie.setMaxAge(30*60);// cookie保存两周
		return cookie;
	}

	// 得到cookie
	public boolean getCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		System.out.println("cookies: " + cookies);
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				System.out.println("cookie: " + cookie.getName());
				if (CookieUtils.USER_COOKIE.equals(cookie.getName())) {
					String value = cookie.getValue();
					if (StringUtils.isNotBlank(value)) {
						String[] split = value.split(",");
						String username = split[0];
						String password = split[1];
						Student user = userDAO.loadStudent(username);
						if (user != null) {
							HttpSession session = request.getSession();
							session.setAttribute(UserLoginAction.USER_SESSION, user);// 添加用户到session中
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	// 删除cookie
	public Cookie delCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (USER_COOKIE.equals(cookie.getName())) {
					cookie.setValue("");
					cookie.setMaxAge(0);
					return cookie;
				}
			}
		}
		return null;
	}
	public boolean getc(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		
		for(int i = 0;i<cookies.length;i++){
			System.out.println("-----------*******************"+cookies[i].getName());
			if(cookies[i].getName().equals(USER_COOKIE)){ishavecookie = true;}
			else ishavecookie = false;
		}
		return ishavecookie;
	}
}
