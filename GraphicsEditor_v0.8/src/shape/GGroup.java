package shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import java.util.Vector;


public class GGroup extends GShape {
	private static final long serialVersionUID = 1L;
	// attributes
	private Rectangle2D rectangle;
	protected Vector<GShape> shapeVector;
	
	public Vector<GShape> getShapeVector() {return this.shapeVector;}
	public void setShapeVector(Vector<GShape> containedShape) {
		this.shapeVector = containedShape;
		int x = 0;
		for(GShape gShape : shapeVector) {
			if(x++ == 0) this.rectangle = gShape.getShape().getBounds2D();
			Rectangle2D.union(this.rectangle, gShape.getShape().getBounds2D(), this.rectangle);
		}
		this.setShape(this.rectangle);
	}
	public void setLineColor(Color color) {
		for(GShape gShape : shapeVector) {
			gShape.lineColor = color;
		}
	}
	public void setFillColor(Color color) {
		for(GShape gShape : shapeVector) {
			gShape.fillColor = color;
		}
	}
	
	public void makeLineThickUp() {
		for(GShape gShape : shapeVector) {
			gShape.makeLineThickUp();
		}
	}

	public void makeLineThickDown() {
		for(GShape gShape : shapeVector) {
			gShape.makeLineThickDown();
		}
	}
	
	public void transformShape(AffineTransform affineTransform) {
		for(GShape shape : this.shapeVector) {
			shape.setShape(affineTransform.createTransformedShape(shape.getShape()));
		}
		this.setShape(affineTransform.createTransformedShape(this.getShape()));
	}
	
	public GGroup() {
		super(new Rectangle2D.Double(0, 0 , 0, 0));
		this.rectangle = (Rectangle2D.Double)this.getShape();
		this.shapeVector = new Vector<GShape>();
	}
	
	public void setLocation(int x, int y) {
		this.rectangle.setFrame(x, y, 
				this.rectangle.getWidth(), this.rectangle.getHeight());
	}
	public void resize(int newX, int newY) {
		double w = newX - this.rectangle.getX();
		double h = newY - this.rectangle.getY();
		this.rectangle.setFrame(this.rectangle.getX(), this.rectangle.getY(), w, h);
	}

	public void draw(Graphics2D g2D) {
		for(GShape gShape : shapeVector) {
			gShape.draw(g2D);
		}
		Stroke savedStroke = g2D.getStroke();
		Stroke stroke = new BasicStroke(
				1.0f,
				BasicStroke.CAP_SQUARE,
				BasicStroke.JOIN_MITER,
				10.0f,
				new float[] {2.0f, 5.0f},
				0.0f
				);
		g2D.setStroke(stroke);
		g2D.draw(this.getShape());
		g2D.setStroke(savedStroke);
	}
	public GGroup clone() {
		Vector<GShape> cloneVector = new Vector<GShape>(); 
		for(GShape shape : this.shapeVector) {
			cloneVector.add(shape.clone());
		}
		GGroup cloneGroup = (GGroup)super.clone();
		cloneGroup.setShapeVector(cloneVector);
		return cloneGroup;
	}
	
	@Override
	public GShape newInstance() {
		return new GGroup();
	}

	
}
