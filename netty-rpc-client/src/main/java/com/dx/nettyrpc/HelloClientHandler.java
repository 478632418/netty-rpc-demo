package com.dx.nettyrpc;

import java.util.concurrent.Callable;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class HelloClientHandler extends ChannelInboundHandlerAdapter implements Callable<Object> {
	private ChannelHandlerContext context;
	private String result;
	private String para;

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		context = ctx;
	}

	/**
	 * 收到服务端数据，唤醒等待线程
	 */
	@Override
	public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) {
		result = msg.toString();
		notify();
	}

	/**
	 * 写出数据，开始等待唤醒
	 * @throws InterruptedException 
	 */
	public synchronized Object call() throws InterruptedException  {
		context.writeAndFlush(para);
		wait();
		return result;
	}

	protected void setPara(String para) {
		this.para = para;
	}
}