package swat.algorithms.tree;

import swat.algorithms.tree.utils.BinaryTreeUtils;

import java.util.*;

/**
 * @author Sujal
 */
public class BinaryTree <T> {
    BinaryTreeNode<T> root;
	BinaryTreeUtils binaryTreeUtils = new BinaryTreeUtils();

    public BinaryTree() {
    }

	public BinaryTree(String treeStr) {
		if(! isValidTreeString(treeStr)) {
			return;
		}

		treeStr = removeAllSpaces(treeStr);
	}

	private String removeAllSpaces(String treeStr) {
		return treeStr.replaceAll(" ", "");
	}

	private boolean isValidTreeString(String treeStr) {
		// TODO: implement logic to validate the tree string
		return true;
	}

	public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(BinaryTreeNode<T> root) {
        this.root = root;
    }

    public BinaryTree getCopy() {

        BinaryTree treeCopy = new BinaryTree();

        if(this.getRoot() == null) {
            return treeCopy;
        }

        treeCopy.setRoot(new BinaryTreeNode(this.getRoot().getKey()));
        copyChildren(this.getRoot(), treeCopy.getRoot());

        return treeCopy;
    }

    /**
     * Copy children of srcNode to destNode recursively
     *
     * @param srcNode
     * @param destNode
     */
    private void copyChildren(BinaryTreeNode srcNode, BinaryTreeNode destNode) {
        if(srcNode.getlChild() != null) {
            destNode.setlChild(new BinaryTreeNode(srcNode.getlChild().getKey()));
            copyChildren(srcNode.getlChild(), destNode.getlChild());
        }

        if(srcNode.getrChild() != null) {
            destNode.setrChild(new BinaryTreeNode(srcNode.getrChild().getKey()));
            copyChildren(srcNode.getrChild(), destNode.getrChild());
        }
    }
}
