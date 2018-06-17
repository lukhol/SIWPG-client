package com.lukhol.politechnika.aiclient.communication;

import com.lukhol.politechnika.aiclient.model.GameMap;
import com.lukhol.politechnika.aiclient.model.Player;
import com.lukhol.politechnika.aiclient.model.Point;
import com.lukhol.politechnika.aiclient.model.ServerResponseType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ServerMoveRequest {
    private ServerResponseType type = ServerResponseType.MoveRequest;
    private GameMap map;
    private List<Player> players;
    private Point flag;
}