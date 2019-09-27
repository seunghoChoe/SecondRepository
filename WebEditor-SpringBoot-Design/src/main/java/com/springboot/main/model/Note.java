package com.springboot.main.model;



public class Note {
	private int textIndex;
	private String textDate;
	private String textTitle;
	private String textContent;
	private String textState;
	private int bookIndex;
	
	
	public String getTextState() {
		return textState;
	}
	public void setTextState(String textState) {
		this.textState = textState;
	}
	public int getBookIndex() {
		return bookIndex;
	}
	public void setBookIndex(int bookIndex) {
		this.bookIndex = bookIndex;
	}
	public int getTextIndex() {
		return textIndex;
	}
	public void setTextIndex(int textIndex) {
		this.textIndex = textIndex;
	}
	
	public String getTextDate() {
		return textDate;
	}
	public void setTextDate(String textDate) {
		this.textDate = textDate;
	}
	public String getTextTitle() {
		return textTitle;
	}
	public void setTextTitle(String textTitle) {
		this.textTitle = textTitle;
	}
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	
	
}
