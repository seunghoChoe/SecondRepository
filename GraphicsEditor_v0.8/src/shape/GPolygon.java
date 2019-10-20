package shape;

import java.awt.Polygon;

public class GPolygon extends GShape {
	private static final long serialVersionUID = 1L;
	// attributes
	private Polygon polygon;
	
	public GPolygon() {
		super(new Polygon());
		this.polygon = (Polygon)this.getShape();
	}
	
	public void setLocation(int x, int y) {
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);
	}
	
	public void resize(int x, int y) {
		this.polygon.xpoints[this.polygon.npoints-1] = x;
		this.polygon.ypoints[this.polygon.npoints-1] = y;
	}

	public void addPoint(int x, int y) {
		this.polygon.addPoint(x, y);
	}
	
	@Override
	public GShape newInstance() {
		return new GPolygon();
	}
}
