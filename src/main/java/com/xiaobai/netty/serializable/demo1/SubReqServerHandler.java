package com.xiaobai.netty.serializable.demo1;

import org.apache.log4j.Logger;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqServerHandler extends ChannelHandlerAdapter {

	private final static Logger logger = Logger
			.getLogger(SubReqServerHandler.class);

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {

		SubscribeReq req = (SubscribeReq) msg;

		if ("chenguihang".equals(req.getUserName())) {
			logger.warn("服务器接收到的消息:" + req.toString());
		}
		ctx.writeAndFlush(resp(req.getSubReqId()));
	}

	private SubscribeResp resp(int subReqId) {
		SubscribeResp resp = new SubscribeResp();
		resp.setSubReqId(subReqId);
		resp.setRespCode(0);
		resp.setDesc("Netty boot order succeed , 3 days later, sent to the designated address");
		return resp;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
