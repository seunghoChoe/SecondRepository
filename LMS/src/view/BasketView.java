package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import entity.Lecture;
import view.DirectoryPanel.MouseHandler;

public class BasketView extends JPanel {
	private static final long serialVersionUID = 1L;
	// sub-components
	private JButton btMoveToRight, btMoveToLeft;
	private JButton btRemoveBasket, btRemoveSincheong;
	LectureList basketList;
	LectureList sincheongList;
	
	
	
	public LectureList getBasketList() {return basketList;}
	public void setBasketList(LectureList basketList) {this.basketList = basketList;}
	public LectureList getSincheongList() {return sincheongList;}
	public void setSincheongList(LectureList sincheongList) {	this.sincheongList = sincheongList;}

	public BasketView(JPanel parentPanel, MouseHandler mousehandler) {
		ActionHandler actionhandler = new ActionHandler();
		FocusHandler focusHandler= new FocusHandler();
		TableModelHandler tableModelhandler = new TableModelHandler();
		// attributes
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(Box.createHorizontalStrut(10));
			JPanel leftPanel = new JPanel();
			leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
				leftPanel.add(Box.createVerticalStrut(10));
				this.basketList = new LectureList(null);
				this.basketList.getTableModel().addTableModelListener(tableModelhandler);
				this.basketList.addFocusListener(focusHandler);
				this.basketList.addMouseListener(mousehandler);
				JScrollPane basketListScrollPane = new JScrollPane();
				basketListScrollPane.setViewportView(basketList);
				leftPanel.add(basketListScrollPane);
				leftPanel.add(Box.createVerticalStrut(10));
				this.btRemoveBasket = new JButton("삭제");
				this.btRemoveBasket.setEnabled(false);
				this.btRemoveBasket.addActionListener(actionhandler);
				btRemoveBasket.setAlignmentX(Component.CENTER_ALIGNMENT);
				leftPanel.add(btRemoveBasket);				
				leftPanel.add(Box.createVerticalStrut(10));
				this.add(leftPanel);
			
				this.add(Box.createHorizontalStrut(10));
			JPanel centerPanel = new JPanel();
			centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
				this.btMoveToRight = new JButton(">>");
				this.btMoveToRight.setEnabled(false);
				this.btMoveToRight.addActionListener(actionhandler);
				centerPanel.add(btMoveToRight);
				this.btMoveToLeft = new JButton("<<");
				this.btMoveToLeft.setEnabled(false);
				this.btMoveToLeft.addActionListener(actionhandler);

				centerPanel.add(btMoveToLeft);
				this.add(centerPanel);
				this.add(Box.createHorizontalStrut(10));
			JPanel rightPanel = new JPanel();
			rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
				rightPanel.add(Box.createVerticalStrut(10));
				this.sincheongList = new LectureList(null);
				this.sincheongList.getTableModel().addTableModelListener(tableModelhandler);
				this.sincheongList.addFocusListener(focusHandler);
				this.sincheongList.addMouseListener(mousehandler);
				JScrollPane sincheongListScrollPane = new JScrollPane();
				sincheongListScrollPane.setViewportView(sincheongList);
				rightPanel.add(sincheongListScrollPane);
				rightPanel.add(Box.createVerticalStrut(10));
				this.btRemoveSincheong = new JButton("삭제");
				this.btRemoveSincheong.setEnabled(false);
				this.btRemoveSincheong.addActionListener(actionhandler);
				btRemoveSincheong.setAlignmentX(Component.CENTER_ALIGNMENT);
				rightPanel.add(btRemoveSincheong);				
				rightPanel.add(Box.createVerticalStrut(10));
				this.add(rightPanel);
				this.add(Box.createHorizontalStrut(10));
		// add sub-components
		
	
	} 

	public void addLectures(Vector<Lecture> selectedLectures) {
		this.basketList.showLectures(selectedLectures);
		
	}

	public void saveRegistrations(String basketFileName, String sincheongFileName) throws FileNotFoundException {
		this.basketList.saveLectures(basketFileName);		
		this.sincheongList.saveLectures(sincheongFileName);		
	}
	
	public void moveToRight() {
		Vector<Lecture> movingLectures = basketList.removeLectures();
		sincheongList.showLectures(movingLectures);
		this.grabFocus();
	}

