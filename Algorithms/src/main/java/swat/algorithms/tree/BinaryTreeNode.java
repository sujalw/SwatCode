package swat.algorithms.tree;

/**
 * @author Sujal
 */
public class BinaryTreeNode <T> {
    private T key;
    BinaryTreeNode<T> lChild;
    BinaryTreeNode<T> rChild;
    BinaryTreeNode<T> parent;

    public BinaryTreeNode(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
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
