package top.kkoishi.util;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author KKoishi_
 */
public class UndirectedGraph<V> implements Graph<V>, Cloneable, Iterable<V> {

    @Override
    @SuppressWarnings("all")
    public UndirectedGraph<V> clone () {
        try {
            UndirectedGraph clone = (UndirectedGraph) super.clone();
            clone.vexes = this.vexes.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    private class Vex implements Graph.Point, Cloneable {
        V value;
        Edge first;
        boolean visited;

        public Vex (V value, Edge first) {
            this.value = value;
            this.first = first;
            visited = false;
        }

        @Override
        public String toString () {
            return "Vex{" +
                    "value=" + value +
                    ", first=" + first +
                    '}';
        }

        @Override
        @SuppressWarnings("all")
        public Vex clone () {
            try {
                Vex clone = (Vex) super.clone();
                clone.value = value;
                clone.first = first;
                clone.visited = visited;
                return clone;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }

    private class Edge {
        int weight;
        Vex from;
        Vex to;
        Edge next;

        public Edge (int weight, Vex from, Vex to, Edge next) {
            this.weight = weight;
            this.from = from;
            this.to = to;
            this.next = next;
        }

        @Override
        public String toString () {
            return "Edge{" +
                    "weight=" + weight +
                    ", from=" + from.value +
                    ", to=" + to.value +
                    ", next=" + next +
                    '}';
        }
    }

    private Vector<Vex> vexes;

    public UndirectedGraph () {
        vexes = new Vector<>();
    }

    final boolean offer (V value) {
        for (Vex vex : vexes) {
            if (vex.value.equals(value)) {
                return false;
            }
        }
        return vexes.add(new Vex(value, null));
    }

    final boolean link (V from, int weight, V to) {
        for (Vex vex : vexes) {
            // if vexes has the one which its value equals to the given value,
            // then continue to execute, or return false.
            // And if to node does not exist, create new one.
            if (vex.value.equals(from)) {
                for (Vex v : vexes) {
                    if (v.value.equals(to)) {
                        Edge ePointer = vex.first;
                        if (ePointer == null) {
                            vex.first = new Edge(weight, vex, v, null);
                        } else {
                            while (ePointer.next != null) {
                                ePointer = ePointer.next;
                            }
                            ePointer.next = new Edge(weight, vex, v, null);
                        }
                        return true;
                    }
                }
                Vex v = new Vex(to, null);
                Edge ePointer = vex.first;
                if (ePointer == null) {
                    vex.first = new Edge(weight, vex, v, null);
                } else {
                    while (ePointer.next != null) {
                        ePointer = ePointer.next;
                    }
                    ePointer.next = new Edge(weight, vex, v, null);
                }
                vexes.add(v);
                return true;
            }
        }
        System.gc();
        return false;
    }

    final boolean push (V value) {
        return false;
    }

    final boolean unlink (V from, V to) {
        return false;
    }

    private class InnerIterator implements Iterator<V> {
        Vector<Vex> vs = vexes.clone();
        Vex vex = vs.get(0);
        Edge edge = (vex == null ? null : vex.first);
        int amount = vexes.size();
        int i = 0;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext () {
            return i < amount && vex != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public V next () {
            final V val = vex.value;
            vex.visited = true;
            if (edge != null) {
                edge = edge.next;
                if (edge != null) {
                    vex = edge.to;
                } else {
                    for (Vex v : vs) {
                        if (!v.visited) {

                        }
                    }
                }
            } else {

            }
            ++i;
            return val;
        }
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     * @deprecated
     */
    @Override
    @Deprecated
    public Iterator<V> iterator () {
        return new InnerIterator();
    }

    /**
     * Add a void point to the graph.
     * The point does not have any sides, you can add side
     * with invoking add (from, sideWeight, to);
     * You should make sure next when override the method:
     * <p>
     * For adjacency-matrix or other implement structures, the graph should not
     * allow the already-existed element be appended.
     *
     * @param value the void point's value.
     * @return true if added,or false
     */
    @Override
    public boolean add (V value) {
        return offer(value);
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
     */
    @Override
    public void add (V from, int sideWeight, V to) {
        if (!link(from, sideWeight, to)) {
            throw new NoSuchElementException();
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
        vexes.clear();
    }

    /**
     * Remove a point by providing its value.
     * <p>
     * You should throw an exception when the point with the value is null.
     * The graph points' value should be only, that makes the removed
     * element is the last one.
     *
     * @param value the value of the node which you'd like to remove.
     * @return if success
     */
    @Override
    public boolean remove (V value) {

        return false;
    }

    /**
     * Remove a side between two points.
     *
     * @param from from point
     * @param to   next point
     * @return if success
     */
    @Override
    public boolean remove (V from, V to) {

        return false;
    }

    /**
     * Remove all sides that match the sideWeight
     *
     * @param sideWeight the sides' weight
     * @return if success
     */
    @Override
    public boolean removeAll (int sideWeight) {

        return false;
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
    public Point getPoint (V value) {
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

    /**
     * Get the side amount which its side-weight is value of the param.
     *
     * @param sideWeight the side-weight of the sides
     * @return amount
     */
    @Override
    public int sides (int sideWeight) {
        return 0;
    }

    /**
     * Get a list of connection component.
     * A connection component is a child graph that is not linked
     * with the other connection component.
     *
     * @return List.
     * @throws NullPointerException when the graph is empty.
     */
    @Override
    public List<Graph<V>> getConnectionComponent () {
        return null;
    }

    @Override
    public String toString () {
        return ("UndirectedGraph{\n" +
                "vexes=\n" + vexes +
                "\n}").replaceAll(",\\s*Vex", ",\nVex");
    }
}

class UnDirectedGraphTest {
    public static void main (String[] args) {
        Graph<String> stringGraph = new UndirectedGraph<>();
        System.out.println(stringGraph.add("a"));
        System.out.println(stringGraph.add("b"));
        stringGraph.add("a", 1, "b");
        stringGraph.add("b", 2, "c");
        System.out.println(stringGraph);
    }
}