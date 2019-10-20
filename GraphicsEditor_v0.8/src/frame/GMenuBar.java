package frame;

import javax.swing.JMenuBar;

import global.GConstants.EMenu;
import menu.GFileMenu;

public class GMenuBar extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GMenuBar() {
		// component
		for(EMenu eMenu : EMenu.values()) {
			this.add(eMenu.getMenu());
		}
	}
	
	public void initialize(GDrawingPanel drawingPanel) {
		for(EMenu eMenu : EMenu.values()) {
			eMenu.getMenu().initialize(drawingPanel);
		}
	}

	public GFileMenu getFileMenu() {
		return (GFileMenu) this.getMenu(EMenu.eFileMenu.ordinal());
	}

	

}
