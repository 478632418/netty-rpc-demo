package com.dx.patterns.reactor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dx.patterns.reactor.event.Event;
import com.dx.patterns.reactor.event.EventHandler;
import com.dx.patterns.reactor.event.EventType;

/**
 * reactorģʽ��Dispatcher�࣬����event�ķַ���eventHandler��ά��<br>
 * �¼��ַ���������reactorģʽ�������Ҫ��������ڽ��յ��������ݷַ������ٽ��зַ�����Ӧ���¼�������������Ҫ�ӿ�ʼ״̬������
 */
public class Dispatcher {
	/**
	 * ͨ��ConcurrentHashMap��ά����ͬ�¼�������
	 */
	Map<EventType, EventHandler> eventHandlerMap = new ConcurrentHashMap<EventType, EventHandler>();
	/**
	 * ����ֻά��һ��selector�����¼�ѡ��nettyΪ�˱�֤����ʵ���˶��selector����֤ѭ���������ܣ�
	 * ��ͬ�¼����벻ͬ��selector���¼��������
	 */
	Selector selector;

	Dispatcher(Selector selector) {
		this.selector = selector;
	}

	/**
	 * ��Dispatcher��ע��eventHandler
	 */
	public void registEventHandler(EventType eventType, EventHandler eventHandler) {
		eventHandlerMap.put(eventType, eventHandler);
	}

	public void removeEventHandler(EventType eventType) {
		eventHandlerMap.remove(eventType);
	}

	public void handleEvents() {
		dispatch();
	}

	/**
	 * ����ֻ��ʵ���˼򵥵��¼��ַ�����Ӧ�Ĵ��������������еĴ���������ͬ������reactorģʽ�ĵ���ʵ��NIO�ж�����handle�첽����
	 * ����֤������
	 * 
	 * @return
	 */
	private void dispatch() {
		System.out.println("enter dispacth...");

		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			List<Event> events = selector.select();

			for (Event event : events) {
				System.out.println("dispacth..." + event.getType());
				EventHandler eventHandler = eventHandlerMap.get(event.getType());
				eventHandler.handle(event);
			}
		}
	}
}