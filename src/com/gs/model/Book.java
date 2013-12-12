package com.gs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book {
	private int id;
	private String bookname;
	private boolean isBrrowed;
	private long startDay;
	
	
	public long getStartDay() {
		return startDay;
	}

	public boolean isBrrowed() {
		return isBrrowed;
	}

	public void setBrrowed(boolean isBrrowed) {
		this.isBrrowed = isBrrowed;
	}
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public void setStartDay(long startDay) {
		this.startDay = startDay;
	}

}
