package RayTracer.geometry;

public class Orthonormal {
	
	private Vector u, v, w;
	
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
		
		

	
}
