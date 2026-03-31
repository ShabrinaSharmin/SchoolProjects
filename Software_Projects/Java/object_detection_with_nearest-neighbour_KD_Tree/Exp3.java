import java.io.FileNotFoundException;
import java.util.List;

/**
 * This class validates the Compilation time for the  
 * @author Shabrina Sharmin
 * Student Id: 300230297
 * @version 1.0
 */
public class Exp3 {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException {
		DBScan dB ;
		double eps=0.0;
		double minPts=0.0;
		
		Long totalEndTime;
		if(args.length == 3) {
			String fileName= args[0];
			try {
				 eps = Double.parseDouble(args[1]);
				 minPts = Double.parseDouble(args[2]);
			}catch(NumberFormatException e) {
				System.out.println("Parsing issues for doubles");
			}
			//reading file and saving points to list
			Long totalStartTime = System.nanoTime();
			List<Point3D>listOfPoints = DBScan.read(fileName);
			Long fileReadTime = System.nanoTime();
			dB = new DBScan(listOfPoints);
			Long treeConstructionTime = System.nanoTime();
			dB.setEps(eps);
			dB.setMinPts(minPts);
			//System.out.println("xxxxxxxxxxxxxxxxxxxxxxxCLUSTERINGxxxxxxxxxxxxxxxxxx");
			System.out.println(fileName);
			//splitting the filename to match output req.
			String[] fileSplit = fileName.split("\\.");
			Long tempTime = System.nanoTime();
			dB.findClusters();
			Long rangeQueryTime = System.nanoTime();
			dB.save(fileSplit[0]+ "_clusters_" + eps+"_" + minPts + "_" +dB.getNumberOfCluster() + "." + fileSplit[1]);
			//System.out.println("Save done");
			dB.sort(); 
			System.out.println("The custer sizs in decreasing order:");
			dB.printClusterSizes();
			dB.printNoise();
			totalEndTime = System.nanoTime();
			System.out.println("Runtime information:");
			System.out.println("The total program runtime was (ms): " +  (totalEndTime - totalStartTime)/1000000);
			System.out.println("\tOf which file read time (ms) was: " +  (fileReadTime - totalStartTime)/1000000 + 
					"\n\tTree construction time (ms) was: " + (treeConstructionTime- fileReadTime)/1000000  +
					"\n\tCluster search time(ms) was: " + (rangeQueryTime - tempTime)/1000000);
			
		}else {
			System.out.println("Input format is not correct");
		}
		

	}

}
