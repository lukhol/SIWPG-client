package com.lukhol.politechnika.aiclient.communication;

import com.lukhol.politechnika.aiclient.model.MoveDirection;
import com.lukhol.politechnika.aiclient.model.ServerRequestType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoveRequest {
    private ServerRequestType type = ServerRequestType.Move;
    private MoveDirection move;
    private int playerId;
}