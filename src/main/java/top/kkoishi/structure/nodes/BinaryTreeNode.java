package top.kkoishi.structure.nodes;

public class BinaryTreeNode<V> {
    public V value;
    public BinaryTreeNode<V> left;
    public BinaryTreeNode<V> right;

    public BinaryTreeNode (V value) {
        this.value = value;
    }

    public BinaryTreeNode () {
    }

    @Override
    public String toString () {
        return value.toString();
    }
}
