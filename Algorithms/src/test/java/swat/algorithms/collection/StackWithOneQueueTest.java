package swat.algorithms.collection;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Sujal
 */
public class StackWithOneQueueTest {

	@DataProvider(name = "stackData")
	public static Object[][] stackData() {
		return new Object[][] {
				{new int[]{1}, new int[]{1}},
				{new int[]{1, 2}, new int[]{2, 1}},
				{new int[]{1, 2, 3, 4, 5}, new int[]{5, 4, 3, 2, 1}},
				{new int[]{5, 3, 9}, new int[]{9, 3, 5}}
		};
	}

	@Test(dataProvider = "stackData")
	public void testStackOperations(int[] inputElements, int[] expectedPoppedElements) {
		StackWithOneQueue<Integer> stackWithOneQueue = new StackWithOneQueue<Integer>();

		for(Integer i : inputElements) {
			stackWithOneQueue.push(i);
		}

		assert inputElements.length == stackWithOneQueue.size();

		for(Integer i : expectedPoppedElements) {
			assert i == stackWithOneQueue.pop();
		}
	}
}
