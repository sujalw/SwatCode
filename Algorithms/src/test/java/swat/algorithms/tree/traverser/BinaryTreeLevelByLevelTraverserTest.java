package swat.algorithms.tree.traverser;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import swat.algorithms.tree.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Sujal
 */
public class BinaryTreeLevelByLevelTraverserTest {

	@DataProvider(name = "levelByLevelTraversalData")
	public static Object[][] levelByLevelTraversalData() {
		// arg1 = tree string
		// arg2 = expected level-by-level traversal
		return new Object[][]{
				{"1(2(3,4),5(6))", new ArrayList<String>(Arrays.asList("1", "2", "5", "3", "4", "6"))},
				{"", new ArrayList<String>()},
				{"24()", new ArrayList<String>(Arrays.asList("24"))},
				{"7(3(1(0,2), 6(4(,5),)), 12(9(8, 11(10,)), 13(,15(14,))))",
						new ArrayList<String>(Arrays.asList("7","3","12","1","6","9","13","0","2","4","8","11","15","5","10","14"))}
		};
	}

	@Test(dataProvider = "levelByLevelTraversalData")
	public void testLevelByLevelTraversalUsingIteration(String treeStr, List<String> expectedLevelByLevelTraversal) {
		BinaryTree<String> binaryTree = new BinaryTree<String>(treeStr);

		BinaryTreeTraverser<String> binaryTreeTraverser = new BinaryTreeLevelByLevelTraverser<String>(binaryTree);
		List<String> computedLevelByLevelTraversal = binaryTreeTraverser.getTraversalUsingIteration();

		assert expectedLevelByLevelTraversal.equals(computedLevelByLevelTraversal);
	}
}
