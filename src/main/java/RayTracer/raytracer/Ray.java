package RayTracer.raytracer;

import RayTracer.geometry.Vector;
import RayTracer.geometry.Point;

/**
 * Représente un rayon Ray ayant un Point origin et un Vector direction.
 */
public class Ray {

	private Point origin;
	private Vector direction;
	
	
	public Point getOrigin() {
		return origin;
	}
	public Vector getDirection() {
		return direction;
	}


	public Ray(Point origin, Vector direction) {
		this.origin = origin;
		this.direction = direction;
	}
}
