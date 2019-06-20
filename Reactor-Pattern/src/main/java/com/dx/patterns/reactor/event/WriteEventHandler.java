package com.dx.patterns.reactor.event;

import com.dx.patterns.reactor.Selector;

/**
 * WRITE 事件处理器
 */
public class WriteEventHandler extends EventHandler {
	public WriteEventHandler(Selector selector) {
		super(selector);
	}

	@Override
	public void handle(Event event) {
		// 处理WRITE的event事件
		if (event.getType() == EventType.WRITE) {

			// TODO 处理WRITE状态的事件
			System.out.println("WriteEventHandler " + event.getSource());
		}
	}
}