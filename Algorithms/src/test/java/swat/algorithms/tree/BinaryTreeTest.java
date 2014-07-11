package swat.algorithms.tree;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Sujal
 */
public class BinaryTreeTest {

    @DataProvider(name = "inorderTraversal")
    private static Object[][] treeDataForInorderTraversal() {
        // arg1 => tree elements
        // arg2 => expected inorder traversal
        return new Object[][] {
                {new int[]{}, new ArrayList<Integer>()},
                {new int[]{2}, new ArrayList<Integer>(Arrays.asList(2))},
                {new int[]{1, 2, 3, 4, 5, 6, 7}, new ArrayList<Integer>(Arrays.asList(4, 2, 5, 1, 6, 3, 7))},
                {new int[]{4, 2, 5, 1, 3}, new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5))}

        };
    }

    @DataProvider(name = "preorderTraversal")
    private static Object[][] treeDataForPreorderTraversal() {
        // arg1 => tree elements
        // arg2 => expected preorder traversal
        return new Object[][] {
                {new int[]{}, new ArrayList<Integer>()},
                {new int[]{2}, new ArrayList<Integer>(Arrays.asList(2))},
                {new int[]{1, 2, 3, 4, 5, 6, 7}, new ArrayList<Integer>(Arrays.asList(1, 2, 4, 5, 3, 6, 7))},
                {new int[]{4, 2, 5, 1, 3}, new ArrayList<Integer>(Arrays.asList(4, 2, 1, 3, 5))}
        };
    }

    @DataProvider(name = "postorderTraversal")
    private static Object[][] treeDataForPostorderTraversal() {
        // arg1 => tree elements
        // arg2 => expected postorder traversal
        return new Object[][] {
                {new int[]{}, new ArrayList<Integer>()},
                {new int[]{2}, new ArrayList<Integer>(Arrays.asList(2))},
                {new int[]{1, 2, 3, 4, 5, 6, 7}, new ArrayList<Integer>(Arrays.asList(4, 5, 2, 6, 7, 3, 1))},
                {new int[]{4, 2, 5, 1, 3}, new ArrayList<Integer>(Arrays.asList(1, 3, 2, 5, 4))}
        };
    }

    /*@Test(dataProvider = "inorderTraversal")
    public void testInorderTraversalUsingRecursion(int[] treeElements, List<Integer> expectedInorderTraversal) {
        BinaryTree binaryTree = new BinaryTree(treeElements);
        List<Integer> computedInorderTraversal = binaryTree.getInorderUsingRecursion();

        assert expectedInorderTraversal.equals(computedInorderTraversal);
    }

    @Test(dataProvider = "inorderTraversal")
    public void testInorderTraversalUsingIteration(int[] treeElements, List<Integer> expectedInorderTraversal) {
        BinaryTree binaryTree = new BinaryTree(treeElements);
        List<Integer> computedInorderTraversal = binaryTree.getInorderUsingIteration();

        assert expectedInorderTraversal.equals(computedInorderTraversal);
    }

    @Test(dataProvider = "preorderTraversal")
    public void testPreorderTraversalUsingRecursion(int[] treeElements, List<Integer> expectedPreorderTraversal) {
        BinaryTree binaryTree = new BinaryTree(treeElements);
        List<Integer> computedPreorderTraversal = binaryTree.getPreorderUsingRecursion();

        assert expectedPreorderTraversal.equals(computedPreorderTraversal);
    }

    @Test(dataProvider = "preorderTraversal")
    public void testPreorderTraversalUsingIteration(int[] treeElements, List<Integer> expectedPreorderTraversal) {
        BinaryTree binaryTree = new BinaryTree(treeElements);
        List<Integer> computedPreorderTraversal = binaryTree.getPreorderUsingIteration();

        assert expectedPreorderTraversal.equals(computedPreorderTraversal);
    }

    @Test(dataProvider = "postorderTraversal")
    public void testPostorderTraversalUsingRecursion(int[] treeElements, List<Integer> expectedPostorderTraversal) {
        BinaryTree binaryTree = new BinaryTree(treeElements);
        List<Integer> computedPostorderTraversal = binaryTree.getPostorderUsingRecursion();

        assert expectedPostorderTraversal.equals(computedPostorderTraversal);
    }

    @Test(dataProvider = "postorderTraversal")
    public void testPostorderTraversalUsingIteration(int[] treeElements, List<Integer> expectedPostorderTraversal) {
        BinaryTree binaryTree = new BinaryTree(treeElements);
        List<Integer> computedPostorderTraversal = binaryTree.getPostorderUsingIteration();

        assert expectedPostorderTraversal.equals(computedPostorderTraversal);
    }*/
}
