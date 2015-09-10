package com.bhn.lc.localfiles;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.bhn.lc.rangefiles.Info_range;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

/** 手机向电脑发送文件 **/
public class UploadFile {

	Socket socket = Info_range.socket;
	String back;

	public void send(File file) {
		try {

			OutputStream outputStream = socket.getOutputStream();
			String fname = "$" + file.getName();

			outputStream.write(fname.getBytes());//发送文件名

			FileInputStream fin = new FileInputStream(file);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
					outputStream);

			byte[] buf = new byte[1024 * 4];
			int len = 0;
			while ((len = fin.read(buf)) != -1) {
				bufferedOutputStream.write(buf, 0, len);
			}
			outputStream.flush();
			bufferedOutputStream.flush();
			socket.shutdownOutput();

			// fin.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
