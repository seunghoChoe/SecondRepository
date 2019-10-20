package menu;
import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JMenuItem;

import global.GConstants.EColorMenu;


public class GColorMenu extends GMenu {
	private static final long serialVersionUID = 1L;

	public GColorMenu(String name) {
		super(name);
		
		for(EColorMenu eColorMenu : EColorMenu.values()) {
			JMenuItem menuItem = eColorMenu.getMenuItem();
			menuItem.setActionCommand(eColorMenu.getActionCommand());
			menuItem.addActionListener(actionHandler);
			this.add(menuItem);
		}
	}

	public void line() {
		Color color = JColorChooser.showDialog(this.getDrawingPanel(), "Line Color", this.getForeground());
		this.getDrawingPanel().setLineColor(color);
	}
	
	public void fill() {
		Color color = JColorChooser.showDialog(this.getDrawingPanel(), "Fill Color", this.getForeground());
		this.getDrawingPanel().setFillColor(color);

	}
}
