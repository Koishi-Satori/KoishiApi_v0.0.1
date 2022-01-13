/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package top.kkoishi.parser.util;

/**
 * The types of the commands and its parts
 * @author KKoishi_
 * @see java.lang.constant.Constable
 * @see java.lang.Enum
 */
public enum SyntaxType implements java.io.Serializable {
    /**
     * The prepared sentences which their annotates are removed
     */
    PRE,
    ANNOTATE,
    STRING,
    CONST,
    OPERATOR,
    COMMAND,
    INT
}
