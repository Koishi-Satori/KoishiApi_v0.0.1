package top.kkoishi.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * A data structure which is defined as one of the adjacency list's elements,
 * and it is used to express the Graph.
 * The graph will have a vector of this to replace the adjacency list.
 * @param <V> The param of the graph
 * @author KKoishi_
 */
public class GraphLinkedList<V> {
    /**
     * inner class of the linkedlist's node.
     * @param <V>
     */
    static class Node<V> {
        V value;
        Node<V> front;
        Node<V> next;

        public Node (V value) {
            this.value = value;
        }

        public Node () {
        }

        @Override
        public String toString () {
            return value.toString();
        }
    }

    private Node<V> headNode;
    private int size;

    public GraphLinkedList () {
        size = 0;
    }

    public void addLast (V value) {
        if (headNode == null) {
            headNode = new Node<>(value);
        } else {
            linkedToLast(value);
        }
        size++;
    }

    public void addFirst (V value) {
        if (headNode == null) {
            headNode = new Node<>(value);
        } else {
            linkedToFirst(value);
        }
        size++;
    }

    public boolean hasNext () {
        return headNode.next != null;
    }

    public boolean isEmpty () {
        return size == 0;
    }

    public int size () {
        return size;
    }

    private void linkedToFirst (V value) {
        Node<V> node = new Node<>(value);
        node.next = headNode;
        headNode.front = node;
    }

    private void linkedToLast (V value) {
        Node<V> pointer = headNode;
        while (pointer.next != null) {
            pointer = pointer.next;
        }
        Node<V> node = new Node<>(value);
        node.front = pointer;
        pointer.next = node;
    }

    private List<V> toArrayList(GraphLinkedList<V> graphLinkedList) {
        Node<V> pointer = graphLinkedList.headNode;
        List<V> res = new ArrayList<>();
        while (pointer.next != null) {
            res.add(pointer.value);
            pointer = pointer.next;
        }
        res.add(pointer.value);
        return res;
    }

    @Override
    public String toString () {
        return toArrayList(this).toString();
    }
}
