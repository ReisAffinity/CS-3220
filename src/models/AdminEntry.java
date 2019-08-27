package models;

import java.util.Date;

public class AdminEntry {

	static int count = 0;
	
	int id;
	String name;
	String quotation;
	Date date;
	
	public AdminEntry() {
		super();
		this.id = count++;
		this.date = new Date();
	}
	public AdminEntry(int id, String name, String quotation) {
		super();
		this.name = name;
		this.quotation = quotation;
		this.id = id;
		this.date = new Date();
	}
	
	public AdminEntry(String name, String quotation) {
		super();
		this.name = name;
		this.quotation = quotation;
		this.id = count++;
		this.date = new Date();
	}

	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuotation() {
		return quotation;
	}

	public void setQuotation(String message) {
		this.quotation = message;
	}

	public int getId() {
		return id;
	}
	
	
	
}