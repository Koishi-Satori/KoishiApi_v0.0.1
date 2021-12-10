package top.kkoishi.structure.hyperTree.nodes;

import java.util.List;

public class BplusNode<V, K extends Comparable<K>> extends AbstractPlusNode<V, K> {
    public BplusNode () {
        super();
    }

    @Override
    V get (K key) {
        return null;
    }

    @Override
    V set (K key, V value) {
        return null;
    }

    @Override
    List<V> search (K leftBorder, K rightBorder) {
        return null;
    }

    @Override
    AbstractPlusNode<V, K> insert (K key, V value) {
        return null;
    }
}
