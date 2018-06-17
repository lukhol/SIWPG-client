package com.lukhol.politechnika.aiclient.communication;

import com.lukhol.politechnika.aiclient.model.Player;
import com.lukhol.politechnika.aiclient.model.ServerResponseType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerGameOverResponse {
    private ServerResponseType type;
    private Player winner;
}