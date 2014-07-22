package com.xiaobai.netty.serializable.demo2;

import org.apache.log4j.Logger;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqClientHandler extends ChannelHandlerAdapter {

	private static final Logger logger = Logger
			.getLogger(SubReqClientHandler.class);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for (int i = 0; i < 10; i++) {
			ctx.writeAndFlush(req(i));
		}
	}

	private SubscribeReqProto.SubscribeReq req(int subReqId) {
		SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq
				.newBuilder();
		builder.setSubReqId(subReqId);
		builder.setProductName("Netty Book For Protobuf");
		builder.setUserName("chenguihang");
		builder.addAddress("Nanjing YuHuaTai");
		builder.addAddress("BeiJing LiuLiChang");
		builder.addAddress("ShenZhen HongShuLin");

		return builder.build();
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		SubscribeRespProto.SubscribeResp resp = (SubscribeRespProto.SubscribeResp) msg;
		logger.info("客户端接收到的消息：" + resp.toString());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
