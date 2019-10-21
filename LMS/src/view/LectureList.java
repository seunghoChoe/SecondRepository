package view;

import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import control.LectureManager;
import entity.Lecture;
import view.DirectoryPanel.ListSelectionHandler;

public class LectureList extends JTable {
	private static final long serialVersionUID = 1L;
	// controller
	private LectureManager lectureManager;
	// data model
	private TableModel tableModel;
	// entity data
	private Vector<Lecture> lectures;
	// selected lectures
	private Vector<Lecture> selectedLectures;
	
	
	
	public TableModel getTableModel() {return tableModel;}
	public void setTableModel(TableModel tableModel) {	this.tableModel = tableModel;}

	public Vector<Lecture> getSelectedLectures() {
		this.selectedLectures = new Vector<Lecture>();
		for (int i=0; i<this.getRowCount(); i++) {
			if (this.isRowSelected(i)) {
				this.selectedLectures.addElement(this.lectures.get(i));
			}
		}
		return this.selectedLectures; 
	}
	
	public LectureList(ListSelectionHandler listSelectionHandler) {
		// controller
		this.lectureManager = new LectureManager();
		// data model
		Vector<String> header = new Vector<String>();
		header.addElement("강좌명");
		header.addElement("담당교수");
		this.tableModel = new TableModel(header, 0);
		this.setModel(this.tableModel);
		// add listener
		this.lectures = new Vector<Lecture>();
	}
	
	public void initialize(){
	}
	
	public String getSelectedFileName() {
		return null;
	}
	public void showLectures(Vector<Lecture> lectures) {
		Vector<String> rowData = null;
		Boolean duplication = false;
		for (Lecture lecture: lectures) {
			duplication = false;
			if(this.lectures.size() == 0) {
				this.lectures.addElement(lecture);
			}
			else if(this.lectures.size() != 0) {
				for(Lecture aheadlecture : this.lectures) {
					if(aheadlecture.getId() == lecture.getId()) {
						duplication = true;
					}
				}
				if(!duplication) {
					this.lectures.addElement(lecture);
				}
			}
		}
		this.tableModel.setRowCount(0);
		for (Lecture lecture: this.lectures) {
			rowData = new Vector<String>();
			rowData.addElement(lecture.getName());
			rowData.addElement(lecture.getProfessorName());
			this.tableModel.addRow(rowData);
		}
		this.updateUI();	
	}

	public void showLectures(String fileName) throws FileNotFoundException {
		this.tableModel.setRowCount(0);
		if (fileName == null) {
			return;
		}
		this.lectures = this.lectureManager.getLectures(fileName);
		Vector<String> rowData = null;
		for (Lecture lecture: lectures) {
			rowData = new Vector<String>();
			rowData.addElement(lecture.getName());
			rowData.addElement(lecture.getProfessorName());
			this.tableModel.addRow(rowData);
		}
		this.updateUI();	
	}

	public void saveLectures(String fileName) throws FileNotFoundException {
		this.lectureManager.saveLectures(fileName, this.lectures);
	}

	public Vector<Lecture> removeLectures() {
		Vector<String> rowData = null;
		Vector<Lecture> movingLectures = new Vector<Lecture>();
		for(Lecture lecture : this.getSelectedLectures()) {
			movingLectures.addElement(lecture);
			this.lectures.removeElement(lecture);
		}
		this.tableModel.setRowCount(0);
		for (Lecture lecture: this.lectures) {
			rowData = new Vector<String>();
			rowData.addElement(lecture.getName());
			rowData.addElement(lecture.getProfessorName());
			this.tableModel.addRow(rowData);
		}
		
		return movingLectures;
	}
	
	class TableModel extends DefaultTableModel {
		private static final long serialVersionUID = 1L;
		public TableModel(Vector<String> header, int row) {
			this.setColumnIdentifiers(header);
			this.setRowCount(row);
		}
		
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}
}
