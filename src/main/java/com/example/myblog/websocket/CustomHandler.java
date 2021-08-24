package com.example.myblog.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

public class CustomHandler extends SimpleChannelInboundHandler <TextWebSocketFrame>{
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private int num = 0;


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame webSocketFrame) throws Exception {
        System.out.println("接收到的信息是" + webSocketFrame.text());
        JSONObject jsonObject = JSON.parseObject(webSocketFrame.text());
        String msg = (String) jsonObject.get("msg");
        System.out.println(msg);

    }



    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
        num = num + 1 ;
        System.out.println("目前一共有:"  + num);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
       clients.remove(ctx.channel());
        num = num - 1 ;
        System.out.println("目前一共有:"  + num);
    }
}
