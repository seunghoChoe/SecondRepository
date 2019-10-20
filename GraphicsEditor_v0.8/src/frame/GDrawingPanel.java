package frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import global.GConstants.EAnchors;
import global.GConstants.EToolBar;
import shape.GGroup;
import shape.GPolygon;
import shape.GSelect;
import shape.GSelectContainer;
import shape.GShape;
import transformer.GDrawer;
import transformer.GMover;
import transformer.GResizer;
import transformer.GRotater;
import transformer.GTransformer;

public class GDrawingPanel extends JPanel {
	// declarations
	private enum EDrawingState {eIdle, eTPTransforming, eNPTransforming};	
	public enum EKeyState {eIdle, eKeyCtrl};	

	// attributes
	private static final long serialVersionUID = 1L;	
	
	// components
	private Stack<Vector<GShape>> undoShapeStack, ddoShapeStack;
	private Vector<GShape> shapeVector;
	// working variables
	private EDrawingState eDrawingState;
	private EKeyState eKeyState;
	private GShape selectedShape;
	private GSelectContainer selectedContainer;
	private GShape savedShape;
	private GShape selectedTool;
	private GTransformer transformer;
	private boolean updated;
	private Graphics2D g2D;
	
	public void setLineColor(Color color) {
		if(this.selectedShape != null) {
			this.saveShapeStack();
			this.selectedShape.setLineColor(color);
			this.paint(g2D);
		}
		
	}

	public void setFillColor(Color color) {
		if(this.selectedShape != null) {
			this.saveShapeStack();
			this.selectedShape.setFillColor(color);
			this.paint(g2D);
		}
	}
	public GShape getSelectedShape() {return this.selectedShape;}
	public Object getShapeVector() {
		return this.shapeVector;
	}

	@SuppressWarnings("unchecked")
	public void setShapeVector(Object shapeVector) {
		if(shapeVector == null) {
			this.shapeVector.clear();
		}else {
			this.shapeVector = (Vector<GShape>) shapeVector;
		}
		this.initialize();
	}

	public void setActionCommand(GShape selectedTool) {
		this.selectedTool = selectedTool;
	}
	
	public void setKeyState(EKeyState eKeyState) {
		this.eKeyState = eKeyState;
	}

	public boolean isUpdated() {
		return this.updated;
	}
	public void setUpdated(boolean updated) {
		this.updated = updated;
	}
	
	public GDrawingPanel() {
		super();
		// initialize components
		MouseHandler mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		this.shapeVector = new Vector<GShape>();
		this.selectedContainer = new GSelectContainer();
		this.undoShapeStack = new Stack<Vector<GShape>>();
		this.ddoShapeStack = new Stack<Vector<GShape>>();
	}
	
	public void initialize() {
		// initialize attributes
		this.setBackground(Color.WHITE);
		this.g2D = (Graphics2D) this.getGraphics();
		// initialize working variables
		this.selectedShape = null;
		this.savedShape = null;
		this.eDrawingState = EDrawingState.eIdle;
		this.eKeyState = EKeyState.eIdle;
		this.updated = false;
		this.transformer = null;
		this.selectedTool = EToolBar.eRectangle.getSelectedTool();
		this.repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		for (GShape shape: this.shapeVector) {
			shape.draw((Graphics2D)g);
		}
		
		if(this.selectedShape != null) {
			this.selectedShape.draw(g2D);
			
			// prevent drawAnchor in keepTransforming
			if(this.eDrawingState == EDrawingState.eIdle) {
				this.selectedShape.drawAnchors(g2D);
			}
		}

	}
	private GShape onShape(int x, int y) {
		if(this.selectedContainer.contains(x, y))
				return this.selectedContainer;
		for (GShape shape: this.shapeVector) {
			if (shape.contains(x, y)) {
				return shape;
			}
		}
		return null;
	}	
	
