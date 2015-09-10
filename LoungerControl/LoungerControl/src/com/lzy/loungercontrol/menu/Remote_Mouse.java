package com.lzy.loungercontrol.menu;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.lzy.loungercontrol.activity.BaseActivity;
import com.lzy.loungercontrol.net.Connecter;
import com.lzy.loungercontrol.net.ConnecterPool;
import com.lzy.loungercontrol.net.INetCallBack;
import com.lzy.loungercontrol.start.R;
import com.lzy.loungercontrol.until.RemoteOperateImpl;
import com.lzy.loungercontrol.until.Tools;
import com.lzy.loungercontrol.untils.ProjectEnvironment;
import com.lzy.mouse.view.PuerMouseView;

public class Remote_Mouse extends BaseActivity{
	private Connecter connecter;
	private PuerMouseView myPuerMouseView;
	private SeekBar seekBar;
	private RemoteOperateImpl mRemoteOperateImpl;
	private ImageButton mMouse_Left,mMouse_Right,mWheel_Up,mWheel_Down;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_remote_mouse);
		
		/**
		 * 鼠标面板
		 * 
		 * */
		myPuerMouseView = (PuerMouseView)findViewById(R.id.menu_MouseView);
		connecter = ConnecterPool.getConnectorByKey(ConnecterPool.STRING_CKEY);
		if(null!=connecter){
			mRemoteOperateImpl = new RemoteOperateImpl(connecter, new INetCallBack() {
				
				@Override
				public void OnStart() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void OnIntercepted(String source) {
					Tools.doNetLost(Remote_Mouse.this);
					
				}
				
				@Override
				public void OnFinish() {
					// TODO Auto-generated method stub
					
				}
			});
			ProjectEnvironment.BOOLEAN_LOCK_KEYBOAED = true;
		}
		myPuerMouseView.setRemoteOperateImpl(mRemoteOperateImpl);
		myPuerMouseView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("Remote_Mouse", "PureMouseActivity click");
				
			}
		});
		
		/**
		 * 灵敏度
		 * 
		 * */
		seekBar = (SeekBar)findViewById(R.id.seekBar2);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (0 != progress) {
					ProjectEnvironment.INT_MOUSE_SENSE = progress;
				}
				
			}
		});
		
		/**
		 * 
		 * 鼠标事件
		 * 
		 * */
		mMouse_Left = (ImageButton)findViewById(R.id.mouseViewLeft);
		mMouse_Right = (ImageButton)findViewById(R.id.mouseViewRight);
		mWheel_Up = (ImageButton)findViewById(R.id.buttonUp);
		mWheel_Down = (ImageButton)findViewById(R.id.buttonDown);
		
		
		/**
		 * 鼠标左键
		 * */
		mMouse_Left.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(null!=mRemoteOperateImpl){
					mRemoteOperateImpl.mouseDown(-1, -1);
				}
				if (null!=mRemoteOperateImpl) {
					mRemoteOperateImpl.mouseUp(-1, -1);
				}
				
			}
		});
		/**
		 * 鼠标右键
		 * */
		mMouse_Right.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(null!=mRemoteOperateImpl){
					mRemoteOperateImpl.rightClick(-1, -1);
				}
				
			}
		});
		/**
		 * 滚轮向上
		 * */
		mWheel_Up.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mRemoteOperateImpl.sendCommand(ProjectEnvironment.STRING_COMMAND_WHEEL_UP);
				
				
			}
		});
		/**
		 * 滚轮向下
		 * */
		mWheel_Down.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mRemoteOperateImpl.sendCommand(ProjectEnvironment.STRING_COMMAND_WHEEL_DOWN);
				
			}
		});
		
	}
	
}
