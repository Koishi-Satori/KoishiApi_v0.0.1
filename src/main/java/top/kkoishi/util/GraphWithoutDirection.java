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

package top.kkoishi.util;

import top.kkoishi.util.graph.Graph;

import java.util.Iterator;
import java.util.List;

import static java.lang.System.out;

public class GraphWithoutDirection<V> implements Graph<V> {
    /* -------------------------------Field start----------------------------- */

    /**
     * private inner class Vector.
     *
     * @param <K>
     * @param <V>
     */
    private static class Vector<K, V>  {
        /**
         * inner class Pair,to store data pair
         *
         * @param <K>
         * @param <V>
         */
        public static class Pair<K, V> {
            K key;
            V value;

            /**
             * The only constructor
             *
             * @param key   key
             * @param value value
             */
            public Pair (K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        transient Object[] data;
        transient int size;

        public Vector () {
            size = 0;
        }

        private void resize () {
            size++;
            if (data == null) {
                data = new Object[size];
            } else {
                Object[] temp = data;
                data = new Object[size];
                System.arraycopy(temp, 0, data, 0, size - 1);
            }
        }

        private boolean decrease (K key) {
            int pos = 0;
            for (Object o : data) {
                assert o instanceof Pair<?, ?>;
                if (((Pair<?, ?>) o).key.equals(key)) {
                    System.arraycopy(data, pos, data, pos + 1, size - pos - 1);
                }
                pos++;
            }
            size--;
            return true;
        }

        public void set (K key, V value) {
            for (Object o : data) {
                if (o.equals(key)) {
                    ((Pair<K, V>)o).value = value;
                }
            }
        }

        public void setFirst (K key, V value) {
            data[0] = new Pair<>(key, value);
        }

        public boolean add (K key, V value) {
            if (size == 0) {
                resize();
                data[size - 1] = new Pair<>(key, value);
                return true;
            }
            if (!contains(key)) {
                resize();
                data[size - 1] = new Pair<>(key, value);
                return true;
            }
            return false;
        }

        public int size () {
            return size;
        }

        @SuppressWarnings("unchecked")
        public V get (K key) {
            for (Object o : data) {
                if (((Pair<?, ?>) o).key.equals(key)) {
                    return ((Pair<K, V>) o).value;
                }
            }
            return null;
        }

        public boolean remove (K key) {
            if (!contains(key)) {
                return false;
            }
            return decrease(key);
        }

        public boolean contains (K key) {
            for (Object o : data) {
                assert o instanceof Pair<?, ?>;
                if (((Pair<?, ?>) o).key.equals(key)) {
                    return true;
                }
            }
            return false;
        }

        public void clear () {
            this.size = 0;
            this.data = null;
        }
    }

    class Side {
        int weight;
        Point prev;
        Point to;
        transient Side next;

        public Side (int weight) {
            this.weight = weight;
        }
    }

    class Sides {
        /**
         * Head Pointer
         */
        Side first;
        /**
         * Tail Pointer
         */
        Side last;
        transient int size;

        public Sides () {
            size = 0;
        }

        public void add (Side side) {
            final Side s = last;
            final Side newSide = new Side(side.weight);
            last = newSide;
            if (s == null) {
                first = newSide;
            } else {
                s.next = newSide;
            }
            size++;
        }

        public int size () {
            return size;
        }
    }

    class Point implements Graph.Point {
        V value;
        Sides sides;

        public Point (V value) {
            this.value = value;
            sides = new Sides();
        }

        @Override
        public String toString () {
            return value.toString();
        }
    }

    private final transient Vector<V, Point> points;
    private Point root;

    public GraphWithoutDirection () {
        root = null;
        points = new Vector<>();
    }

    public void setRoot (V value) {
        if (root == null) {
            root = new Point(value);
            points.add(value, root);
        } else {
            root.value = value;
            points.setFirst(root.value, root);
        }
    }

    public static class NoSuchElementException extends RuntimeException {
        /**
         * Constructs a new runtime exception with {@code null} as its
         * detail message.  The cause is not initialized, and may subsequently be
         * initialized by a call to {@link #initCause}.
         */
        public NoSuchElementException () {
            super();
        }

        /**
         * Constructs a new runtime exception with the specified detail message.
         * The cause is not initialized, and may subsequently be initialized by a
         * call to {@link #initCause}.
         *
         * @param message the detail message. The detail message is saved for
         *                later retrieval by the {@link #getMessage()} method.
         */
        public NoSuchElementException (String message) {
            super(message);
        }

