package top.kkoishi.structure.hyperTree.nodes;

import java.util.List;

abstract class AbstractPlusNode<V, K extends Comparable<K>> implements TwoParamNode<V, K>{
    protected AbstractPlusNode<V, K> father;
    protected AbstractPlusNode<V, K>[] childs;
    protected Integer number;
    protected Object[] keys;

    public AbstractPlusNode () {
        this.father = null;
        this.childs = new AbstractPlusNode[4];
        this.keys = new Object[4];
    }

    abstract V get (K key);
    abstract V set (K key, V value);
    abstract List<V> search (K leftBorder, K rightBorder);
    abstract AbstractPlusNode<V, K> insert (K key, V value);
}
