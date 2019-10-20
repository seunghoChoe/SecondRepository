package transformer;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import shape.GShape;

public class GMover extends GTransformer {

	public GMover(GShape selectedShape) {
		super(selectedShape);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initTransforming(Graphics2D g2D, int x, int y) {
		this.previous.setLocation(x, y);
	}

	@Override
	public void keepTransforming(Graphics2D g2D, int x, int y) {
		this.getShape().erase(g2D);		
		
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.translate(x-this.previous.getX(), y-this.previous.getY());
		this.selectedShape.transformShape(affineTransform);
		
		this.getShape().draw(g2D);
		this.previous.setLocation(x, y);
	}

	@Override
	public void finishTransforming(Graphics2D g2d, int x, int y) {
	}
	
	public void moveALittle() {
		System.out.println("Ss");
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.translate(15,15);
		this.selectedShape.transformShape(affineTransform);
	}

}
