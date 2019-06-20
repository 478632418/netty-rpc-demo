package com.dx.patterns.reactor.event;

import com.dx.patterns.reactor.Selector;

/**
 * WRITE �¼�������
 */
public class WriteEventHandler extends EventHandler {
	public WriteEventHandler(Selector selector) {
		super(selector);
	}

	@Override
	public void handle(Event event) {
		// ����WRITE��event�¼�
		if (event.getType() == EventType.WRITE) {

			// TODO ����WRITE״̬���¼�
			System.out.println("WriteEventHandler " + event.getSource());
		}
	}
}