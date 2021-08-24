package com.example.myblog.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    public static class SingleNettyServer{
        static final NettyServer instance = new NettyServer();
    }
    public static NettyServer getInstance(){
        return SingleNettyServer.instance;
    }
    private EventLoopGroup master;
    private EventLoopGroup slaver;
    private ServerBootstrap serverBootstrap;
    private ChannelFuture channelFuture;
    private NettyServer(){
        master = new NioEventLoopGroup();
        slaver = new NioEventLoopGroup();
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(master,slaver)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerInitializer());
    }
    public void start(){
        this.channelFuture =serverBootstrap.bind(6666);
        System.out.println("netty 启动了！！！！！");
    }
}
