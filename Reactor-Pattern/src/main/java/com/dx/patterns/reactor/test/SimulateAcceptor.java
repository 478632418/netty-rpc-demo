package com.dx.patterns.reactor.test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Random;

import com.dx.patterns.reactor.Acceptor;
import com.dx.patterns.reactor.event.InputSource;

public class SimulateAcceptor implements Runnable {
	private Acceptor acceptor;

	public SimulateAcceptor(Acceptor acceptor) {
		this.acceptor = acceptor;
	}

	public void run() {
		ServerSocket ss = null;
		try {
			// ����������ServerSocket
			ss = new ServerSocket(18888);
		} catch (IOException e) {
		}

		System.out.println("Simulate running...");

		while (true) {
			try {
				// ͨ��accept()���������ͻ�������
				Socket s = ss.accept();

				String ip = s.getInetAddress().getHostAddress();
				// ��ȡ���ӹ����ͻ��˵�IP��ַ
				// 3��ͨ��socket�����ȡ��������Ҫ��ȡ�ͻ��˷��������ݣ�
				InputStream in = s.getInputStream();
				byte[] buf = new byte[1024];
				int len = in.read(buf);
				String text = new String(buf, 0, len);
				//System.out.println("sockt accept >>>>>>> " + ip + ":" + text);
				this.acceptor.addNewConnection(new InputSource(ip + ":" + text, new Random().nextLong()));
				in.close();
				s.close();
				
//				OutputStream out = s.getOutputStream();
//				DataOutputStream dataOutm = new DataOutputStream(out);
//				dataOutm.write("Received success".getBytes(Charset.forName("utf-8")));				
//				dataOutm.close();
//				out.close();
			} catch (IOException e) {
			}
		}
	}

}
