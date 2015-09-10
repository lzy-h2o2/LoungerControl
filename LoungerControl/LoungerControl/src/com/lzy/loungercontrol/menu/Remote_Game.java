package com.lzy.loungercontrol.menu;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;

import com.lzy.loungercontrol.activity.BaseActivity;
import com.lzy.loungercontrol.net.Connecter;
import com.lzy.loungercontrol.net.ConnecterPool;
import com.lzy.loungercontrol.net.INetCallBack;
import com.lzy.loungercontrol.start.R;
import com.lzy.loungercontrol.until.RemoteOperateImpl;
import com.lzy.loungercontrol.until.Tools;

public class Remote_Game extends BaseActivity {
	private Button backButton;
	private Connecter connecter;
	private RemoteOperateImpl mRemoteOperateImpl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 强制横屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
				, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_remote_game);
		
		connecter = ConnecterPool.getConnectorByKey(ConnecterPool.STRING_CKEY);
		if (null!=connecter) {
			mRemoteOperateImpl = new RemoteOperateImpl(connecter, new INetCallBack() {
				
				@Override
				public void OnStart() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void OnIntercepted(String source) {
					Tools.doNetLost(Remote_Game.this);
					
				}
				
				@Override
				public void OnFinish() {
					// TODO Auto-generated method stub
					
				}
			});
	

		}
		
		
		/**
		 * 游戏按钮  只实现返回   原理同全键盘
		 * 
		 * */
		backButton = (Button)findViewById(R.id.back_btn);
		backButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Remote_Game.this.finish();
				
			}
		});
	}
}
