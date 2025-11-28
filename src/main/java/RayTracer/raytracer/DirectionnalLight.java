package RayTracer.raytracer;

import RayTracer.geometry.Vector;
import RayTracer.imaging.Color;

public class DirectionnalLight extends AbstractLight{
	
	
	public DirectionnalLight(Vector direction, Color color) {
		this.color = color;
	}
	
}
