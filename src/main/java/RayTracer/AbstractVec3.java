package RayTracer;

import java.util.Objects;

public class AbstractVec3 {
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
	public double getX() {		return this.x;	}
	public double getY() {		return this.y;	}
	public double getZ() {		return this.z;	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		
		if(obj == null || getClass() != obj.getClass() )
			return false;
		
		AbstractVec3 other = (AbstractVec3) obj;
		
		return Double.compare(x, other.x) == 0 &&
		           Double.compare(y, other.y) == 0 &&
		           Double.compare(z, other.z) == 0;
		
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}
	
}
