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
public class BinaryTreeVerticalOrderTraverserTest {

	@DataProvider(name = "verticalOrderTraversalData")
	public static Object[][] verticalOrderTraversalData() {
		// arg1 = tree string
		// arg2 = expected vertical order traversal
		return new Object[][]{
				{"1(2(3,4),5(6))", new ArrayList<String>(Arrays.asList("3", "2", "1", "4", "6", "5"))},
				{"", new ArrayList<String>()},
				{"24()", new ArrayList<String>(Arrays.asList("24"))},
				{"7(3(1(0,2), 6(4(,5),)), 12(9(8, 11(10,)), 13(,15(14,))))",
						new ArrayList<String>(Arrays.asList("0","1","3","2","4","8","7","6","5","9","10","12","11","13","14","15"))}
		};
	}

	@Test(dataProvider = "verticalOrderTraversalData")
	public void testverticalOrderTraversalUsingRecursion(String treeStr, List<String> expectedverticalOrderTraversal) {
		BinaryTree<String> binaryTree = new BinaryTree<String>(treeStr);

		BinaryTreeTraverser<String> binaryTreeTraverser = new BinaryTreeVerticalOrderTraverser<String>(binaryTree);
		List<String> computedverticalOrderTraversal = binaryTreeTraverser.getTraversalUsingRecursion();

		assert expectedverticalOrderTraversal.equals(computedverticalOrderTraversal);
	}
}
