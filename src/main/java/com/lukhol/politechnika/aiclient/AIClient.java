package com.lukhol.politechnika.aiclient;

import com.google.gson.Gson;
import com.lukhol.politechnika.aiclient.communication.WebsocketClientEndpoint;
import com.lukhol.politechnika.aiclient.game.GameController;
import com.lukhol.politechnika.aiclient.game.GameService;

import java.net.URI;
import java.net.URISyntaxException;

public class AIClient {

    private static final Gson gson = new Gson();
    private static final GameController gameController = new GameController();
    private static final GameService gameService = new GameService(gameController, gson);

    public static void main(String[] args) throws URISyntaxException {
        try {
            System.out.println("Bot started");
            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("ws://localhost:8000"), gson);
            clientEndPoint.setMessageConsumer(gameService::receiveMessage);
            gameService.setWebsocketClientEndpoint(clientEndPoint);
            gameController.setGameService(gameService);
            gameController.start(false);
            while(true){}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

