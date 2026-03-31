import java.util.ArrayList;
import java.util.List;
/**
 * This class represents the neighbors of any particular point of {@link #Point3D  } object.
 * @author Shabrina Sharmin
 * Student Id: 300230297
 * @version 1.0
 */
public class NearestNeighbors {
	/**
	 * List that holds the neighbors of any given Point3D
	 */
	List<Point3D> pointsList;
	
	
	public NearestNeighbors(List<Point3D> pointsList) {
		this.pointsList = pointsList;
	}
	
	/**
	 * Returns the list that holds the list of neighbors of any given Point3D
	 * @param eps     the distance used to identify the points in the neighborhood of a Point3D object
	 * @param point   the point for which neighborhood points are need to determined
	 * @return        returns the reference of the list "pointsList" that holds all the neighbors
	 */
	public List<Point3D> rangeQuery(double eps, Point3D point){
		List<Point3D> neighborsList = new ArrayList<>();
		for(Point3D p : pointsList) {
			if(point.distance(p) <= eps) {
				neighborsList.add(p);
			}
		}
		return neighborsList;
		
	}
}
