package com.xiaobai.netty.serializable.demo1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class SubReqClient {

	public static void main(String[] args) {
		String host = "127.0.0.1";
		int port = 8080;
		new SubReqClient().connect(host, port);
	}

	public void connect(String host, int port) {
		
		EventLoopGroup worker = new NioEventLoopGroup();
		
		Bootstrap b = new Bootstrap();
		b.group(worker);
		b.channel(NioSocketChannel.class);
		b.option(ChannelOption.TCP_NODELAY, true);
		b.handler(new ChannelInitializer<Channel>() {
			@Override
			protected void initChannel(Channel ch) throws Exception {
				ch.pipeline().addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
				ch.pipeline().addLast(new ObjectEncoder());
				ch.pipeline().addLast(new SubReqClientHandler());
			}
		});
		
		try {
			ChannelFuture f = b.connect(host,port).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			worker.shutdownGracefully();
		}
	}

}
