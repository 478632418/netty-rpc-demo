package com.dx.patterns.reactor.event;

import com.dx.patterns.reactor.Selector;

/**
 * READ 事件处理器
 */
public class ReadEventHandler extends EventHandler {
	public ReadEventHandler(Selector selector) {
		super(selector);
	}

	@Override
	public void handle(Event event) {
		// 处理READ的event事件
		if (event.getType() == EventType.READ) {

			// TODO 处理READ状态的事件
			System.out.println("ReadEventHandler " + event.getSource());
		}
	}
}