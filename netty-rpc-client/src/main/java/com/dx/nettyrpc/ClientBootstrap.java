package com.dx.nettyrpc;

import com.dx.service.HelloService;

public class ClientBootstrap {
	public static final String providerName = "HelloService#hello#";

	public static void main(String[] args) throws InterruptedException {
		RpcConsumer consumer = new RpcConsumer();
		//����һ���������
		HelloService service = (HelloService) consumer.createProxy(HelloService.class, providerName);
		for (;;) {
			Thread.sleep(1000);
			System.out.println(service.hello("are you ok ?"));
		}
	}
}
