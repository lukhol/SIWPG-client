package com.lukhol.politechnika.aiclient.communication;

import com.lukhol.politechnika.aiclient.model.ServerResponseType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerConnectResponse {
    private ServerResponseType type;
    private String msg;
    private int playerId;
}