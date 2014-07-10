package swat.algorithms.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author Sujal
 */
public class BinaryTreePostorderTraverser<T> implements BinaryTreeTraverser {

	BinaryTree<T> binaryTree;

	public BinaryTreePostorderTraverser(BinaryTree<T> binaryTree) {
		this.binaryTree = binaryTree;
	}

	@Override
	public List<T> getTraversalUsingRecursion() {
		assert binaryTree != null;

		List<T> postorderTraversal = new ArrayList<T>();
		getPostorderTraversalUsingRecursion(binaryTree.getRoot(), postorderTraversal);

		return postorderTraversal;
	}

	private void getPostorderTraversalUsingRecursion(BinaryTreeNode<T> node, List<T> postorderTraversal) {
		if(node == null) {
			return;
		}

		getPostorderTraversalUsingRecursion(node.getlChild(), postorderTraversal);
		getPostorderTraversalUsingRecursion(node.getrChild(), postorderTraversal);
		postorderTraversal.add(node.getKey());
	}

	@Override
	public List<T> getTraversalUsingIteration() {
		assert binaryTree != null;

		return getPostorderTraversalUsingIteration(binaryTree.getRoot());
	}

	private List<T> getPostorderTraversalUsingIteration(BinaryTreeNode<T> node) {
		if(node == null) {
			return new ArrayList<T>();
		}

		List<T> reversePreorderTraversal = getReversePreorderTraversalUsingIteration(node);
		if(reversePreorderTraversal == null) {
			return new ArrayList<T>();
		}

		Collections.reverse(reversePreorderTraversal);
		return reversePreorderTraversal;
	}

	private List<T> getReversePreorderTraversalUsingIteration(BinaryTreeNode<T> node) {
		List<T> reversePreorder = new ArrayList<T>();

		if(node == null) {
			return reversePreorder;
		}

		Stack<BinaryTreeNode> reversePreorderTraversalStack = new Stack<BinaryTreeNode>();

		reversePreorder.add(node.getKey());
		reversePreorderTraversalStack.push(node);

		while(! reversePreorderTraversalStack.isEmpty()) {
			while((node != null) && ((node = node.getrChild()) != null)) {
				reversePreorder.add(node.getKey());
				reversePreorderTraversalStack.push(node);
			}

			node = reversePreorderTraversalStack.pop();
			node = node.getlChild();
			if(node != null) {
				reversePreorder.add(node.getKey());
				reversePreorderTraversalStack.push(node);
			}
		}

		return reversePreorder;
	}
}
