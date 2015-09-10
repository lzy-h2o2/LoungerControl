package com.lzy.loungercontrol.activity;

import java.util.ArrayList;

import com.lzy.loungercontrol.net.ConnecterPool;
import com.lzy.loungercontrol.untils.ProjectEnvironment;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class App extends Application {
	public static int screenHeight = 0;
	public static int screenWidth = 0;
	private static App instace;
	public ArrayList<Activity> activitsList;
	private String tag = "lzy";
	
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i(tag, "app create:" + hashCode());
		instace = this;
		activitsList = new ArrayList<Activity>();
	}
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}
	
	public void doExit() {
		try {
			ConnecterPool.clearPool();
		} catch (Exception e) {
			e.printStackTrace();
		}

		ProjectEnvironment.isExit = true;
		for (Activity act : ProjectEnvironment.globalActivitys) {
			if (null != act) {
				try {
					act.finish();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		android.os.Process.killProcess(android.os.Process.myPid());


	}
	public static App getInstance() {
		return instace;
	}
	public static void exitApp(Context mContext) {
		Intent intent = new Intent();
		intent.setClass(mContext, MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("flag", 0);
		mContext.startActivity(intent);
		
	}
}
