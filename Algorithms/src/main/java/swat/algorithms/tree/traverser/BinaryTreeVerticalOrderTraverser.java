package swat.algorithms.tree.traverser;

import swat.algorithms.tree.BinaryTree;
import swat.algorithms.tree.BinaryTreeNode;

import java.util.*;

/**
 * @author Sujal
 */
public class BinaryTreeVerticalOrderTraverser<T> extends BinaryTreeTraverser<T> {

	public BinaryTreeVerticalOrderTraverser(BinaryTree<T> binaryTree) {
		super(binaryTree);
	}

	@Override
	public List<T> getTraversalUsingRecursion() {
		assert binaryTree != null;

		Map<Integer, List<BinaryTreeNode>> verticalTraversalByLevel = new HashMap<Integer, List<BinaryTreeNode>>();
		computePreorderTraversalWithVerticalLevel(binaryTree.getRoot(), verticalTraversalByLevel, 0);
		List<T> verticalTraversal = getFlattenedTraversalByVerticalLevel(verticalTraversalByLevel);
		return verticalTraversal;
	}

	private void computePreorderTraversalWithVerticalLevel(BinaryTreeNode<T> node, Map<Integer, List<BinaryTreeNode>> verticalTraversalByLevel, int currentVerticalLevel) {
		if(node == null) {
			return;
		}

		List<BinaryTreeNode> nodesAtLevel = new ArrayList<BinaryTreeNode>();
		if(verticalTraversalByLevel.containsKey(currentVerticalLevel)) {
			nodesAtLevel = verticalTraversalByLevel.get(currentVerticalLevel);
		}

		nodesAtLevel.add(node);
		verticalTraversalByLevel.put(currentVerticalLevel, nodesAtLevel);
		computePreorderTraversalWithVerticalLevel(node.getlChild(), verticalTraversalByLevel, currentVerticalLevel - 1);
		computePreorderTraversalWithVerticalLevel(node.getrChild(), verticalTraversalByLevel, currentVerticalLevel + 1);
	}

	private List<T> getFlattenedTraversalByVerticalLevel(Map<Integer, List<BinaryTreeNode>> verticalTraversalByLevel) {
		if(verticalTraversalByLevel == null) {
			return null;
		}

		List<T> flattenedVerticalTraversal = new ArrayList<T>();
		List<Integer> levels = new ArrayList<Integer>(verticalTraversalByLevel.keySet());
		Collections.sort(levels);

		for(Integer level : levels) {
			List<BinaryTreeNode> nodesAtCurrentLevel = verticalTraversalByLevel.get(level);
			for(BinaryTreeNode node : nodesAtCurrentLevel) {
				flattenedVerticalTraversal.add((T) node.getKey());
			}
		}

		return flattenedVerticalTraversal;
	}

	@Override
	public List<T> getTraversalUsingIteration() {
		return null;
	}
}
