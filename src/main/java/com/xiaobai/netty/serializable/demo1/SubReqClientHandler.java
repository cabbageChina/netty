package com.xiaobai.netty.serializable.demo1;

import org.apache.log4j.Logger;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqClientHandler extends ChannelHandlerAdapter {

	private static final Logger logger = Logger
			.getLogger(SubReqClientHandler.class);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for (int i = 1; i <= 10; i++) {
			ctx.write(req(i));
		}
		ctx.flush();
	}

	private SubscribeReq req(int subReqId) {
		SubscribeReq req = new SubscribeReq();
		req.setAddress("�Ͼ��н�������ɽ���ҵ��ʹ�԰");
		req.setPhoneNumber("186********");
		req.setProductName("Netty Ȩ��ָ��");
		req.setSubReqId(subReqId);
		req.setUserName("chenguihang");
		return req;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		SubscribeResp resp = (SubscribeResp) msg;
		logger.warn("�ͻ��˽��յ�����Ϣ : " + resp);
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		ctx.close();
	}

}
