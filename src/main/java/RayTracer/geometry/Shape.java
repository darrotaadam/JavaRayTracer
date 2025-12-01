package RayTracer.geometry;

import java.util.Optional;

import RayTracer.raytracer.Ray;

public interface Shape {

	Optional<Intersection> intersect(Ray rayon);
	
	
}
