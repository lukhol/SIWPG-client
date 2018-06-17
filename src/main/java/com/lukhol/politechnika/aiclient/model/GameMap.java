package com.lukhol.politechnika.aiclient.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameMap {
    private int width;
    private int height;
    private int[][] fields;
}