package swat.algorithms.tree;

/**
 * @author Sujal
 */
public class BinaryTreeNode {
    private Integer key;
    BinaryTreeNode lChild;
    BinaryTreeNode rChild;
    BinaryTreeNode parent;

    public BinaryTreeNode(int key) {
        this.key = key;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public BinaryTreeNode getlChild() {
        return lChild;
    }

    public void setlChild(BinaryTreeNode lChild) {
        this.lChild = lChild;
    }

    public BinaryTreeNode getrChild() {
        return rChild;
    }

    public void setrChild(BinaryTreeNode rChild) {
        this.rChild = rChild;
    }

    public BinaryTreeNode getParent() {
        return parent;
    }

    public void setParent(BinaryTreeNode parent) {
        this.parent = parent;
    }

    public String toString() {
        String k = "EMP";
        if(this.getKey() != null) {
            k = String.valueOf(this.getKey());
        }

        return k;
    }
}
