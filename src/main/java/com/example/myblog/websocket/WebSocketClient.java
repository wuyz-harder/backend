package com.example.myblog.websocket;

import lombok.Data;
import lombok.ToString;

import javax.websocket.Session;
@Data
@ToString
public class WebSocketClient {
    private Session session;
    private String uri;

}
