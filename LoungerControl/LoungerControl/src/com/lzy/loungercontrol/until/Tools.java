package com.lzy.loungercontrol.until;

import java.util.Random;

import com.lzy.loungercontrol.start.R;
import com.lzy.loungercontrol.net.ConnecterPool;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

/**
 * 
 * 此类是个工具类
 * 
 * */

public class Tools {
	
	/**
	 * 1.启动一个新的Activity
	 * */
	public static void startActivity(Activity fromActivity,Class toClass){ 
		Intent intent = new Intent();
		intent.setClass(fromActivity, toClass);
		fromActivity.startActivity(intent);
		fromActivity.overridePendingTransition(R.anim.zooin, R.anim.zooout);	
	}
	/**
	 * 2.启动一个新的Activity(传值启动)
	 * */
	public static void startActivity(Activity fromActivity,Class toClass,String key,String value){ 
		Intent intent = new Intent();
		intent.putExtra(key, value);
		intent.setClass(fromActivity, toClass);
		fromActivity.startActivity(intent);
		fromActivity.overridePendingTransition(R.anim.zooin, R.anim.zooout);	
	}
	/**
	 * 3.获取屏幕高度和宽度
	 * */
	public static Point getDisplayMetrics(Context context) {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		Point metricsPoint = new Point();
		metricsPoint.x = metrics.widthPixels;
		metricsPoint.y = metrics.heightPixels;
		return metricsPoint;
	}
	/**
	 * 4.随机鼠标点
	 * */
	public static Point getRandomPoint(Point point) {
		Random rand = new Random();
		int rx = rand.nextInt(point.x);
		int ry = rand.nextInt(point.y);
		return new Point(rx, ry);
	}
	/**
	 * 5.根据设定后的宽度和高度设置按钮的出事随机值
	 * */
	public static Point getDimensionsByDimens(int width, int height) {
		Random rand = new Random();
		int rx = rand.nextInt(width);
		int ry = rand.nextInt(height);
		return new Point(rx, ry);
	}
	/**
	 * 6.提示框工具
	 * */
	public static Builder createADialig(Context context, View view) {
		Builder alertDialog = new AlertDialog.Builder(context);
		alertDialog.setView(view);
		return alertDialog;
	}
	/**
	 * 7.发送广播
	 * */
	public static void sendBoardCast(Context context, Intent intent) {
		context.sendBroadcast(intent);
	}
	/**
	 * 8.断网处理
	 * */
	public static void doNetLost(Activity context) {
		Toast.makeText(context,
				context.getResources().getString(R.string.app_lost_host),
				Toast.LENGTH_SHORT).show();
		context.finish();
		ConnecterPool.mapConnectorPool.remove(ConnecterPool.STRING_CKEY);
	}
	/**
	 * 9.请求url
	 * */
	public static void startUrl(Context context, String url) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		context.startActivity(i);
	}
	/**
	 * 10.获取状态栏和标题栏的高度和宽度
	 * 
	 * */
	public static int getAppRect(Activity context) {
		/**
		 * decorView是window中的最顶层view，可以从window中获取到decorView，
		 * 然后decorView有个getWindowVisibleDisplayFrame方法可以获取到程序显示的区域，包括标题栏，但不包括状态栏
		 */
		Rect frame = new Rect();
		context.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;//状态栏
		/**
		 * getWindow().findViewById(Window.ID_ANDROID_CONTENT)
		 * 这个方法获取到的view就是程序不包括标题栏的部分，然后就可以知道标题栏的高度了。
		 */
		int contentTop = context.getWindow()
				.findViewById(Window.ID_ANDROID_CONTENT).getTop();
		// statusBarHeight是上面所求的状态栏的高度
		int titleBarHeight = contentTop - statusBarHeight;// 标题栏
		return titleBarHeight;
	}
	/**
	 * 11.获取状态栏高度
	 * 
	 * */
	public static int geteAppUnVisibleHeight(Activity activity) {
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;// 状态栏

		int contentTop = activity.getWindow()
				.findViewById(Window.ID_ANDROID_CONTENT).getTop();
		// statusBarHeight是上面所求的状态栏的高度
		int titleBarHeight = contentTop - statusBarHeight;// 标题栏
		return statusBarHeight + titleBarHeight;
	}
	/**
	 * 12.Toast提示用于反馈和测试
	 * */
	public static void ToastShow(Context context, int id) {
		Toast.makeText(context, context.getResources().getString(id),
				Toast.LENGTH_SHORT).show();
	}
	public static void ToastShow(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
	/**
	 * 13.获取wifi信号强度
	 * */
	public static void showWifiStrenth(Context context){
		// 得到的值是一个0到-100的区间值，是一个int型数据，
		// 其中0到-50表示信号最好，-50到-70表示信号偏差，小于-70表示最差，
		// 有可能连接不上或者掉线
		WifiManager wifiManager = (WifiManager)context
				.getSystemService(Context.WIFI_SERVICE);
		
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		int ressi = wifiInfo.getRssi();//RSSI就是接受信号强度指示
		if (ressi<0 && ressi>=-50) {
			//Tools.ToastShow(context, "亲，你的wifi信号不错！");
		}else {
			Tools.ToastShow(context, "亲，你的wifi信号较差,使用起来不够流畅！");
		}
	}
	/**
	 * 14.线程休眠
	 * */
	public static void delay(long times) {
		try {
			Thread.sleep(times);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