	private void selectAction(int x, int y) {
		GShape shape = this.onShape(x, y);
		if (shape == null) {
			shape = selectedTool.newInstance();
			shape.setBackgroundColor(this.getBackground());
			this.transformer = new GDrawer(shape);
		} else {
			if(shape.getSelectedAnchor() == EAnchors.R) {
				System.out.println("new Rotater");
				this.transformer = new GRotater(shape);
			}else if(shape.getSelectedAnchor() == EAnchors.N ||
					shape.getSelectedAnchor() == EAnchors.S ||
					shape.getSelectedAnchor() == EAnchors.W ||
					shape.getSelectedAnchor() == EAnchors.E ||
					shape.getSelectedAnchor() == EAnchors.NW ||
					shape.getSelectedAnchor() == EAnchors.NE ||
					shape.getSelectedAnchor() == EAnchors.SW ||
					shape.getSelectedAnchor() == EAnchors.SE) {
				System.out.println("new Resizer");
				this.transformer = new GResizer(shape);
			}else if(shape.getSelectedAnchor() == null) {
				this.transformer = new GMover(shape);
			}
		}
		this.setSelected(shape);
		this.updated = true;
	}
	
	private void setSelected(GShape selectedShape) {
		if (this.selectedShape != null) {
			this.selectedShape.setSelected(false);
			this.selectedShape.eraseAnchor(g2D);
			this.selectedShape.draw(g2D);
		}
		this.selectedShape = selectedShape;
		this.selectedShape.setSelected(true);
	}
	
	private void initTransforming(int x, int y) {
		this.transformer.initTransforming(g2D, x, y);
	}
	
	private void keepTransforming(int x, int y) {
		this.transformer.keepTransforming(g2D, x, y);
		this.paint(g2D);
	}
	
	private void continueTransforming(int x, int y) {
		this.transformer.continueTransforming(g2D, x, y);
	}
	
	private void finishTransforming(int x, int y) {
		this.transformer.finishTransforming(g2D, x, y);
		if(this.transformer instanceof GDrawer) {
			if(this.selectedShape instanceof GSelect) {
				Vector<GShape> containedShape = new Vector<GShape>();
				for(GShape shape : shapeVector) {
					if(this.selectedShape.getShape().contains(shape.getShape().getBounds2D())) {
						containedShape.add(shape);
					}
				}
				this.selectedContainer = new GSelectContainer();
				this.selectedContainer.setShapeVector(containedShape);
				this.setSelected(this.selectedContainer);
			}else {
				this.shapeVector.add(this.selectedShape);
			}
		}
		System.out.println(this.shapeVector.size());
		this.paint(g2D);
		
	}
	

