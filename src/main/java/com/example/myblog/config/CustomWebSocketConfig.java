//package com.example.myblog.config;
//
//import com.example.myblog.websocket.WebSocketSERVOCE;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//
//@Configuration
//@EnableWebSocket
//@CrossOrigin
//public class CustomWebSocketConfig implements WebSocketConfigurer {
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(WebSocketSERVOCE(), "/webSocketBySpring/customWebSocketHandler");
//        registry.addHandler(WebSocketSERVOCE(), "/sockjs/webSocketBySpring/customWebSocketHandler").withSockJS();
//    }
//
//    @Bean
//    public WebSocketSERVOCE WebSocketSERVOCE() {
//        return new WebSocketSERVOCE();
//    }
//}
