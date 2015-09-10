package com.lzy.loungercontrol.menu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.lzy.loungercontrol.activity.BaseActivity;
import com.lzy.loungercontrol.net.Connecter;
import com.lzy.loungercontrol.net.ConnecterPool;
import com.lzy.loungercontrol.net.INetCallBack;
import com.lzy.loungercontrol.start.R;
import com.lzy.loungercontrol.until.RemoteOperateImpl;
import com.lzy.loungercontrol.until.Tools;
import com.lzy.loungercontrol.untils.ProjectEnvironment;
import com.lzy.meto.view.MyImageView;
import com.lzy.meto.view.MyImageView.OnViewClick;

public class Remote_Pc_Shutdown extends BaseActivity implements INetCallBack{
	protected static final int CHANGE_IMAGE = 1;
	
	//////////界面展示
	
	MyImageView shutdown_quit;//关机与取消关机
	MyImageView restart;//重启
	MyImageView lockuser;//注销（锁定）
	MyImageView hibernation;//休眠
	//////////界面展示

	private Connecter connecter;
	private boolean isShutDown = true;
	private RemoteOperateImpl mRemoteOperateImpl;

	Handler handler = new Handler(){
		@SuppressLint("NewApi")
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				shutdown_quit.setBackground(getResources().getDrawable(R.drawable.left_top_canle));
				break;

			case 2:
				shutdown_quit.setBackground(getResources().getDrawable(R.drawable.left_top));

				break;
			default:
				break;
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_remote_shutdown);
		
		/**
		 * 连接
		 * */
		connecter = ConnecterPool.getConnectorByKey(ConnecterPool.STRING_CKEY);
		if (null!=connecter) {
			mRemoteOperateImpl = new RemoteOperateImpl(connecter, this);
				
				
			ProjectEnvironment.BOOLEAN_LOCK_KEYBOAED = true;
			
		}else {
			Toast.makeText(this, "没有连接到电脑！",
					Toast.LENGTH_SHORT).show();
		}

		/**
		 * 关机或取消关机
		 * */
		shutdown_quit = (MyImageView)findViewById(R.id.shutdown_quit);
		shutdown_quit.setOnClickIntent(new OnViewClick() {
			
			@Override
			public void onClick() {
				if (isShutDown) {
					isShutDown = false;
					new Thread(){
						public void run() {
							Message message = new Message();
							message.what = 1;
							Log.e("lzy", "dcm1");
							handler.sendMessage(message);
						};
					}.start();
					Toast.makeText(Remote_Pc_Shutdown.this, "您的计算机将在15秒后关机！！！", Toast.LENGTH_SHORT).show();
					mRemoteOperateImpl.sendDosCommand("shutdown -s -t 15");
					
				} else {
					isShutDown = true;
					new Thread(){
						public void run() {
							Message message = new Message();
							message.what = 2;
							Log.e("lzy", "dcm2");
							handler.sendMessage(message);
						};
					}.start();
					Toast.makeText(Remote_Pc_Shutdown.this, "您取消了关机！！！", Toast.LENGTH_SHORT).show();
					mRemoteOperateImpl.sendDosCommand("shutdown -a");
				}
				
			}
		});
		/**
		 * 重启
		 * */
		restart = (MyImageView)findViewById(R.id.restart);
		restart.setOnClickIntent(new OnViewClick() {
			
			@Override
			public void onClick() {
				Toast.makeText(Remote_Pc_Shutdown.this, "您的计算机将重启！！！", Toast.LENGTH_SHORT).show();
				mRemoteOperateImpl.sendDosCommand("shutdown -r");
				
			}
		});
		/**
		 * 注销
		 * */
		lockuser = (MyImageView)findViewById(R.id.lockuser);
		lockuser.setOnClickIntent(new OnViewClick() {
			
			@Override
			public void onClick() {
				Toast.makeText(Remote_Pc_Shutdown.this, "您的计算机将注销！！！", Toast.LENGTH_SHORT).show();
				mRemoteOperateImpl.sendDosCommand("shutdown -l");
				
			}
		});
		/**
		 * 休眠
		 * */
		hibernation = (MyImageView)findViewById(R.id.hibernation);
		hibernation.setOnClickIntent(new OnViewClick() {
			
			@Override
			public void onClick() {
				Toast.makeText(Remote_Pc_Shutdown.this, "您的计算机将进入休眠！！！", Toast.LENGTH_SHORT).show();
				mRemoteOperateImpl.sendDosCommand("shutdown -h");
				
			}
		});
	}
	@Override
	public void OnStart() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void OnFinish() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void OnIntercepted(String source) {
		Tools.doNetLost(Remote_Pc_Shutdown.this);
		
	}
}
