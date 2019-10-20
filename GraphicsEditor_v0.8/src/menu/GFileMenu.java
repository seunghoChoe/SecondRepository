package menu;

import java.awt.print.PrinterJob;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import frame.GDrawingPanel;
import global.GConstants.EFileMenu;

public class GFileMenu extends GMenu {
	private static final long serialVersionUID = 1L;

	private File file;
	private File dir;
	// association

	public void initialize(GDrawingPanel drawingPanel) {
		super.initialize(drawingPanel);
		this.file = null;
		this.dir = null;
		this.dir = new File(
				"/Users/choeseungho/eclipse-workspace-18-1/PatternProgramming/GraphicsEditor_v0.842_Hotkey");
	}
 
	public GFileMenu(String name) {
		super(name);
		
		for(EFileMenu eFileMenu : EFileMenu.values()) {
			JMenuItem menuItem = eFileMenu.getMenuItem();
			menuItem.setActionCommand(eFileMenu.getActionCommand());
			menuItem.addActionListener(actionHandler);
			this.add(menuItem);
		}
	}

	private void readObject() {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(this.file)));
			Object object = objectInputStream.readObject();
			this.getDrawingPanel().setShapeVector(object);
			objectInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void writeObject() {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(file)));
			objectOutputStream.writeObject(getDrawingPanel().getShapeVector());
			objectOutputStream.close();
			this.getDrawingPanel().setUpdated(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private boolean checkSave() {
		boolean cancel = false;
		int reply = JOptionPane.NO_OPTION;
		if (this.getDrawingPanel().isUpdated()) {
			reply = JOptionPane.showConfirmDialog(this.getDrawingPanel(), "Do You Save Changed Contents?");
			if (reply == JOptionPane.CANCEL_OPTION) {
				cancel = true;
			}
		}

		if (!cancel) {
			if (reply == JOptionPane.OK_OPTION) {
				this.save();
			}
		}
		return cancel;
	}
	
	public void nnew() {
		boolean cancel = this.checkSave();
		if(!cancel) {
			this.getDrawingPanel().setShapeVector(null);
		}
	}

	public void open() {
		boolean cancel = this.checkSave();
		if(!cancel) {
			JFileChooser chooser = new JFileChooser(this.dir);
			chooser.setSelectedFile(file);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Data", "gvs");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(this.getDrawingPanel());
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
				this.dir = chooser.getCurrentDirectory();
				this.file = chooser.getSelectedFile();
				this.readObject();
			}
		}
	}

	public void save() {
		if(this.file == null) {
			this.saveAs();
		}else {
			this.writeObject();
		}
	}

	public void saveAs() {
		JFileChooser chooser = new JFileChooser(this.dir);
		chooser.setSelectedFile(file);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Data", "gvs");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(this.getDrawingPanel());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
			this.dir = chooser.getCurrentDirectory();
			this.file = chooser.getSelectedFile();
			String fileName = this.file.getName();
			char[] charArray = new char[4];
			this.file.getName().getChars(fileName.length()-4, fileName.length(), charArray, 0);
			System.out.println(charArray);
//			if(this.file.getName().getChars(0, 5, char[4], 0)) {
//				
//			}
			this.writeObject();
		}
	}

	public void close() {
		boolean cancel = this.checkSave();
		if(!cancel) {
			this.getDrawingPanel().setShapeVector(null);
		}
	}

	public void print() {
		PrinterJob printerjob = PrinterJob.getPrinterJob();
		printerjob.printDialog();
		
	}

	public void exit() {
		boolean cancel = this.checkSave();
		if(!cancel) {
			System.exit(0);
		}
	}

	
}
