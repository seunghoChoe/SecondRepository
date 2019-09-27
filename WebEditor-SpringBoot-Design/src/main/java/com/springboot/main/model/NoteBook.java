package com.springboot.main.model;

public class NoteBook {
	private int bookIndex;
	private String bookName;
	
	public NoteBook() {
		
	}
	
	public NoteBook(int bookIndex, String bookName) {
		this.bookIndex = bookIndex;
		this.bookName = bookName;
	}
	public int getBookIndex() {
		return bookIndex;
	}
	public void setBookIndex(int bookIndex) {
		this.bookIndex = bookIndex;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	
}
