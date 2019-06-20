package com.dx.patterns.reactor.event;

import com.dx.patterns.reactor.Selector;

/**
 * event处理器的抽象类
 */
public abstract class EventHandler {
	protected Selector selector;

	public EventHandler(Selector selector) {
		this.selector = selector;
	}

	public abstract void handle(Event event);
}