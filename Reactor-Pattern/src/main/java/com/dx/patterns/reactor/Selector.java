package com.dx.patterns.reactor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.dx.patterns.reactor.event.Event;

/**
 * reactorģʽ�е�Demultiplexer�࣬�ṩselect�����������ڴӻ�������в��ҳ�����������event�б�
 * */
public class Selector {
    //����һ����������queueʵ�ֻ�����У����ڱ�֤�̰߳�ȫ
    private BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<Event>();
    //����һ��object����synchronize����������
    private Object lock = new Object();

    List<Event> select() {
        return select(0);
    }

    //
    List<Event> select(long timeout) {
        if (timeout > 0) {
            if (eventQueue.isEmpty()) {
                synchronized (lock) {
                    if (eventQueue.isEmpty()) {
                        try {
                            lock.wait(timeout);
                        } catch (InterruptedException e) {
                        }
                    }
                }

            }
        }
        
        //TODO ������ֻ�Ǽ򵥵Ľ�event�б�ȫ�����أ������ڴ˴�����ҵ���߼���ѡ������������event���з���
        List<Event> events = new ArrayList<Event>();
        eventQueue.drainTo(events);
        return events;
    }

    public void addEvent(Event e) {
        //��event�¼��������
        boolean success = eventQueue.offer(e);
        if (success) {
            synchronized (lock) {
                //����������¼����lock�������
                lock.notify();
            }

        }
    }

}
