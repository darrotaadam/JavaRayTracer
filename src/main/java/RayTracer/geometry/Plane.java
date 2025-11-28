package RayTracer.geometry;

import RayTracer.imaging.Color;

public class Plane implements Shape{

	public Point position;
	public Vector normale;
	
	private Color specular;
	private Color diffuse;
	
	public Plane(Point position, Vector normale, Color specular, Color diffuse) {
		this.normale = normale;
		this.position = position;
		this.specular = specular;
		this.diffuse = diffuse;
	}
	
}
