package com.xiaobai.netty.tcp.demo4;

import org.apache.log4j.Logger;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class EchoClientHandle extends ChannelHandlerAdapter{
	
	private final static Logger logger = Logger.getLogger(EchoClientHandle.class.getName());
	
	private final static String ECHO_REQ = "Hi , Chenguihang.Welcome to Netty.$_";
	
	private int count;
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for(int i=0;i<100;i++){
			ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
		}
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
		logger.warn("This is " + ++count + " times receive server : " + (String)msg);
		
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		logger.warn(cause.getMessage());
		ctx.close();
	}
}
