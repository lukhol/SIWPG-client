package com.lukhol.politechnika.aiclient.model;

import lombok.Data;

@Data
public class Player {
    private int id;
    private boolean hasFlag;
    private boolean isAlive;
    private String name;
    private int maxMovesPerRound;
    private Point basePosition;
    private int viewRange;
    private double movesLeft;
    private int x;
    private int y;
}