package swat.algorithms.tree.utils;

import swat.algorithms.tree.BinaryTree;
import swat.algorithms.tree.BinaryTreeNode;

/**
 * @author Sujal
 */
public class BinaryTreeUtils {

	public void convertToMirror(BinaryTreeNode node) {
		if(node == null) {
			return;
		}

		if(node.getlChild() != null) {
			convertToMirror(node.getlChild());
		}

		if(node.getrChild() != null) {
			convertToMirror(node.getrChild());
		}

		swapChildren(node);
	}

	private void swapChildren(BinaryTreeNode node) {
		BinaryTreeNode tmpNode = node.getlChild();
		node.setlChild(node.getrChild());
		node.setrChild(tmpNode);
	}

	public boolean isMirrorOf(BinaryTree binaryTree1, BinaryTree binaryTree2) {

		BinaryTreeNode root1 = binaryTree1.getRoot();
		BinaryTreeNode root2 = binaryTree2.getRoot();

		/*
		 * Recursive compare children for mirror-equal
		 */
		return isMirrorEqual(root1, root2);
	}
	private boolean isMirrorEqual(BinaryTreeNode node1, BinaryTreeNode node2) {

		if(node1 == null && node2 == null) {
			return true;
		}

		if(node1 == null || node2 == null) {
			return false;
		}

		/*
		 * First compare the key
		 */
		if(node1.getKey() != node2.getKey()) {
			return false;
		}

		return isMirrorEqual(node1.getlChild(), node2.getrChild()) && isMirrorEqual(node1.getrChild(), node2.getlChild());
	}

	public boolean isEqual(BinaryTree binaryTree1, BinaryTree binaryTree2) {
		BinaryTreeNode root1 = binaryTree1.getRoot();
		BinaryTreeNode root2 = binaryTree2.getRoot();

		return isEqualTo(root1, root2);
	}

	private boolean isEqualTo(BinaryTreeNode node1, BinaryTreeNode node2) {

		if(node1 == null && node2 == null) {
			return true;
		}

		if(node1 == null || node2 == null) {
			return false;
		}

		if(node1.getKey() != node2.getKey()) {
			return false;
		}

		return isEqualTo(node1.getlChild(), node2.getlChild())
				&& isEqualTo(node1.getrChild(), node2.getrChild());
	}
}
