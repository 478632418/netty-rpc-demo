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
	 * �յ���������ݣ����ѵȴ��߳�
	 */
	@Override
	public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) {
		result = msg.toString();
		notify();
	}

	/**
	 * д�����ݣ���ʼ�ȴ�����
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