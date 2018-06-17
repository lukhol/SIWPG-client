package com.lukhol.politechnika.aiclient.game;

import com.lukhol.politechnika.aiclient.model.Point;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Node  implements Comparable<Node>{
    private Node parent;
    private final int hValue;
    private int gValue;
    private Point point;

    public int getFValue() {
        return hValue + gValue;
    }

    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Node))
            return false;

        Node otherNode = (Node)other;
        return otherNode.getPoint().equals(point);
    }

    @Override
    public int hashCode() {
        return point.hashCode();
    }

    @Override
    public int compareTo(Node otherNode) {
        return Integer.compare(getFValue(), otherNode.getFValue());
    }

    @Override
    public String toString() {
        return point.toString() + ", cost: " + gValue;
    }
}