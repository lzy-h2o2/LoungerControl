package com.lzy.loungercontrol.mouse;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MouseActivity {
	private Dimension dimension = null;//存储屏幕尺寸
	private Robot robot = null;
	private String command = null;//命令区分
	private int x = 0;//x坐标
	private int y = 0;//y坐标
	private String newStr = null;//格式化命令
	public void act(String msg) throws AWTException {
		newStr = msg.substring(0, msg.length()-1);//过滤掉最后一个"："
		dimension = Toolkit.getDefaultToolkit().getScreenSize();
		
		System.out.println("移动命令截取结果："+newStr);
		//System.out.println("屏幕尺寸："+dimension.getWidth()+":"+dimension.getHeight());
		
		robot = new Robot();
		String[] str = newStr.split(":");//切分格式
		int len = str.length;
		command = str[0];
		
		
		
		if("moveAdd".equals(command)){//鼠标移动
			for(int j=0;j<(len)/3;j++){
				for (int i = 1; i <= len; i=i+3) {
					x = Integer.parseInt(str[i]);
					y = Integer.parseInt(str[i+1]);
					mouseMove(x,y);
				}
			}
			
		}else if ("mouseDown".equals(command)) {//鼠标左键
			robot.mousePress(InputEvent.BUTTON1_MASK);
		}else if ("mouseUp".equals(command)) {//鼠标按键抬起
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
		}else if ("rightClick".equals(command)) {//鼠标右击
			robot.mousePress(InputEvent.BUTTON3_MASK);
			robot.mouseRelease(InputEvent.BUTTON3_MASK);
		}else if ("doubleClick".equals(command)) {//鼠标双击
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.delay(100);//暂停0.1秒
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
		}
	}
	private void mouseMove(int x, int y) {
		System.out.println("开始移动");
		Point mousePoint = MouseInfo.getPointerInfo().getLocation();//鼠标当前位置
		x += mousePoint.x;
		y += mousePoint.y;
		robot.mouseMove(x, y);
		System.out.println("移动后坐标：" + x + ":" + y);
		
	}

}
