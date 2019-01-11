package com.yjm.netty;

import com.yjm.remote.DefaultFuture;
import com.yjm.remote.Request;
import com.yjm.remote.Response;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * com.yjm.netty
 * Created by YJM6280 .
 */
public class NettyClient {
    private  static  final String host= System.getProperty("host","127.0.0.1");
    private  static  final int port =Integer.parseInt(System.getProperty("port","8009"));
    private Channel channel;
    public void connect(String host,Integer port) throws InterruptedException {
        {
            System.out.println("客户端启动,连接ip>>"+host+">>端口>>"+port);
            NioEventLoopGroup work = new NioEventLoopGroup();
            try {
                Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(work)
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                                ch.pipeline().addLast("decoder", new StringDecoder());
                                ch.pipeline().addLast("encoder", new StringEncoder());
                                ch.pipeline().addLast(new EchoClientHandler());
                            }
                        });
                System.out.println("客户端启动成功");
                ChannelFuture channelFuture = bootstrap.connect(host,port).sync();
                channel = channelFuture.channel();
                //channelFuture.channel().closeFuture().sync();
            } finally {
               // work.shutdownGracefully();
            }
        }
    }

    public Response sendAndGetResponse(Request request){
        DefaultFuture future = new DefaultFuture(channel,request,request.getId(),100);
        channel.writeAndFlush(request);
        return  future.get(100);
    }
}
