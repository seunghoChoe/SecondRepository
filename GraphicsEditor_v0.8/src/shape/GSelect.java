package shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

public class GSelect extends GShape {
	private static final long serialVersionUID = 1L;
	// attributes
	private Rectangle2D rectangle;

	public Vector<GShape> getShapeVector(){
		Vector<GShape> shapeVector = new Vector<GShape>();
		
		return shapeVector;
	}
	
	public GSelect() {
		super(new Rectangle2D.Double(0, 0 , 0, 0));
		this.rectangle = (Rectangle2D.Double)this.getShape();
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
		Stroke savedStroke = g2D.getStroke();
		Color savedColor = g2D.getColor();
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
		g2D.setColor(savedColor);
	}
	
	public void drawAnchors(Graphics2D g2D) {
	}

	@Override
	public GShape newInstance() {
		return new GSelect();
	}
}
