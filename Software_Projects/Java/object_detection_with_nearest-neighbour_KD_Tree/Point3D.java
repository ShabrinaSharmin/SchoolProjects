/**
 * This class represents a point in 3D space
 * @author Shabrina Sharmin
 * Student Id: 300230297
 * @version 1.0
 */
public class Point3D {
	
	public final int  X_AXIS = 0;
	public final int  Y_AXIS = 1;
	public final int  Z_AXIS = 2;
	
	
	
	/**
	 * The x-coordinate of a Point3D object
	 */
	private double x;
	/**
	 * The y-coordinate of a Point3D object
	 */
	private double y;
	/**
	 * The z-coordinate of a Point3D object
	 */
	private double z;
	/**
	 * The ClusterLabel that a Point3D object belongs to
	 */
	private int clusterLabel;
	/**
	 * The color code of a Point3D object
	 */
	private double r;
	/**
	 * The color code of a Point3D object
	 */
	private double g;
	/**
	 * The color code of a Point3D object
	 */
	private double b;
	
	
	
	
	public Point3D() {}
	
	public Point3D(double x,double y,double z) {
		this.x= x;
		this.y= y;
		this.z= z;	
		this.clusterLabel = -1;  //- means the Point3D object is undefined
	}
	/**
	 * Returns the x-coordinate	of the Point3D
	 * @return  double value of x-coordinate of the Point3D
	 */
	public double getX() {
		return this.x;
	}
	/**
	 * Returns the y-coordinate	of the Point3D
	 * @return  double value of y-coordinate of the Point3D
	 */
	public double getY() {
		return this.y;
	}
	/**
	 * Returns the z-coordinate	of the Point3D
	 * @return  double value of z-coordinate of the Point3D
	 */
	public double getZ() {
		return this.z;
	}
	
	/**
	 * Returns the ClusterLabel of a Point3D object
	 * @return  int value of clusterLabel of a Point3D object
	 */
	public int getClusterLabel() {
		return this.clusterLabel ;
	}
	
	/**
	 * Returns the color code of a Point3D object
	 * @return  double value  represents the color of the Point3D object
	 */
	public double getR() {
		return this.r;
	}
	/**
	 * Returns the color code of a Point3D object
	 * @return  double value  represents the color of the Point3D object
	 */
	public double getG() {
		return this.g;
	}
	/**
	 * Returns the color code of a Point3D object
	 * @return  double value  represents the color of the Point3D object
	 */
	public double getB() {
		return this.b;
	}
	
	
	public double get(int axis) {
		double value = 0.0;
		if(axis == this.X_AXIS) {
			value = this.getX();
		}
		if(axis == this.Y_AXIS) {
			value = this.getY();
		}
		if(axis == this.Z_AXIS) {
			value = this.getZ();
		}
		return value;
		
	}
	
	/**
	 * Sets the ClusterLabel of a Point3D object to which the point belong to
	 * @param   int value of clusterLabel of a Point3D object
	 */
	public void setClusterLabel(int value) {
		//System.out.println("setting value  " + value );
		 this.clusterLabel = value ;
	}
	
	/**
	 * Sets the color codes of a Point3D object
	 * @param   int value representing the color codes of a Point3D object
	 */
	public void setRGB(double r,double g, double b) {
		this.r = r;
		this.g = g;
		this.b = b;
			
	}
	
	/**
	 * Calculates the distance between  two Point3D objects
	 * @return  double value representing the distance between two Point3D objects
	 */
	public double distance(Point3D pt) {
		double distance = Math.pow( ( Math.pow((pt.getX()-this.x), 2)+ 
									Math.pow((pt.getY()-this.y), 2)+
									Math.pow((pt.getZ()-this.z), 2) ),0.5);
		return distance;
	}
	
	/**
	 * Prints a Point3D objects
	 * @return  String representation of a Point3D object
	 */
	public String toString() {
		String toReturn = "#X: "+this.getX() + "   #Y: "+this.getY() + "  #Z: "+this.getZ() +
				"  #clusterLabel: " + this.getClusterLabel();
		return toReturn;
	}
	
	
	
	
	

}
