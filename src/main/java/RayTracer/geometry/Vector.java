package RayTracer.geometry;

import java.util.Objects;


/**
 * Classe Implémentant le type d'object vecteur, en 3 dimensions x,y,z maximum. 
 * La majorité des opérations sur des Vector instanciés sont faites sur une copie de l'instance Vector, et renvoient cette copie.
 */

public class Vector extends AbstractVec3{

	/* Constructors */
	public Vector() 							{		super(0,0,0);	}
	public Vector(double x) 					{		super(x,0,0);	}
	public Vector(double x, double y) 			{		super(x,y,0);	}
	public Vector(double x, double y, double z) {		super(x,y,z);	}
	
	/* Getters */
	public double getX() {		return this.x;	}
	public double getY() {		return this.y;	}
	public double getZ() {		return this.z;	}
	
	/* Setters */

	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setZ(double z) {
		this.z = z;
	}
	
	/* Operations */
	
	/**
	 * Addition d'un Vector à l'objet Vector
	 * @param vectorToAdd 
	 * @return Vector 
	 */
	public Vector add(Vector vectorToAdd) {
		double newX = this.x + vectorToAdd.x;
		double newY = this.y + vectorToAdd.y;
		double newZ = this.z + vectorToAdd.z;
		return new Vector(newX, newY, newZ);
	}
	
	/**
	 * Addition d'un Point à l'objet Vector
	 * @param pointToAdd
	 * @return Point
	 */
	public Point add(Point pointToAdd) {
		double newX = this.x + pointToAdd.x;
		double newY = this.y + pointToAdd.y;
		double newZ = this.z + pointToAdd.z;
		return new Point(newX, newY, newZ);
	}
	
	/**
	 * Soustraction d'un Vector à l'objet Vector
	 * @param vectorToSubstract
	 * @return Vector
	 */
	public Vector sub(Vector vectorToSubstract) {
		double newX = this.x - vectorToSubstract.x;
		double newY = this.y - vectorToSubstract.y;
		double newZ = this.z - vectorToSubstract.z;
		return new Vector(newX, newY, newZ);
	}
	
	/**
	 * Multiplication de l'objet Vector par un scalaire
	 * @param scalar
	 * @return Vector
	 */
	public Vector multByScalar(double scalar) {
		double newX = this.x * scalar;
		double newY = this.y * scalar;
		double newZ = this.z * scalar;
		return new Vector(newX, newY, newZ);
	}
	
	/**
	 * Produit scalaire entre l'objet Vector et un autre Vector
	 * @param vecteur
	 * @return double
	 */
	public double produitScalaire(Vector vecteur) {
		double resultat = 0;
		resultat += this.x * vecteur.x;
		resultat += this.y * vecteur.y;
		resultat += this.z * vecteur.z;
		return resultat;
	}
	
	/**
	 * Produit vectoriel A x B où A est l'objet Vector et B est le Vector passé en paramètre
	 * @param vecteur
	 * @return Vector 
	 */
	public Vector produitVectoriel(Vector vecteur) {
		double newX = this.y * vecteur.z - this.z * vecteur.y;
		double newY = this.z * vecteur.x - this.x * vecteur.z;
		double newZ = this.x * vecteur.y - this.y * vecteur.x;
		return new Vector(newX, newY, newZ);
	}
	
	/**
	 * Calcul de la longueur de l'objet Vector
	 * @return Double
	 */
	public double length() {
		double suareSum=0;
		suareSum+= this.x * this.x;
		suareSum += this.y * this.y;
		suareSum += this.z * this.z;
		return Math.sqrt(suareSum);
	
	}
	
	/**
	 * Normalisation de l'objet Vector. Utilise Vector.length().
	 * @return Vector
	 */
	public Vector normalisation() {
		double vecLen = this.length();
		double newX = this.x / vecLen;
		double newY = this.y / vecLen;
		double newZ = this.z / vecLen;
		return new Vector(newX, newY, newZ);
	}
	
	/**
	 * Redéfinition de la méthode equals().
	 * Considère que l'object Vector est égal à l'objet Object si 
	 * <ul>
	 * <li>Les références pointent au même objet</li>
	 * <li>Ou si l'objet Object est une instance de Vector, et que les coordonnées x,y,z sont égales à celles de l'objet Vector</li>
	 * </ul>
	 */
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		
		if(obj == null || !( obj instanceof Vector ))
			return false;
		
		
		// obj est forcément un Vector
		Vector otherV = (Vector) obj;
		
		return Double.compare(this.x, otherV.x) == 0 &&
		           Double.compare(this.y, otherV.y) == 0 &&
		           Double.compare(this.z, otherV.z) == 0;
		
	}
	
	/**
	 * Redéfinition de la méthode hashCode pour le bon fonctionnement de Vector.equals().
	 */
	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}
	
	
	
	
}
