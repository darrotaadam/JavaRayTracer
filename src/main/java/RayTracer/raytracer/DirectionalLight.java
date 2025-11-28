package RayTracer.raytracer;

import RayTracer.geometry.Vector;
import RayTracer.imaging.Color;

public class DirectionalLight extends AbstractLight{
	
	
	public DirectionalLight(Vector direction, Color color) {
		this.color = color;
	}
	
}
