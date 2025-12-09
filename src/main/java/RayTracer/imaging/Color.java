package RayTracer.imaging;

import java.util.Objects;

import RayTracer.geometry.AbstractVec3;
import RayTracer.geometry.Point;

/**
 * Classe Implémentant le type d'object couleur, en 3 attributs r,g,b maximum. Une valeur non définie vaudra 0.0. 
 * La majorité des opérations sur des Color instanciés sont faites sur une copie de l'instance Color, et renvoient cette copie.
 * Un attibut de Color ne peut pas dépasser 1 ni être inférieur à 0.
 */

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
		return this.r;
	}
	public void setR(double r) {
		this.r = clamp01(r);
	}
	public double getG() {
		return this.g;
	}
	public void setG(double g) {
		this.g = clamp01(g);
	}
	public double getB() {
		return this.b;
	}
	public void setB(double b) {
		this.b = clamp01(b);
	}
	
	/* Operations */
	/**
	 * Addition d'une Color à l'objet Color
	 * @param colorToAdd 
	 * @return Color
	 */
	public Color add(Color colorToAdd) {
		double newR = clamp01(this.r + colorToAdd.r);
		double newG = clamp01(this.g + colorToAdd.g);
		double newB = clamp01(this.b + colorToAdd.b);
		return new Color(newR, newG, newB);
	}
	
	/**
	 * Multiplication de l'objet Color par un scalaire (double)
	 * @param scalar
	 * @return Color
	 */
	public Color multiply(double scalar) {
		double newR = clamp01(this.r * scalar);
		double newG = clamp01(this.g * scalar);
		double newB = clamp01(this.b * scalar);
		return new Color(newR, newG, newB);
	}
	
	/**
	 * Produit de Schur entre l'objet Color et la Color passée en paramètre
	 * @param colorSchurProduct
	 * @return Color
	 */
	public Color schurProduct(Color colorSchurProduct) {
		double newR = clamp01(this.r * colorSchurProduct.r);
		double newG= clamp01(this.g * colorSchurProduct.g);
		double newB = clamp01(this.b * colorSchurProduct.b);
		return new Color(newR, newG, newB);
	}
	
	
	/**
	 * Convertit l'objet Color en un int sous la forme RGB
	 * @return intRgb
	 */
	public int toRGB() {
		int red = (int) Math.round(r*255);
		int green = (int) Math.round(g*255);
		int blue = (int) Math.round(b*255);
		
		return ((red & 0xff) << 16)
				+ ((green & 0xff) << 8)
				+ (blue & 0xff);
	}
	
	/**
	 * Mesure de sécurité pour garantir qu'une Color reste entre 0 et 1
	 * @param x
	 * @return double
	 */
	private double clamp01(double x) {
		return Math.max(0.0, Math.min(x, 1.0));
	}
	
	/**
	 * Redéfinition de la méthode equals().
	 * Considère que l'object Color est égal à l'objet Object si 
	 * <ul>
	 * <li>Les références pointent au même objet</li>
	 * <li>Ou si l'objet Object est une instance de Color, et que les coordonnées x,y,z sont égales à celles de l'objet Color</li>
	 * </ul>
	 */
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
	/**
	 * Redéfinition de la méthode hashCode pour le bon fonctionnement de Color.equals().
	 */
	@Override
	public int hashCode() {
		return Objects.hash(r, g, b);
	}	
	
	
}
