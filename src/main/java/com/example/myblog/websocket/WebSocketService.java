package com.example.myblog.websocket;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/websocket/{username}")
public class WebSocketService {
        private final static Logger logger = Logger.getLogger(WebSocketService.class);
        private static int clientNum = 0;
        private static ConcurrentHashMap<String,WebSocketClient> webSocketClientMap = new ConcurrentHashMap<>();
//       保存每个用户的session
        private Session session;
//        保存每个用户的用户名
        private String username;

        @OnOpen
        public void onOpen(Session session, @PathVariable("username") String username){
                if(!webSocketClientMap.containsKey(username)){
                        clientNum = clientNum + 1;
                }

        }

}
