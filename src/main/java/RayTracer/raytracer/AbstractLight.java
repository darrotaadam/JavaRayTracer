package RayTracer.raytracer;

import RayTracer.imaging.Color;

public abstract class AbstractLight implements Light{

	protected Color color;

	public Color getColor() {
		return this.color;
	}
	
}
