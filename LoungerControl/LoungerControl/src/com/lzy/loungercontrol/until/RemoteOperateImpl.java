package com.lzy.loungercontrol.until;

import java.io.IOException;

import android.util.Log;

import com.lzy.loungercontrol.net.Connecter;
import com.lzy.loungercontrol.net.INetCallBack;
import com.lzy.loungercontrol.untils.ProjectEnvironment;

public class RemoteOperateImpl implements IRemoteOperate {

	private Connecter connector = null;
	private INetCallBack iNetCallBack = null;
	private String tag = "RemoteOperateImpl";

	public RemoteOperateImpl(Connecter connecter, INetCallBack iNetCallBack) {
		this.connector = connecter;
		this.iNetCallBack = iNetCallBack;
	}

	@Override
	public String moveMouse(float x, float y) {
		try {
			String message = buildMessage(EventStringSet.MOVE_MOUSE, x, y);
			Log.e("moveMouse-->", message);
			this.connector.writeMessage(message);
		} catch (IOException e) {
			e.printStackTrace();
			iNetCallBack.OnIntercepted(tag);
		}

		return null;
	}

	/**
	 * 遥控鼠标移动
	 * 
	 * */
	public String mouseAdd(float x, float y) {
		String message = null;
		try {
			message = buildMessageMouseAdd(EventStringSet.MOVE_ADD, x, y);
			Log.e("mouseAdd-->", message);
			this.connector.writeMessage(message);
		} catch (IOException e) {
			e.printStackTrace();
			iNetCallBack.OnIntercepted(tag);
		}
		return message;
	}

	@Override
	public String click(float x, float y) {
		try {
			String message = buildMessage(EventStringSet.CLICK, x, y);
			Log.e("单击-->", message);
			this.connector.writeMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
			iNetCallBack.OnIntercepted(tag);
		}
		return null;
	}

	@Override
	public String doubleClick(float x, float y) {
		try {
			String message = buildMessage(EventStringSet.DOUBLE_CLICK, x, y);
			Log.e("双击-->", message);
			this.connector.writeMessage(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			iNetCallBack.OnIntercepted(tag);
		}
		return null;
	}

	@Override
	public String rightClick(float x, float y) {
		try {
			// TODO Auto-generated method stub
			String message = buildMessage(EventStringSet.RIGHT_CLICK, x, y);
			Log.e("右击-->", message);
			this.connector.writeMessage(message);
		} catch (IOException e) {
			iNetCallBack.OnIntercepted(tag);
			e.printStackTrace();
		}
		return null;
	}

	public String rightClick2(float x, float y) {
		// TODO Auto-generated method stub
		try {
			// TODO Auto-generated method stub
			String message = buildMessage(
					ProjectEnvironment.STRING_COMMAND_RIGHT_CLICK, x, y);
			Log.e("rightClick2-->", message);
			this.connector.writeMessage(message);
		} catch (IOException e) {
			iNetCallBack.OnIntercepted(tag);
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String keyDown(int keyCode) {
		try {
			String message = buildMessage(EventStringSet.KEY_DOWN, keyCode);
			Log.e("keyDown-->", message);
			this.connector.writeMessage(message);
		} catch (IOException e) {
			iNetCallBack.OnIntercepted(tag);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String keyUp(int keyCode) {
		try {
			String message = buildMessage(EventStringSet.KEY_UP, keyCode);
			Log.e("keyUp-->", message);
			this.connector.writeMessage(message);
		} catch (IOException e) {
			iNetCallBack.OnIntercepted(tag);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String mouseDown(float x, float y) {
		try {
			if (x != -1 && y != -1) {
				this.moveMouse(x, y);
			}
			String message = buildMessage(EventStringSet.MOUSE_DOWN, x, y);
			Log.e("mouseDown-->", message);
			this.connector.writeMessage(message);
		} catch (IOException e) {
			iNetCallBack.OnIntercepted(tag);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String mouseUp(float x, float y) {
		try {
			if (x != -1 && y != -1) {
				this.moveMouse(x, y);
			}
			String message = buildMessage(EventStringSet.MOUSE_UP, x, y);
			Log.e("mouseUp-->", message);
			this.connector.writeMessage(message);
		} catch (IOException e) {
			iNetCallBack.OnIntercepted(tag);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String mouseWheel(int x) {
		try {
			String message = buildWheelMessage(EventStringSet.MOUSE_WHEEL, x);
			this.connector.writeMessage(message);
		} catch (IOException e) {
			iNetCallBack.OnIntercepted(tag);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String sendCommand(String command) {
		try {
			this.connector.writeMessage("#" + command);// ppt按钮命令
		} catch (IOException e) {
			iNetCallBack.OnIntercepted(tag);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String sendDosCommand(String command) {
		try {
			this.connector.writeMessage("|" + command);// 关机重启命令
		} catch (IOException e) {
			iNetCallBack.OnIntercepted(tag);
			e.printStackTrace();
		}
		return null;
	}

	// -----------------------------------------
	public String buildMessage(String eventString, float x, float y) {
		return eventString + ":" + (int) (x * ProjectEnvironment.FLOAT_SCALE)
				+ ":" + (int) (y * ProjectEnvironment.FLOAT_SCALE)
				+ EventStringSet.Split;
	}

	// -----------------------------------------
	public String buildMessageMouseAdd(String eventString, float x, float y) {
		return eventString + ":"
				+ (int) (ProjectEnvironment.INT_MOUSE_SENSE * x * 0.5) + ":"
				+ (int) (ProjectEnvironment.INT_MOUSE_SENSE * y * 0.5)
				+ EventStringSet.Split;
	}

	public String buildMessage(String eventString, int code) {
		return eventString + ":" + (char) code + EventStringSet.Split;
	}

	public String buildWheelMessage(String eventString, int code) {
		return eventString + ":" + code + EventStringSet.Split;
	}

	public class EventStringSet {

		public static final String MOVE_MOUSE = "moveMouse";
		public static final String MOVE_ADD = "moveAdd";
		public static final String CLICK = "click";
		public static final String DOUBLE_CLICK = "doubleClick";
		public static final String RIGHT_CLICK = "rightClick";
		public static final String KEY_DOWN = "keydown";
		public static final String KEY_UP = "keyup";
		public static final String MOUSE_DOWN = "mouseDown";
		public static final String MOUSE_UP = "mouseUp";
		public static final String MOUSE_WHEEL = "mouse_wheel";
		public static final String Split = ":";

	}
}
