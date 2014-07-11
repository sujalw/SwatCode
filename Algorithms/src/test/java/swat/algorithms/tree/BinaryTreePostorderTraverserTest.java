package swat.algorithms.tree;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Sujal
 */
public class BinaryTreePostorderTraverserTest {

	@DataProvider(name = "postorderTraversalData")
	public static Object[][] postorderTraversalData() {
		// arg1 = tree string
		// arg2 = expected postorder traversal
		return new Object[][]{
				{"1(2(3,4),5(6))", new ArrayList<String>(Arrays.asList("3", "4", "2", "6", "5", "1"))},
				{"", new ArrayList<String>()},
				{"24()", new ArrayList<String>(Arrays.asList("24"))},
				{"7(3(1(0,2), 6(4(,5),)), 12(9(8, 11(10,)), 13(,15(14,))))",
						new ArrayList<String>(Arrays.asList("0","2","1","5","4","6","3","8","10","11","9","14","15","13","12","7"))}
		};
	}

	@Test(dataProvider = "postorderTraversalData")
	public void testPostorderTraversalUsingRecursion(String treeStr, List<String> expectedPostorderTraversal) {
		BinaryTree<String> binaryTree = new BinaryTree<String>(treeStr);

		BinaryTreeTraverser<String> binaryTreeTraverser = new BinaryTreePostorderTraverser<String>(binaryTree);
		List<String> computedPreorderTraversal = binaryTreeTraverser.getTraversalUsingRecursion();

		assert expectedPostorderTraversal.equals(computedPreorderTraversal);
	}

	@Test(dataProvider = "postorderTraversalData")
	public void testPostorderTraversalUsingIteration(String treeStr, List<String> expectedPostorderTraversal) {
		BinaryTree<String> binaryTree = new BinaryTree<String>(treeStr);

		BinaryTreeTraverser<String> binaryTreeTraverser = new BinaryTreePostorderTraverser<String>(binaryTree);
		List<String> computedPreorderTraversal = binaryTreeTraverser.getTraversalUsingIteration();

		assert expectedPostorderTraversal.equals(computedPreorderTraversal);
	}
}
