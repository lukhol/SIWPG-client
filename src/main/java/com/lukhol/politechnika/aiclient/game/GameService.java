package com.lukhol.politechnika.aiclient.game;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lukhol.politechnika.aiclient.communication.*;
import com.lukhol.politechnika.aiclient.model.MoveDirection;
import com.lukhol.politechnika.aiclient.model.ServerRequestType;
import com.lukhol.politechnika.aiclient.model.ServerResponseType;
import lombok.Setter;

import java.util.*;


public class GameService {
    private final GameController gameController;
    private final Gson gson;

    @Setter
    private WebsocketClientEndpoint websocketClientEndpoint;

    public GameService(GameController gameController, Gson gson) {
        this.gameController = gameController;
        this.gson = gson;
    }

    public void receiveMessage(ServerResponseType serverResponseType, String message) {
        switch(serverResponseType) {
            case Connected: {
                ServerConnectResponse connectResponse = gson.fromJson(message, ServerConnectResponse.class);
                gameController.connected(connectResponse.getPlayerId());
                break;
            }
            case Error: {
                //System.out.println(ServerResponseType.Error);
                break;
            }
            case ResponseOK: {
                //System.out.println(ServerResponseType.ResponseOK);
                break;
            }
            case GameOver: {
                //System.out.println(ServerResponseType.GameOver);
                break;
            }
            case FrontConnect: {
                //System.out.println(ServerResponseType.FrontConnect);
                break;
            }
            case MoveRequest: {
                ServerMoveRequest moveRequest = gson.fromJson(message, ServerMoveRequest.class);
                gameController.moveRequest(moveRequest.getMap(), moveRequest.getPlayers(), moveRequest.getFlag());
                break;
            }
        }
    }

    public void connectToGame(String playerName) {
        ConnectRequest connectRequest = new ConnectRequest();
        connectRequest.setName(playerName);

        websocketClientEndpoint.sendMessage(gson.toJson(connectRequest));
    }

    public void moveRequest(int playerId, MoveDirection moveDirection) {
        MoveRequest moveRequest = new MoveRequest();
        moveRequest.setPlayerId(playerId);
        moveRequest.setMove(moveDirection);

        websocketClientEndpoint.sendMessage(gson.toJson(moveRequest));
    }
}