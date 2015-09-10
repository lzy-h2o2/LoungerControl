package com.lzy.loungercontrol.activity;
/**
 * 
 * 本类描述了软件欢迎界面
 * 当软件是第一次启动时就加载引导页
 * 
 * */

import com.lzy.loungercontrol.start.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.Window;
import android.widget.ImageView;

public class WelcomeActivity extends Activity {
	//loading动画
	private ImageView animationIV;
	private AnimationDrawable animationDrawable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//无标题欢迎页启动
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_welcome);
		
		animationIV = (ImageView)findViewById(R.id.animtimageView);
		animationIV.setImageResource(R.drawable.loading);
		animationDrawable = (AnimationDrawable)animationIV.getDrawable();
		animationDrawable.start();
		//判断是否第一次加载
		boolean isFirst = isFirstEnter(WelcomeActivity.this,WelcomeActivity.this.getClass().getName());
		if (isFirst) 
			myHandler.sendEmptyMessageDelayed(SWITCH_GUIDACTIVITY, 2000);
		else
			myHandler.sendEmptyMessageDelayed(SWITCH_MAINACTIVITY, 2000);
			
	}

	//****************************************************************
    // 判断应用是否初次加载，读取SharedPreferences中的guide_activity字段
    //****************************************************************
    private static final String SHAREDPREFERENCES_NAME = "my_pref";
    private static final String KEY_GUIDE_ACTIVITY = "guide_activity";	
	private boolean isFirstEnter(Context context, String className) {
		// TODO Auto-generated method stub
		if(context==null || className==null || "".equalsIgnoreCase(className))
			return false;
		@SuppressWarnings("deprecation")
		String myResultStr = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_WORLD_READABLE)
				.getString(KEY_GUIDE_ACTIVITY, ""); //取得所有类名
		
		if(myResultStr.equalsIgnoreCase("false"))
			return false;
		else 
			return true;
		
	}

	//*************************************************
    // Handler:跳转至不同页面
    //*************************************************
	private final static int SWITCH_MAINACTIVITY = 1000;
	private final static int SWITCH_GUIDACTIVITY = 1001;
	public Handler myHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case SWITCH_MAINACTIVITY:
				Intent myIntent = new Intent();
				myIntent.setClass(WelcomeActivity.this, MainActivity.class);
				WelcomeActivity.this.startActivity(myIntent);
				WelcomeActivity.this.finish();
				break;

			case SWITCH_GUIDACTIVITY:
				myIntent = new Intent();
				myIntent.setClass(WelcomeActivity.this, GuideActivity.class);
				WelcomeActivity.this.startActivity(myIntent);
				WelcomeActivity.this.finish();
				break;
			}
			super.handleMessage(msg);
		};
	};
	

}

