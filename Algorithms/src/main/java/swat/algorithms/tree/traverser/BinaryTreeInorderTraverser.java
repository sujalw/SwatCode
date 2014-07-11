package swat.algorithms.tree.traverser;

import swat.algorithms.tree.BinaryTree;
import swat.algorithms.tree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Sujal
 */
public class BinaryTreeInorderTraverser<T> extends BinaryTreeTraverser<T> {

	BinaryTree<T> binaryTree;

	public BinaryTreeInorderTraverser(BinaryTree<T> binaryTree) {
		super(binaryTree);
	}

	@Override
	public List<T> getTraversalUsingRecursion() {
		assert binaryTree != null;

		List<T> inorderTraversal = new ArrayList<T>();
		getInorderTraversalUsingRecursion(binaryTree.getRoot(), inorderTraversal);

		return inorderTraversal;
	}

	private void getInorderTraversalUsingRecursion(BinaryTreeNode<T> node, List<T> inorderTraversal) {
		if(node == null) {
			return;
		}

		getInorderTraversalUsingRecursion(node.getlChild(), inorderTraversal);
		inorderTraversal.add(node.getKey());
		getInorderTraversalUsingRecursion(node.getrChild(), inorderTraversal);
	}

	@Override
	public List<T> getTraversalUsingIteration() {
		assert binaryTree != null;

		return getInorderTraversalUsingIteration(binaryTree.getRoot());
	}

	private List<T> getInorderTraversalUsingIteration(BinaryTreeNode<T> node) {
		List<T> inorderTraversal = new ArrayList<T>();

		if(node == null) {
			return inorderTraversal;
		}

		Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
		stack.push(node);

		while(true) {
			if(stack.isEmpty()) {
				break;
			}

			while((node != null) && ((node = node.getlChild()) != null)) {
				stack.push(node);
			}

			node = stack.pop();
			inorderTraversal.add(node.getKey());

			node = node.getrChild();
			if(node != null) {
				stack.push(node);
			}
		}

		return inorderTraversal;
	}
}
