package top.kkoishi.structure;

public class GraphWithoutDirection<V> implements Graph<V> {
    /**
     * 邻接表节点
     * @param <V> 节点值
     */
    static class Node<V> {
        V nodeValue;
        GraphLinkedList<Node<V>> nodes;

        public Node (V nodeValue) {
            this.nodeValue = nodeValue;
        }

        public Node () {
        }
    }

    /**
     * 邻接表
     */
    private Vector<Node<V>> adjacencyList;

    public GraphWithoutDirection () {
        adjacencyList = new Vector<>();
    }

    @SafeVarargs
    private void setLink (Node<V>... nodes) {

    }

    public void add (V value) {
        Node<V> node = new Node<>(value);
        adjacencyList.add(node);
    }
}
