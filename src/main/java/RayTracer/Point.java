package RayTracer;

public class Point extends AbstractVec3{

	/* Constructors */
	public Point() 							{		super(0,0,0);	}
	public Point(double x) 					{		super(x,0,0);	}
	public Point(double x, double y) 			{		super(x,y,0);	}
	public Point(double x, double y, double z) {		super(x,y,z);	}

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
	
}
