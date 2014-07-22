package com.xiaobai.netty.tcp.demo3;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeServerHandler extends ChannelHandlerAdapter {

	private final static Logger logger = Logger
			.getLogger(TimeServerHandler.class.getName());

	private int counter;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
		String body = (String)msg;
		logger.warn("The time server receive order : " + body
				+ " ; the counter is : " + ++counter);
		String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss sss").format(new Date()) + System.getProperty("line.separator");
		byte[] resp = currentTime.getBytes();
		ByteBuf respBuf = Unpooled.buffer(resp.length);
		respBuf.writeBytes(resp);
		ctx.writeAndFlush(respBuf);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		ctx.close();
	}

}
