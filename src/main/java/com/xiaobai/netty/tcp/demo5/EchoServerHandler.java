package com.xiaobai.netty.tcp.demo5;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import org.apache.log4j.Logger;

public class EchoServerHandler extends ChannelHandlerAdapter {

	private static final Logger logger = Logger
			.getLogger(EchoServerHandler.class);

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		logger.warn((String) msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		logger.error("handler has error : " + cause.getMessage());
		ctx.close();
	}
	
}