	private void changeCursor(int x, int y) {
		GShape shape = this.onShape(x, y);
		if (shape == null) {
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));				
		} else {
			EAnchors eAnchor = shape.getSelectedAnchor();
			if (eAnchor == null) {
				this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
			} else {
				this.setCursor(new Cursor(eAnchor.getCursorType()));
			}
		}
	}
	
	
	private void saveShapeStack() {
		Vector<GShape> shapeVector = new Vector<GShape>(); 
		for(GShape shape : this.shapeVector) {
			GShape cloneGShape = shape.clone();
			shapeVector.add(cloneGShape);
		}
		this.undoShapeStack.add(shapeVector);
	}

	public void makeLineThcikUp() {
		if(this.selectedShape != null) {
			this.selectedShape.makeLineThickUp();
			this.paint(g2D);
		}
	}

	public void makeLineThcikDown() {
		if(this.selectedShape != null) {
			this.selectedShape.makeLineThickDown();
			this.paint(g2D);
		}
	}

	public void cut() {
		if(this.selectedShape != null) {
			this.saveShapeStack();
			this.savedShape = this.selectedShape;
			this.delete();
		}
	}

	public void copy() {
		if(this.selectedShape != null) {
			this.savedShape = this.selectedShape.clone();
		}		
	}

	public void paste() {
		if(this.savedShape != null) {
			this.saveShapeStack();
			this.transformer = new GMover(this.savedShape);
			this.transformer.moveALittle();
			this.setSelected(this.savedShape.clone());
			this.shapeVector.addElement(this.selectedShape);
		}
		this.paint(g2D);
	}

	public void delete() {
		if(this.selectedShape != null) {
			this.saveShapeStack();
			this.shapeVector.remove(this.selectedShape);
			this.selectedShape = null;
			this.paint(g2D);
		}		
	}


	public void ddo() {
		if(!this.ddoShapeStack.empty()) {
			this.undoShapeStack.add(this.shapeVector);
			this.shapeVector = this.ddoShapeStack.pop();
		}
		for(GShape shape : this.shapeVector) {
			if(shape.isSelected())
				this.selectedShape = shape;
		}
		this.paint(g2D);
	}

	public void undo() {
		if(!this.undoShapeStack.empty()) {
			this.ddoShapeStack.add(this.shapeVector);
			this.shapeVector = this.undoShapeStack.pop();
			this.selectedShape = null;
		}
		for(GShape shape : this.shapeVector) {
			if(shape.isSelected())
				this.selectedShape = shape;
		}
		this.paint(g2D);
	}


	public void group() {
		if(!this.selectedContainer.getShapeVector().isEmpty()) {
			this.saveShapeStack();
			GGroup group = new GGroup();
			for(GShape shape : this.selectedContainer.getShapeVector()) {
				this.shapeVector.remove(shape);
			}
			group.setShapeVector(this.selectedContainer.getShapeVector());
			this.selectedContainer = new GSelectContainer();
			this.shapeVector.add(group);
			this.setSelected(group);
			this.paint(g2D);
		}
	}

	public void unGroup() {
		if(this.selectedShape instanceof GGroup) {
			this.saveShapeStack();
			for(GShape shape : ((GGroup)this.selectedShape).getShapeVector()) {
				this.shapeVector.add(shape);
			}
			this.shapeVector.remove(this.selectedShape);
			this.selectedShape = null;
			this.paint(g2D);
		}
	}


	class MouseHandler implements MouseInputListener {
		
		private void mouseL1Clicked(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdle) {
				if((selectedTool instanceof GPolygon) && (transformer instanceof GDrawer)) {
					// the reason of problem of anchor
					initTransforming(e.getX(), e.getY());
					eDrawingState = EDrawingState.eNPTransforming;
				}
			}else if (eDrawingState == EDrawingState.eNPTransforming) {
				continueTransforming(e.getX(), e.getY());
			}
		}
		private void mouseL2Clicked(MouseEvent e) {
			if (eDrawingState == EDrawingState.eNPTransforming) {
				if((selectedTool instanceof GPolygon) && (transformer instanceof GDrawer)) {
					finishTransforming(e.getX(), e.getY());
					eDrawingState = EDrawingState.eIdle;
				}
			}
		}
		private void mouseR1Clicked(MouseEvent e) {
			
		}
		private void mouseR2Clicked(MouseEvent e) {
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				if(e.getClickCount() == 1) {
					mouseL1Clicked(e);
				}else if(e.getClickCount() == 2) {
					mouseL2Clicked(e);
				}
			}
					
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdle) {
				saveShapeStack();
				selectAction(e.getX(), e.getY());
				if(!((selectedTool instanceof GPolygon) &&(transformer instanceof GDrawer)) ) {
					initTransforming(e.getX(), e.getY());
					eDrawingState = EDrawingState.eTPTransforming;
				}
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			 if (eDrawingState == EDrawingState.eTPTransforming) {
				keepTransforming(e.getX(), e.getY());
			} 		
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if (eDrawingState == EDrawingState.eTPTransforming) {
				finishTransforming(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdle;
				paint(g2D);
			}   
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdle) {
				changeCursor(e.getX(), e.getY());
			}else if (eDrawingState == EDrawingState.eNPTransforming) {
				keepTransforming(e.getX(), e.getY());
			} 	
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
	}

}

