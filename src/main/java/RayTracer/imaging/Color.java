package RayTracer.imaging;

import java.util.Objects;

import RayTracer.geometry.AbstractVec3;
import RayTracer.geometry.Point;


public class Color {
	private double r;
	private double g;
	private double b;
	
	/* Constructors */
	public Color() {
		this.r = 0.0;
		this.g = 0.0;
		this.b = 0.0;
	}
	public Color(double r) {
		this.r = clamp01(r);
		this.g = 0.0;
		this.b = 0.0;
	}
	public Color(double r, double g) {
		this.r =  clamp01(r);
		this.g =  clamp01(g);
		this.b = 0.0;
	}
	public Color(double r, double g, double b) {
		this.r =  clamp01(r);
		this.g =  clamp01(g);
		this.b =  clamp01(b);
	}
	public Color(Color toCopy) {
		 this.r = toCopy.r;
		 this.g = toCopy.g;
		 this.b = toCopy.b;
	}
	
	
	/* Setters & Getters */
	public double getR() {
		return r;
	}
	public void setR(double r) {
		this.r = clamp01(r);
	}
	public double getG() {
		return g;
	}
	public void setG(double g) {
		this.g = clamp01(g);
	}
	public double getB() {
		return b;
	}
	public void setB(double b) {
		this.b = clamp01(b);
	}
	
	/* Operations */
	public Color add(Color colorToAdd) {
		double newR = clamp01(this.r + colorToAdd.r);
		double newG = clamp01(this.g + colorToAdd.g);
		double newB = clamp01(this.b + colorToAdd.b);
		return new Color(newR, newG, newB);
	}
	
	public Color multiply(double scalar) {
		double newR = clamp01(this.r * scalar);
		double newG = clamp01(this.g * scalar);
		double newB = clamp01(this.b * scalar);
		return new Color(newR, newG, newB);
	}
	
	
	public Color schurProduct(Color colorSchurProduct) {
		double newR = clamp01(this.r * colorSchurProduct.r);
		double newG= clamp01(this.g * colorSchurProduct.g);
		double newB = clamp01(this.b * colorSchurProduct.b);
		return new Color(newR, newG, newB);
	}
	
	
	
	public int toRGB() {
		int red = (int) Math.round(r*255);
		int green = (int) Math.round(g*255);
		int blue = (int) Math.round(b*255);
		
		return ((red & 0xff) << 16)
				+ ((green & 0xff) << 8)
				+ (blue & 0xff);
	}
	
	private double clamp01(double x) {
		return Math.max(0.0, Math.min(x, 1.0));
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		
		if(obj == null || getClass() != obj.getClass() )
			return false;
		
		AbstractVec3 other = (AbstractVec3) obj;
		
		return Double.compare(r, other.getX()) == 0 &&
		           Double.compare(g, other.getY()) == 0 &&
		           Double.compare(b, other.getZ()) == 0;
		
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(r, g, b);
	}	
	
	
}
