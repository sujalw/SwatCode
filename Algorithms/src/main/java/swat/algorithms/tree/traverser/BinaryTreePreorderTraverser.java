package swat.algorithms.tree.traverser;

import swat.algorithms.tree.BinaryTree;
import swat.algorithms.tree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Sujal
 */
public class BinaryTreePreorderTraverser<T> implements BinaryTreeTraverser {

	BinaryTree<T> binaryTree;

	public BinaryTreePreorderTraverser(BinaryTree<T> binaryTree) {
		this.binaryTree = binaryTree;
	}

	@Override
	public List getTraversalUsingRecursion() {
		assert binaryTree != null;

		List<T> preorderTraversal = new ArrayList<T>();
		getPreorderTraversalUsingRecursion(binaryTree.getRoot(), preorderTraversal);

		return preorderTraversal;
	}

	private void getPreorderTraversalUsingRecursion(BinaryTreeNode<T> node, List<T> preorderTraversal) {
		if(node == null) {
			return;
		}

		preorderTraversal.add(node.getKey());
		getPreorderTraversalUsingRecursion(node.getlChild(), preorderTraversal);
		getPreorderTraversalUsingRecursion(node.getrChild(), preorderTraversal);
	}

	@Override
	public List<T> getTraversalUsingIteration() {
		assert binaryTree != null;

		return getPreorderTraversalUsingIteration(binaryTree.getRoot());
	}

	private List<T> getPreorderTraversalUsingIteration(BinaryTreeNode<T> node) {
		List<T> preorderTraversal = new ArrayList<T>();
		if(node == null) {
			return preorderTraversal;
		}

		Stack<BinaryTreeNode> binaryTreeNodeStack = new Stack<BinaryTreeNode>();

		preorderTraversal.add(node.getKey());
		binaryTreeNodeStack.push(node);

		while(! binaryTreeNodeStack.isEmpty()) {
			while((node != null) && ((node = node.getlChild()) != null)) {
				preorderTraversal.add(node.getKey());
				binaryTreeNodeStack.push(node);
			}

			node = binaryTreeNodeStack.pop();
			node = node.getrChild();
			if(node != null) {
				preorderTraversal.add(node.getKey());
				binaryTreeNodeStack.push(node);
			}
		}

		return preorderTraversal;
	}
}
