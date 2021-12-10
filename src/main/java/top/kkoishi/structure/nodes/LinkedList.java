package top.kkoishi.structure.nodes;

import java.lang.reflect.Array;
import java.util.List;

public class LinkedList<E> {
    static class Node<E> {
        E value;
        Node<E> next;

        public Node (E value) {
            this.value = value;
        }

        public Node () {
        }
    }

    private Node<E> headNode;
    private int size;

    public LinkedList () {
    }

    private void linkedToHead (E value) {
        Node<E> node = new Node<>(value);
        node.next = headNode;
        headNode = node;
    }

    private void linkedTail (E value) {
        Node<E> node = new Node<>(value);
        Node<E> pointer = headNode;
        while (pointer.next != null) {
            pointer = pointer.next;
        }
        pointer.next = node;
    }

    public void add (E value) {
        if (headNode == null) {
            headNode = new Node<>(value);
        } else {
            linkedTail(value);
        }
        size++;
    }

    public void pushHead (E value) {
        if (headNode == null) {
            headNode = new Node<>(value);
        } else {
            linkedToHead(value);
        }
        size++;
    }

    public int size () {
        return size;
    }

    public void set (int index, E value) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node<E> pointer = headNode;
        for (int i = 0; i < index; i++) {
            pointer = pointer.next;
        }
        pointer.value = value;
    }

    @SuppressWarnings("unchecked")
    public E[] toArray (Class<?> type) {
        Node<E> pointer = headNode;
        E[] array = (E[]) Array.newInstance(type, size);
        int counter = 0;
        while (pointer.next != null) {
            array[counter++] = pointer.value;
            pointer = pointer.next;
        }
        array[counter] = pointer.value;
        return array;
    }

    public boolean contains (E value) {
        Node<E> pointer = headNode;
        while (pointer.next != null) {
            if (pointer.value.equals(value)) {
                return true;
            }
        }
        return pointer.value.equals(value);
    }

    @Override
    public String toString () {
        StringBuilder builder = new StringBuilder("[");
        Node<E> pointer = headNode;
        while (pointer.next != null) {
            builder.append(pointer.value).append(" ,");
            pointer = pointer.next;
        }
        builder.append(pointer.value).append("]");
        return builder.toString();
    }
}
