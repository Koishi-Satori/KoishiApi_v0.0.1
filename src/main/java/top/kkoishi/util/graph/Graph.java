/*
 *
 *
 *
 *
 * Copyright (c) 2021 KKoishi owns. All rights reserved
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package top.kkoishi.util.graph;

import java.util.List;

/**
 * @author KKoishi
 */
public interface Graph<V> {
    /**
     * Empty interface for correctly return.
     */
    interface Point {
    }

    /**
     * Add a void point to the graph.
     * The point does not have any sides, you can add side
     * with invoking add (from, sideWeight, to);
     * You should make sure next when override the method:
     * <p>
     * Even there are multiple parts which do not connect with each other,
     * the search can still normally run.
     * And when the value has existed, the old point's value will be replaced
     *
     * @param value the void point's value.
     * @return true if added,or false
     */
    boolean add (V value);

    /**
     * An empty method which is used to build a side between
     * the node with a value of the first param,
     * and the one with the last param.
     * The implement method should take next:
     * <p>
     * The frontValue's point must be not null,or a {@code NosuchElementException}
     * will be thrown.
     * Further, a node will be created if the point with the value of
     * the last param does not exist.<br>
     * Like this:frontValue -> sideWeight -> nextValue.
     *
     * @param from       the front node' value.
     * @param sideWeight the side's weight
     * @param to         the node which the side will point at.
     */
    void add (V from, int sideWeight, V to);

    /**
     * Directly clear the whole graph.
     * Recommend way:<br>
     * re-initialize the data-structure which
     * stores the data.
     */
    void clear ();

    /**
     * Remove a point by providing its value.
     * <p>
     * You should throw an exception when the point with the value is null.
     * The graph points' value should be only, that makes the removed
     * element is the last one.
     *
     * @param value the value of the node which you'd like to remove.
     */
    void remove (V value);

    void remove (V from, V to);

    void removeAll (int sideWeight);

    /**
     * Get the only point which its value is the param.
     * if the point is {@code null},the return should be null.
     * the return type is inner interface Point.
     *
     * @param value the value which is searched.
     * @return a Point.
     */
    Point getPoint (V value);

    List<?> getSides (V value);

    List<?> getPoints ();

    List<?> getPoints (V value);

    List<?> getSides (int sideWeight);

    Graph<V> deepCopy ();

    int size ();

    int pointAmount ();

    int sides ();

    int sides (int sideWeight);

    static Object newInstance () {
        return new GraphWithDirection<>();
    }

    @Override
    String toString ();
}
