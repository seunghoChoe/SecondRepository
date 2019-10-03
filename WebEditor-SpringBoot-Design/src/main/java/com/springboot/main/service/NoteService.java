package com.springboot.main.service;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.model.Note;
import com.springboot.main.model.NoteBook;
import com.springboot.main.model.NoteDAO;

@Service
public class NoteService {
	@Autowired
	private NoteDAO noteDAO;
	
	
	public List<Note> getAllNote() {
		return this.noteDAO.getAllNote();
	}

	public Note getNoteByIndex(int textIndex) {
		return noteDAO.getNoteByIndex(textIndex);
		
	}

	public List<Note> getNotesByBookIndex(int bookIndex) {
		if(bookIndex == 0) return noteDAO.getAllNote();
		else return noteDAO.getNotesByBookIndex(bookIndex);
	}

	public void insertNote(Note note) {
		this.noteDAO.insertNote(note);
	}

	public void updateNote(Note note) {
		noteDAO.updateNote(note);
	}

	public void deleteNote(Note note) {
		noteDAO.deleteNote(note);
	}

	public List<NoteBook> getAllBooks() {
		return noteDAO.getAllBooks();
	}

	public void insertNoteBook(String bookName) {
		noteDAO.insertNoteBook(bookName);
	}

	public void deleteNoteBook(int bookIndex) {
		noteDAO.deleteNoteBook(bookIndex);
	}

	public NoteBook getBookByIndex(int bookIndex) {
		if(bookIndex == 0) return new NoteBook(0, "전체보기");
		else return noteDAO.getBookByIndex(bookIndex);
	}

//	public void goToDoing(String toState, int noteIndex) {
//		noteDAO.goToDoing(toState, noteIndex);
//	}
//
//	public Vector<Note> getNotesByState(String state) {
//		return noteDAO.getNotesByState(state);
//	}
}
