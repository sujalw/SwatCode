package swat.algorithms.tree;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Sujal
 */
public class BinaryTreePreorderTraverserTest {

	@DataProvider(name = "preorderTraversalData")
	public static Object[][] preorderTraversalData() {
		// arg1 = tree string
		// arg2 = expected preorder traversal
		return new Object[][]{
				{"1(2(3,4),5(6))", new ArrayList<String>(Arrays.asList("1", "2", "3", "4", "5", "6"))},
				{"", new ArrayList<String>()},
				{"24()", new ArrayList<String>(Arrays.asList("24"))},
				{"7(3(1(0,2), 6(4(,5),)), 12(9(8, 11(10,)), 13(,15(14,))))",
						new ArrayList<String>(Arrays.asList("7","3","1","0","2","6","4","5","12","9","8","11","10","13","15","14"))}
		};
	}

	@Test(dataProvider = "preorderTraversalData")
	public void testPreorderTraversalUsingRecursion(String treeStr, List<String> expectedPreorderTraversal) {
		BinaryTree<String> binaryTree = new BinaryTree<String>(treeStr);

		BinaryTreeTraverser<String> binaryTreeTraverser = new BinaryTreePreorderTraverser<String>(binaryTree);
		List<String> computedPreorderTraversal = binaryTreeTraverser.getTraversalUsingRecursion();

		assert expectedPreorderTraversal.equals(computedPreorderTraversal);
	}

	@Test(dataProvider = "preorderTraversalData")
	public void testPreorderTraversalUsingIteration(String treeStr, List<String> expectedPreorderTraversal) {
		BinaryTree<String> binaryTree = new BinaryTree<String>(treeStr);

		BinaryTreeTraverser<String> binaryTreeTraverser = new BinaryTreePreorderTraverser<String>(binaryTree);
		List<String> computedPreorderTraversal = binaryTreeTraverser.getTraversalUsingIteration();

		assert expectedPreorderTraversal.equals(computedPreorderTraversal);
	}
}
