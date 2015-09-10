package com.bhn.lc.rangefiles;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ReceiveName implements Runnable {

	Socket socket = Info_range.socket;
	InputStream inputStream;
	OutputStream outputStream;
	String fname;
	Handler handler;
	public ReceiveName(Handler handler) {
		this.handler=handler;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			outputStream = socket.getOutputStream();
			outputStream.write("GMF".getBytes());

			fname = getServerMsg();
			if (fname.equals("NotSelectFile")) {//服务器文件未选择
				Message message=new Message();
				message.what=0;
				handler.sendMessage(message);
			} else {
				new Thread(new ReceiveFileBuffere(fname,handler)).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//Log.e("no back", fname);
			e.printStackTrace();
		}
	}

	/**
	 * 接收服务器的消息
	 * 
	 * @throws IOException
	 **/
	public String getServerMsg() throws IOException {
		InputStream inMsg = socket.getInputStream();
		byte[] buf = new byte[1024];
		int len = inMsg.read(buf);
		String serverMsg = new String(buf, 0, len);
		return serverMsg;
	}
}
