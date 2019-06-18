package com.dx.nettyrpc;

import com.dx.service.impl.HelloServiceImpl;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * ���ڴ�����������
 */
public class HelloServerHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		// ��η���Լ��������ñ��ط�������������
		if (msg.toString().startsWith(ClientBootstrap.providerName)) {
			String result = new HelloServiceImpl().hello(msg.toString().substring(msg.toString().lastIndexOf("#") + 1));
			ctx.writeAndFlush(result);
		}
	}
}