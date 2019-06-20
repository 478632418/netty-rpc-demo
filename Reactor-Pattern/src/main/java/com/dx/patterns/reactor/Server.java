package com.dx.patterns.reactor;

import com.dx.patterns.reactor.event.AcceptEventHandler;
import com.dx.patterns.reactor.event.EventType;
import com.dx.patterns.reactor.event.ReadEventHandler;
import com.dx.patterns.reactor.event.WriteEventHandler;
import com.dx.patterns.reactor.test.SimulateAcceptor;
import com.dx.patterns.reactor.test.SimulateClient;

/**
 * 负责启动reactor服务并启动相关服务接收请求
 */
public class Server {
	Selector selector = new Selector();
	Dispatcher eventLooper = new Dispatcher(selector);
	Acceptor acceptor;

	public Server(int port) {
		acceptor = new Acceptor(selector, port);
	}

	/**
	 * 1）应用启动，将关注的事件handle注册到Reactor中；<br>
	 * 2）调用Reactor，进入无限事件循环，等待注册的事件到来；<br>
	 * 3）事件到来，select返回，Reactor将事件分发到之前注册的回调函数中处理；
	 */
	public void start() {
		eventLooper.registEventHandler(EventType.ACCEPT, new AcceptEventHandler(selector));
		eventLooper.registEventHandler(EventType.READ, new ReadEventHandler(selector));
		eventLooper.registEventHandler(EventType.WRITE, new WriteEventHandler(selector));

		new Thread(acceptor, "Acceptor-" + acceptor.getPort()).start();
		new Thread(new SimulateAcceptor(acceptor), "SimulateAcceptor-" + acceptor.getPort()).start();
		new Thread(new SimulateClient(), "SimulateClient-" + acceptor.getPort()).start();

		eventLooper.handleEvents();
	}

}
