/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package top.kkoishi.parser.util;

import top.kkoishi.parser.Parser;

/**
 * The tree will be build when syntax analysing.
 * Actually, it is a child-brother tree.
 * @author KKoishi_
 */
public class SyntaxTree implements java.io.Serializable {
    /**
     * A simple node of the tree.
     */
    protected static class Node implements java.io.Serializable {
        Parser.Token content;
        Node father;
        Node firstChild;
        Node next;

        public SyntaxType getType () {
            return content.getType();
        }

        public String getContent () {
            return content.getContent();
        }

        public Parser.Token getStrictContent () {
            return content;
        }
    }

    /*----------------------------Field start-------------------------------*/

    /**
     * The root node.
     */
    Node root;

    /*-----------------------------Field end--------------------------------*/

    protected SyntaxTree () {
    }

    protected static SyntaxTree getInstance() {
        return new SyntaxTree();
    }

    @Override
    public String toString () {
        return "SyntaxTree{" +
                "root=" + root +
                '}';
    }
}
