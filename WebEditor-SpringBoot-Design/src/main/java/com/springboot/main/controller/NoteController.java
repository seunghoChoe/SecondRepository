package com.springboot.main.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.main.model.Note;
import com.springboot.main.model.NoteBook;
import com.springboot.main.service.NoteService;


@Controller
public class NoteController {
	@Autowired
	private NoteService noteService;
	
	private enum EditorState {
		eDefault, eUpdate, eCreate;
	}
	
	@RequestMapping("/")
	public String index(HttpServletRequest request) throws Exception {
		return "redirect:/0";
	}
	
	@RequestMapping("/{bookIndex}")
	public ModelAndView selectBook(@PathVariable int bookIndex, ModelAndView mav, HttpServletRequest request) throws Exception {
		this.initialize(request);
		
		List<Note> notes = new Vector<Note>(); 
		if(bookIndex == 0) {
			notes = noteService.getAllNote();
			request.getSession().setAttribute("selectedBook", noteService.getBookByIndex(bookIndex));
		}else {
			notes = noteService.getNotesByBookIndex(bookIndex);
			request.getSession().setAttribute("selectedBook", noteService.getBookByIndex(bookIndex));

		}
		mav.addObject("notes", notes);
		mav.addObject("editorState", EditorState.eDefault.ordinal());
		
		Vector<Note> toDoNotes = noteService.getNotesByState("ToDo");
		mav.addObject("toDoNotes", toDoNotes);
		Vector<Note> doingNotes = noteService.getNotesByState("Doing");
		mav.addObject("doingNotes", doingNotes);
		Vector<Note> doneNotes = noteService.getNotesByState("Done");
		mav.addObject("doneNotes", doneNotes);
		
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/{bookIndex}/{textIndex}")
	public ModelAndView selectNote(@PathVariable int bookIndex, @PathVariable int textIndex,
			ModelAndView mav, HttpServletRequest request) throws Exception {
		this.initialize(request);
		List<Note> notes = noteService.getNotesByBookIndex(bookIndex);
		request.getSession().setAttribute("selectedBook", noteService.getBookByIndex(bookIndex));
		mav.addObject("notes", notes);
		
		Note note = noteService.getNoteByIndex(textIndex);
		mav.addObject("editorState", EditorState.eUpdate.ordinal());
		mav.addObject("selectedNote", note);

		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/updateNote")
	public String updateNote(@ModelAttribute("modifyingNote") Note note) throws Exception {
		noteService.updateNote(note);
		return "redirect:/" + note.getBookIndex() + "/" + note.getTextIndex();
	}
	
	@RequestMapping("/callCreater")
	public ModelAndView callCreater(ModelAndView mav, HttpServletRequest request) throws Exception {
		this.initialize(request);
		List<Note> notes = noteService.getNotesByBookIndex(((NoteBook)request.getSession().getAttribute("selectedBook")).getBookIndex());
		mav.addObject("notes", notes);
		mav.addObject("editorState", EditorState.eCreate.ordinal());
		
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("/createNewNote")
	public String createNewNote(HttpServletRequest request, Note newNote, ModelAndView mav) throws Exception {
		noteService.insertNote(newNote);
		return "redirect:/" + ((NoteBook)request.getSession().getAttribute("selectedBook")).getBookIndex();
	}

	@RequestMapping("/deleteNote")
	public String deleteNote(@RequestParam("textIndex") int textIndex, HttpServletRequest request) throws Exception {
		Note note = noteService.getNoteByIndex(textIndex);
		noteService.deleteNote(note);
		return "redirect:/"+ ((NoteBook)request.getSession().getAttribute("selectedBook")).getBookIndex();
	}
	
	@RequestMapping("/createNoteBook")
	public String createNoteBook(@RequestParam("bookName") String bookName, HttpServletRequest request) throws Exception {
		if(bookName != null) {
			noteService.insertNoteBook(bookName);
		}
		request.getSession().removeAttribute("books");
		request.getSession().setAttribute("books", noteService.getAllBooks());
		return "redirect:/" + ((NoteBook)request.getSession().getAttribute("selectedBook")).getBookIndex();
	}
	
	@RequestMapping("/deleteNoteBook")
	public String deleteNoteBook(@RequestParam("bookIndex") int bookIndex, HttpServletRequest request) throws Exception {
		noteService.deleteNoteBook(bookIndex);
		request.getSession().removeAttribute("books");
		request.getSession().setAttribute("books", noteService.getAllBooks());
		return "redirect:/";
	}
	
	@RequestMapping("/goTo")
	public String goToDoing( @RequestParam("textState") String toState, @RequestParam("noteIndex") int noteIndex) throws Exception {
		noteService.goToDoing(toState, noteIndex);
		return "redirect:/";
	}

	private void initialize(HttpServletRequest request) {
		if(request.getSession().getAttribute("books") == null) {
			request.getSession().setAttribute("books", noteService.getAllBooks());
		}
		request.getSession().setAttribute("today", LocalDate.now());
		
	}
}
