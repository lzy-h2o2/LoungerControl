package com.lzy.loungercontrol.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.lzy.loungercontrol.untils.ProjectEnvironment;

import android.os.Build;
import android.util.Log;

/**
 * 本类为网络连接管理者
 * */
public class Connecter {
	private Socket mSocketReceive;
	private String mStringRemoteHost;
	private int mIntRemotePort;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	private String tag = "Connecter";
	
	public Connecter(Socket socket) {
		try {
			this.mSocketReceive = socket;
			this.mSocketReceive.setSendBufferSize(4);
			this.mStringRemoteHost = socket.getInetAddress().getHostAddress();
			Log.e("ip-->", mStringRemoteHost);
			this.mIntRemotePort = socket.getPort();
			this.dataInputStream = new DataInputStream(
					this.mSocketReceive.getInputStream());
			this.dataOutputStream = new DataOutputStream(
					this.mSocketReceive.getOutputStream());
			
			// byte[] buffer = new byte[1024];
			// this.dataInputStream.read(buffer);
			// //电脑屏5幕大小
			// //返回来的格式为:screen:width:height
			// String utf=new String(buffer,0,buffer.length);
			// String[] datas = utf.trim().split(":");
			// int width = Integer.parseInt(datas[1]);
			// int height = Integer.parseInt(datas[2]);
			// Point pointScreen = new Point(width,height);
			// //保持系统设置参数
			// ProjectEnvironment.pointHostScreen=pointScreen;
			this.dataOutputStream.write(Build.MODEL.getBytes());// 发送手机型号给服务器
			Log.e("mobile model-->", Build.MODEL);
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(tag, "e:" + e.getMessage());
		}
	}

	/**
	 * 发送命令
	 * */
	public void writeMessage(String utf) throws IOException {
		if (ProjectEnvironment.BOOLEAN_LOCK_KEYBOAED) {
			// 锁定状态才发送命令
			// this.dataOutputStream.writeUTF(utf);
			this.dataOutputStream.write(utf.getBytes());
			this.dataOutputStream.flush();
		}
	}
	
	public void killSelf() {
		// TODO Auto-generated method stub
		try {
			if (null != mSocketReceive && mSocketReceive.isClosed() == false) {
				mSocketReceive.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 最终关闭
	 * */
	protected void finalize() throws Throwable {
		super.finalize();
		killSelf();
	}

	public Socket getmSocketReceive() {
		return mSocketReceive;
	}

	public void setmSocketReceive(Socket mSocketReceive) {
		this.mSocketReceive = mSocketReceive;
	}

	public String getmStringRemoteHost() {
		return mStringRemoteHost;
	}

	public void setmStringRemoteHost(String mStringRemoteHost) {
		this.mStringRemoteHost = mStringRemoteHost;
	}

	public int getmIntRemotePort() {
		return mIntRemotePort;
	}

	public void setmIntRemotePort(int mIntRemotePort) {
		this.mIntRemotePort = mIntRemotePort;
	}

	public DataInputStream getDataInputStream() {
		return dataInputStream;
	}

	public void setDataInputStream(DataInputStream dataInputStream) {
		this.dataInputStream = dataInputStream;
	}

	public DataOutputStream getDataOutputStream() {
		return dataOutputStream;
	}

	public void setDataOutputStream(DataOutputStream dataOutputStream) {
		this.dataOutputStream = dataOutputStream;
	}

}
