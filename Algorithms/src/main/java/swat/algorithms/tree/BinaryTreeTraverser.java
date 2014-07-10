package swat.algorithms.tree;

import java.util.List;

/**
 * @author Sujal
 */
public interface BinaryTreeTraverser<T> {
	public List<T> getTraversalUsingRecursion();
	public List<T> getTraversalUsingIteration();
}
