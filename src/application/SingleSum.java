package application;

/** A class which uses a single thread to execute an array sum.
 * @author derekdileo */
public class SingleSum implements Runnable {

	// Local instance variables
	private int[] randomArray;
	private int start, end, singleSum;
	
	// Standard constructor
	public SingleSum(int[] array, int start, int end) {
		this.randomArray = array;
		this.start = start;
		this.end = end;
	}
	
	// Overloaded constructor
	public SingleSum(int[] array) {
		this.randomArray = array;
		this.start = 0;
		this.end = array.length;
	}
	
	@Override
	public void run() {
		singleSum = sum(randomArray, start, end);
	}
	
	/** Getter for singleSum instance variable
	 *  @return - sum of all array values */
	public int getSingleSum() {
		return singleSum;
	}
	
	/** Overloaded sum Method
	 * @param array - array of values to be summed
	 * @return standard arguments to traverse array and sum all indexes. */
	public static int sum(int[] array) {
		return sum(array, 0, array.length);
	}

	/** Method used to traverse an array and sum the values from start to end
	 * @param array - array of values to be summed
	 * @param start - array index position at which it begins traversal
     * @param end - array index position at which it terminates traversal 
	 * @return - singleSum int variable to run() */
	public static int sum(int[] array, int start, int end) {
		int total = 0;	
		
		for (int i = start; i < end; i++) {
			total += array[i];
		}
		
		return total;
	}

	/** Method which instantiates a Thread from a SingleSum(array) object. 
	 *  When Thread is started, sum(array) is called and resultant sum is returned.    
	 * @param array - array of values to be summed
	 * @return - combined sum of all values in array */
	public static int singleSum(int[] array) {
		
		// Instantiate SingleSum Thread and start
		SingleSum sum = new SingleSum(array);
		Thread sumThread = new Thread(sum);
		sumThread.start();
		
		// Wait until thread dies before continuing
		while(sumThread.isAlive()) {}
		
		
		/* Execute final summa from each Thread and print result */
		int total = sum.getSingleSum();
		
		System.out.println(ThreadColor.ANSI_PURPLE + "\nSingle Thread sum total is: " + total);
		
		return total;
		
	}
	
}
