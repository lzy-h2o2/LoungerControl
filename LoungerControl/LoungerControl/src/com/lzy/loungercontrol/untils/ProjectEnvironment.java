package com.lzy.loungercontrol.untils;

/**
 * 
 * 保存系统设置的参数
 * 
 * */
import java.util.ArrayList;
import android.app.Activity;
import android.graphics.Point;

public class ProjectEnvironment {

	public static Point pointHostScreen = null;
	public static float FLOAT_BUTTON_RADIUS = 50f;// 按钮半径
	public static float FLOAT_MOUSE_RADIUS = 10f;// 鼠标触点半径
	public static String STRING_PROJECT_RADIUS_KEY = "radius_key";
	public static String STRING_LOCATION_KEY = "location";// 坐标位置的key

	public static int INT_DEFAULT_PORT_COMMAND = 1987;// 默认端口
	public static int INT_DEFAULT_PORT_IMG = 1988;// 接受图片的端口号码
	public static int INT_MOUSE_SENSE = 1;
	public static String STRING_HOST_IP = "192.168.0.104";// 默认ip

	public static final String STRING_IP_REGX = "(0|[1-9]|[1-9][0-9]|(1[0-9][0-9]|(2[0-4][0-9]|25[0-5]))).(0|[1-9]|[1-9][0-9]|(1[0-9][0-9]|(2[0-4][0-9]|25[0-5]))).(0|[1-9]|[1-9][0-9]|(1[0-9][0-9]|(2[0-4][0-9]|25[0-5]))).(0|[1-9]|[1-9][0-9]|(1[0-9][0-9]|(2[0-4][0-9]|25[0-5])))";
	public static final String STRING_COMMAND_CURRENT_LOCATION = "current_location";

	public static int INT_LOCATIONS_SIZE = 3;// 传送坐标的message常量值
	public static int INT_LOCATIONS_X = 0;
	public static int INT_LOCATIONS_Y = 1;
	public static int INT_LOCATIONS_KEY_STRING = 2;

	public static int INT_AFTER_SCALE_X = -1;
	public static int INT_AFTER_SCALE_Y = -1;

	public static boolean BOOLEAN_LOCK_KEYBOAED = false;// 当前键盘是否处于锁定状态
	public static String STRING_CASE_KEY = "case";
	public static String ACTION_FILTER_SHUT_DOWN = "shutdown_filter_action";
	public static String STRING_KEY_SHUTDOWN = "shutdown_key";
	public static float FLOAT_SCALE = 1.0f;

	public static String STRING_IP_KEY = "ip_key";

	public static String STRING_COMMAND_ENTER = "enter";
	public static String STRING_COMMAND_CLOSE = "close_current";
	public static String STRING_COMMAND_RIGHT_CLICK = "right_click";
	public static String STRING_COMMAND_STOP_IMG_SEND = "stop_send_img";
	public static String STRING_COMMMAND_START_IMG_SEND = "start_send_img";
	public static String STRING_COMMAND_DOUBLE_CLICK = "doubleClick";
	public static String STRING_COMMAND_LASER_POINTER  = "laser_pointer";
	public static String STRING_COMMAND__GRAFFITI_PEN  = "graffiti_pen";
	public static String STRING_COMMAND_ERASER  = "eraser";
	public static String STRING_COMMAND_STANDARD  = "standard";
	public static String STRING_COMMAND_WHEEL_UP  = "wheel_up";
	public static String STRING_COMMAND_WHEEL_DOWN  = "wheel_down";
	
	

	public static String STRING_COMMAND_PPT_F5 = "F5";
	public static String STRING_COMMAND_PPT_ESC = "ESC";
	public static String STRING_COMMAND_PPT_PRE = "pre";
	public static String STRING_COMMAND_PPT_NEX = "nex";
	public static String STRING_COMMAND_PPT_UP = "up";
	public static String STRING_COMMAND_PPT_DOWN = "down";
	public static String STRING_COMMAND_LOCK_SCREEN = "lock_screen";
	public static String STRING_COMMAND_LEFT = "left";
	public static String STRING_COMMAND_RIGHT = "right";

	public static byte[] BYTE_ENDDATA = new byte[] { 19, 87, 11 };// 发送图片的结束标志数据
	public static String STRING_AD_URL = "http://www.jjandroid.com/ad/main!getAllList.action";
//	public static ArrayList<AdBean> arrayListAdBeanDefault = new ArrayList<AdBean>();

	public static String STRING_KEY_NOTIFY = "key_notify";
	public static String STRING_VALUE_NOTIFY = "1";

	public static String STRING_KEY_EUI = "eui_key";
	public static String STRING_VALUE_EUI = "yes";

	public static String YOUR_PHONE_DIMEN = "screen";

	public static ArrayList<Activity> globalActivitys = new ArrayList<Activity>();

	public static boolean isExit=false;
}
