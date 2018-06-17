package com.lukhol.politechnika.aiclient.game;

import com.lukhol.politechnika.aiclient.model.GameMap;
import com.lukhol.politechnika.aiclient.model.MoveDirection;
import com.lukhol.politechnika.aiclient.model.Player;
import com.lukhol.politechnika.aiclient.model.Point;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GameController {
    private GameService gameService;

    private String playerName;
    private int playerId;

    public void start() {
        this.playerName = "Player 1";
        gameService.connectToGame(playerName);
    }

    public void connected(int playerId) {
        this.playerId = playerId;
    }

    public void moveRequest(GameMap map, List<Player> players, Point flag) {
        Player myPlayerFromRequest = players.stream().filter(a -> this.playerId == a.getId()).findFirst().orElse(null);
        Point startPoint = new Point(myPlayerFromRequest.getX(), myPlayerFromRequest.getY());
        Point endPoint = flag;

        if(myPlayerFromRequest.isHasFlag()) {
            endPoint = myPlayerFromRequest.getBasePosition();
        }

        if(!startPoint.equals(endPoint)) {
            AStarAlgorithm aStarAlgorithm = new AStarAlgorithm(startPoint, endPoint, map);
            List<Point> points = aStarAlgorithm.start(false);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            gameService.moveRequest(playerId, determinateMoveDirection(startPoint, points.get(0)));
        } else {
            throw new RuntimeException("End?");
        }
    }

    private MoveDirection determinateMoveDirection(Point start, Point next) {
        if(next.getX() > start.getX())
            return MoveDirection.RIGHT;
        else if(next.getX() < start.getX())
            return MoveDirection.LEFT;

        else if(next.getY() > start.getY())
            return MoveDirection.DOWN;
        else if(next.getY() < start.getY())
            return MoveDirection.UP;

        else
            return MoveDirection.NO_MOVE;
    }
}
