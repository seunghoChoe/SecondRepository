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
	private static final String MAPPER_NAME_SPACE = "mapper.noteMapper.";
	
	/*
	 * Note 관련 query
	 */
	
	public List<Note> getAllNote() {
		List<Note> noteList = sqlSession.selectList(MAPPER_NAME_SPACE + "selectAllMemo");
		return noteList;
	}
	
	/*
	 *  NoteBook 관련 query 
	 */
	
	public Note getNoteByIndex(int textIndex) {
		String query ="SELECT * FROM Memo WHERE textIndex = " + textIndex + ";";
		return jdbcTemplate.queryForObject(query, new NoteMapper());
	}

	public Vector<Note> getNotesByBookIndex(int bookIndex) {
		String query ="SELECT * FROM Memo WHERE bookIndex = " + bookIndex + ";";
		Vector<Note> notes = new Vector<Note>();
		List<Note> noteList = jdbcTemplate.query(query, new NoteMapper());
		notes.addAll(noteList);
		return notes;
	}

	public void insertNote(Note note) {
		String query ="INSERT INTO Memo(textDate, textTitle, textContent, textState, bookIndex)"
				+ " VALUES('" + note.getTextDate() + "', '" + note.getTextTitle() + "',"
						+ " '" + note.getTextContent() +"', 'ToDo', " + note.getBookIndex() + ");";
		this.jdbcTemplate.execute(query);
	}
	
	
	
	/*
	 *  NoteBook 관련 query 
	 */

	public void updateNote(Note note) {
		String query ="UPDATE Memo"
				+ " SET textTitle = '" + note.getTextTitle() + "', "
					+ " textContent = '" + note.getTextContent() + "', "
					+ " bookIndex = '" + note.getBookIndex() + "' "
				+ "WHERE textIndex = " + note.getTextIndex() + ";";
		System.out.println(query);
		System.out.println(this.jdbcTemplate.update(query));
	}

	public void deleteNote(Note note) {
		String query ="DELETE FROM Memo"
				+ " WHERE"
				+ " textIndex = " + note.getTextIndex() + " AND"
				+ " textTitle = '" + note.getTextTitle() + "';";
		this.jdbcTemplate.execute(query);
	}

	public Vector<NoteBook> getAllBooks() {
		String query ="SELECT * FROM Notebook";
		Vector<NoteBook> books = new Vector<NoteBook>();
		List<NoteBook> bookList = jdbcTemplate.query(query, new NoteBookMapper());
		books.addAll(bookList);
		return books;
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
