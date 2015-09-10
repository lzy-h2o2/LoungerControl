package com.lzy.loungercontrol.activity;

/**
 * 
 * 本类为软件主界面
 * 
 * */

import java.util.concurrent.CountDownLatch;

import com.bhn.lc.filesmain.FilesMain;
import com.lzy.circlemenu.view.CircleImageView;
import com.lzy.circlemenu.view.CircleLayout;
import com.lzy.circlemenu.view.CircleLayout.OnItemClickListener;
import com.lzy.circlemenu.view.CircleLayout.OnItemSelectedListener;
import com.lzy.loungercontrol.about.ShowAbout_PopuWind;
import com.lzy.loungercontrol.menu.*;
import com.lzy.loungercontrol.net.Connecter;
import com.lzy.loungercontrol.net.ConnecterPool;
import com.lzy.loungercontrol.sendadvice.SendAdvice;
import com.lzy.loungercontrol.start.R;
import com.lzy.loungercontrol.until.Tools;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends BaseActivity implements OnItemSelectedListener, OnItemClickListener{
	protected static final int CONN = 0;
	private Button conButton = null;//连接电脑
	@SuppressWarnings("unused")
	private Button optionBtn = null;//设置菜单
	private View view = null;//设置布局控件
	private PopupWindow mPopupWindow;//设置菜单动画对象
	private PopupWindow sendPopupWindow;
	private PopupWindow aboutPopupWindow;
	private View[] btns;//设置菜单功能数组
	@SuppressWarnings("unused")
	private EditText adviceEditText = null;//反馈内容
	@SuppressWarnings("unused")
	private Button sendButton = null;//反馈发送按钮
	private CountDownLatch countDownLatch;//一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
	//操作菜单名称
	TextView selectedTextView;
	
	
	//连接成功  更新界面
	@SuppressLint("NewApi")
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case CONN:
				conButton.setBackground(getResources().getDrawable(R.drawable.lc_connect_suceess));
				conButton.setEnabled(false);
				break;

			default:
				break;
			}
		};
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/**
		 * 
		 * //圆盘监听
		 * 
		 * */
		CircleLayout circleMenu = (CircleLayout)findViewById(R.id.main_circle_layout);
		circleMenu.setOnItemSelectedListener(this);
		circleMenu.setOnItemClickListener(this);

		selectedTextView = (TextView)findViewById(R.id.main_selected_textView);
		selectedTextView.setText(((CircleImageView)circleMenu.getSelectedItem()).getName());
		
		/**
		 * 
		 * //连接电脑按钮事件
		 * 
		 * */
		conButton = (Button)findViewById(R.id.btnConn);
		conButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Tools.startActivity(MainActivity.this, ConnSettingActivity.class);
				
				
				
				//showConDialog();
				
			}

			@SuppressWarnings("unused")
			private void startOtherActivity(
					Class class1) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, class1);
				startActivity(intent);
				
			}

			@SuppressWarnings("unused")
			private void showConDialog() {
				
				AlertDialog.Builder builDialog = new Builder(MainActivity.this);
				builDialog.setIcon(getResources().getDrawable(R.drawable.input_ip_dialog_title));
				builDialog.setTitle("请输入IP地址");
				//装载自定义对话框
				RelativeLayout dialogLayout = (RelativeLayout)getLayoutInflater()
						.inflate(R.layout.activity_conn_setting, null);
				//设置对话框显示的View对象
				builDialog.setView(dialogLayout);
				
				//设置监听
				builDialog.setPositiveButton("连接", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						final ProgressDialog mProgressBar = ProgressDialog.show(MainActivity.this,"",
								"正在连接");
						new Thread(){
							public void run() {
								try {
									sleep(3000);
									Message message = new Message();
									message.what = CONN;
									handler.sendMessage(message);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}finally{
									mProgressBar.dismiss();
								}
							};
						}.start();
					}
				});
				builDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(MainActivity.this, "quxiao", Toast.LENGTH_LONG).show();
						
					}
				});
				builDialog.create();
				builDialog.show();
			}
		});
		
		/**
		 * 
		 * 设置菜单
		 * 
		 * */
		optionBtn = (Button)findViewById(R.id.option_btn);
		optionBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 显示设置菜单
				showPopupWindow(optionBtn);
			}
			
			
		});
		initScreenSize();
		//初始化popupwindow布局
		initPopuWindow(R.layout.option_popupwindow);
		
		//提示wifi信号强度
		countDownLatch = new CountDownLatch(1);
		Tools.showWifiStrenth(this);
		//加入广告
		//AppConnect.getInstance(this).initAdInfo();
		
	}
	/**
	 * 
	 * 按两次返回键退出
	 * 
	 * */
	private long exitTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
	        if((System.currentTimeMillis()-exitTime) > 2000){  
	            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();                                
	            exitTime = System.currentTimeMillis();   
	        } else {
	            finish();
	            System.exit(0);
	        }
	        return true;   
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
	private void initScreenSize() {
		if (App.screenHeight==0||App.screenWidth==0) {
			DisplayMetrics dm = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(dm);
			App.screenHeight = dm.heightPixels;
			App.screenWidth = dm.widthPixels;
					
		}
		
	}

	/**
	 * 
	 * 初始化popupwindow布局
	 * 
	 * */
	private void initPopuWindow(int resId) {
		LayoutInflater mlLayoutInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
		view = mlLayoutInflater.inflate(resId, null);
		mPopupWindow = new PopupWindow(view, 180, LayoutParams.WRAP_CONTENT);
		mPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.option_bg));//android.R.color.darker_gray
		mPopupWindow.setOutsideTouchable(true);
		mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
		mPopupWindow.update();
		mPopupWindow.setTouchable(true);
		mPopupWindow.setFocusable(true);
		
		btns = new View[4];
		btns[0] = view.findViewById(R.id.btn_0);
		btns[1] = view.findViewById(R.id.btn_1);
		btns[2] = view.findViewById(R.id.btn_2);
		btns[3] = view.findViewById(R.id.btn_3);
		
		//设置“设置菜单”对应功能监听事件
		btns[0].setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Toast.makeText(MainActivity.this, "使用说明", Toast.LENGTH_SHORT).show();
				aboutPopupWindow = new ShowAbout_PopuWind(MainActivity.this);
				aboutPopupWindow.showAtLocation(MainActivity.this.findViewById(R.id.main), Gravity.CENTER_VERTICAL, 0, 0);
			}
		});
		
		btns[1].setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Toast.makeText(MainActivity.this, "分享", Toast.LENGTH_SHORT).show();
				shareMyApp(MainActivity.this,"分享到","分享到","<懒人遥控，遥控让生活如此简单>"+"\n我发现了一款很好用的软件，很不错！赶紧来试试吧！送上传送门："+Uri.decode("www.baidu.com"));
			}

		});
		
		btns[2].setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Toast.makeText(MainActivity.this, "反馈", Toast.LENGTH_SHORT).show();
				Tools.startActivity(MainActivity.this, SendAdvice.class);
			}
		});
		
		btns[3].setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Toast.makeText(MainActivity.this, "退出", Toast.LENGTH_SHORT).show();
				//java.lang.System.exit(0);
				myExit();
			}
		});
	}
	
	/**
	 * 
	 * 软件分享
	 * 
	 * */
	private void shareMyApp(Context context, final String chooseTitle,
			final String title, String msg) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, title);
		intent.putExtra(Intent.EXTRA_TEXT, msg);
		context.startActivity(Intent.createChooser(intent, chooseTitle));
		
		
	}

	/**
	 * 
	 * 设置菜单的退出
	 * 
	 * */
	private void myExit(){
		AlertDialog.Builder exitDialog = new Builder(MainActivity.this);
		exitDialog.setTitle("确认退出？");
		exitDialog.setIcon(R.drawable.confirm_title);
		exitDialog.setMessage("您确定要离开我吗？");
		exitDialog.setPositiveButton("残忍退出", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {

				android.os.Process.killProcess(Process.myPid());
				
			}
		});
		exitDialog.setNegativeButton("继续使用", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				
			};
		});
		
		exitDialog.show();
	}
	
	/**
	 * 
	 * 意见反馈
	 * 
	 * *
	 *
	 * */
	public void click(View view) {
		Toast.makeText(MainActivity.this, "123", Toast.LENGTH_SHORT).show();
	}
	private OnClickListener itemsOnclick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Toast.makeText(MainActivity.this, "123", Toast.LENGTH_SHORT).show();
			sendPopupWindow.dismiss();
		}
	};
	/**
	 * 
	 * // 显示设置菜单
	 * 
	 * */
	
	private void showPopupWindow(View view) {
		if(!mPopupWindow.isShowing()){
			mPopupWindow.showAsDropDown(view);
			//设置显示位置
			mPopupWindow.showAtLocation(view, Gravity.RIGHT, 0, 0);
		}
		
	}


	@Override
	public void onItemSelected(View view, int position, long id, String name) {		
		selectedTextView.setText(name);
	}

	/**
	 * 
	 * 圆盘事件
	 * 
	 * */
	
	@Override
	public void onItemClick(View view, int position, long id, String name) {
		
		/**
		 * 网络是否连接
		 * 
		 * */
		
		Connecter ckm = ConnecterPool.getConnectorByKey(ConnecterPool.STRING_CKEY);
		view.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadescale_in));
		//Toast.makeText(getApplicationContext(), getResources().getString(R.string.start_app) + " " + name, Toast.LENGTH_SHORT).show();
		if ("遥控PPT".equals(name)) {
			if (null==ckm) {
				notifyNoNet();
				return;
			}
			Toast.makeText(getApplicationContext(), getResources().getString(R.string.start_app) + " " + name, Toast.LENGTH_SHORT).show();

			Tools.startActivity(MainActivity.this,Remote_PPT.class);
		}else if ("模拟鼠标".equals(name)) {
			if (null==ckm) {
				notifyNoNet();
				return;
			}
			Toast.makeText(getApplicationContext(), getResources().getString(R.string.start_app) + " " + name, Toast.LENGTH_SHORT).show();

			Tools.startActivity(MainActivity.this,Remote_Mouse.class);
		}else if ("模拟键盘".equals(name)) {
			if (null==ckm) {
				notifyNoNet();
				return;
			}
			Toast.makeText(getApplicationContext(), getResources().getString(R.string.start_app) + " " + name, Toast.LENGTH_SHORT).show();

			Tools.startActivity(MainActivity.this,Remote_KeyBoard.class);
		}else if ("文件互传".equals(name)) {
			if (null==ckm) {
				notifyNoNet();
				return;
			}
			Toast.makeText(getApplicationContext(), getResources().getString(R.string.start_app) + " " + name, Toast.LENGTH_SHORT).show();

			Tools.startActivity(MainActivity.this, FilesMain.class);
		}else if ("开关电脑".equals(name)) {
			if (null==ckm) {
				notifyNoNet();
				return;
			}
			Toast.makeText(getApplicationContext(), getResources().getString(R.string.start_app) + " " + name, Toast.LENGTH_SHORT).show();

			Tools.startActivity(MainActivity.this,Remote_Pc_Shutdown.class);
		}else if("游戏手柄".equals(name)){
			Toast.makeText(getApplicationContext(), getResources().getString(R.string.start_app) + " " + name, Toast.LENGTH_SHORT).show();
			Tools.startActivity(MainActivity.this,Remote_Game.class);
		}
		
	}

	/**
	 * 提醒没有网络
	 * 
	 * */
	private void notifyNoNet() {
		Toast.makeText(MainActivity.this, getResources().getString(R.string.mouse_activity_net), Toast.LENGTH_SHORT).show();
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
		conButton.startAnimation(animation);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	
}

