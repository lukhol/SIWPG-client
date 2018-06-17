package com.lukhol.politechnika.aiclient.communication;

import com.lukhol.politechnika.aiclient.model.ServerResponseType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerErrorResponse {
    private ServerResponseType type;
    private ErrorCode msg;
}