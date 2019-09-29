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

	public int getBookIndexByName(String bookName) {
		String query ="SELECT bookIndex FROM NoteBook WHERE bookName = '" + bookName + "';";
		return this.jdbcTemplate.queryForObject(query, Integer.class);
	}
	
	public void insertNoteBook(String bookName) {
		String query ="INSERT INTO NoteBook(bookName)"
				+ " VALUES('" + bookName + "');";
		this.jdbcTemplate.execute(query);
	}

	public void deleteNoteBook(int bookIndex) {
		String query ="DELETE FROM NoteBook"
				+ " WHERE bookIndex = " + bookIndex + ";";
		this.jdbcTemplate.execute(query);
	}

	public void goToDoing(String toState, int noteIndex) {
		String query ="UPDATE Memo "
				+ " SET textState = '" + toState + "'"  
				+ " WHERE textIndex = " + noteIndex + ";";
		System.out.println(query);
		this.jdbcTemplate.execute(query);
		
	}

	class NoteMapper implements RowMapper<Note> {
		@Override
		public Note mapRow(ResultSet rs, int rowNum) throws SQLException {
			Note note = new Note();
			note.setTextIndex(rs.getInt("textIndex"));
			note.setTextTitle(rs.getString("textTitle"));
			note.setTextContent(rs.getString("textContent"));
			note.setTextState(rs.getString("textState"));
			note.setTextDate(rs.getString("textDate"));
			note.setBookIndex(rs.getInt("bookIndex"));
			return note;
		}
	}
	
	class NoteBookMapper implements RowMapper<NoteBook> {

		@Override
		public NoteBook mapRow(ResultSet rs, int rowNum) throws SQLException {
			NoteBook noteBook = new NoteBook();
			noteBook.setBookIndex(rs.getInt("bookIndex"));
			noteBook.setBookName(rs.getString("bookName"));
			return noteBook;
		}
		
	}

	public Vector<Note> getNotesByState(String state) {
		String query ="SELECT * FROM Memo WHERE textState = '" + state + "';";
		Vector<Note> notes = new Vector<Note>();
		List<Note> noteList = jdbcTemplate.query(query, new NoteMapper());
		notes.addAll(noteList);
		return notes;
	}

	public NoteBook getBookByIndex(int bookIndex) {
		String query ="SELECT * FROM Notebook WHERE bookIndex = " + bookIndex;
		NoteBook noteBook = jdbcTemplate.queryForObject(query, new NoteBookMapper());
		return noteBook;
	}
}
