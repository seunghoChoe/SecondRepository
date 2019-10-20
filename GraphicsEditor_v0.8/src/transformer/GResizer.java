package transformer;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import global.GConstants.EAnchors;
import shape.GSelectContainer;
import shape.GShape;

public class GResizer extends GTransformer{

	public GResizer(GShape selectedShape) {
		super(selectedShape);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initTransforming(Graphics2D g2d, int x, int y) {
		this.previous.setLocation(x, y);
	}

	@Override
	public void keepTransforming(Graphics2D g2D, int x, int y) {
		this.selectedShape.erase(g2D);

		AffineTransform affineTransform = new AffineTransform();
		Point2D resizeOrigin = this.getResizeOrigin();
		affineTransform.translate(resizeOrigin.getX(), resizeOrigin.getY());

		Point2D resizeFactor = this.computeResizeFactor(this.previous, new Point2D.Double(x,y));
		affineTransform.scale(resizeFactor.getX(), resizeFactor.getY());
		
		affineTransform.translate(-resizeOrigin.getX(), -resizeOrigin.getY());
		if(this.selectedShape instanceof GSelectContainer)
			this.selectedShape.transformShape(affineTransform, resizeOrigin, resizeFactor);
		else 
			this.selectedShape.transformShape(affineTransform);

		this.selectedShape.draw(g2D);
		this.previous.setLocation(x, y);		
	}

	@Override
	public void finishTransforming(Graphics2D g2d, int x, int y) {
		
	}

	private Point2D getResizeOrigin() {
		Point2D resizeOrigin = new Point2D.Double();
		EAnchors eAnchor = null;
		
		switch(this.selectedShape.getSelectedAnchor()) {
		case N : eAnchor = EAnchors.S; break;
		case S : eAnchor = EAnchors.N; break;
		case W : eAnchor = EAnchors.E; break;
		case E : eAnchor = EAnchors.W; break;
		case NW : eAnchor = EAnchors.SE; break;
		case NE : eAnchor = EAnchors.SW; break;
		case SW : eAnchor = EAnchors.NE; break;
		case SE : eAnchor = EAnchors.NW; break;
		default : break;
		}
		
		resizeOrigin = this.selectedShape.getAnchors(eAnchor);
		return resizeOrigin;
	}

	private Point2D computeResizeFactor(Point2D previous, Point2D current) {
		Point2D resizeFactor = new Point2D.Double();
		double px, py, cx, cy, rx, ry, fx, fy;
		px = previous.getX();
		py = previous.getY();
		cx = current.getX();
		cy = current.getY();
		rx = 0;		ry = 0;
		fx = 1.0;	fy = 1.0;
		switch(this.selectedShape.getSelectedAnchor()) {
			case N : rx = 0; 				ry = -(cy - py); break;
			case S : rx = 0;				    ry = (cy - py); break;
			case W : rx = -(cx - px);		ry = 0; break;
			case E : rx = (cx - px);		    ry = 0; break;
			case NW : rx = -(cx - px);  	    ry = -(cy - py); break;
			case NE : rx = (cx - px);		ry = -(cy - py); break;
			case SW : rx = -(cx - px);		ry = (cy - py); break;
			case SE : rx = (cx - px);		ry = (cy - py); break;
			default : break;
		}
		
		fx = rx / this.selectedShape.getWidth() + fx;
		fy = ry / this.selectedShape.getHeight() + fy;
		resizeFactor.setLocation(fx,fy);
		return resizeFactor;
	}

}
