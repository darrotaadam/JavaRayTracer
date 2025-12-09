package RayTracer.imaging;

/**
 * Représente un pixel, de largeur width et de hauteur height
 */

public class Pixel {
	private double width;
	private double height;
	public Pixel(double width, double height) {
		super();
		this.width = width;
		this.height = height;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}

	
	
	
}

