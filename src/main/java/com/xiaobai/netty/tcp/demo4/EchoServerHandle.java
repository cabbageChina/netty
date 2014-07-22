package com.xiaobai.netty.tcp.demo4;

import org.apache.log4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class EchoServerHandle extends ChannelHandlerAdapter{
	
	private final static Logger logger = Logger.getLogger(EchoServerHandle.class.getName());
	
	private int counter = 0;
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		String body = (String)msg;
		logger.warn("This is " + ++counter + " times receive client : [" + body + "]" );
		body += "$_";
		ByteBuf resp = Unpooled.compositeBuffer(body.length());
		resp.writeBytes(body.getBytes());
		ctx.writeAndFlush(resp);
		
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		logger.warn(cause.getMessage());
		ctx.close();
	}
	
}
