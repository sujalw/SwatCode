package swat.algorithms.tree;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
				{"1(2(3,4),5(6))", new ArrayList<String>(Arrays.asList("3", "2", "4", "1", "6", "5"))}
		};
	}

	@Test(dataProvider = "inorderTraversalData")
	public void testInorderTraversalUsingRecursion(String treeStr, List<String> expectedInorderTraversal) {
		BinaryTree<String> binaryTree = new BinaryTree<String>(treeStr);

		BinaryTreeTraverser<String> binaryTreeTraverser = new BinaryTreeInorderTraverser<String>(binaryTree);
		List<String> computedInorderTraversal = binaryTreeTraverser.getTraversalUsingRecursion();

		assert expectedInorderTraversal.equals(computedInorderTraversal);
	}
}
