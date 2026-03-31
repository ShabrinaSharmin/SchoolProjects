import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class validates the Compilation time for the  
 * @author Shabrina Sharmin
 * Student Id: 300230297
 * @version 1.0
 */
public class Exp2 {
	public NearestNeighbors nB;
	public NearestNeighborsKD nBTree;
	public List<Long> timeLinear;
	public List<Long> timeTree;
	public int eps;
	
	/*
	 * Constructor
	 */
	public Exp2(List<Point3D> allPointsList) {
		nB = new NearestNeighbors(allPointsList);
		nBTree = new NearestNeighborsKD(allPointsList);
		timeTree = new ArrayList<Long>();
		timeLinear = new ArrayList<Long>();
	}
	
	/**
	 * 
	 * @param eps
	 * @param point
	 * @return
	 */
	public List<Point3D> findNeighborsLinearRequiredTime(double eps,Point3D point){
		Long startTime = System.nanoTime();
		List<Point3D> tempList = nB.rangeQuery(eps, point);
		Long endTime = System.nanoTime();
		timeLinear.add((endTime-startTime));
		return tempList;
	}
	
	
	/**
	 * 
	 * @param eps
	 * @param point
	 * @return
	 */
	public List<Point3D> findNeighborsKDTreeRequiredTime(double eps,Point3D point){
		Long startTime = System.nanoTime();
		List<Point3D> tempList = nBTree.rangeQuery(eps, point);
		Long endTime = System.nanoTime();
		timeTree.add(endTime-startTime);
		//System.out.println(timeTree.size()+ startTime.toString() + endTime.toString());
		return tempList;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public double calculateAverageLinearRunningTime() {
		//System.out.println("size lin:" +timeLinear.size());
		long sum=0;
		double avg=0;
		for(long p : timeLinear) {
			sum = sum+p;
		}
		avg = sum/timeLinear.size();
		return avg/1000000;
		
	}
	
	
	/**
	 * 
	 * @return
	 */
	public double calculateAverageTreeRunningTime() {
		//System.out.println("size kd:" +timeTree.size());
		long sum=0;
		double avg=0;
		//System.out.println(timeTree.size());
		for(long p : timeTree) {
			sum = sum+p;
		}
		avg = sum/timeTree.size();
		//avg = TimeUnit.MILLISECONDS.convert(avg, TimeUnit.NANOSECONDS);
		return (avg)/1000000;
		
	}
	
	/**
	 * 
	 * @param filename
	 * @return
	 * @throws FileNotFoundException
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
	
//	private void printNeighbors(List<Point3D>l) {
//		for(Point3D p : l) {
//			System.out.println(p.toString());
//		}
//		
//	}
	/**
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[]args) throws FileNotFoundException{
		
		Exp2 experiment2;
		Double eps = 0.0;
		String fileName = null;
		String type=null;
		int step = 0;
		//List<Double> coor_xyz = new ArrayList<>();
		
		if(args.length == 4 ) {
		
			try {
				type = args[0];
				eps = Double.parseDouble(args[1]);
				fileName = args[2];
				step = Integer.parseInt(args[3]);
	
			}catch(NumberFormatException e) {
				System.out.println("Double parsing issur for input");
			}
			
			List<Point3D> listOfPoints = Exp2.readFromFile(fileName);
			experiment2 = new Exp2(listOfPoints);
			if(type.equalsIgnoreCase("lin")) {
				for( int i = 0; i<listOfPoints.size(); i+=step) {
					Point3D p = listOfPoints.get(i);
					List<Point3D> tempListNb = experiment2.findNeighborsLinearRequiredTime(step,p );
					//System.out.println("Neighbors for point ##lin:" + p.toString());
					//experiment2.printNeighbors(tempListNb);
				}
				double tLNb = experiment2.calculateAverageLinearRunningTime();
				System.out.println("Linear average Time:" + tLNb);
			}
			else {  //kdtree
				for( int i = 0; i<listOfPoints.size(); i+=step) {
					Point3D p = listOfPoints.get(i);
					List<Point3D> tempListKd = experiment2.findNeighborsKDTreeRequiredTime(step,p );
					//System.out.println("Size of the neighbors for point: ####"+tempListKd.size());
					//System.out.println("Neighbors for point ##kdTree:" + p.toString());
					//experiment2.printNeighbors(tempListKd);
					
				}
				//System.out.println(experiment2.timeTree.size());
				double tLKd = experiment2.calculateAverageTreeRunningTime();
				System.out.println("KDtree average Time:" + tLKd);
			}
			
		}else{
			System.out.println("Input formating is not correct");
		}
			
		}
		
	
	
	
}
