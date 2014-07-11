package swat.algorithms.tree.traverser;

import swat.algorithms.tree.BinaryTree;

import java.util.List;

/**
 * @author Sujal
 */
public abstract class BinaryTreeTraverser<T> {

	BinaryTree<T> binaryTree;

	public BinaryTreeTraverser(BinaryTree<T> binaryTree) {
		this.binaryTree = binaryTree;
	}

	public abstract List<T> getTraversalUsingRecursion();
	public abstract List<T> getTraversalUsingIteration();
}
