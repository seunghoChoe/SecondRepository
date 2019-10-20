package shape;

import java.awt.geom.Line2D;

public class GLine extends GShape {
	private static final long serialVersionUID = 1L;
	// attributes
	private Line2D line;
	private int px, py;	
	
	public GLine() {
		super(new Line2D.Double(0, 0 , 0, 0));
		this.line = (Line2D.Double)this.getShape();
	}
	
	public void setLocation(int x, int y) {
		this.line.setLine(x, y, x, y);
	}
	public void saveCurrentPosition(int x, int y) {
		this.px = x;
		this.py = y;
	}
	public void move(int newX, int newY) {
		int dx = newX - px;
		int dy = newY - py;		
		double x1 = this.line.getX1() + dx;
		double y1 = this.line.getY1() + dy;
		double x2 = this.line.getX2() + dx;
		double y2 = this.line.getY2() + dy;
		this.line.setLine(x1, y1, x2, y2);
		this.px = newX;
		this.py = newY;
	}
	
	
	public void resize(int newX, int newY) {
//		double w = newX - this.line.getX1();
//		double h = newY - this.line.getY1();
		this.line.setLine(this.line.getX1(), this.line.getY1(), newX, newY);
	}

	@Override
	public GShape newInstance() {
		return new GLine();
	}
}
