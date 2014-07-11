package swat.algorithms.tree.traverser;

import swat.algorithms.tree.BinaryTree;
import swat.algorithms.tree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Sujal
 */
public class BinaryTreeLevelByLevelTraverser<T> extends BinaryTreeTraverser<T> {

	public BinaryTreeLevelByLevelTraverser(BinaryTree<T> binaryTree) {
		super(binaryTree);
	}

	@Override
	public List getTraversalUsingRecursion() {
		return null;
	}

	@Override
	public List getTraversalUsingIteration() {
		assert binaryTree != null;

		return getLevelByLevelTraversalUsingIteration(binaryTree.getRoot());
	}

	private List<T> getLevelByLevelTraversalUsingIteration(BinaryTreeNode<T> node) {

		if(node == null) {
			return new ArrayList<T>();
		}

		List<T> levelByLevelTraversal = new ArrayList<T>();
		Queue<BinaryTreeNode> nodes = new LinkedList<BinaryTreeNode>();
		nodes.add(node);

		BinaryTreeNode<T> currentNode = null;
		while(! nodes.isEmpty()) {
			currentNode = nodes.remove();
			levelByLevelTraversal.add(currentNode.getKey());

			if(currentNode.hasLeftChild()) {
				nodes.add(currentNode.getlChild());
			}

			if(currentNode.hasRightChild()) {
				nodes.add(currentNode.getrChild());
			}
		}

		return levelByLevelTraversal;
	}
}
