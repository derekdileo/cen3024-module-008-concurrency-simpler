package application;

/** A class which detects available processors and uses 
 *  multi-threading to execute a parallel array sum.
 * @author derekdileo */
public class ParallelSum implements Runnable {

	// Local variables
	private int[] randomArray;
    private int start, end, partialSum;

    /* Constructor for ParallelSum with: 
     * @param array - array of values to be summed
     * @param start - array index position at which it begins traversal
     * @param end - array index position at which it terminates traversal */
    public ParallelSum(int[] array, int start, int end) {
    	this.randomArray = array;
    	this.start = start;
    	this.end = end;
    }

	/** Getter for partialSum variable
	 *  @return - sum of values in portion of array traversed by last-active Thread*/
    public int getPartialSum() {
    	return partialSum;
    }

	@Override
	public void run() {
		partialSum = sum(randomArray, start, end);
	}

	/** Method called inside run() to be executed by each Thread
	 * @param array - array of values to be summed
	 * @param start - array index position at which it begins traversal
     * @param end - array index position at which it terminates traversal 
	 * @return - partialSum int variable to run() */
	public static int sum(int[] array, int start, int end) {
		int total = 0;
		
		for (int i = start; i < end; i++) {
			total += array[i];
		}
		
		return total;
	}
	
	/** Overloaded Method which gathers the number of processors (noOfThreads) available
	 *  and passes that number into the parallelArraySum(int[] arr, int noOfThreads) Method.
	 * @param array - array of values to be summed
	 * @return parallelArraySum(int[] arr, int noOfThreads) Method which,
	 * in turn, returns the sum of all Threads.
	 */
	public static int parallelArraySum(int[] array) {
		return parallelArraySum(array, Runtime.getRuntime().availableProcessors());
	}
	
	/** Method which creates an array of ParallelSum threads and divides the 
	 *  array into equal parts to be summed by each Thread. Threads are joined 
	 *  and the resultant sum of each Thread is combined into a final sum to be returned.   
	 * @param array - array of values to be summed
	 * @param noOfThreads - gathered by Runtime.getRuntime().availableProcessors()
	 * @return - combined final sum of all Threads */
	public static int parallelArraySum(int[] array, int noOfThreads) {
		// Determine size of partitions that array is to be split into
		int size = (int)Math.ceil(array.length * 1.0 / noOfThreads);
		
		// Create arrays to hold ParallelSum and Thread objects
		ParallelSum[] sums = new ParallelSum[noOfThreads];
		Thread[] threads = new Thread[noOfThreads];
		
		/* For each available processor, instantiate a new ParallelSum object and its Thread.
		 * With (i*size) and ((i+1)*size) as start and end index location params */  
		for (int i = 0; i < noOfThreads; i++) {
			sums[i] = new ParallelSum(array, i * size, (i + 1) * size);
			threads[i] = new Thread(sums[i]);
			threads[i].start();
		}
		
		// Join threads to ensure one terminates before next begins
		try {
			for (Thread thread : threads) {
				thread.join();
			}
		} catch (InterruptedException e) {
			
		}
		
		// Execute final summation of returned partialSum from each Thread and print result
		int total = 0;
		for (ParallelSum sum : sums) {
			total += sum.getPartialSum();
		}
		
		System.out.println(ThreadColor.ANSI_CYAN + "\nParallel Thread sum total is: " + total);
		
		return total;
		
	}
	
}
