import java.util.ArrayList;
/**
 * This class represents the data structure to hold the list of {@link #Point3D  } objects.
 * @author Shabrina Sharmin
 * Student Id: 300230297
 * @version 2.0
 */
public class KDtree<E>{
	
	/*
	 * This class is an inner class which represents a particular node of the structure holding the Point3D object
	 * @param <T>
	 */
	public class KDNode<T>{
		public T point;
		public int axis;
		public double value;
		public KDNode<T>left;
		public KDNode<T>right;
		
		
		public KDNode( T pt,int axis) {
			this.point = pt;
			this.axis = axis;
			this.value = ((Point3D) pt).get(axis);
			this.left = null;
			this.right = null;
		}
	}
	/*
	 * A reference to the head of the tree structure
	 */
	private KDNode<E> root;
	/*
	 * Coordinate of the points
	 */
	public final int DIM = 3;
	
	/*
	 * Constructor
	 */
	public KDtree() {
		this.root = null;
	}
	
	
	public KDNode<E> root() {
		return this.root;
	}
	private KDNode<E> insert(E p, KDNode<E> node, int axis) {
		//System.out.println("KDTree::insert Inserting point:" + p.toString());
		if(node == null) { // insert(P(5,25), null, 1)
			node = new KDNode<E>(p, axis);
			//System.out.println(node.point.hashCode());
		}
		else if(((Point3D)p).get(axis)<= node.value) { //(30,40) (5,25) -> insert((10,12), (5,25), 1)
			//System.out.println("KDTree::insert left point is:" + p.toString() + "parent is: " + node.toString());
			node.left = insert(p,node.left,((axis+1)% DIM));
		}else {
			//System.out.println("KDTree::insert right point is:" + p.toString() + "parent is: " + node.toString());
			node.right = insert(p,node.right,((axis+1)% DIM));
		}
		return node;
	}
	
	/**
	 * Adds the object to the main tree
	 * @param p  a Point3D object to be added 
	 */
	public void add(E p) {
		//System.out.println("KDTree::add Inserting point:" + p.hashCode());
		//this.root = this.insert(p, root, 0);  ///3rd param, need to check
		if (root == null) {
			this.root = new KDNode<E>(p, 0);
		}
		else{
			this.insert(p, this.root, this.root.axis);
		}
	}

//	public void printTree() {
//		printT(root, "Root");
//	}
//	public void print(KDNode<E> node) {
//		int dir = 0;
//		if(node.left != null) { //(30,40) (5,25) -> insert((10,12), (5,25), 1)
//			//System.out.print("Left:");
//			dir = 1;
//			print(node.left);
//		}
//		if (node.right != null){
//			//System.out.print("Right:");
//			dir = 2;
//			print(node.right);
//		}
//		if (dir == 0)
//			System.out.println( " Node:" + node.point.toString());
//		else if (dir == 1)
//			System.out.println( " Left Node:" + node.point.toString());
//		else
//			System.out.println( "Right Node:" + node.point.toString());
//	}
//	
//	public void printT(KDNode<E> node, String dir) {
//		if(node == null) { // insert(P(5,25), null, 1)
//			return;
//		}
//		System.out.println( dir + " Node: " + node.point.toString());
//		if(dir != "Root") {
//			printT(node.left, "Left");
//			printT(node.right, "Right");
//		}
//		else {
//			printT(node.left, " Root Left");
//			printT(node.right, "Root Right");
//		}
//		
//		}
//	
//	public static void main(String [] args ) {
//		ArrayList<Point3D> nBpointsList = new ArrayList<>();
//		nBpointsList.add(new Point3D(30,40,0));
//		nBpointsList.add(new Point3D(5,25,0));
//		nBpointsList.add(new Point3D(70,70,0));
//		nBpointsList.add(new Point3D(10,12,0));
//		nBpointsList.add(new Point3D(50,30,0));
//		nBpointsList.add(new Point3D(35,45,0));
//		KDtree<Point3D> kdTree= new KDtree<Point3D>();
//		for(Point3D p:nBpointsList) {
//			kdTree.add(p);
//		}
//		kdTree.printTree();
//	}
	
}
