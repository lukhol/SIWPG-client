package com.lukhol.politechnika.aiclient.game;

import com.lukhol.politechnika.aiclient.model.GameMap;
import com.lukhol.politechnika.aiclient.model.Point;
import lombok.NonNull;

import java.util.*;

public class AStarAlgorithm {
    private boolean printPath = false;

    private final LinkedList<Node> openList = new LinkedList<>();
    private final LinkedList<Node> closedList = new LinkedList<>();
    private final GameMap gameMap;
    private final Point startPoint;
    private final Point finishPoint;

    private Point actualPoint;
    private Node actualNode;

    public AStarAlgorithm(@NonNull Point startPoint, @NonNull Point finishPoint, @NonNull GameMap gameMap) {
        this.startPoint = startPoint;
        this.finishPoint = finishPoint;
        this.actualPoint = startPoint;
        this.gameMap = gameMap;

        if(startPoint.equals(finishPoint))
            throw new RuntimeException("Start point cannot be finish point!");
    }

    public List<Point> start(boolean printPath) {
        this.printPath = printPath;

        Node startNode = new Node(
                null,
                computeHValue(startPoint, finishPoint),
                0,
                startPoint
        );

        closedList.add(startNode);
        openList.addAll(findAllPossibleMoves(startNode));
        Collections.sort(openList);

        return startAlgorithm();
    }

    public Point getActualPoint() {
        return actualPoint;
    }

    private List<Point> startAlgorithm() {
        while(!actualPoint.equals(finishPoint)) {
            Node nextNode = openList.removeFirst();
            actualPoint = nextNode.getPoint();
            actualNode = nextNode;
            closedList.push(nextNode);
            openList.addAll(findAllPossibleMoves(nextNode));
            Collections.sort(openList);
        }

        return printPath(actualNode);
    }

    private List<Point> printPath(Node node) {
        List<Point> points = new ArrayList<>();
        points.add(node.getPoint());

        Node parentNode = node.getParent();

        if(printPath)
            System.out.println(node);

        while(parentNode.getParent() != null) {
            if(printPath)
                System.out.println(parentNode);

            parentNode = parentNode.getParent();
            points.add(parentNode.getPoint());
        }

        if(printPath)
            System.out.println(parentNode);

        points.add(parentNode.getPoint());

        Collections.reverse(points);
        points.removeIf(p -> p.equals(startPoint));

        return points;
    }

    private List<Node> findAllPossibleMoves(Node parentNode) {
        List<Node> nodesList = new ArrayList<>();
        int x = parentNode.getPoint().getX();
        int y = parentNode.getPoint().getY();
        int mapHeight = gameMap.getHeight();
        int mapWidth = gameMap.getWidth();

        if(y > 0) {
            checkPoint(nodesList, parentNode, x, y-1);
        }

        if(mapHeight - 1 > y) {
            checkPoint(nodesList, parentNode, x, y+1);
        }

        if(x > 0) {
            checkPoint(nodesList, parentNode, x-1, y);
        }

        if(mapWidth - 1 > x) {
            checkPoint(nodesList, parentNode, x+1, y);
        }

        return nodesList;
    }

    private void checkPoint(List<Node> nodesList, Node parentNode, int x, int y) {
        int mapHeight = gameMap.getHeight();
        int mapWidth = gameMap.getWidth();

        Point point = new Point(x, y);
        int fieldCost = gameMap.getFields()[point.getY()][point.getX()];
        int actualGValue = parentNode.getGValue() + 10 + (fieldCost == -1 ? fieldCost*-4 : fieldCost*3);
        Node node = new Node(
                parentNode,
                computeHValue(point, finishPoint),
                actualGValue,
                point
        );

        if(!isOnOpenedList(node)) {
            if(!isOnClosedList(node))
                nodesList.add(node);
        } else {
            //Special check
            for(Node n : openList) {
                if(n.equals(node)) {
                    node = n;
                    int possibleNewMovementCost = node.getGValue();

                    if(possibleNewMovementCost > actualGValue) {
                        node.setParent(parentNode);
                        node.setGValue(actualGValue);
                    }
                    break;
                }
            }
        }
    }

    private boolean isOnClosedList(Node node) {
        return closedList.contains(node);
    }

    private boolean isOnOpenedList(Node node) {
        return openList.contains(node);
    }

    private int computeHValue(Point startPoint, Point endPoint) {
        return (endPoint.getX() - startPoint.getX())+(endPoint.getY() - startPoint.getY());
    }
}