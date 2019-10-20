package frame;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import frame.GDrawingPanel.EKeyState;
import global.GConstants;

public class GMainFrame extends JFrame {
	// attributes 
	private static final long serialVersionUID = 1L;
	// component 
	private GMenuBar menuBar;
	private GToolBar toolBar;
	private GDrawingPanel drawingPanel;

	public GMainFrame() {
		WindowHandler windowHandler = new WindowHandler();
		KeyHandler keyHandler = new KeyHandler();
		
		this.addWindowListener(windowHandler);
		this.addKeyListener(keyHandler);
		// attributes
		this.setLocation(GConstants.MAINFRAME_X, GConstants.MAINFRAME_Y);
		this.setSize(GConstants.MAINFRAME_W, GConstants.MAINFRAME_H);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		// components
		this.menuBar = new GMenuBar();
		this.setJMenuBar(this.menuBar);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.add(panel, BorderLayout.NORTH);
		
		this.toolBar = new GToolBar();
		panel.add(this.toolBar);
		
		this.drawingPanel = new GDrawingPanel();
		this.add(this.drawingPanel, BorderLayout.CENTER);
	}

	public void initialize() {
		// association
		
		// initialize
		this.menuBar.initialize(this.drawingPanel);
		this.toolBar.initialize(this.drawingPanel);
		this.drawingPanel.initialize();		
	}
	
	private class KeyHandler implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.isControlDown() && !(e.isShiftDown())) {
				drawingPanel.setKeyState(EKeyState.eKeyCtrl);
				switch (e.getKeyCode()) {
				case KeyEvent.VK_N:
					menuBar.getFileMenu().nnew();
					break;
				case KeyEvent.VK_S:
					menuBar.getFileMenu().save();
					break;
				case KeyEvent.VK_Q:
					menuBar.getFileMenu().exit();
					break;
				case KeyEvent.VK_V:
					drawingPanel.paste();
					break;
				case KeyEvent.VK_Z:
					drawingPanel.undo();
					break;
				}
				if(drawingPanel.getSelectedShape() != null) {
					switch (e.getKeyCode()) {
					case KeyEvent.VK_C:
						drawingPanel.copy();
						break;
					case KeyEvent.VK_X:
						drawingPanel.cut();
						break;
					case KeyEvent.VK_D:
						drawingPanel.delete();
						break;
					case KeyEvent.VK_G:
						drawingPanel.group();
						break;
					}
				}
			}else if(e.isControlDown() && e.isShiftDown()) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_S:
					menuBar.getFileMenu().saveAs();
					break;
				case KeyEvent.VK_G:
					drawingPanel.unGroup();
					break;
				case KeyEvent.VK_Z:
					drawingPanel.ddo();
					break;
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if(!(e.isControlDown())) {
				drawingPanel.setKeyState(EKeyState.eIdle);	
			}
		}
		
	}
	
	private class WindowHandler implements WindowListener{

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			menuBar.getFileMenu().exit();
		}

		@Override
		public void windowClosed(WindowEvent e) {
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
