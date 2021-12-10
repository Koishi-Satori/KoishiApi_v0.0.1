package top.kkoishi.structure.hyperTree;

public class Leaf<K extends Comparable<K>, V> extends AbstractBNode<K, V> {
    Object[] keys;
    Object[] values;
    Leaf<K, V> front;
    Leaf<K, V> next;
    HeadNode<K, V> father;
}
