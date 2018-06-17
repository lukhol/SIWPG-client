package com.lukhol.politechnika.aiclient.communication;

import com.lukhol.politechnika.aiclient.model.ServerRequestType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConnectRequest {
    private ServerRequestType type = ServerRequestType.Connect;
    private String name;
}