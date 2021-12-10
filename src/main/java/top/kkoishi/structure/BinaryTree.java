package top.kkoishi.structure;

import top.kkoishi.structure.nodes.BinaryTreeNode;

import java.util.*;
import java.util.function.Consumer;

public class BinaryTree<T> {
    private BinaryTreeNode<T> root;
    private int nodeAmount;

    public BinaryTree () {
        nodeAmount = 1;
    }

    private String fotPartition (BinaryTreeNode<T> node) {
        if (node == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder(node.value.toString());
        builder.append(" ").append(fotPartition(node.left));
        builder.append(" ").append(fotPartition(node.right));
        return builder.toString();
    }

    private List<BinaryTreeNode<T>> fot (BinaryTreeNode<T> node) {
        if (node == null) {
            return new ArrayList<>();
        }
        List<BinaryTreeNode<T>> ans = new ArrayList<>();
        ans.add(node);
        ans.addAll(fot(node.left));
        ans.addAll(fot(node.right));
        return ans;
    }

    public BinaryTree (T value) {
        this();
        root = new BinaryTreeNode<>(value);
    }

    public int size () {
        return nodeAmount;
    }

    public void forEach (Consumer<T> action) {
        List<T> list = view(this.root);
        for (T value : list) {
            action.accept(value);
        }
    }

    public List<T> view (BinaryTreeNode<T> node) {
        if (node == null) {
            return new ArrayList<>();
        }
        List<T> ans = new ArrayList<>();
        ans.addAll(view(node.left));
        ans.addAll(view(node.right));
        return ans;
    }

    public String firstOrderTraversalStr () {
        return fotPartition(root);
    }

    public List<BinaryTreeNode<T>> firstOrderTraversal () {
        return fot(root);
    }

    @SuppressWarnings("all")
    public void build (List<T> c) {
        int size = c.size(), depth = 0;
        BinaryTreeNode<T>[] nodes = new BinaryTreeNode[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new BinaryTreeNode<>(c.get(i));
        }
        while ((size /= 2) != 0) {
            depth++;
        }
        this.root = nodes[0];
        for (int i = 0; i < depth; i++) {
            int limit = (int) Math.pow(2, i);
            for (int j = 0; j < limit; j += 2) {
                nodes[limit / 2 + j / 2].left = nodes[limit + j];
                nodes[limit / 2 + j / 2].right = nodes[limit + j + 1];
            }
        }
    }

    public List<List<T>> levelOrderTraversalToList () {
        List<List<T>> ans = new ArrayList<>();
        Queue<BinaryTreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<T> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                BinaryTreeNode<T> node = queue.poll();
                assert node != null;
                temp.add(node.value);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ans.add(temp);
        }
        return ans;
    }
}

class TreeTest {
    public static void main (String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 31; i++) {
            list.add(i);
        }
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.build(list);
        System.out.println(binaryTree.levelOrderTraversalToList());
    }
}
