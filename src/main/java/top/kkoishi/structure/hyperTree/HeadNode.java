package top.kkoishi.structure.hyperTree;

import java.util.List;

public class HeadNode<K extends Comparable<K>, V> extends AbstractBNode<K, V> {
    Object[] keys;
    List<AbstractBNode<K, V>> childs;
    HeadNode<K, V> father;

    public HeadNode () {
        keys = new Object[4];
        father = null;
    }

    public HeadNode (HeadNode<K, V> father) {
        keys = new Object[4];
        this.father = father;
    }

    public AbstractBNode<K, V> insert (K key, V value) {
        return null;
    }
}
