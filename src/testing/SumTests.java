package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import application.Main;
import application.ParallelSum;
import application.SingleSum;

class SumTests {

	static int[] arr = null;
	static int singleSum, parallelSum;
	
	@BeforeAll
	@DisplayName("Should Create Random int[] With length = 200E6 and Compute singleSum and parallelSum Prior to All Tests.")
	static void setUpBeforeClass() throws Exception {
		System.out.println("Creating random array before all tests...");
		arr = Main.generateArray(arr);
		singleSum = SingleSum.sum(arr);
		parallelSum = ParallelSum.parallelArraySum(arr);
	}

	@Test
	@DisplayName("Should Compare parallelSum to singleSum and Return True Because They Are Equal.")
	void parallelSumShouldBeEqualToBeforeSum() {
		assertEquals(parallelSum, singleSum);
	}

	@Test
	@DisplayName("Should Compare parallelSum to singleSum+1 and Return True Because They Are NOT Equal.")
	void parallelSumShouldNotBeEqualToSingleSum() {
		assertNotEquals(ParallelSum.parallelArraySum(arr), SingleSum.sum(arr) + 1);
	}

	@Test
	@DisplayName("Should Compare singleSum and parallelSum using Main.areEqual Method and Return True Because They Are Equal")
	void areEqualShouldBeTrue() {
		assertEquals(Main.areEqual(singleSum, parallelSum), true);
	}
	
	@Test
	@DisplayName("Should Compare singleSum and parallelSum using Main.areEqual Method and Return False Because They Are Equal, But Comparing to False")
	void areEqualShouldNotBeFalse() {
		assertNotEquals(Main.areEqual(singleSum, parallelSum), false);
	}
	
}
