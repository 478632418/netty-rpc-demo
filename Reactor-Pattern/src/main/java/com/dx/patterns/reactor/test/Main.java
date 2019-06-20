package com.dx.patterns.reactor.test;

import com.dx.patterns.reactor.Server;

public class Main {
	public static void main(String[] args) {
		Server server = new Server(10);			
		server.start();		
	}
}
