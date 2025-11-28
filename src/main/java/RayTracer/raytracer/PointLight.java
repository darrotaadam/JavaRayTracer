package RayTracer.raytracer;

import RayTracer.geometry.Point;
import RayTracer.imaging.Color;

public class PointLight extends AbstractLight{

	
	public PointLight(Point origin, Color color) {
		this.color = color;
	}
	
}
