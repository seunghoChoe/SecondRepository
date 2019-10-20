package shape;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Vector;

import global.GConstants.EAnchors;


public class GSelectContainer extends GGroup {
	private static final long serialVersionUID = 1L;

	private GShape selectedShape;
	
	public Vector<GShape> getShapeVector() {return this.shapeVector;}
	public void setShapeVector(Vector<GShape> containedShape) {this.shapeVector = containedShape;}
	
	
	public boolean isInShape(GShape shape) {
		for(GShape gShape : this.shapeVector) {
			if(gShape.equals(shape)) return true;
		}
		return false;
	}
	
	public void transformShape(AffineTransform affineTransform, Point2D resizeOrigin, Point2D resizeFactor) {
		EAnchors eAnchor = EAnchors.values()[this.selectedShape.anchors.getSelectedAnchor().ordinal()];
		
		for(GShape shape : this.shapeVector) {
			shape.setShape(affineTransform.createTransformedShape(shape.getShape()));
		}
		this.setShape(affineTransform.createTransformedShape(this.getShape()));
	}
	
	public GSelectContainer() {
		this.shapeVector = new Vector<GShape>();
	}
	
	public void draw(Graphics2D g2D) {
		for(GShape gShape : shapeVector) {
			gShape.draw(g2D);
		}
	}
	
	public void drawAnchors(Graphics2D g2D) {
		for(GShape gShape : shapeVector) {
			gShape.drawAnchors(g2D);
		}
	}
	
	@Override
	public GShape newInstance() {
		return new GSelectContainer();
	}
	public void add(GShape selectedShape) {
		this.shapeVector.addElement(selectedShape);
	}
	
	public boolean contains(int x, int y) {
		for(GShape shape : this.shapeVector) {
			if(shape.contains(x, y)) {
				this.selectedShape = shape;
				return true;
			}else if(shape.anchors.contains(x, y)) {
				return true;
			}
		}
		return false;
	}
	
	public EAnchors getSelectedAnchor() {
		if(this.selectedShape != null) return this.selectedShape.anchors.getSelectedAnchor();
		else return null;
	}
	public Point2D getAnchors(EAnchors eAnchor) {return this.selectedShape.anchors.getAnchor(eAnchor);}
	public double getWidth() {return this.selectedShape.shape.getBounds2D().getWidth();	}
	public double getHeight() {return this.selectedShape.shape.getBounds2D().getHeight();}

	
}
