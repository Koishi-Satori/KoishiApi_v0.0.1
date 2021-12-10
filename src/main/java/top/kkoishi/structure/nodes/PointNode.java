package top.kkoishi.structure.nodes;

import java.util.Objects;

public class PointNode<V> {
    V value;
    LinkedList<SideNode> sideNodes;

    public PointNode () {
        sideNodes = new LinkedList<>();
    }

    public PointNode (V value) {
        this();
        this.value = value;
    }

    public void setValue (V value) {
        this.value = value;
    }

    public void addLinkedSide (SideNode side) {
        sideNodes.add(side);
    }

    public void addLinkedSides (SideNode... nodes) {
        for (SideNode sideNode : nodes) {
            sideNodes.add(sideNode);
        }
    }

    public void setSide (int index, SideNode side) {
        sideNodes.set(index, side);
    }

    public LinkedList<SideNode> getSideNodes () {
        return sideNodes;
    }

    public SideNode[] getSideNodeArray () {
        return sideNodes.toArray(SideNode.class);
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PointNode<?> pointNode)) {
            return false;
        }
        return Objects.equals(value, pointNode.value) && Objects.equals(sideNodes, pointNode.sideNodes);
    }

    @Override
    public int hashCode () {
        return Objects.hash(value, sideNodes);
    }

    @Override
    public String toString () {
        return value.toString();
    }
}
