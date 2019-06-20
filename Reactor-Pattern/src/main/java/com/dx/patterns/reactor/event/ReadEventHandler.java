package com.dx.patterns.reactor.event;

import com.dx.patterns.reactor.Selector;

/**
 * READ �¼�������
 */
public class ReadEventHandler extends EventHandler {
	public ReadEventHandler(Selector selector) {
		super(selector);
	}

	@Override
	public void handle(Event event) {
		// ����READ��event�¼�
		if (event.getType() == EventType.READ) {

			// TODO ����READ״̬���¼�
			System.out.println("ReadEventHandler " + event.getSource());
		}
	}
}