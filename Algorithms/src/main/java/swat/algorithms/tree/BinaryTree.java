package swat.algorithms.tree;

import java.util.*;

/**
 * @author Sujal
 */
public class BinaryTree {
    BinaryTreeNode root;

    public BinaryTree() {

    }

    public BinaryTree(int[] input) {
        this.createTree(input);
    }

    public BinaryTreeNode getRoot() {
        return root;
    }
    public void setRoot(BinaryTreeNode root) {
        this.root = root;
    }

    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(BinaryTreeNode root) {
        if(root == null) {
            return 0;
        }

        int lTreeHeight = getHeight(root.getlChild());
        int rTreeHeight = getHeight(root.getrChild());

        return 1 + Math.max(lTreeHeight, rTreeHeight);
    }

    public BinaryTreeNode createTree(int[] input) {

        if(input == null || input.length == 0) {
            return null;
        }

        root = new BinaryTreeNode(input[0]);
        Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
        q.add(root);

        for(int i=1 ; i<input.length ; i++) {
            BinaryTreeNode node = q.peek();
            BinaryTreeNode n = new BinaryTreeNode(input[i]);

            if(node.getlChild() == null) {
                node.setlChild(n);
            } else if(node.getrChild() == null) {
                node.setrChild(n);
                q.poll();
            }
            q.add(n);
        }

        return root;
    }

    public List<Integer> getInorderUsingRecursion() {
        return getInorder(root, new ArrayList<Integer>());
    }

    private List<Integer> getInorder(BinaryTreeNode root, ArrayList<Integer> arrayList) {
        if(root == null) {
            return arrayList;
        }

        getInorder(root.getlChild(), arrayList);
        arrayList.add(root.getKey());
        getInorder(root.getrChild(), arrayList);

        return arrayList;
    }

    public List<Integer> getInorderUsingIteration() {
        return getInorderUsingIteration(root);
    }

    private List<Integer> getInorderUsingIteration(BinaryTreeNode node) {

        List<Integer> inorderTraversal = new ArrayList<Integer>();

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

    public List<Integer> getPreorderUsingRecursion() {
        List<Integer> preorder = new ArrayList<Integer>();
        getPreorderUsingRecursion(root, preorder);

        return preorder;
    }

    private void getPreorderUsingRecursion(BinaryTreeNode root, List<Integer> arrayList) {
        if(root == null) {
            return;
        }

        arrayList.add(root.getKey());
        getPreorderUsingRecursion(root.getlChild(), arrayList);
        getPreorderUsingRecursion(root.getrChild(), arrayList);
    }

    public List<Integer> getPreorderUsingIteration() {
        return getPreorderUsingIteration(root);
    }

    private List<Integer> getPreorderUsingIteration(BinaryTreeNode node) {

        List<Integer> preorderTraversal = new ArrayList<Integer>();
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


    public List<Integer> getPostorderUsingRecursion() {
        List<Integer> postorder = new ArrayList<Integer>();
        getPostorder(root, postorder);

        return postorder;
    }

    private void getPostorder(BinaryTreeNode root, List<Integer> arrayList) {
        if(root == null) {
            return;
        }

        getPostorder(root.getlChild(), arrayList);
        getPostorder(root.getrChild(), arrayList);
        arrayList.add(root.getKey());
    }

    public List<Integer> getPostorderUsingIteration() {
        return getPostorderUsingIteration(root);
    }

    private List<Integer> getPostorderUsingIteration(BinaryTreeNode node) {

        if(node == null) {
            return new ArrayList<Integer>();
        }

        List<Integer> reversePreorderTraversal = getReversePreorderUsingIteration(node);
        if(reversePreorderTraversal == null) {
            return new ArrayList<Integer>();
        }

        Collections.reverse(reversePreorderTraversal);
        return reversePreorderTraversal;
    }

    private List<Integer> getReversePreorderUsingIteration(BinaryTreeNode node) {

        List<Integer> reversePreorder = new ArrayList<Integer>();

        if(node == null) {
            return reversePreorder;
        }

        Stack<BinaryTreeNode> reversePreorderTraversalStack = new Stack<BinaryTreeNode>();

        reversePreorder.add(node.getKey());
        reversePreorderTraversalStack.push(node);

        while(! reversePreorderTraversalStack.isEmpty()) {
            while((node != null) && ((node = node.getrChild()) != null)) {
                reversePreorder.add(node.getKey());
                reversePreorderTraversalStack.push(node);
            }

            node = reversePreorderTraversalStack.pop();
            node = node.getlChild();
            if(node != null) {
                reversePreorder.add(node.getKey());
                reversePreorderTraversalStack.push(node);
            }
        }

        return reversePreorder;
    }

    public void convertToMirror() {
        convertToMirror(root);
    }
    private void convertToMirror(BinaryTreeNode node) {
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

    public boolean isMirrorOf(BinaryTree binaryTree) {

        BinaryTreeNode root1 = this.getRoot();
        BinaryTreeNode root2 = binaryTree.getRoot();
		
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

    public boolean isEqualTo(BinaryTree binaryTree) {
        BinaryTreeNode root1 = this.getRoot();
        BinaryTreeNode root2 = binaryTree.getRoot();

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

        return isEqualTo(node1.getlChild(), node2.getlChild()) && isEqualTo(node1.getrChild(), node2.getrChild());
    }
}
