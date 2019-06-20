package com.dx.patterns.reactor.event;

import com.dx.patterns.reactor.Selector;

/**
 * ACCEPT �¼�������
 * */
public class AcceptEventHandler extends EventHandler {
    public AcceptEventHandler(Selector selector) {
		super(selector);
	}

    @Override
    public void handle(Event event) {
        //����Accept��event�¼�
        if (event.getType() == EventType.ACCEPT) {

			System.out.println("AcceptEventHandler " + event.getSource());
            //TODO ����ACCEPT״̬���¼�

            //���¼�״̬��Ϊ��һ��READ״̬��������selector�Ļ��������
            Event readEvent = new Event();
            readEvent.setSource(event.getSource());
            readEvent.setType(EventType.READ);

            selector.addEvent(readEvent);
        }
    }
}