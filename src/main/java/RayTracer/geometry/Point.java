package RayTracer.geometry;

import java.util.Objects;

public class Point extends AbstractVec3{

	/* Constructors */
	public Point() 							{		super(0,0,0);	}
	public Point(double x) 					{		super(x,0,0);	}
	public Point(double x, double y) 			{		super(x,y,0);	}
	public Point(double x, double y, double z) {		super(x,y,z);	}
	
	
	
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
	public Vector sub(Point pointToSubstract) {
		double newX = this.x - pointToSubstract.x;
		double newY = this.y - pointToSubstract.y;
		double newZ = this.z - pointToSubstract.z;
		return new Vector(newX, newY, newZ);
	}
	
	public Point multByScalar(double scalar) {
		double newX = this.x * scalar;
		double newY = this.y * scalar;
		double newZ = this.z * scalar;
		return new Point(newX, newY, newZ);
	}
	
	
	

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
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}
	
	
	
}
