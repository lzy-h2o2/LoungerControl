package com.bhn.loungercontrol.pcserver;

import java.io.IOException;
import java.net.ServerSocket;

public class Connect {
	ServerSocket serverSocket;
	Thread listenerThread;
	Thread receiveThread;
	Listener listener;
	public static String model;
    public void start(int port) {
		try {
			serverSocket=new ServerSocket(port);
			listener=new Listener(serverSocket);
			
		    listenerThread=new Thread(listener);
			listenerThread.start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    
    public void stop() {
		try {
			listenerThread.stop();
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
