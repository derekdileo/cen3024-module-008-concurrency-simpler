package application;

import java.util.Random;

/** Using an array which contains 200 million randomly-generated numbers between 1 and 10,
 *  this application implements a parallel array sum and compares performance with single thread sum.
 *  The resulting sums and execution times are then printed to the console.
 * @author derekdileo */
public class Main {
	
	// Local variables for randomArray
	protected static int arraySize = 200000000;
	protected static int[] randomArray = null;
	private static Random random = new Random();

	// Local variables for sum and runTime of each 
	private static int singleSumTotal, parallelSumTotal;
	private static long startTime;

	// Proverbial main method
	public static void main(String[] args) {

		// Create array with 200M random numbers between 1 & 10
		if(randomArray == null) {
			randomArray = generateArray();
		}
		
		// Store start time, execute singleSum on array, print results
		startTime = System.currentTimeMillis();
		singleSumTotal = SingleSum.singleSum(randomArray);
		System.out.println(ThreadColor.ANSI_PURPLE + "Single Thread sum time: " + (System.currentTimeMillis() - startTime) + " ms");
		
		
		// Store start time, execute parallelArraySum, print results
		startTime = System.currentTimeMillis();
		parallelSumTotal = ParallelSum.parallelArraySum(randomArray);
		System.out.println(ThreadColor.ANSI_CYAN + "Parallel Thread sum time is: " + (System.currentTimeMillis() - startTime) + " ms");
		
		// Determine if resulting sums are equal, print to console
		printEqual(singleSumTotal, parallelSumTotal);
		
	}
	
	/* Method to generate an array of random numbers to be summed.
	 * @return - an array of randomly-generated numbers with length = arraySize */
	public static int[] generateArray() {
		// Initialize array
		randomArray = new int[arraySize];
		
		// Generate random number at each index from 1-10
		for(int i = 0; i < arraySize; i++) {
			randomArray[i] = random.nextInt(10) + 1;
		}
		
		return randomArray;
	}
	
	/** A Method to check if two ints are equal using subtraction and == 0.
	 * @param s1 - singleSumTotal
	 * @param s2 - parallelSumTotal
	 * @return - boolean value of true (if equal) or false (if not) */
	public static boolean areEqual(int s1, int s2) {
		if(s1 - s2 == 0) {
			return true;
		}
		return false;
	}
	
	/** Method to print results of areEqual using ThreadColor Class for effect
	 * @param s1 - singleSumTotal
	 * @param s2 - parallelSumTotal */
	public static void printEqual(int s1, int s2) {
		boolean value = areEqual(s1, s2);
		
		if(value) {
			System.out.println(ThreadColor.ANSI_GREEN + "\nThe two sums are equal!");
		} else {
			System.out.println(ThreadColor.ANSI_RED + "\nThe two sums are NOT equal!");
		}
	}
	
}
