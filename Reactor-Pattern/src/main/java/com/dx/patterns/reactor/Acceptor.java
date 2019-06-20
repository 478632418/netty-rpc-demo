package com.dx.patterns.reactor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.dx.patterns.reactor.event.Event;
import com.dx.patterns.reactor.event.EventType;
import com.dx.patterns.reactor.event.InputSource;

/**
 * reactor���¼������࣬�����ʼ��selector�ͽ��ջ������
 * */
public class Acceptor implements Runnable{
    private int port; // server socket port
    private Selector selector;

    /**
     * ���� serversocket��ͨ��LinkedBlockingQueue��ģ���ⲿ�����������
     * */ 
    private BlockingQueue<InputSource> sourceQueue = new LinkedBlockingQueue<InputSource>();

    Acceptor(Selector selector, int port) {
        this.selector = selector;
        this.port = port;
    }

    /**
     * �ⲿ�������������Ҫ���뵽���������
     * */
    public void addNewConnection(InputSource source) {
        sourceQueue.offer(source);
    }

    public int getPort() {
        return this.port;
    }

    public void run() {
        while (true) {
            InputSource source = null;
            try {
                // �൱�� serversocket.accept()�������������󣬸�������������л�ȡ��������
                source = sourceQueue.take();
            } catch (InterruptedException e) {
                // ignore it;
            }

            //���յ�InputSource�󽫽��յ�event����typeΪACCEPT������source��ֵ��event
            if (source != null) {
                Event acceptEvent = new Event();
                acceptEvent.setSource(source);
                acceptEvent.setType(EventType.ACCEPT);

                selector.addEvent(acceptEvent);
            }

        }
    }
}