package com.gs.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="studentbooks")
@org.hibernate.annotations.Proxy(lazy = false)
public class StudentBooks {
	private String student_userName;
	private String book_id;
	@Id
	public String getStudent_userName() {
		return student_userName;
	}

	public void setStudent_userName(String student_userName) {
		this.student_userName = student_userName;
	}

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

}
