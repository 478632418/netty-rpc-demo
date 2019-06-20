package com.dx.patterns.reactor.event;

import com.dx.patterns.reactor.Selector;

/**
 * ACCEPT 事件处理器
 * */
public class AcceptEventHandler extends EventHandler {
    public AcceptEventHandler(Selector selector) {
		super(selector);
	}

    @Override
    public void handle(Event event) {
        //处理Accept的event事件
        if (event.getType() == EventType.ACCEPT) {

			System.out.println("AcceptEventHandler " + event.getSource());
            //TODO 处理ACCEPT状态的事件

            //将事件状态改为下一个READ状态，并放入selector的缓冲队列中
            Event readEvent = new Event();
            readEvent.setSource(event.getSource());
            readEvent.setType(EventType.READ);

            selector.addEvent(readEvent);
        }
    }
}