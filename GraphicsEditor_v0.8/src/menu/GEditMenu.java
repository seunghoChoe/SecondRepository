package menu;
import javax.swing.JMenuItem;

import global.GConstants.EEditMenu;

public class GEditMenu extends GMenu {
	private static final long serialVersionUID = 1L;

	public GEditMenu(String name) {
		super(name);
		
		for(EEditMenu eEditMenu : EEditMenu.values()) {
			JMenuItem menuItem = eEditMenu.getMenuItem();
			menuItem.setActionCommand(eEditMenu.getActionCommand());
			menuItem.addActionListener(actionHandler);
			this.add(menuItem);
		}
	}
	
	public void group() {
		this.getDrawingPanel().group();
	}
	public void unGroup() {
		this.getDrawingPanel().unGroup();
	}
	public void cut() {
		this.getDrawingPanel().cut();
	}
	public void copy() {
		this.getDrawingPanel().copy();
		}
	public void paste() {
		this.getDrawingPanel().paste();
	}
	public void delete() {
		this.getDrawingPanel().delete();
	}
	public void ddo() {
		this.getDrawingPanel().ddo();
	}
	public void undo() {
		this.getDrawingPanel().undo();
	}
}
