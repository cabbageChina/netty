package com.xiaobai.netty.serializable.demo2;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.protobuf.InvalidProtocolBufferException;

public class TestSubscribeReqProto {
	
	private static final Logger logger = Logger.getLogger(TestSubscribeReqProto.class);
	
	public static void main(String[] args) throws InvalidProtocolBufferException {
		SubscribeReqProto.SubscribeReq req = createSubscribeReq();
		
		logger.warn("Before encode : " + req.toString());
		SubscribeReqProto.SubscribeReq req2 = decode(encode(req));
		logger.warn("After decode : " + req.toString());
		logger.warn("Assert equal : --> " + req2.equals(req));
	}
	
	
	private static byte[] encode(SubscribeReqProto.SubscribeReq req) {
		return req.toByteArray();
	}
	
	private static SubscribeReqProto.SubscribeReq decode(byte[] body) throws InvalidProtocolBufferException {
		return SubscribeReqProto.SubscribeReq.parseFrom(body);
	}
	
	private static SubscribeReqProto.SubscribeReq createSubscribeReq() {
		
		SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
		builder.setSubReqId(1);
		builder.setUserName("chenguihang");
		builder.setProductName("Netty Book");
		List<String> address = new ArrayList<>();
		address.add("NanJing YuHuaTai");
		address.add("BeiJing LiuLiChang");
		address.add("ShenZhen HongShuLin");
		builder.addAllAddress(address);
		
		return builder.build();
		
	}
}
