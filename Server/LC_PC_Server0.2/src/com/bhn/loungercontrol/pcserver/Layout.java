package com.bhn.loungercontrol.pcserver;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.bhn.loungercontrol.file.FileInfo;

@SuppressWarnings("serial")
public class Layout extends JFrame {
	public Layout() {
		// TODO Auto-generated constructor stub
		final JTextField portfield;
		final JButton stopbutton;
		final JButton startbutton;
		
		final JButton selectfile;
		final JTextField filepath;
		final JFileChooser fileChooser=new JFileChooser();
		final JLabel selectJLabel1;
		final JLabel selectJLabel2;
		
		JLabel iplabel, localiplable, portlable, scopelable;
		JLabel phonemodel;
		final JLabel model;
		
		String title = "懒人遥控";
		String content_iplabel = "本机IP：";
		String content_protlable = "端口号：";
		String content_scopelable = "（端口号的范围：1025~63535）";
		String content_phonemodle = "手机型号：";
		final String content_modle = "未开启";
		String content_startbutton = "连接";
		String content_stopbutton = "断开";
		final String msgdialog1 = "端口号不能为空";
		final String msgdialog2 = "端口号范围不正确";
		final String msgdialog3 = "端口号被占用";
		this.setResizable(false);
		final Connect connect = new Connect();
		/** 字体 **/
		Font font = new Font("SimSun", Font.PLAIN, 16);

		setTitle(title);
		setSize(230, 290);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Toolkit toolkit = getToolkit();
		Dimension dimension = toolkit.getScreenSize();
		int height = dimension.height;// 获取窗口的高
		int width = dimension.width;

		int window_height = this.getHeight();// 获取窗口高度
		int window_width = this.getWidth();
		setLocation((width - window_width) / 2, (height - window_height) / 2);// 设置窗口位置为中间

		getContentPane().setLayout(null);// 在java里面，设置布局要取得Frame的ContentPane
											// 方法就是getContentPane()；然后通过ContentPane
											// 的方法setLayout()设置布局管理器，括号里面就是
		// 本机IP： // 布局管理器的名称，什么都不选就写null
		iplabel = new JLabel();
		iplabel.setText(content_iplabel);
		// iplabel.setFont(font);
		iplabel.setBounds(40, 10, 60, 25);
		getContentPane().add(iplabel);

		// 显示本机IP
		localiplable = new JLabel();
		try {
			localiplable.setText(InetAddress.getLocalHost().getHostAddress());
			// System.out.println("yyyyy"+InetAddress.getAllByName(Ine));
			localiplable.setBounds(100, 10, 120, 25);
			getContentPane().add(localiplable);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 端口号：
		portlable = new JLabel();
		portlable.setText(content_protlable);
		portlable.setBounds(40, 40, 60, 25);
		getContentPane().add(portlable);

		// 端口号范围
		scopelable = new JLabel();
		scopelable.setText(content_scopelable);
		scopelable.setBounds(30, 70, 200, 25);
		getContentPane().add(scopelable);

		// 输入端口号
		portfield = new JTextField();
		portfield.setText("8888");
		portfield.setBounds(100, 40, 70, 25);
		getContentPane().add(portfield);

		// 开始按钮
		startbutton = new JButton();
		startbutton.setText(content_startbutton);
		startbutton.setBounds(25, 100, 80, 25);
		getContentPane().add(startbutton);
		startbutton.setEnabled(false);

		// 断开按钮
		stopbutton = new JButton();
		stopbutton.setText(content_stopbutton);
		stopbutton.setBounds(115, 100, 80, 25);
		getContentPane().add(stopbutton);
		

		selectJLabel1=new JLabel();
		selectJLabel1.setText("请选择准备发送的文件，");
		selectJLabel1.setBounds(10, 130, 200, 25);
		getContentPane().add(selectJLabel1);
		
		selectJLabel2=new JLabel();
		selectJLabel2.setText("然后再点击手机上的接收按钮：");
		selectJLabel2.setBounds(10, 160, 200, 25);
		getContentPane().add(selectJLabel2);
		
		filepath=new JTextField();
		filepath.setBounds(10, 190, 200, 25);
		filepath.setText("未选择");
		/*filepath.setEditable(false);
		filepath.setFocusable(true);*/
		getContentPane().add(filepath);
		
		selectfile=new JButton();
		selectfile.setText("选择文件");
		selectfile.setBounds(95, 220, 100, 25);
		getContentPane().add(selectfile);

		/*// 手机型号
		phonemodel = new JLabel();
		phonemodel.setText(content_phonemodle);
		phonemodel.setBounds(30, 130, 70, 25);
		getContentPane().add(phonemodel);*/

		/*// 型号内容
		model = new JLabel();
		model.setText(content_modle);
		model.setBounds(110, 130, 100, 25);
		getContentPane().add(model);*/

		setVisible(true);
		
		/**
		 * 
		 * ######################################   服务端体验更新
		 * 
		 * */
		final String x = portfield.getText().trim();///////←
		final int port = Integer.parseInt(x);
		portfield.setEditable(false);//端口号输入框只可读
		System.out.println("xxxx是" + x);
		// 创建连接
		connect.start(port);

		startbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				stopbutton.setEnabled(true);
				startbutton.setEnabled(false);
				portfield.setEditable(false);//端口号输入框只可读
				
				System.out.println("xxxx是" + x);

				if (x.equals("")) {
					JOptionPane.showMessageDialog(null, msgdialog1);
					return;
				}
				
				if (port < 1025 || port > 63535) {
					JOptionPane.showMessageDialog(null, msgdialog2);
					return;
				}
				try {
					// 创建连接
					connect.start(port);

				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, msgdialog3);
				}

			}

		});
		String y = Connect.model;
		System.out.println("yyyyy:" + y);
		stopbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				stopbutton.setEnabled(false);
				startbutton.setEnabled(true);
				portfield.setEditable(true);
				// 断开连接
				connect.stop();
			}
		});
        selectfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int result=fileChooser.showOpenDialog(null);
				File file=fileChooser.getSelectedFile();
				
				if (result==JFileChooser.APPROVE_OPTION) {
					FileInfo.file=file;
					filepath.setText(file.getPath());
					System.out.println("path:"+file.getPath());
					System.out.println("文件路径是："+FileInfo.file.getPath());
				}else if (result==JFileChooser.CANCEL_OPTION) {
					filepath.setText("未选择");
				}
			}
		});

	}

}
