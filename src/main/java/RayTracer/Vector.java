package RayTracer;

public class Vector {

	private double x;
	private double y;
	private double z;
	
	/* Constructors */
	public Vector() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	public Vector(double x) {
		this.x = x;
		this.y = 0;
		this.z = 0;
	}
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
		this.z = 0;
	}
	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/* Setters & Getters */
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getZ() {
		return z;
	}
	public void setZ(double z) {
		this.z = z;
	}
	
	/* Operations */
	public Vector add(Vector vectorToAdd) {
		double newX = this.x + vectorToAdd.x;
		double newY = this.y + vectorToAdd.y;
		double newZ = this.z + vectorToAdd.z;
		return new Vector(newX, newY, newZ);
	}
	
	public Vector sub(Vector vectorToSubstract) {
		double newX = this.x - vectorToSubstract.x;
		double newY = this.y - vectorToSubstract.y;
		double newZ = this.z - vectorToSubstract.z;
		return new Vector(newX, newY, newZ);
	}
	
	public Vector multByScalar(double scalar) {
		double newX = this.x * scalar;
		double newY = this.y * scalar;
		double newZ = this.z * scalar;
		return new Vector(newX, newY, newZ);
	}
	
	public double produitScalaire(Vector vecteur) {
		double resultat = 0;
		resultat += this.x * vecteur.x;
		resultat += this.y * vecteur.y;
		resultat += this.z * vecteur.z;
		return resultat;
	}
	
	
	public Vector produitVectoriel(Vector vecteur) {
		double newX = this.y * vecteur.z - this.z * vecteur.y;
		double newY = this.z * vecteur.x - this.x * vecteur.z;
		double newZ = this.x * vecteur.y - this.y * vecteur.x;
		return new Vector(newX, newY, newZ);
	}
	
	
	public double length() {
		double suareSum=0;
		suareSum+= this.x * this.x;
		suareSum += this.y * this.y;
		suareSum += this.z * this.z;
		return Math.sqrt(suareSum);
	
	}
	
	public Vector normalisation() {
		double vecLen = this.length();
		double newX = this.x / vecLen;
		double newY = this.y / vecLen;
		double newZ = this.z / vecLen;
		return new Vector(newX, newY, newZ);
	}
	
	
	
	
	
}
