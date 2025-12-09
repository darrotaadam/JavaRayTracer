package RayTracer.geometry;

import java.util.Optional;

import RayTracer.imaging.Color;
import RayTracer.raytracer.Ray;

/**
 * Interface comportant les types Sphere, Triangle, Plane
 */
public interface Shape {

	Optional<Intersection> intersect(Ray rayon);
	
	Color getDiffuse();
	Color getSpecular();
	Double getShininess();
}
