package com.lukhol.politechnika.aiclient.communication;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lukhol.politechnika.aiclient.model.ServerResponseType;

import javax.websocket.*;
import java.net.URI;
import java.util.function.BiConsumer;

@ClientEndpoint
public class WebsocketClientEndpoint {

    private final Gson gson;
    private Session userSession = null;
    private BiConsumer<ServerResponseType, String> messageConsumer;

    public WebsocketClientEndpoint(URI endpointURI, Gson gson) {
        this.gson = gson;
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void onOpen(Session userSession) {
        this.userSession = userSession;
    }


    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        System.out.println("closing websocket");
        this.userSession = null;
    }

    @OnMessage
    public void onMessage(String message) {
        //System.out.println(String.format("RECEIVED: %s", message));

        JsonObject jsonObject = gson.fromJson(message, JsonObject.class);
        ServerResponseType serverResponseType = ServerResponseType.valueOf(jsonObject.get("type").toString().replace("\"",""));

        messageConsumer.accept(serverResponseType, message);
    }

    public void sendMessage(String message) {
        //System.out.println(String.format("    SEND: %s", message));
        this.userSession.getAsyncRemote().sendText(message);
    }

    public void setMessageConsumer(BiConsumer<ServerResponseType, String> consumer){
        this.messageConsumer = consumer;
    }
}