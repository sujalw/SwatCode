package swat.algorithms.tree;

import swat.swatcodeutilities.SwatStringUtils;

import java.util.Stack;

/**
 * @author Sujal
 */
public class BinaryTree <T> {
    BinaryTreeNode<T> root;

	private final Character LPAREN = '(';
	private final Character RPAREN = ')';
	private final Character COMMA = ',';

    public BinaryTree() {
		String str = "(hello world)";
		System.out.println("str = " + removeSurroundingParenthesis(str));
	}

	public BinaryTree(String treeStr) {
		if(! isValidTreeString(treeStr)) {
			return;
		}

		treeStr = removeAllSpaces(treeStr);

		root = new BinaryTreeNode();
		initializeNode(root, treeStr);
	}

	private boolean isValidTreeString(String treeStr) {

		if(SwatStringUtils.isEmpty(treeStr)) {
			return false;
		}

		// TODO: implement logic to validate the tree string
		return true;
	}

	private String removeAllSpaces(String treeStr) {
		return treeStr.replaceAll(" ", "");
	}

	private void initializeNode(BinaryTreeNode<T> node, String treeStr) {

		String[] rootAndChildren = extractRootAndChildren(treeStr);

		node.setKey((T) rootAndChildren[0]);

		initializeChildren(node, rootAndChildren[1]);
	}

	private String[] extractRootAndChildren(String treeStr) {

		int indexOfFirstLParen = treeStr.indexOf(LPAREN);

		String root = treeStr.substring(0, indexOfFirstLParen);

		String children = "";
		if(indexOfFirstLParen != -1) {
			children = removeSurroundingParenthesis(treeStr.substring(indexOfFirstLParen));
		}

		return new String[]{root, children};
	}

	private String removeSurroundingParenthesis(String string) {
		return string.substring(1, string.length()-1);
	}

	private void initializeChildren(BinaryTreeNode<T> node, String childrenStr) {
		if(! SwatStringUtils.isEmpty(childrenStr)) {
			String[] children = extractChildren(childrenStr);

			if(! SwatStringUtils.isEmpty(children[0])) {
				BinaryTreeNode lChild = new BinaryTreeNode();

				if(isSingleNode(children[0])) {
					lChild.setKey(children[0]);
				} else {
					initializeNode(lChild, children[0]);
				}

				node.setlChild(lChild);
			}

			if(! SwatStringUtils.isEmpty(children[1])) {
				BinaryTreeNode rChild = new BinaryTreeNode();

				if(isSingleNode(children[1])) {
					rChild.setKey(children[1]);
				} else {
					initializeNode(rChild, children[1]);
				}

				node.setrChild(rChild);
			}
		}
	}

	private boolean isSingleNode(String treeStr) {
		return ! treeStr.contains(String.valueOf(LPAREN));
	}

	private String[] extractChildren(String childrenStr) {

		Stack<Character> parenStack = new Stack<Character>();
		StringBuilder lChild = new StringBuilder();
		String rChildStr = "";

		for(int i=0 ; i<childrenStr.length() ; i++) {
			char currentChar = childrenStr.charAt(i);

			if(currentChar == LPAREN) {
				parenStack.push(LPAREN);
			} else if(currentChar == RPAREN) {
				parenStack.pop();
			} else if(currentChar == COMMA) {
				if(parenStack.isEmpty()) {
					rChildStr = childrenStr.substring(i+1);
					break;
				}
			}

			lChild.append(currentChar);
		}

		return new String[]{lChild.toString(), rChildStr};
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

	public static void main(String[] args) {
		new BinaryTree<String>();
	}
}
