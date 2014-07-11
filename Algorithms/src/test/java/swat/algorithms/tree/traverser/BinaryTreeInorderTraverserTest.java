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
public class BinaryTreeInorderTraverserTest {

	@DataProvider(name = "inorderTraversalData")
	public static Object[][] inorderTraversalData() {
		// arg1 = tree string
		// arg2 = expected inorder traversal
		return new Object[][]{
				{"1(2(3,4),5(6))", new ArrayList<String>(Arrays.asList("3", "2", "4", "1", "6", "5"))},
				{"", new ArrayList<String>()},
				{"24()", new ArrayList<String>(Arrays.asList("24"))},
				{"7(3(1(0,2), 6(4(,5),)), 12(9(8, 11(10,)), 13(,15(14,))))",
						new ArrayList<String>(Arrays.asList("0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"))}
		};
	}

	@Test(dataProvider = "inorderTraversalData")
	public void testInorderTraversalUsingRecursion(String treeStr, List<String> expectedInorderTraversal) {
		BinaryTree<String> binaryTree = new BinaryTree<String>(treeStr);

		BinaryTreeTraverser<String> binaryTreeTraverser = new BinaryTreeInorderTraverser<String>(binaryTree);
		List<String> computedInorderTraversal = binaryTreeTraverser.getTraversalUsingRecursion();

		assert expectedInorderTraversal.equals(computedInorderTraversal);
	}

	@Test(dataProvider = "inorderTraversalData")
	public void testInorderTraversalUsingIteration(String treeStr, List<String> expectedInorderTraversal) {
		BinaryTree<String> binaryTree = new BinaryTree<String>(treeStr);

		BinaryTreeTraverser<String> binaryTreeTraverser = new BinaryTreeInorderTraverser<String>(binaryTree);
		List<String> computedInorderTraversal = binaryTreeTraverser.getTraversalUsingIteration();

		assert expectedInorderTraversal.equals(computedInorderTraversal);
	}
}
