package RayTracer.geometry;


/**
 * Représente un repère orthonormé ( u, v, w ) avec u,v,w des vecteurs 
 */
public class Orthonormal {
	
	private Vector u, v, w;
	
	/**
	 * Détermine les vecteurs (u, v, w) à partir des paramètres.
	 * @param up direction de la caméra
	 * @param lookFrom point de vue
	 * @param lookAt point vers lequel regarde la caméra
	 */
	public Orthonormal(Vector up, Point lookFrom, Point lookAt) {
		
		this.w = calculateW(lookFrom, lookAt);
		this.u = calculateU(up, this.w);
		this.v = calculateV(this.u, this.w);
		
	}
	
	
	private Vector calculateW(Point lookFrom, Point lookAt) {	
		return lookFrom.sub(lookAt).normalisation();			
	}
	private Vector calculateU(Vector up, Vector w) {
		return up.produitVectoriel(w).normalisation();
	}
	private Vector calculateV(Vector u, Vector w) {
		return w.produitVectoriel(u).normalisation();
	}

	/**
	 * Getter pour u
	 * @return u
	 */
	public Vector getU() {
		return u;
	}

	/**
	 * Getter pour v
	 * @return v
	 */
	public Vector getV() {
		return v;
	}

	/**
	 * Getter pour w
	 * @return w
	 */
	public Vector getW() {
		return w;
	}
		
		

	
}
