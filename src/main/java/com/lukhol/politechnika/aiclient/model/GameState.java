package com.lukhol.politechnika.aiclient.model;

import lombok.Data;

import java.util.List;

@Data
public class GameState {
    private List<Player> players;
    private Point flag;
}