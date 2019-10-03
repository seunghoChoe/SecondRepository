package com.springboot.main.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class NoteDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private static final String NOTE_MAPPER_NAME_SPACE = "mapper.NoteMapper.";
	private static final String NOTEBOOK_MAPPER_NAME_SPACE = "mapper.NoteBookMapper.";

	/**
	 * 모든 Note를 조회하는 쿼리 
	 */
	public List<Note> getAllNote() {
		return sqlSession.selectList(NOTE_MAPPER_NAME_SPACE + "selectAllNote");
	}
	
	/**
	 *  Note의 Index를 통해서 노트를 조회하는 쿼리 
	 */
	public Note getNoteByIndex(int textIndex) {
		return sqlSession.selectOne(NOTE_MAPPER_NAME_SPACE + "selectNoteByIndex", textIndex);
	}

	/**
	 *  NoteBook의 Index를 통해서 노트를 조회하는 쿼리 
	 */
	public List<Note> getNotesByBookIndex(int bookIndex) {
		return sqlSession.selectList(NOTE_MAPPER_NAME_SPACE + "selectNotesByBookIndex", bookIndex);
	}
	
	/**
	 *  Note를 새롭게 입력하는 쿼리  
	 */
	public void insertNote(Note note) {
		sqlSession.insert(NOTE_MAPPER_NAME_SPACE + "insertNote", note);
	}
	
	
	/**
	 *  Note를 수정하는 쿼리   
	 */
	public void updateNote(Note note) {
		sqlSession.update(NOTE_MAPPER_NAME_SPACE + "updateNote", note);
	}

	/**
	 *  Note를 삭제하는 쿼리   
	 */
	public void deleteNote(Note note) {
		sqlSession.delete(NOTE_MAPPER_NAME_SPACE + "deleteNote", note);
	}

	/**
	 *  모든 NoteBook을 조회하는 쿼리   
	 */
	public List<NoteBook> getAllBooks() {
		return sqlSession.selectList(NOTEBOOK_MAPPER_NAME_SPACE + "selectAllNoteBook");
	}
	
	/**
	 *  String값의 bookName을 받아서 새로운 NoteBook을 추가하는 쿼리  	
	 * @param bookName
	 */

	public void insertNoteBook(String bookName) {
		sqlSession.insert(NOTEBOOK_MAPPER_NAME_SPACE + "insertNoteBook", bookName);
	}

	/**
	 * Integer값의 bookIndex를 받아서 NoteBook 삭제하는 쿼리
	 * @param bookIndex
	 */
	public void deleteNoteBook(int bookIndex) {
		sqlSession.insert(NOTEBOOK_MAPPER_NAME_SPACE + "deleteNoteBook", bookIndex);
	}
	
	/**
	 * Integer값의 bookIndex를 받아서 노트북을 조회하는 쿼리 
	 * @param bookIndex
	 * @return
	 */
	public NoteBook getBookByIndex(int bookIndex) {
		return sqlSession.selectOne(NOTEBOOK_MAPPER_NAME_SPACE + "selectNoteBookByBookIndex", bookIndex);
	}

//	public void goToDoing(String toState, int noteIndex) {
//		String query ="UPDATE Memo "
//				+ " SET textState = '" + toState + "'"  
//				+ " WHERE textIndex = " + noteIndex + ";";
//		System.out.println(query);
//		this.jdbcTemplate.execute(query);
//	}

//	public Vector<Note> getNotesByState(String state) {
//		String query ="SELECT * FROM Memo WHERE textState = '" + state + "';";
//		Vector<Note> notes = new Vector<Note>();
//		List<Note> noteList = jdbcTemplate.query(query, new NoteMapper());
//		notes.addAll(noteList);
//		return notes;
//	}
	
}
