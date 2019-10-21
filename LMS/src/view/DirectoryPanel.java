package view;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import entity.Lecture;
import global.Constants;

public class DirectoryPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private ListSelectionHandler listSelectionHandler;
	private MouseHandler mouseHandler;
	
	private DirectoryList campusList;
	private DirectoryList collegeList;
	private DirectoryList departmentList;
	private LectureList lectureList;
	private BasketView basketView;
	
	
	
	public BasketView getBasketView() {return basketView;}
	public void setBasketView(BasketView basketView) {this.basketView = basketView;}

	public LectureList getLectureList() {return this.lectureList;}

	public DirectoryPanel() {
		// listeners
		this.listSelectionHandler = new ListSelectionHandler();
		this.mouseHandler = new MouseHandler();

		// layout
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// upper panel
		JPanel upperPanel = new JPanel();
		this.add(upperPanel);
		upperPanel.setLayout(new GridLayout(1,3));
		// components
		JScrollPane scrollPane = null;
		this.campusList = new DirectoryList(this.listSelectionHandler);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(this.campusList);
		upperPanel.add(scrollPane);

		this.collegeList = new DirectoryList(this.listSelectionHandler);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(this.collegeList);
		upperPanel.add(scrollPane);
		
		this.departmentList = new DirectoryList(this.listSelectionHandler);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(this.departmentList);
		upperPanel.add(scrollPane);
		
		// lower panel
		this.lectureList = new LectureList(this.listSelectionHandler);
		this.lectureList.addMouseListener(mouseHandler);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(this.lectureList);
		this.add(scrollPane);
		
		this.basketView = new BasketView(this, this.mouseHandler);
		this.add(basketView);
		
		
		
	}
	public void initialize() {
		this.showDirectories(null);
	}
	private void showDirectories(Object source) {
		try {
			String fileName = null;
			if (source == null) {
				this.campusList.showDirectories(Constants.FILENAME_ROOT);
			} else if (source.equals(this.campusList)) {
				fileName = this.campusList.getSelectedFileName();
				this.collegeList.showDirectories(fileName);
				fileName = this.collegeList.getSelectedFileName();
				this.departmentList.showDirectories(fileName);
				fileName = this.departmentList.getSelectedFileName();
				this.lectureList.showLectures(fileName);
			} else if (source.equals(this.collegeList)) {
				fileName = this.collegeList.getSelectedFileName();
				this.departmentList.showDirectories(fileName);			
				fileName = this.departmentList.getSelectedFileName();
				this.lectureList.showLectures(fileName);
			} else if (source.equals(this.departmentList)) {
				fileName = this.departmentList.getSelectedFileName();				
				fileName = this.departmentList.getSelectedFileName();
				this.lectureList.showLectures(fileName);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}		
	public void showRegistrations() {
		Vector<Lecture> selectedLectures = this.lectureList.getSelectedLectures();
		basketView.addLectures(selectedLectures);
		basketView.setVisible(true);
	}
	public void saveRegistrations() {
		try {
			this.basketView.saveRegistrations("basket", "sincheong");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public class ListSelectionHandler implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent event) {
			showDirectories(event.getSource());
		}
	}
	
	public class MouseHandler implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2) {
				if(e.getSource() == lectureList) {
					System.out.println("dd");
					Vector<Lecture> lectures = lectureList.getSelectedLectures();
					basketView.getBasketList().showLectures(lectures);
				}else if(e.getSource() == basketView.getBasketList()) {
					basketView.moveToRight();
				}else if(e.getSource() == basketView.getSincheongList()) {
					basketView.moveToLeft();
				}
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {	}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {	}
		@Override
		public void mouseExited(MouseEvent e) {	}
	}
}
