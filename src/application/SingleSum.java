package application;

/* 
 * 
 * */
public class SingleSum implements Runnable {

	private int[] randomArray;
	private int start, end, singleSum;
	
	public SingleSum(int[] array, int start, int end) {
		this.randomArray = array;
		this.start = start;
		this.end = end;
	}
	
	public SingleSum(int[] array) {
		this.randomArray = array;
		this.start = 0;
		this.end = array.length;
	}
	
	@Override
	public void run() {
		singleSum = sum(randomArray, start, end);
	}
	
	
	public int getSingleSum() {
		return singleSum;
	}
	
	public static int sum(int[] array) {
		return sum(array, 0, array.length);
	}


	public static int sum(int[] array, int start, int end) {
		int total = 0;	
		
		for (int i = start; i < end; i++) {
			total += array[i];
		}
		
		return total;
	}

	
	public static int singleSum(int[] array) {
		
		SingleSum sum = new SingleSum(array);
		Thread sumThread = new Thread(sum);
		sumThread.start();

		while(sumThread.isAlive()) {}
		
		int total = 0;
		
		total += sum.getSingleSum();
		
		System.out.println(ThreadColor.ANSI_PURPLE + "Single Thread sum total is: " + total);
		
		return total;
		
	}
	
}
