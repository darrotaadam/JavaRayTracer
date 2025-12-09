package RayTracer.raytracer;

import RayTracer.imaging.Color;

/**
 * Base pour les classes lumière
 */
public abstract class AbstractLight implements Light{

	protected Color color;

	public Color getColor() {
		return this.color;
	}
	
}
