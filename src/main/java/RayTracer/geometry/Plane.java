package RayTracer.geometry;

import RayTracer.imaging.Color;

public class Plane implements Shape{

	public Point position;
	public Vector normale;
	
	private Color specular;
	private Color diffuse;
	
	public Plane(Point position, Vector normale) {
		this.normale = normale;
		this.position = position;
	}
	
}
