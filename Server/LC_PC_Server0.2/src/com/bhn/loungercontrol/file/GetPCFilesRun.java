package com.bhn.loungercontrol.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;
/**电脑向手机发送文件**/
public class GetPCFilesRun implements Runnable {

	File file=FileInfo.file;
	Socket socket;
	OutputStream outputStream;
	public GetPCFilesRun(Socket socket){
		this.socket=socket;
	}
	/**向客户端发送消息
	 * @throws IOException **/
	public void sendMsg(String msg) throws IOException {
		outputStream=socket.getOutputStream();
		outputStream.write(msg.getBytes());
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			
			if (file==null) {
			    sendMsg("NotSelectFile");
			    JOptionPane.showMessageDialog(null, "没有选择文件！");
			    return;
			}else {
				System.out.println("准备发送");
				sendMsg(file.getName());
			}
			
			outputStream=socket.getOutputStream();
			
			FileInputStream fileInputStream=new FileInputStream(file);
			BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(outputStream);
			byte[] buf=new byte[1024*4];
			int len=0;
			while ((len=fileInputStream.read(buf))!=-1) {
				bufferedOutputStream.write(buf,0,len);
			}
			bufferedOutputStream.flush();
			JOptionPane.showMessageDialog(null, "发送完成了！文件放在了sdcard文件下或者sdcard中。");
			System.out.println("完毕");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("失败");
		}
		
	}

}
