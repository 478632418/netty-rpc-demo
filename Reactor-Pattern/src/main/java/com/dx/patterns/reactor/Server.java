package com.dx.patterns.reactor;

import com.dx.patterns.reactor.event.AcceptEventHandler;
import com.dx.patterns.reactor.event.EventType;
import com.dx.patterns.reactor.event.ReadEventHandler;
import com.dx.patterns.reactor.event.WriteEventHandler;
import com.dx.patterns.reactor.test.SimulateAcceptor;
import com.dx.patterns.reactor.test.SimulateClient;

/**
 * ��������reactor����������ط����������
 */
public class Server {
	Selector selector = new Selector();
	Dispatcher eventLooper = new Dispatcher(selector);
	Acceptor acceptor;

	public Server(int port) {
		acceptor = new Acceptor(selector, port);
	}

	/**
	 * 1��Ӧ������������ע���¼�handleע�ᵽReactor�У�<br>
	 * 2������Reactor�����������¼�ѭ�����ȴ�ע����¼�������<br>
	 * 3���¼�������select���أ�Reactor���¼��ַ���֮ǰע��Ļص������д���
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
