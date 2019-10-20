package shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;

import global.GConstants.EAnchors;

public abstract class GShape implements Serializable, Cloneable{
	
	// attributes
	private static final long serialVersionUID = 1L;
	protected Color lineColor, fillColor, bakcgroundColor;
	protected float lineThick;
	protected Shape shape;
	protected boolean selected;	
	// components
	protected GAnchors anchors;
	
	public GShape(Shape shape) {
		this.lineColor = Color.BLACK;
		this.fillColor = null;
		this.bakcgroundColor = null;
		this.lineThick = 1.0f;
		this.shape = shape;
		this.selected = false;		
		this.anchors = new GAnchors();
	}
	
	public abstract GShape newInstance();
	public GShape clone() {
		try {
			return (GShape) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return this;
		}
	}

	// getter & setter
	public Point2D getAnchors(EAnchors eAnchor) {return this.anchors.getAnchor(eAnchor);}
	public Shape getShape() {return this.shape;}
	public void setShape(Shape shape) {this.shape = shape;}
	public boolean isSelected() {return selected;}
	public void setSelected(boolean selected) {this.selected = selected;}	
	public void setLineColor(Color color) {this.lineColor = color;}
	public void setFillColor(Color color) {this.fillColor = color;}
	
	public void setBackgroundColor(Color backgroundColor) {
		this.bakcgroundColor = backgroundColor;
	}

	// affinetransform
	public double getCenterX() {return this.shape.getBounds2D().getCenterX();}
	public double getCenterY() {return this.shape.getBounds2D().getCenterY();}
	public void transformShape(AffineTransform affineTransform) {
		this.shape = affineTransform.createTransformedShape(this.shape);
	}
	
	// draw
	public void draw(Graphics2D g2D) {
		Color savedColor = g2D.getColor();
		Stroke savedStroke = g2D.getStroke();
		if(this.fillColor != null) {
			g2D.setColor(this.fillColor);
			g2D.fill(this.shape);
		}
		g2D.setColor(this.lineColor);
		BasicStroke stroke = new BasicStroke(this.lineThick);
		g2D.setStroke(stroke);
		g2D.draw(this.shape);
		g2D.setColor(savedColor);
		g2D.setStroke(savedStroke);
	}
	
	public void erase(Graphics2D g2D) {
		if(this.fillColor != null) {
			g2D.setColor(this.bakcgroundColor);
			g2D.fill(this.shape);
		}
		g2D.setColor(this.bakcgroundColor);
		g2D.draw(this.shape);
	}
	
	public void drawAnchors(Graphics2D g2D) {
		this.anchors.draw(g2D, this.shape.getBounds2D());
	}
	public void eraseAnchor(Graphics2D g2D) {
		Color previousColor = g2D.getColor();
		g2D.setColor(this.bakcgroundColor);
		this.anchors.erase(g2D, this.shape.getBounds2D());
		g2D.setColor(previousColor);
	}
	
	public boolean contains(int x, int y) {
		if (this.selected) {
			if (this.anchors.contains(x, y)) {
				return true;
			}
		}
		return this.shape.getBounds().contains(x, y);
	}
	
	// for GPolygon only
	public void addPoint(int x, int y) {
		
	}
	
	public EAnchors getSelectedAnchor() {
		return this.anchors.getSelectedAnchor();
	}
	
	public abstract void setLocation(int x, int y);
	public abstract void resize(int newX, int newY);
	public double getWidth() {return this.shape.getBounds2D().getWidth();	}
	public double getHeight() {return this.shape.getBounds2D().getHeight();}

	public void makeLineThickUp() {
		this.lineThick += 0.2f;
	}

	public void makeLineThickDown() {
		if(this.lineThick > 0.2) {
			this.lineThick -= 0.2f;
		}
	}

	// only for GSelectContainer
	public void transformShape(AffineTransform affineTransform, Point2D resizeOrigin, Point2D resizeFactor) {
		
	}

	
}
