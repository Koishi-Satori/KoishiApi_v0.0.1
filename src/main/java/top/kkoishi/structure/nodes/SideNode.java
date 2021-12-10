package top.kkoishi.structure.nodes;

import java.util.Objects;

public class SideNode {
    int weight;
    PointNode<?> nextPoint;

    public SideNode () {
        nextPoint = new PointNode<>();
    }

    public SideNode (int weight) {
        this();
        this.weight = weight;
    }

    public void setWeight (int weight) {
        this.weight = weight;
    }

    public void setPoint (PointNode<?> point) {
        nextPoint = point;
    }

    public PointNode<?> getPoint () {
        return nextPoint;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SideNode sideNode)) {
            return false;
        }
        return weight == sideNode.weight && Objects.equals(nextPoint, sideNode.nextPoint);
    }

    @Override
    public int hashCode () {
        return Objects.hash(weight, nextPoint);
    }
}
