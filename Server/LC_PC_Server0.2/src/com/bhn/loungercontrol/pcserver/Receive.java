package com.bhn.loungercontrol.pcserver;


import java.awt.AWTException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JLabel;

import com.bhn.loungercontrol.file.FileActivity;
import com.lzy.loungercontrol.dosremote.DosRemotePc;
import com.lzy.loungercontrol.mouse.MouseActivity;
import com.bhn.loungercontrol.file.*;


public class Receive implements Runnable {
	Socket socket;
	InputStream in;
	String model;
	String msg;
	JLabel scopelable;
	Activity activity;
	MouseActivity mouseActivity;
	FileActivity fileActivity;
	DosRemotePc dosRemotePc;
	public Receive(Socket socket) {
		this.socket = socket;
	}
	public Receive(){}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				in = socket.getInputStream();
				byte[] buf = new byte[1024*4];
				int len = in.read(buf);
				if (len==-1) {
					in.close();
					break;
				}
				msg = new String(buf, 0, len);
				System.out.println("开"+msg);
				if (msg.startsWith("#")) {//PPT按键和键盘事件
					activity=new Activity();
					activity.act(msg);
				}else if (msg.endsWith(":")) {//鼠标相关事件
					mouseActivity = new MouseActivity();
					mouseActivity.act(msg);
				}else if(msg.startsWith("$")){//文件操作
					System.out.println("开始文件操作");
					fileActivity=new FileActivity();
					fileActivity.file(socket,msg);
				}else if (msg.equals("GMF")) {
					new Thread(new GetPCFilesRun(socket)).start();
				}else if (msg.startsWith("|")) {
					dosRemotePc = new DosRemotePc();
					dosRemotePc.act(msg);
				}
				else{//手机型号
					Connect.model = msg;
				}
				
				
				System.out.println("receive msg->"+msg+";model:"+model);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
