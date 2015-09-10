package com.bhn.loungercontrol.pcserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener implements Runnable {
	ServerSocket serverSocket;
	Socket socket;
	Receive receive;
	Thread receiveThread;
	
	public Listener(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	public Listener(){}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				socket = serverSocket.accept();
				
				receive=new Receive(socket);
				receiveThread=new Thread(receive);
				receiveThread.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
