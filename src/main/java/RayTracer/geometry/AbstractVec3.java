package RayTracer.geometry;



/**
 * Classe abstraite servant de base utilisée par les classes composées de trois attributs (x,y,z), (r,g,b).
 * Implémente un constructeur.
 */


public abstract class AbstractVec3 {
	/**
	 * Coordonnée x
	 */
	protected double x;
	/**
	 * Coordonnée y
	 */
	protected double y;
	/**
	 * Coordonnée z
	 */
	protected double z;
	
	/* Constructors */
	/**
	 * Constructeur vide.
	 * Crée (0.0, 0.0, 0.0)
	 */
	protected AbstractVec3() {
		this.x = 0.0;
		this.y = 0.0;
		this.z = 0.0;
	}
	/**
	 * Constructeur x .
	 * Crée (x, 0.0, 0.0)
	 * @param x x
	 */
	protected AbstractVec3(double x) {
		this.x = x;
		this.y = 0.0;
		this.z = 0.0;
	}
	/**
	 * Constructeur x, y .
	 * Crée (x, y, 0.0)
	 * @param x x
	 * @param y y
	 */
	protected AbstractVec3(double x, double y) {
		this.x = x;
		this.y = y;
		this.z = 0.0;
	}
	/**
	 * Constructeur x, y , z .
	 * Crée  (x,y,z)
	 * @param x x
	 * @param y y
	 * @param z z
	 */
	protected AbstractVec3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/* Getters */
	/**
	 * Getter pour x
	 * @return x
	 */
	public abstract double getX();
	/**
	 * Getter pour y
	 * @return y
	 */
	public abstract double getY();
	/**
	 * Getter pour z
	 * @return z
	 */
	public abstract double getZ();
	
	
	
	public abstract boolean equals(Object obj);
	public abstract int hashCode();
	
	
}
