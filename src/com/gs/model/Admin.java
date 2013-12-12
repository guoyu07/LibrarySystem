package com.gs.model;


import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;


public class Admin implements User {

	private String userName;
	private String passWord;

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
