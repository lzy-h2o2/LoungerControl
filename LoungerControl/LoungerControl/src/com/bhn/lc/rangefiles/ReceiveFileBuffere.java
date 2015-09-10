package com.bhn.lc.rangefiles;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/** 手机接收电脑文件 **/
public class ReceiveFileBuffere implements Runnable {
	InputStream inputStream;
	Socket socket = Info_range.socket;
	String x = "这";
	String con = "GMF";
	String fname;
	OutputStream outputStream;
	Handler handler;

	public ReceiveFileBuffere(String fname, Handler handler) {
		this.fname = fname;
		this.handler = handler;
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File sdcard = Environment.getExternalStorageDirectory();

			try {
				FileOutputStream fileOutputStream = new FileOutputStream(sdcard
						+ "/" + fname);
				inputStream = socket.getInputStream();
				BufferedInputStream bufferedInputStream = new BufferedInputStream(
						inputStream);
				byte[] bc = new byte[8];
				int m = 0;
				Log.e(x, x + sdcard.toString());
				while ((m = bufferedInputStream.read(bc)) != -1) {
					fileOutputStream.write(bc, 0, m);
				}
				
				Message message = new Message();
				message.what = 1;
				handler.sendMessage(message);
				Log.e("sdf", "sfd");
				fileOutputStream.flush();
				fileOutputStream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
