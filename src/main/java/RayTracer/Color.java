package RayTracer;


public class Color {
	private double r;
	private double g;
	private double b;
	
	public Color(double r, double g, double b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	
	
	public void add(Color colorToAdd) {
		this.r = Math.min(this.r + colorToAdd.r, 1.0);
		this.g = Math.min(this.g + colorToAdd.g, 1.0);
		this.b = Math.min(this.b + colorToAdd.b, 1.0);
	}
	
	public void multiply(double scalar) {
		this.r = Math.min(this.r * scalar, 1.0);
		this.g = Math.min(this.g * scalar, 1.0);
		this.b = Math.min(this.b * scalar, 1.0);
	}
	
	
	public void schurProduct(Color colorSchurProduct) {
		this.r = Math.min(this.r + colorSchurProduct.r, 1.0);
		this.g = Math.min(this.g + colorSchurProduct.g, 1.0);
		this.b = Math.min(this.b + colorSchurProduct.b, 1.0);
	}
	
	
	
	public int toRGB() {
		int red = (int) Math.round(r*255);
		int green = (int) Math.round(g*255);
		int blue = (int) Math.round(b*255);
		
		return (  (red & 0xff) << 16
				+ (green & 0xff) << 8
				+ (blue & 0xff)
		);
	}
	
		
}
