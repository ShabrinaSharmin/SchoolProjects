import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * This class validates the results generated 
 * @author Shabrina Sharmin
 * Student Id: 300230297
 * @version 1.0
 */

public class Exp1 {
	/*
	 * A list to hold the neighbors of any Point3D objects that are part of linear data structure
	 */
	public NearestNeighbors nB;
	/*
	 * A list to hold the neighbors of any Point3D objects that are part of KDtree data structure
	 */
	public NearestNeighborsKD nBTree;
	/*
	 * The distance used to find the neighbors of a any POint3D object
	 */
	public int eps;
	
	/*
	 * Constructor
	 */
	public Exp1(List<Point3D> allPointsList) {
		nB = new NearestNeighbors(allPointsList);
		nBTree = new NearestNeighborsKD(allPointsList);
//		refPoint.add(new Point3D(-5.429850155, 0.807567048 ,-0.398216823));
//		refPoint.add(new Point3D(-12.97637373, 5.09061138, 0.762238889));
//		refPoint.add(new Point3D(-36.10818686 ,14.2416184 ,4.293473762 ));
//		refPoint.add(new Point3D(3.107437007 ,0.032869335 ,0.428397562));
//		refPoint.add(new Point3D(11.58047393, 2.990601868 ,1.865463342));
//		refPoint.add(new Point3D(14.15982089 ,4.680702457 ,-0.133791584));
			
	}
	
	/*
	 * Finds the nearest neighbors for a given Point3D objects and a distance
	 * @param eps   distance used to find the neighbors
	 * @param point Point3D object for which neighbors are needed to be found
	 * @return  a list of all the Point3D objects that are neighbor
	 */
	public List<Point3D> findNeighbors(double eps,Point3D point){
		List<Point3D> tempList = nB.rangeQuery(eps, point);
		return tempList;
	}
	
	
	/*
	 * Finds the nearest neighbors for a given Point3D objects and a distance
	 * @param eps   distance used to find the neighbors
	 * @param point Point3D object for which neighbors are needed to be found
	 * @return      a list of all the Point3D objects that are neighbor
	 */
	public List<Point3D> findNeighborsKDTree(double eps,Point3D point){
		List<Point3D> tempList = nB.rangeQuery(eps, point);
		return tempList;
	}
	
	/*
	 * 
	 */
	public void writeToFile(String fileName, List<Point3D>neighborsList)  {
		StringBuilder line = new StringBuilder();
		line.append("Number of Neighbor found: "+ neighborsList.size()+"\n");
		try {
			File fName = new File("exp1");
			if (!fName.exists()) {
		        fName.mkdir();
		}
			FileWriter myWriter = new FileWriter(new File(fName.getAbsolutePath() +"/" +  fileName));
			line.append("x,y,z,c\n");
			for(Point3D point: neighborsList) {
				line.append(point.getX()+","+point.getY()+","+point.getZ()+","+point.getClusterLabel()+",");
				line.append("\n");
				//myWriter.write(line.toString());	
			}
			myWriter.write(line.toString());
			myWriter.close();
		}catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	
	/**
	 * Reads the points that represents Point3D objects and stores them in a list
	 * @param filename                       the filename to be read from
	 * @return                               the list that holds all the point3D objects 
	 * @throws FileNotFoundException         if the file could not be found
	 */
	public static List<Point3D> readFromFile(String filename) throws FileNotFoundException{
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
				//System.out.println("c: " + point.toString());
				tempList.add(point);
				//System.out.println("X:"+coordinates[0] + "Y:"+coordinates[1] + "Z:"+coordinates[2]);
			}	
		}

		return tempList;	
	}
	
	
	/*
	 * Main method
	 */
	public static void main(String[]args) throws FileNotFoundException{
		
		Exp1 experiment1;
		Double eps = 0.0;
		String fileName = null;
		String type=null;
		Point3D inputPoint;
		List<Double> coor_xyz = new ArrayList<>();
		
		if(args.length == 6 ) {
		
			try {
				type = args[0];
				eps = Double.parseDouble(args[1]);
				fileName = args[2];
				double x_coor = Double.parseDouble(args[3]);
				double y_coor = Double.parseDouble(args[4]);
				double z_coor = Double.parseDouble(args[5]);
				coor_xyz.add(x_coor);
				coor_xyz.add(y_coor);
				coor_xyz.add(z_coor);
				//inputPoint = new Point3D()
	
			}catch(NumberFormatException e) {
				System.out.println("");
			}
			
			List<Point3D> listOfPoints = Exp1.readFromFile(fileName);
			experiment1 = new Exp1(listOfPoints);
			List<Point3D>neighborListLinear=experiment1.findNeighbors(eps, (new Point3D(coor_xyz.get(0),coor_xyz.get(1),coor_xyz.get(2))));
			List<Point3D>neighborListKdTree=experiment1.findNeighborsKDTree(eps, (new Point3D(coor_xyz.get(0),coor_xyz.get(1),coor_xyz.get(2))));
			//String[] fileSplit = fileName.split("\\.");
//			for(int i=1;i<=experiment1.refPoint.size();i++) {
//				experiment1.writeToFile("pt"+i+"_"+type+".txt", neighborListLinear);
//				experiment1.writeToFile("pt"+i+"_"+type+".txt", neighborListKdTree);
//			}
			if(type.equalsIgnoreCase("lin")) {
				System.out.println("pt1_"+type+".txt");
				experiment1.writeToFile("pt1_"+type+".txt", neighborListLinear);
			}
			else {  //kdtree
				experiment1.writeToFile("pt1_"+type+".txt", neighborListKdTree);
			}
			
		}else{
			System.out.println("Input formating is not correct");
		}
			
		}
		
		
		
	}

