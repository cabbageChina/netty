package com.xiaobai.netty.serializable.demo2;

import org.apache.log4j.Logger;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqServerHandler extends ChannelHandlerAdapter {
	
	private static final Logger logger = Logger.getLogger(SubReqServerHandler.class);
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq) msg;
		logger.info("服务端接收到的消息：" + req.toString());
		ctx.writeAndFlush(resp(req.getSubReqId()));
	}

	private SubscribeRespProto.SubscribeResp resp(int subReqId) {
		SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp
				.newBuilder();
		builder.setSubReqId(subReqId);
		builder.setDesc("Netty book order succeed , 3 days later , sent to the designated address");
		builder.setRespCode(0);
		return builder.build();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
