package RayTracer.geometry;



public abstract class AbstractVec3 {
	protected double x;
	protected double y;
	protected double z;
	
	/* Constructors */
	protected AbstractVec3() {
		this.x = 0.0;
		this.y = 0.0;
		this.z = 0.0;
	}
	protected AbstractVec3(double x) {
		this.x = x;
		this.y = 0.0;
		this.z = 0.0;
	}
	protected AbstractVec3(double x, double y) {
		this.x = x;
		this.y = y;
		this.z = 0.0;
	}
	protected AbstractVec3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/* Getters */
	public abstract double getX();
	public abstract double getY();
	public abstract double getZ();
	
	

	public abstract boolean equals(Object obj);
	public abstract int hashCode();
	
	
}
