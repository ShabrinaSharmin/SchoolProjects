/**
 * This class represents the a Cluster and it's other characteristics (i.e:the label of any particular clusters
 * and the size of that cluster. 
 * @author Shabrina Sharmin
 * Student Id: 300230297
 * @version 1.0
 */
public class Clusters {
	/**
	 * The label associated to each cluster
	 */
	private int cid;
	/**
	 * The size of each cluster
	 */
	private int size;
	
	public Clusters(int numberOfCluster, int size) {
		this.cid = numberOfCluster;
		this.size = size;
	}
	/**
	 * Returns the label associated with the cluster
	 * @return an int representing the label
	 */
	public int getCid() {
		return cid;
	}
	
	/**
	 * Returns the size associated with the cluster
	 * @return an int representing the size of a particular cluster
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Sets the label associated with the cluster
	 * @param cid  the label of a Cluster
	 */
	public void setCid(int cid) {
		this.cid = cid;
	}
	
	/**
	 * Sets the size associated with the cluster
	 * @param size  the size of a Cluster
	 */
	public void setSize(int size) {
		this.size = size;
	}
}
