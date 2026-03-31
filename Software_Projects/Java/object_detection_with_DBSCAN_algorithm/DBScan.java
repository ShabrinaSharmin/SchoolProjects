
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * This class represents the Cluster Clouds comprised of {@linkplain#Point3D} objects
 * @author Shabrina Sharmin
 * Student Id: 300230297
 * @version 1.0
 */

public class DBScan {
	/**
	 * The distance used to find the neighbors of a any POint3D object
	 */
	private double eps;
	
	/**
	 * The minimum number of Point3D object required to be a core point of a Cluster Cloud
	 */
	private double minPts;

	/**
	 * The number of clusters
	 */
	private int numberOfClusters;
	/**
	 * The list to hold all the reference of Point3D objects provided
	 */
	private List<Point3D> listOfPoints;
	/**
	 * 
	 */
	private List<Clusters> cluster;
	/**
	 * 
	 */
	private NearestNeighbors nB;
	
	
	public DBScan(List<Point3D> listOfPoints) {
		this.listOfPoints = listOfPoints;
		this.cluster = new ArrayList<Clusters>();
		this.numberOfClusters = 0;
		this.nB = new NearestNeighbors(listOfPoints);
	}

	/**
	 * Sets the value of eps 
	 * @param   double value representing the minimum distance to look for neighbors around any particular Point3D object
	 */
	public void setEps(double eps) {
		this.eps = eps;
	}

	/**
	 * Sets the value of minimum points required 
	 * @param   double value representing the minimum number of points required to be a core point of a Cluster Cloud
	 */
	public void setMinPts(double minPts) {
		this.minPts = minPts;
	}
	
	/**
	 * Returns the value of numberOfCluster in a Cluster Cloud
	 * @param   int value representing the number of clusters created 
	 */
	public int getNumberOfCluster() {
		return this.numberOfClusters;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Point3D> getPoints(){
		return this.listOfPoints;
	}
	

	
	/**
	 * Creates all the Cluster Clouds  from a list of Poin3D objects
	 */
	public void findClusters() {
		
		double r= -1, g = -1, b = -1;  //variable to represent color
		
		for(Point3D p : listOfPoints) {
			// the point in hand has already been processed
			if(p.getClusterLabel() != -1) { //-1 is undefined
				continue;
			}
			//the point in hand has not been processed. So, find neighbors of "p"
			List<Point3D> tempList = nB.rangeQuery(this.eps, p);  //tempList=> to hold neighbors of a point "p" of type Point3D
			//Density check.If point in hand at least the minimum number of points within the required eps
			if(tempList.size() < this.minPts) {
				p.setClusterLabel(-999);  ///Label as noise
				p.setRGB(-1, -1, -1);
				continue;
			}
			
			//tempList.size() >= this.minPts.So,start of clustering
			this.numberOfClusters = this.numberOfClusters + 1;   //increment the numberOfCluster
			//Generate random  color code for that cluster
			r=Math.random();
			g=Math.random();
			b=Math.random();
			p.setClusterLabel(numberOfClusters);		// set the clusterLabel for all the Point3D objects to which #Cluster they belong to	
			cluster.add(new Clusters(numberOfClusters, 1));
			//set the color code for that cluster
			p.setRGB(r, g, b);
			//Stack to hold the neighbors
			Stack<Point3D>stackOfPoints = new Stack<Point3D>();
			this.pushTOStack(tempList, stackOfPoints);
			
			
			//now we will try to check each of the neighbors of "p" that are in "templist"
			while( ! stackOfPoints.isEmpty()) {
				 Point3D q = stackOfPoints.pop();  
				
				 if(q.getClusterLabel() == -999) {       //Noise becomes a border point since 
					 q.setClusterLabel(numberOfClusters);
					 q.setRGB(r, g, b);
				 }
				 
				 if(q.getClusterLabel() != -1) {    //Q has already been processed
					 continue;	 
				 }
				 q.setClusterLabel(numberOfClusters);  //Label neighbor
				 q.setRGB(r, g, b);
				 Clusters sz = this.getSizeOfClusterByClusterId(numberOfClusters);
				 sz.setSize(sz.getSize()+1);
				 //now try to get neighbors of "q"
				 List<Point3D>tempList2 = nB.rangeQuery(eps, q);
				 if(tempList2.size() >= this.minPts) {     //density check 
					 pushTOStack(tempList2, stackOfPoints);
				 }				
			}

		}
	}
	
	
	/**
	 * Returns a Cluster from the Cluster Cloud
	 * @param cId   The id of the Cluster
	 * @return      a reference of a Cluster object. If the cluster Id is not found , returns null
	 */
	
	private Clusters getSizeOfClusterByClusterId(int cId) {
		//System.err.println(clusterSizes.size());
		for(Clusters sz: cluster) {
			//System.out.println(sz.getCid());
			if(cId == sz.getCid()) {
				return sz;
			}
		}
		return null;
	}
	
	/**
	 * Sorts the a list of Clusters in descending order
	 */
	private void sort() {
		for(int i=1; i<this.cluster.size();i++) {
			int key = this.cluster.get(i).getSize();
			Clusters temp = this.cluster.get(i);
//			System.out.println("Current key"+ this.clusterSizes.get(i).getSize());
			int j =i-1;
				while(j>=0 &&   key > this.cluster.get(j).getSize()) {
					cluster.set(j+1,this.cluster.get(j));
					j=j-1;
				}
				cluster.set(j+1, temp);
		}
		
	}

	/**
	 * Prints the Cluster Sizes to the console
	 */
	private void printClusterSizes() {
		for(Clusters sz : this.cluster) {
			System.out.println("Cluster Label: " + sz.getCid() + " Cluster size: " + sz.getSize());
		}
		
	}
	
	
	private void printNoise() {
		for(Point3D p : listOfPoints) {
			if(p.getClusterLabel()==-999) {
				System.out.println("Noise points are:" + p);
			}
		}
	}
	
	/**
	 * Saves all the neighbors of a Point3D object to a stack
	 * @param list               the Point3D objects that need to be added as a neighbor
	 * @param stackOfPoints      The stack where the Point3D object would be pushed 
	 */
	private void pushTOStack(List<Point3D> list, Stack<Point3D> stackOfPoints ) {
		for(Point3D p : list) {
			stackOfPoints.push(p);
		}
	}

	/**
	 * Saves the Clusters Clouds that has been created along with the color coded value to CSV file
	 * @param fileN  The file where Clusters needs to be saved
	 */
	public void save(String fileN) {
		try {
			FileWriter myWriter = new FileWriter(new File(fileN));
			myWriter.write("x,y,z,C,R,G,B\n");
			for(Point3D point: listOfPoints) {
				StringBuilder line = new StringBuilder();
				line.append(point.getX()+","+point.getY()+","+point.getZ()+","+point.getClusterLabel()+",");
				line.append(point.getR()+",");
				line.append(point.getG()+",");
				line.append(point.getB());
				line.append("\n");
				myWriter.write(line.toString());
				
			}
			myWriter.close();
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	/**
	 * Reads the points that represents Point3D objects and stores them in a list
	 * @param filename                       the filename to be read from
	 * @return                               the list that holds all the point3D objects 
	 * @throws FileNotFoundException         if the file could not be found
	 */
	public static List<Point3D> read(String filename) throws FileNotFoundException{
		Scanner sC = new Scanner(new File(filename));
		List<Point3D> tempList = new ArrayList<Point3D>();
		Point3D point;
		String str =sC.nextLine();  //ignore first line of the csv file
		while(sC.hasNext()){
			str = sC.nextLine();
			//System.out.println(str);
			if(!str.isEmpty()) {
				String[]coordinates = str.split(",");
				double x_coor = Double.parseDouble(coordinates[0]);
				double y_coor = Double.parseDouble(coordinates[1]);
				double z_coor = Double.parseDouble(coordinates[2]);
				
				point = new Point3D(x_coor,y_coor,z_coor);
				//System.out.println("c: " + c + " " + point.toString());
				tempList.add(point);
				//System.out.println("X:"+coordinates[0] + "Y:"+coordinates[1] + "Z:"+coordinates[2]);
			}	
		}

		return tempList;	
	}
	
	/**
	 * The main method to run the DBScan Program
	 * @param args  Filename          from where the points to be read
	 * @param args  eps               the minimum radius around each points to search for neighbors
	 * @param args  minPts            the  threshHold for a Point3D to be a core point in a cluster cloud
	 * @throws FileNotFoundException  Exception if file could not be found
	 */
	public static void  main(String[]args)throws FileNotFoundException{
		
		DBScan dB ;
		double eps=0.0;
		double minPts=0.0;
		
		if(args.length == 3) {
			String fileName= args[0];
			try {
				 eps = Double.parseDouble(args[1]);
				 minPts = Double.parseDouble(args[2]);
			}catch(NumberFormatException e) {
				System.out.println("Parsing issues for doubles");
			}
			//reading file and saving points to list
			List<Point3D>listOfPoints = DBScan.read(fileName);
			dB = new DBScan(listOfPoints);
			dB.setEps(eps);
			dB.setMinPts(minPts);
			//System.out.println("xxxxxxxxxxxxxxxxxxxxxxxCLUSTERINGxxxxxxxxxxxxxxxxxx");
			System.out.println(fileName);
			//splitting the filename to match output req.
			String[] fileSplit = fileName.split("\\.");
			dB.findClusters();
			
			dB.save(fileSplit[0]+ "_clusters_" + eps+"_" + minPts + "_" +dB.getNumberOfCluster() + "." + fileSplit[1]);
			//System.out.println("Save done");
			dB.sort(); 
			System.out.println("The custer sizs in decreasing order:");
			dB.printClusterSizes();
			dB.printNoise();
		}else {
			System.out.println("Input format is not correct");
		}
		
	}


	
}
