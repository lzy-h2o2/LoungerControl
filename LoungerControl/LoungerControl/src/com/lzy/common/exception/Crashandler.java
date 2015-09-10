package com.lzy.common.exception;

import java.lang.Thread.UncaughtExceptionHandler;

import com.lzy.loungercontrol.activity.App;
import com.lzy.loungercontrol.start.R;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * 异常捕获
 * */
public class Crashandler implements UncaughtExceptionHandler{
	private static Crashandler INSTANCE;
	private Thread.UncaughtExceptionHandler mDeExceptionHandler;//系统默认处理类
	private Context mContext; 
	private String tag="Crashandler";
	private CrashCallback mCrashCallback;//回调口令
	private Crashandler() {
		
	}
	public static Crashandler getInstance(){
		if(null==INSTANCE){
			INSTANCE=new Crashandler();
		}
		return INSTANCE;
	}
	public void init(Context ctx,CrashCallback mcback){
    	mContext=ctx;
    	this.mCrashCallback=mcback;
    	mDeExceptionHandler=Thread.getDefaultUncaughtExceptionHandler();
    	Thread.setDefaultUncaughtExceptionHandler(this);
    }
	
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		// TODO Auto-generated method stub
		handleException(ex);
		
	}
	private void handleException(Throwable ex) {
		// TODO Auto-generated method stub
		mCrashCallback.OnCrash();
		 if(ex==null){
			 return ;
		 }
		 ex.printStackTrace();
		 final String msg=ex.getLocalizedMessage();
		 Log.e(tag, "ex:"+msg);
		 new Thread(){
			 @Override
			public void run() {
				 Looper.prepare();
				 Toast toast = Toast.makeText(mContext, mContext.getResources().getString(R.string.exception), Toast.LENGTH_SHORT);
				 toast.setView(View.inflate(mContext, R.layout.error_view, null));
				 toast.show();
				 Looper.loop();
			}
		 }.start();
		 
		 try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		 App.exitApp(mContext);
	}
	/**
	 * 回调接口
	 * */
	public interface CrashCallback{
		public void OnCrash();
	}
}