	public void moveToLeft() {
		Vector<Lecture> movingLectures =  sincheongList.removeLectures();
		basketList.showLectures(movingLectures);
		this.grabFocus();

	}

	public class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource().equals(btMoveToRight)) {
				moveToRight();
			}
			else if (event.getSource().equals(btMoveToLeft)) {
				moveToLeft();
			}
			else if (event.getSource().equals(btRemoveSincheong)) {
				sincheongList.removeLectures();
			}
			else if (event.getSource().equals(btRemoveBasket)) {
				basketList.removeLectures();
			}
		}
	}
	
	public class TableModelHandler implements TableModelListener {

		@Override
		public void tableChanged(TableModelEvent e) {
			if(e.getSource().equals(basketList.getTableModel())) {
				System.out.println("tablechanged");
			boolean isselection = false;
			for (int i=0; i<basketList.getRowCount(); i++) {
				if (basketList.isRowSelected(i)) {
					isselection = true;
				}
			}
				if(isselection) {
					btRemoveBasket.setEnabled(true);
					btMoveToRight.setEnabled(true);
				}
				else if(!isselection ) {
					btRemoveBasket.setEnabled(false);
					btMoveToRight.setEnabled(false);
				}
			}
			else if(e.getSource().equals(sincheongList.getTableModel())) {
				System.out.println("tablechanged");
			boolean isselection = false;
			for (int i=0; i<sincheongList.getRowCount(); i++) {
				if (sincheongList.isRowSelected(i)) {
					isselection = true;
				}
			}
				if(isselection) {
					btRemoveSincheong.setEnabled(true);
					btMoveToLeft.setEnabled(true);
				}
				else if(!isselection ) {
					btRemoveSincheong.setEnabled(false);
					btMoveToLeft.setEnabled(false);
				}
			}
			
			
//			if(basketList.getRowCount() == 0) {
//				btRemoveBasket.setEnabled(false);
//				btMoveToRight.setEnabled(false);
//
//			}else if(basketList.getRowCount() != 0) {
//				btRemoveBasket.setEnabled(true);
//				btMoveToRight.setEnabled(true);
//
//			}
//			if(sincheongList.getRowCount() == 0) {
//				btRemoveSincheong.setEnabled(false);
//				btMoveToLeft.setEnabled(false);
//			}else if(sincheongList.getRowCount() != 0) {
//				btRemoveSincheong.setEnabled(true);
//				btMoveToLeft.setEnabled(true);
//			}
			
		}

	}
	
	public class FocusHandler implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			if(e.getSource().equals(basketList)) {
				System.out.println("focusinbasket");

				boolean isselection = false;
				for (int i=0; i<basketList.getRowCount(); i++) {
					if (basketList.isRowSelected(i)) {
						isselection = true;
					}
				}
					if(isselection) {
						btRemoveBasket.setEnabled(true);
						btMoveToRight.setEnabled(true);
					}
					else if(!isselection ) {
						btRemoveBasket.setEnabled(false);
						btMoveToRight.setEnabled(false);
				}
			}
			else if(e.getSource().equals(sincheongList)) {
				System.out.println("focusinsincheong");

				boolean isselection = false;
				for (int i=0; i<sincheongList.getRowCount(); i++) {
					if (sincheongList.isRowSelected(i)) {
						isselection = true;
					}
				}
					if(isselection) {
						btRemoveSincheong.setEnabled(true);
						btMoveToLeft.setEnabled(true);
					}
					else if(!isselection ) {
						btRemoveSincheong.setEnabled(false);
						btMoveToLeft.setEnabled(false);
				}
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
//			if(e.getSource().equals(basketList)) {
//				System.out.println("focusout");
//
//				boolean isselection = false;
//				for (int i=0; i<basketList.getRowCount(); i++) {
//					if (basketList.isRowSelected(i)) {
//						isselection = true;
//					}
//				}
//				System.out.println(isselection);
//
//					if(isselection) {
//						btRemoveBasket.setEnabled(true);
//						btMoveToRight.setEnabled(true);
//					}
//					else if(!isselection ) {
//						btRemoveBasket.setEnabled(false);
//						btMoveToRight.setEnabled(false);
//				}
//			}
		}
	}
}
