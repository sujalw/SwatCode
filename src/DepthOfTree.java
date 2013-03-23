/* 
 *         Problem Statement:
 * 
 *         Consider this string representation for binary trees. Each node is of
 *         the form (lr), where l represents the left child and r represents the
 *         right child. If l is the character 0, then there is no left child.
 *         Similarly, if r is the character 0, then there is no right child.
 *         Otherwise, the child can be a node of the form (lr), and the
 *         representation continues recursively.
 * 
 *         For example: (00) is a tree that consists of one node. ((00)0) is a
 *         two-node tree in which the root has a left child, and the left child
 *         is a leaf. And ((00)(00)) is a three-node tree, with a root, a left
 *         and a right child.
 * 
 *         Write a function that takes as input such a string, and returns -1 if
 *         the string is malformed, and the depth of the tree if the string is
 *         well-formed.
 * 
 *         Hint: trees are recursive data structures! 
 *         
 *         For instance:
 *         find_depth('(00)') -> 0 
 *         find_depth('((00)0)') -> 1
 *         find_depth('((00)(00))') -> 1 
 *         find_depth('((00)(0(00)))') -> 2
 *         find_depth('((00)(0(0(00))))') -> 3 
 *         find_depth('x') -> -1
 *         find_depth('0') -> -1 
 *         find_depth('()') -> -1 
 *         find_depth('(0)') -> -1
 *         find_depth('(00)x') -> -1 
 *         find_depth('(0p)') -> -1
 * 
 */

/**
 * 
 * @author sujal
 * 
 */
public class DepthOfTree {

	public DepthOfTree(String tree) {

		int depth = find_depth(tree);
		System.out.println("Depth = " + depth);
	}

	public int find_depth(String tree) {

		int root = 0;

		if (!hasValidCharacters(tree)) {
			return -1;
		}

		if (tree.equals("(00)")) {
			return 0;
		}

		if (tree.equals("0")) {
			return 0;
		}

		if (tree.equals("")) {
			return -1;
		}

		// remove surrounding parenthesis, if present
		if (tree.startsWith("(") && tree.endsWith("")) {
			tree = tree.substring(1, tree.length() - 1);
			root = 1;
		}

		String l = getLeft(tree);
		String r = tree.substring(l.length());

		int lDepth = find_depth(l);
		if (lDepth == -1) {
			return -1;
		}

		int rDepth = find_depth(r);
		if (rDepth == -1) {
			return -1;
		}

		return root + Math.max(lDepth, rDepth);
	}

	String getLeft(String tree) {
		String l = "";

		if (tree.startsWith("(")) {
			int count = 1;
			int index = 1;
			l = "(";
			while (index < tree.length() && count != 0) {
				char c = tree.charAt(index);
				l += c;

				if (c == '(') {
					count++;
				}

				if (c == ')') {
					count--;
				}

				index++;
			}

			if (count != 0) {
				l = "";
			}
		} else {
			int index = 0;
			boolean flag = false;
			while (index < tree.length() && tree.charAt(index) != '(') {
				char c = tree.charAt(index);

				if (c == '(') {
					flag = true;
				}
				if (c == ')') {
					if (!flag) {
						l = "";
						break;
					}
				}
				l += c;
				index++;
			}
		}

		return l;
	}

	boolean hasValidCharacters(String tree) {
		boolean valid = true;

		// chk for invalid characters
		String tmp = tree.replaceAll("\\(", "");
		tmp = tmp.replaceAll("\\)", "");
		tmp = tmp.replaceAll("0", "");

		if (tmp.length() != 0) {
			valid = false;
		}

		return valid;
	}

	public static void main(String[] args) {
		String tree = "((00)0)";
		new DepthOfTree(tree);
	}
}
