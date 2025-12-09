package RayTracer.raytracer;

import RayTracer.geometry.Vector;
import RayTracer.imaging.Color;

/**
 * Class représentant une lumière directionnelle formée d'un Vector direction et d'une Color couleur
 */
public class DirectionalLight extends AbstractLight{
	
	private Vector direction;
	
	public DirectionalLight(Vector direction, Color color) {
		this.color = color;
		this.direction = direction;
	}
	public Vector getDirection() {
		return direction;
	}
	
	
	
	
}


