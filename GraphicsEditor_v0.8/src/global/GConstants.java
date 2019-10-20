package global;

import java.awt.Cursor;

import javax.swing.JMenuItem;

import menu.GColorMenu;
import menu.GEditMenu;
import menu.GFileMenu;
import menu.GMenu;
import shape.GEllipse;
import shape.GLine;
import shape.GRectangle;
import shape.GShape;
import shape.GPolygon;
import shape.GSelect;

public class GConstants {
	public final static int MAINFRAME_X = 200;
	public final static int MAINFRAME_Y = 100;
	public final static int MAINFRAME_W = 600;
	public final static int MAINFRAME_H = 600;

	public enum EWeek {
		mon, tue, wed, thr, fri, sat, sun;
	}
	
	public enum EMenu {
		eFileMenu(new GFileMenu("File")),
		eEditMenu(new GEditMenu("Edit")),
		eColorMenu(new GColorMenu("Color"));
		
		
		private GMenu menu;
		private EMenu(GMenu menu){
			this.menu = menu;
		}
		public GMenu getMenu() {
			return this.menu;
		}
	}
	
	public enum EFileMenu {
		eNew(new JMenuItem("new"), "nnew"),
		eOpen(new JMenuItem("open"), "open"),
		eSave(new JMenuItem("save"), "save"),
		eSaveAs(new JMenuItem("saveas"), "saveAs"),
		eClose(new JMenuItem("close"), "close"),
		ePrint(new JMenuItem("print"), "print"),
		eExit(new JMenuItem("exit"), "exit");

		private JMenuItem menuItem;
		private String actionCommand;
		
		private EFileMenu(JMenuItem menuItem, String actionCommand){
			this.menuItem = menuItem;
			this.actionCommand = actionCommand;
		}
		public JMenuItem getMenuItem() {
			return this.menuItem;
		}
		public String getActionCommand() {
			return this.actionCommand;
		}
	}
	
	public enum EEditMenu {
		eGroup(new JMenuItem("group"), "group"),
		eUnGroup(new JMenuItem("unGroup"), "unGroup"),
		eCut(new JMenuItem("cut"), "cut"),
		eCopy(new JMenuItem("copy"), "copy"),
		ePaste(new JMenuItem("paste"), "paste"),
		eDelete(new JMenuItem("delete"), "delete"),
		eDo(new JMenuItem("do"), "ddo"),
		eUndo(new JMenuItem("undo"), "undo");

		private JMenuItem menuItem;
		private String actionCommand;
		
		private EEditMenu(JMenuItem menuItem, String actionCommand){
			this.menuItem = menuItem;
			this.actionCommand = actionCommand;
		}
		public JMenuItem getMenuItem() {
			return this.menuItem;
		}
		public String getActionCommand() {
			return this.actionCommand;
		}
	}
	
	public enum EColorMenu {
		eLine(new JMenuItem("line"), "line"),
		eFill(new JMenuItem("fill"), "fill");

		private JMenuItem menuItem;
		private String actionCommand;
		
		private EColorMenu(JMenuItem menuItem, String actionCommand){
			this.menuItem = menuItem;
			this.actionCommand = actionCommand;
		}
		public JMenuItem getMenuItem() {
			return this.menuItem;
		}
		public String getActionCommand() {
			return this.actionCommand;
		}
	}
	public enum EToolBar {
		eSelect(new GSelect(), "resource/select.gif","resource/selectSLT.gif"),
		eRectangle(new GRectangle(), "resource/rectangle.gif","resource/rectangleSLT.gif"),
		eEllipse(new GEllipse(), "resource/ellipse.gif","resource/ellipseSLT.gif"),
		eLine(new GLine(), "resource/line.gif","resource/lineSLT.gif"),
		ePolygon(new GPolygon(), "resource/polygon.gif","resource/polygonSLT.gif");

		private GShape shape;
		private String iconFileName;
		private String iconSLTFileName;
		
		private EToolBar(GShape shape, String iconFileName, String iconSLTFileName){
			this.shape = shape;
			this.iconFileName = iconFileName;
			this.iconSLTFileName = iconSLTFileName;
		}
		public GShape getSelectedTool() {
			return this.shape;
		}
		public String getIconFileName() {
			return this.iconFileName;
		}
		public String getIconSLTFileName() {
			return this.iconSLTFileName;
		}
	}
	
	public enum EAnchors {
		N(Cursor.N_RESIZE_CURSOR),
		S(Cursor.S_RESIZE_CURSOR),
		E(Cursor.E_RESIZE_CURSOR),
		W(Cursor.W_RESIZE_CURSOR),
		NE(Cursor.NE_RESIZE_CURSOR),
		NW(Cursor.NW_RESIZE_CURSOR),
		SE(Cursor.SE_RESIZE_CURSOR),
		SW(Cursor.SW_RESIZE_CURSOR),
		R(Cursor.HAND_CURSOR);
		
		private int cursorType;
		private EAnchors(int cursorType) {
			this.cursorType = cursorType;
		}
		public int getCursorType() {
			return this.cursorType;
		}
	}
}
