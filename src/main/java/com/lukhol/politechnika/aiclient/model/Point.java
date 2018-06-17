package com.lukhol.politechnika.aiclient.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Point {
    private int x;
    private int y;

    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Point))
            return false;

        Point otherPoint = (Point)other;
        return otherPoint.x == x && otherPoint.y == y;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31*result + x;
        result = 31*result + y;
        return result;
    }

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y;
    }
}
