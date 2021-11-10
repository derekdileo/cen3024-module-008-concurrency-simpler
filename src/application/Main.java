package application;

import java.util.Random;


/* Implement a parallel array sum, and compare performance with single thread sum. 
 * This is not an easy assignment – just do as much as you can and turn in what you have for partial credit.
 * Make an array of 200 million random numbers between 1 and 10. 
 * Compute the sum in parallel using multiple threads. 
 * Then compute the sum with only one thread, and display the sum and times for both cases.
 * */

public class Main {
	
	// Local variables for randomArray
	protected static int arraySize = 200000000;
	protected static int[] randomArray = null;
	private static Random random = new Random();

	// Local variables for sum and runTime of each 
	private static int singleSumTotal, parallelSumTotal;
	private static long startTime, runTime;

	
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		// Create array with 200M random numbers between 1 & 10
		if(randomArray == null) {
			randomArray = generateArray();
		}
		
		SingleSum singleSumTest = new SingleSum(randomArray);
		startTime = System.currentTimeMillis();
		
		singleSumTotal = SingleSum.singleSum(randomArray);
		runTime = System.currentTimeMillis() - startTime;
		
		System.out.println(ThreadColor.ANSI_PURPLE + "Single Thread sum time: " + runTime + " ms");
		
		
		
		startTime = System.currentTimeMillis();
		
		parallelSumTotal = ParallelSum.parallelArraySum(randomArray);
		
		runTime = System.currentTimeMillis() - startTime;
		
		System.out.println(ThreadColor.ANSI_CYAN + "Parallel Thread sum time is: " + runTime + " ms");
		
		
		boolean areEqual = false;
		
		if (singleSumTotal - parallelSumTotal == 0) {
			areEqual = true;
		}
		
		System.out.println(ThreadColor.ANSI_RESET + "Are they equal?   " + areEqual);
		
	}

	
	/* Method to generate an array of random numbers */
	public static int[] generateArray() {
		// Initialize array
		randomArray = new int[arraySize];
		
		// Generate random number at each index from 1-10
		for(int i = 0; i < arraySize; i++) {
			randomArray[i] = random.nextInt(10) + 1;
		}
		
		return randomArray;
	}
	
}
