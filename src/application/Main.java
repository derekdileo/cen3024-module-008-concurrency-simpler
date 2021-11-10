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

	
	public static void main(String[] args) {

		// Create array with 200M random numbers between 1 & 10
		if(randomArray == null) {
			randomArray = generateArray();
		}
		
		startTime = System.currentTimeMillis();
		
		singleSumTotal = SingleSum.singleSum(randomArray);
		runTime = System.currentTimeMillis() - startTime;
		
		System.out.println(ThreadColor.ANSI_PURPLE + "Single Thread sum time: " + runTime + " ms");
		
		
		
		startTime = System.currentTimeMillis();
		
		parallelSumTotal = ParallelSum.parallelArraySum(randomArray);
		
		runTime = System.currentTimeMillis() - startTime;
		
		System.out.println(ThreadColor.ANSI_CYAN + "Parallel Thread sum time is: " + runTime + " ms");
		
		printEqual(singleSumTotal, parallelSumTotal);
		
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
	
	public static boolean areEqual(int s1, int s2) {
		if(s1 - s2 == 0) {
			return true;
		}
		return false;
	}
	
	public static void printEqual(int s1, int s2) {
		boolean value = areEqual(s1, s2);
		
		if(value) {
			System.out.println(ThreadColor.ANSI_GREEN + "The two sums are equal!");
		} else {
			System.out.println(ThreadColor.ANSI_RED + "The two sums are NOT equal!");
		}
	}
	
}
