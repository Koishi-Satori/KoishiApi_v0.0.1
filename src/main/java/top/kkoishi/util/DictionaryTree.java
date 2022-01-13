/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package top.kkoishi.util;

import java.util.Collection;
import java.util.Objects;

/**
 * @author KKoishi_
 */
public class DictionaryTree {

    /**
     * Total number of alphabet.
     */
    private static final int ALPHABET_SIZE = 26;

    /**
     * The ascii code of a.
     */
    private static final int ASCII_CODE_LOWER_A = 97;

    /**
     * The ascii code of z.
     */
    private static final int ASCII_CODE_LOWER_Z = 122;

    /**
     * Define a static class of inner node.
     */
    static class Node {
        String data;
        LinkedList<Node> children;

        public Node (String data) {
            this.data = data;
            children = new LinkedList<>();
        }
    }

    Node[] nodes;
    int amount;

    public DictionaryTree (String[] initDict) {
        if (initDict == null || initDict.length == 0) {
            amount = 0;
            nodes = new Node[ALPHABET_SIZE];
            init();
        } else {
            //TODO:finish.
        }
    }

    public DictionaryTree () {
        this(null);
    }

    public DictionaryTree (Collection<? extends String> dictCollection, Class<? extends String> className)
            throws ClassCastException {
        Objects.requireNonNull(dictCollection);
        if (dictCollection.isEmpty()) {
            amount = 0;
            nodes = new Node[ALPHABET_SIZE];
            init();
        } else {
            if (dictCollection.iterator().next().getClass() != className) {
                throw new ClassCastException();
            } else {
                //TODO:finish constructor
            }
        }
    }

    final void init () {
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            nodes[i] = new Node(String.valueOf((char) (i + ASCII_CODE_LOWER_A)));
        }
    }

    final void link (Node node, String value) {

    }

    final String unlink (Node node) {
        final String str = node.data;
        return null;
    }
}
