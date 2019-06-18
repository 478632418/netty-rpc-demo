package com.dx.nettyrpc;

public class ClientBootstrap {
	public static final String providerName = "HelloService#hello#";

	public static void main(String[] args) {
		NettyServer.startServer("localhost", 18088);
		System.out.println("Netty RPC Server Running .... ");
	}
}
