package swat.algorithms.tree;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Sujal
 */
public class BinaryTreeTest {

    @DataProvider(name = "inorderTraversal")
    private static Object[][] treeDataForInorderTraversal() {
        return new Object[][] {
                {new int[]{1, 2, 3, 4, 5, 6, 7}, new ArrayList<Integer>(Arrays.asList(4, 2, 5, 1, 6, 3, 7))},
                {new int[]{4, 2, 5, 1, 3}, new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5))}
        };
    }

    @DataProvider(name = "preorderTraversal")
    private static Object[][] treeDataForPreorderTraversal() {
        return new Object[][] {
                {new int[]{1, 2, 3, 4, 5, 6, 7}, new ArrayList<Integer>(Arrays.asList(1, 2, 4, 5, 3, 6, 7))},
                {new int[]{4, 2, 5, 1, 3}, new ArrayList<Integer>(Arrays.asList(4, 2, 1, 3, 5))}
        };
    }

    @DataProvider(name = "postorderTraversal")
    private static Object[][] treeDataForPostorderTraversal() {
        return new Object[][] {
                {new int[]{1, 2, 3, 4, 5, 6, 7}, new ArrayList<Integer>(Arrays.asList(4, 5, 2, 6, 7, 3, 1))},
                {new int[]{4, 2, 5, 1, 3}, new ArrayList<Integer>(Arrays.asList(1, 3, 2, 5, 4))}
        };
    }

    @Test(dataProvider = "inorderTraversal")
    public void testInorderTraversalThroughRecursion(int[] treeElements, List<Integer> expectedInorderTraversal) {
        BinaryTree binaryTree = new BinaryTree(treeElements);
        List<Integer> computedInorderTraversal = binaryTree.getInorderThroughRecursion();

        assert computedInorderTraversal.equals(expectedInorderTraversal);
    }

    @Test(dataProvider = "inorderTraversal")
    public void testInorderTraversalThroughIteration(int[] treeElements, List<Integer> expectedInorderTraversal) {
        BinaryTree binaryTree = new BinaryTree(treeElements);
        List<Integer> computedInorderTraversal = binaryTree.getInorderThroughIteration();

        assert computedInorderTraversal.equals(expectedInorderTraversal);
    }

    @Test(dataProvider = "preorderTraversal")
    public void testPreorderTraversalThroughRecursion(int[] treeElements, List<Integer> expectedPreorderTraversal) {
        BinaryTree binaryTree = new BinaryTree(treeElements);
        List<Integer> computerPreorderTraversal = binaryTree.getPreorderThroughRecursion();

        assert computerPreorderTraversal.equals(expectedPreorderTraversal);
    }

    @Test(dataProvider = "postorderTraversal")
    public void testPostorderTraversalThroughRecursion(int[] treeElements, List<Integer> expectedPostorderTraversal) {
        BinaryTree binaryTree = new BinaryTree(treeElements);
        List<Integer> computerPostorderTraversal = binaryTree.getPostorderThroughRecursion();

        assert computerPostorderTraversal.equals(expectedPostorderTraversal);
    }
}
