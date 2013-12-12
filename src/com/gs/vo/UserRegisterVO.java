package com.gs.vo;

public class UserRegisterVO {
	private String username;
	private String password;
	private String password2;
	private boolean isDif;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public boolean isDif() {
		return isDif;
	}

	public void setDif(boolean isDif) {
		this.isDif = isDif;
	}

	public void setPassword2(String passwordt) throws Exception {
		password2 = passwordt;
		if(!password2.equals(password)) {isDif = true;return;} 
		else {isDif = false;return;}
	}

}
