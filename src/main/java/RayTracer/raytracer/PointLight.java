package RayTracer.raytracer;

import RayTracer.geometry.Point;
import RayTracer.imaging.Color;

/**
 * Représente une lumière partant d'un Point origin et ayant une Color color
 */
public class PointLight extends AbstractLight{

	private Point origin;
	private Color color;
	public PointLight(Point origin, Color color) {
		this.color = color;
		this.origin = origin;
		
	}
	public Point getOrigin() {
		return origin;
	}
	public Color getColor() {
		return color;
	}
	
}
