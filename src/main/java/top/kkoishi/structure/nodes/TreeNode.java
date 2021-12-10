package top.kkoishi.structure.nodes;

public class TreeNode<V> {
    V value;
    TreeNode<V> father;
    TreeNode<V> firstChild;
    TreeNode<V> next;

    public TreeNode (V value) {
        this.value = value;
    }

    public TreeNode () {
    }

    public V getValue () {
        return value;
    }

    public void setValue (V value) {
        this.value = value;
    }

    public void addChild (V value) {
        TreeNode<V> node = new TreeNode<>(value);
        node.father = this;
        if (this.firstChild == null) {
            this.firstChild = node;
            return;
        }
        TreeNode<V> pointer = this.firstChild;
        while (firstChild.next != null) {
            firstChild = firstChild.next;
        }
        firstChild.next = node;
    }

    public TreeNode<V> getChild (int index) throws NullPointerException, IndexOutOfBoundsException {
        if (this.firstChild == null) {
            throw new NullPointerException();
        }
        int size = 1;
        TreeNode<V> pointer = this.firstChild;
        while (pointer.next != null) {
            pointer = pointer.next;
            size++;
        }
        if (size <= index) {
            throw new IndexOutOfBoundsException();
        }
        TreeNode<V> node = this.firstChild;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public String toString () {
        return value.toString();
    }
}