        /**
         * Constructs a new runtime exception with the specified detail message and
         * cause.  <p>Note that the detail message associated with
         * {@code cause} is <i>not</i> automatically incorporated in
         * this runtime exception's detail message.
         *
         * @param message the detail message (which is saved for later retrieval
         *                by the {@link #getMessage()} method).
         * @param cause   the cause (which is saved for later retrieval by the
         *                {@link #getCause()} method).  (A {@code null} value is
         *                permitted, and indicates that the cause is nonexistent or
         *                unknown.)
         * @since 1.4
         */
        public NoSuchElementException (String message, Throwable cause) {
            super(message, cause);
        }

        /**
         * Constructs a new runtime exception with the specified cause and a
         * detail message of {@code (cause==null ? null : cause.toString())}
         * (which typically contains the class and detail message of
         * {@code cause}).  This constructor is useful for runtime exceptions
         * that are little more than wrappers for other throwables.
         *
         * @param cause the cause (which is saved for later retrieval by the
         *              {@link #getCause()} method).  (A {@code null} value is
         *              permitted, and indicates that the cause is nonexistent or
         *              unknown.)
         * @since 1.4
         */
        public NoSuchElementException (Throwable cause) {
            super(cause);
        }

        /**
         * Constructs a new runtime exception with the specified detail
         * message, cause, suppression enabled or disabled, and writable
         * stack trace enabled or disabled.
         *
         * @param message            the detail message.
         * @param cause              the cause.  (A {@code null} value is permitted,
         *                           and indicates that the cause is nonexistent or unknown.)
         * @param enableSuppression  whether or not suppression is enabled
         *                           or disabled
         * @param writableStackTrace whether or not the stack trace should
         *                           be writable
         * @since 1.7
         */
        protected NoSuchElementException (String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }

    /* ------------------------------------------Field End----------------------------------------- */

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
     * @return true if added,or false.
     */
    @Override
    public boolean add (V value) {
        if (points.contains(value)) {
            return false;
        } else {
            return points.add(value, new Point(value));
        }
    }

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
     * @throws NoSuchElementException when the 'from' element does not exist.
     */
    @Override
    public void add (V from, int sideWeight, V to) {
        if (!points.contains(from)) {
            throw new NoSuchElementException();
        }
        if (!points.contains(to)) {
            Point p = new Point(to);
            Side side = new Side(sideWeight);
            Point point = points.get(from);
            assert point != null;
            side.prev = point;
            side.to = p;
            point.sides.add(side);
            points.set(from, point);
        } else {
            Point p1 = points.get(from);
            Point p2 = points.get(to);
            assert p1 != null;
            assert p2 != null;
            Side side = new Side(sideWeight);
            side.prev = p1;
            side.to = p2;
            p1.sides.add(side);
        }
    }

    /**
     * Directly clear the whole graph.
     * Recommend way:<br>
     * re-initialize the data-structure which
     * stores the data.
     */
    @Override
    public void clear () {
        this.root = null;
        this.points.clear();
    }

    /**
     * Remove a point by providing its value.
     * <p>
     * You should throw an exception when the point with the value is null.
     * The graph points' value should be only, that makes the removed
     * element is the last one.
     *
     * @param value the value of the node which you'd like to remove.
     */
    @Override
    public void remove (V value) {

    }

    @Override
    public void remove (V from, V to) {

    }

    @Override
    public void removeAll (int sideWeight) {

    }

    /**
     * Get the only point which its value is the param.
     * if the point is {@code null},the return should be null.
     * the return type is inner interface Point.
     *
     * @param value the value which is searched.
     * @return a Point.
     */
    @Override
    public Graph.Point getPoint (V value) {
        return null;
    }

    @Override
    public List<?> getSides (V value) {
        return null;
    }

    @Override
    public List<?> getPoints () {
        return null;
    }

    @Override
    public List<?> getPoints (V value) {
        return null;
    }

    @Override
    public List<?> getSides (int sideWeight) {
        return null;
    }

    @Override
    public Graph<V> deepCopy () {
        return null;
    }

    @Override
    public int size () {
        return 0;
    }

    @Override
    public int pointAmount () {
        return 0;
    }

    @Override
    public int sides () {
        return 0;
    }

    @Override
    public int sides (int sideWeight) {
        return 0;
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString () {
        return toString(root);
    }

    private String toString (Point p) {
        StringBuilder builder = new StringBuilder(p.toString());
        Side firstSide = p.sides.first;
        builder.append((firstSide.to != root ? firstSide.to.toString() : ""));
        while (firstSide.next != null) {
            firstSide = firstSide.next;
            builder.append((firstSide.to != root ? firstSide.to : ""));
        }
        return builder.toString();
    }
}

class GWDTest {
    public static void main (String[] args) {
        GraphWithoutDirection<Integer> graph = new GraphWithoutDirection<>();
        graph.setRoot(0);
        graph.add(0, -1, 1);
        out.println(graph);
    }
}
