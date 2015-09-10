package com.bhn.loungercontrol.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * 
 * 电脑接收手机文件
 * 
 * */
public class FileActivity {

	public void file(Socket socket,String msg) {
		try {
			InputStream ins = socket.getInputStream();
			File file = getFileName(socket, msg);// 获得文件名后，创建同名文件
			
			if (file == null){				
				//socket.close();
				//writeOutInfo("FileNull", socket);
				return;
			}else {
				//writeOutInfo(msg, socket);
				System.out.println("msg:" + ",socket:" + socket.toString());
				FileOutputStream fos = new FileOutputStream(file);// 写入硬盘
				BufferedInputStream bufferedInputStream = new BufferedInputStream(
						ins);
				byte[] buf = new byte[8]; // 接收数据缓存
				int len = 0;
				while ((len = bufferedInputStream.read(buf)) != -1) {
					fos.write(buf, 0, len); // 写入硬盘文件
				}
				// writeOutInfo("上传成功", socket);
				fos.flush();
				//writeOutInfo("Receive", socket);
				JOptionPane.showMessageDialog(null, "接收成功了！文件在D盘下。");
				System.out.println("接收成功了"
						+ System.getProperty("line.separator"));
			}
			//fos.close();
			//socket.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void writeOutInfo(String string, Socket socket) {
		// TODO Auto-generated method stub
		try {
			OutputStream out = socket.getOutputStream();
			out.write(string.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private File getFileName(Socket socket, String fname) throws IOException {
		// 获取客户端发送的文件名，判断在d盘同名文件的情况
		System.out.println("filename is "+fname);
		File dir = new File("d:\\");
		File[] files = dir.listFiles();
		for (File f : files) {// 寻找同名
			if (!f.isDirectory()) {// 如果不是个目录
				if (f.getName().equals(fname)) {// 判断是否同名
					//writeOutInfo("存在同名", socket);
					System.out.println(f.getName() + "文件已存在，断开连接"
							+ System.getProperty("line.separator"));
					JOptionPane.showMessageDialog(null, "D盘存在同名文件，接收失败！");
					return null;
				}
			}
		}
		System.out.println("（" + fname + ")存放在" + dir.getAbsolutePath());
		File file = new File(dir + fname);
		if (file.createNewFile()) {
			System.out.println("创建成功:" + fname + ",准备写入......");
			//writeOutInfo("FileSendNow", socket);
			return file;
		} else {
			JOptionPane.showMessageDialog(null, "接收失败，请重新发送！");
			return null;
		}

	}

}
