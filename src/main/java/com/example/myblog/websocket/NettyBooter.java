package com.example.myblog.websocket;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
            if(event.getApplicationContext().getParent()==null){
                try{
                    NettyServer.getInstance().start();
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
    }
}
