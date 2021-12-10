package top.kkoishi.structure;

import top.kkoishi.structure.nodes.PointNode;
import top.kkoishi.structure.nodes.SideNode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Build a graph with direction,you need to define its headNode,
 * add void node and build side.
 * The graph example:
 * <blockquote>
 *         <p>GraphWithDirection<Integer> graph = new GraphWithDirection<>(0);</p>
 *         <p>PointNode<Integer> node1 = graph.addVoidPoint(1);</p>
 *         <p>PointNode<Integer> node2 = graph.addVoidPoint(2);</p>
 *         <p>PointNode<Integer> node3 = graph.addVoidPoint(3);</p>
 *         <p>graph.linkPoints(-1, node1, node2);</p>
 *         <p>graph.linkPoints(-2, node1, node3);</p>
 *         <p>graph.linkPoints(-3, node2, node3);</p>
 *         <p>graph.linkToHead(-10, node3);</p>
 *         <p>graph.linkFromHead(-11, node1);</p>
 *         <p>graph.linkFromHead(-12, node2);</p>
 * </blockquote>
 * <p><b>That means the head is PointNode{@code {0}}
 * and then add void node 1 2 3,the next three codes build links
 * between them,and the last three ones connect node 1 2 3 to the head node</b></p>
 * @see GraphWithDirection#addVoidPoint(Object)
 * @see GraphWithDirection#linkPoints(int, PointNode, PointNode)
 * @see GraphWithDirection#linkFromHead(int, PointNode)
 * @see GraphWithDirection#linkToHead(int, PointNode)
 * @author KKoishi_
 * @version 1.0.1
 * @since java8
 * @param <V> Type of the value of the graph.
 */
public class GraphWithDirection<V> implements Graph<V>{
    private PointNode<V> headPoint;
    private Vector<PointNode<V>> nodes;
    private HashMap<PointNode<V>, Boolean> hashMap = new HashMap<>();

    public GraphWithDirection (V value) {
        this.headPoint = new PointNode<>(value);
    }

    public GraphWithDirection () {
        headPoint = new PointNode<>();
    }

    public void createHead (V value) {
        headPoint.setValue(value);
    }

    /**
     * add a void point to the graph
     * @param value the value of the point
     * @return the node which is constructed,you can use it to link points
     */
    public PointNode<V> addVoidPoint (V value) {
        PointNode<V> node = new PointNode<>(value);
        return node;
    }

    /**
     * link two node with a side node,like:
     * <br>
     * fromNode -> sideNode{@code {sideWeight}} -> toNode
     * @param sideWeight the weight of the side
     * @param fromNode the side's resource node
     * @param toNode
     */
    public void linkPoints (int sideWeight, PointNode<V> fromNode, PointNode<V> toNode) {
        SideNode side = new SideNode(sideWeight);
        fromNode.addLinkedSide(side);
        side.setPoint(toNode);
    }

    public void linkFromHead (int sideWight, PointNode<V> node) {
        SideNode side = new SideNode(sideWight);
        headPoint.addLinkedSide(side);
        side.setPoint(node);
    }

    public void linkToHead (int sideWight, PointNode<V> node) {
        SideNode side = new SideNode(sideWight);
        node.addLinkedSide(side);
        side.setPoint(headPoint);
    }

    @SuppressWarnings("unchecked")
    public PointNode<V>[] getNextArray (PointNode<V> point) {
        Vector<PointNode<V>> vector = new Vector<>();
        SideNode[] sides = point.getSideNodeArray();
        for (SideNode side : sides) {
            vector.add((PointNode<V>) side.getPoint());
        }
        return vector.getArray(headPoint.getClass());
    }

    public PointNode<V>[] getHeadNextArray () {
        return getNextArray(headPoint);
    }

    public String getNext (PointNode<V> point) {
        SideNode[] sides = point.getSideNodeArray();
        StringBuilder builder = new StringBuilder();
        for (SideNode side : sides) {
            builder.append(side.getPoint()).append(" ");
        }
        return builder.toString();
    }

    public String getHeadNext () {
        return getNext(headPoint);
    }

    @Deprecated
    public String getFront (PointNode<V> point) {
        StringBuilder builder = new StringBuilder();
        for (PointNode<V> node : nodes) {
            SideNode[] temp = node.getSideNodeArray();
            for (SideNode side : temp) {
                if (side.getPoint().equals(point)) {
                    builder.append("[").append(node).append("]");
                    break;
                }
            }
        }
        //[test-System.out.println(Arrays.toString(nodes.get(0).getSideNodeArray()[0].getArray()));]
        return builder.toString();
    }

    @Deprecated
    public String getHeadFront () {
        return getFront(headPoint);
    }

    public String depthFirstSearch () {
        String str = depthFirstSearch(headPoint);
        hashMap = new HashMap<>();
        return str;
    }

    private String depthFirstSearch (PointNode<V> pointNode) {
        StringBuilder builder = new StringBuilder(pointNode.toString());
        PointNode<V>[] points = getNextArray(pointNode);
        for (PointNode<V> point : points) {
            if (!hashMap.containsKey(point)) {
                hashMap.put(point, true);
                builder.append("->").append(depthFirstSearch(point));
            }
        }
        return builder.toString();
    }
}

class Test {
    public static void main (String[] args) {
        GraphWithDirection<Integer> graph = new GraphWithDirection<>(0);
        PointNode<Integer> node1 = graph.addVoidPoint(1);
        PointNode<Integer> node2 = graph.addVoidPoint(2);
        PointNode<Integer> node3 = graph.addVoidPoint(3);
        graph.linkPoints(-1, node1, node2);
        graph.linkPoints(-2, node1, node3);
        graph.linkPoints(-3, node2, node3);
        graph.linkToHead(-10, node3);
        graph.linkFromHead(-11, node1);
        graph.linkFromHead(-12, node2);
        System.out.println(graph.getNext(node1));
        System.out.println(graph.getHeadNext());
        //此方法测试失败System.out.println(graph.getFront(node2))
        System.out.println(graph.depthFirstSearch());
        System.out.println(Arrays.toString(graph.getHeadNextArray()));
    }
}
