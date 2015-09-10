package com.lzy.loungercontrol.untils;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 
 * 保存Iip地址和端口号
 * 
 * */

public class SharePersistent {

	private static final String PREFS_NAME = "wirelessKeyBoard";
	public static String getPerference(Context context,
			String key) {
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
		
		return settings.getString(key, "");
	}
	public static void savePerference(Context context, String key, String value) {
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(key, value);
		editor.commit();
	}
	public static void saveBoolean(Context context, String key, boolean value) {
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	

	
	
	public static boolean getBoolean(Context context, String key){
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
		return settings.getBoolean(key, false);
	}

}
