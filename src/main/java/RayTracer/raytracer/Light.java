package RayTracer.raytracer;

import RayTracer.imaging.Color;

/**
 * Interface de base pour toutes les lumières (DirectionalLight et PointLight)
 */
public interface Light {
	 Color getColor();
}
