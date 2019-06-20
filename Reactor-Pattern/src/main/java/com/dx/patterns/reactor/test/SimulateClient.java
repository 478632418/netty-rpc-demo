package com.dx.patterns.reactor.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.zip.DeflaterInputStream;

public class SimulateClient implements Runnable {
	@SuppressWarnings("resource")
	public void run() {
		for (;;) {
			try {
				Thread.sleep(2 * 1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			try {
				// 创建Socket,IP及端口号
				Socket s1 = new Socket("127.0.0.1", 18888);			

				OutputStream out = s1.getOutputStream(); // 输入流
				DataOutputStream dataOut = new DataOutputStream(out);

				dataOut.write(("Test-" + new Random().nextInt()).getBytes(Charset.forName("utf-8")));
				
				dataOut.close();
				out.close();
				s1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// dataOut.close();
			// out.close();

		}
	}

}
