package RayTracer.geometry;

import RayTracer.raytracer.Ray;

public interface Shape {

	Intersection intersect(Ray rayon);
	
	
}
