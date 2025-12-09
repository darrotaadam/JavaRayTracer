package RayTracer.geometry;

import java.util.Objects;

/**
 * Classe Implémentant le type d'object point, en 3 coordonnées x,y,z maximum. 
 * La majorité des opérations sur des Point instanciés sont faites sur une copie de l'instance Point, et renvoient cette copie.
 */

public class Point extends AbstractVec3{

	/* Constructors */
	/**
	 * Constructeur nul (0.0, 0.0, 0.0)
	 */
	public Point() 							{		super(0,0,0);	}
	/**
	 * Constructeur x (x, 0.0, 0.0)
	 * @param x x
	 */
	public Point(double x) 					{		super(x,0,0);	}
	/**
	 * Constructeur x,y (x, y, 0.0)
	 * @param x x
	 * @param y y
	 */
	public Point(double x, double y) 			{		super(x,y,0);	}
	/**
	 * Constructeur x,y,z (x, y, z)
	 * @param x x
	 * @param y y 
	 * @param z z
	 */
	public Point(double x, double y, double z) {		super(x,y,z);	}
	
	
	/**
	 * Copie un Point
	 * @param toCopy Point à copier
	 */
	public Point(Point toCopy) {
		 this.x = toCopy.x;
		 this.y = toCopy.y;
		 this.z = toCopy.z;
	}
	
	/* Getters */
	public double getX() {		return this.x;	}
	public double getY() {		return this.y;	}
	public double getZ() {		return this.z;	}
	
	
	/* Setters */
	/**
	 * Setter pour x
	 * @param x x
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * Setter pour y
	 * @param y y
	 */
	public void setY(double y) {
		this.y = y;
	}
	/**
	 * Setter pour z
	 * @param z z
	 */
	public void setZ(double z) {
		this.z = z;
	}
	
	/**
	 * Soustraction d'un Point à l'objet Point.
	 * Retourne un Vector.
	 * @param pointToSubstract Point a soustraire
	 * @return Vector 
	 */
	/* Operations */
	public Vector sub(Point pointToSubstract) {
		double newX = this.x - pointToSubstract.x;
		double newY = this.y - pointToSubstract.y;
		double newZ = this.z - pointToSubstract.z;
		return new Vector(newX, newY, newZ);
	}
	
	/**
	 * Multiplication de l'objet point par un scalaire (double)
	 * @param scalar Scalaire de la multiplication
	 * @return Point
	 */
	public Point multByScalar(double scalar) {
		double newX = this.x * scalar;
		double newY = this.y * scalar;
		double newZ = this.z * scalar;
		return new Point(newX, newY, newZ);
	}
	
	
	
	/**
	 * Redéfinition de la méthode equals().
	 * Considère que l'object Point est égal à l'objet Object si 
	 * <ul>
	 * <li>Les références pointent au même objet</li>
	 * <li>Ou si l'objet Object est une instance de Point, et que les coordonnées x,y,z sont égales à celles de l'objet Point</li>
	 * </ul>
	 */
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		
		if(obj == null || !( obj instanceof Point ))
			return false;
		
		
		// obj est forcément un Point
		Point otherP = (Point) obj;
		
		return Double.compare(this.x, otherP.x) == 0 &&
		           Double.compare(this.y, otherP.y) == 0 &&
		           Double.compare(this.z, otherP.z) == 0;
		
	}
	
	/**
	 * Redéfinition de la méthode hashCode pour le bon fonctionnement de Point.equals().
	 */
	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}
	
	
	
}
