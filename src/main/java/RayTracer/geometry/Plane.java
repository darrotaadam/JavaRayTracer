package RayTracer.geometry;

public class Plane implements Shape{

	public Point position;
	public Vector normale;
	
	
	public Plane(Point position, Vector normale) {
		this.normale = normale;
		this.position = position;
	}
	
}
