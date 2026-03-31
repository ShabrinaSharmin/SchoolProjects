import java.util.ArrayList;
import java.util.List;
/**
 * This class represents the neighbors of any particular point of {@link #Point3D  } object.
 * @author Shabrina Sharmin
 * Student Id: 300230297
 * @version 2.0
 */
public class NearestNeighborsKD {
	/**
	 * List that holds the neighbors of any given Point3D
	 */
	List<Point3D> nBpointsList;
	/*
	 * A tree like data structure that holds the list of Point3D objects 
	 */
	KDtree<Point3D> kdTree;
	
	/**
	 * Constructor
	 * @param pointsList
	 */
	public NearestNeighborsKD(List<Point3D> pointsList) {
		this.nBpointsList = pointsList;
		kdTree= new KDtree<Point3D>();
		for(Point3D p:nBpointsList) {
			//System.out.println( "Point is " + p.toString() );
			//System.err.println("nn" + p.hashCode());
			kdTree.add(p);
		}
		//kdTree.printT(kdTree.root(), "Root");
	}
	
	/**
	 * Returns the list that holds the list of neighbors of any given Point3D
	 * @param eps     the distance used to identify the points in the neighborhood of a Point3D object
	 * @param point   the point for which neighborhood points are need to determined
	 * @return        returns the reference of the list "pointsList" that holds all the neighbors
	 */
	public List<Point3D> rangeQuery(double eps, Point3D point){
		List<Point3D> neighborsList = new ArrayList<>();
		
		//for(Point3D p : nBpointsList) {
		//	if(point.distance(p) <= eps) {
		rangeQuery(point, eps, neighborsList, kdTree.root());
				
		//	}
		//}
		//System.out.println( "Point is " + point.toString() + "Size" + neighborsList.size());
		return neighborsList;
		
	}
	
	/**
	 * Returns the list that holds the list of neighbors of any given Point3D
	 * @param eps     the distance used to identify the points in the neighborhood of a Point3D object
	 * @param point   the point for which neighborhood points are need to determined
	 * @param neighborsList    the list where all the neighbors of a given Point3D objects would be saved
	 */
	private void rangeQuery(Point3D point, double eps, List<Point3D> neighborsList,
			KDtree<Point3D>.KDNode<Point3D> node) {
		//System.out.println("range:" + point  + "eps: " + eps);
		if(node == null) {
			//System.out.println("node is null, range query returning");
			return;
		}
		//System.out.println("Comparing p: " + point.toString() + " with " + node.point.toString());
		if( node.point.distance( point) < eps) {
			//System.out.println("rangeQuery add " + point.toString() + "  "  + node.point.toString() );
			neighborsList.add(node.point);
		}
		if(point.get(node.axis)-eps <= node.value) {
			rangeQuery(point, eps, neighborsList, node.left);
		}
		if(point.get(node.axis)+eps > node.value) {
			rangeQuery(point, eps, neighborsList, node.right);
		}
		return;
	}
	
	

}
