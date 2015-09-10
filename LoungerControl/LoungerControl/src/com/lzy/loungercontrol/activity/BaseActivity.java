package com.lzy.loungercontrol.activity;

import com.lzy.common.exception.Crashandler;
import com.lzy.common.exception.Crashandler.CrashCallback;
import com.lzy.loungercontrol.untils.ProjectEnvironment;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class BaseActivity extends Activity {
	String tag = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		tag = this.getClass().getSimpleName();
		if(ProjectEnvironment.isExit){
			finish();
		}
		ProjectEnvironment.globalActivitys.add(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		ProjectEnvironment.BOOLEAN_LOCK_KEYBOAED = false;
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
//		Crashandler crashandler = Crashandler.getInstance();
//		crashandler.init(getApplicationContext(), new CrashCallback() {
//			
//			@Override
//			public void OnCrash() {
//				App.exitApp(BaseActivity.this);
//				
//			}
//		});
	}

	@Override
	public void onBackPressed() {
		finish();
		return;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
}
