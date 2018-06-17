package com.lukhol.politechnika.aiclient.model;

import lombok.Data;

@Data
public class FrontConnect {
    private GameMap map;
    private String msg;
    private String type;
    private int token;
}