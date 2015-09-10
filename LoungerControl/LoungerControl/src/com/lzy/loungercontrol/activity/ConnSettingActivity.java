package com.lzy.loungercontrol.activity;



import java.net.InetAddress;
import java.net.Socket;
import java.util.TimerTask;

import com.bhn.lc.rangefiles.Info_range;
import com.lzy.loungercontrol.net.Connecter;
import com.lzy.loungercontrol.net.ConnecterPool;
import com.lzy.loungercontrol.start.R;
import com.lzy.loungercontrol.until.Tools;
import com.lzy.loungercontrol.untils.ProjectEnvironment;
import com.lzy.loungercontrol.untils.SharePersistent;
import com.lzy.loungercontrol.untils.StringUtils;



import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class ConnSettingActivity extends BaseActivity {
	private EditText mEditTextIpInput;
	private EditText mEditTextPortInput;
	private ImageButton buttonLink;
	private int mIntPort = ProjectEnvironment.INT_DEFAULT_PORT_COMMAND;
	private LinkHostTask linkHostTask;
	private ProgressDialog mProgressDialog = null;
	
	//信号动画
	private ImageView singal_animation_img;
	private AnimationDrawable singal_animationDrawable;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setUpView();
	}

	private void setUpView() {
		setContentView(R.layout.activity_conn_setting);
		setTitle("连接电脑");
		//信号动画
		singal_animation_img = (ImageView)findViewById(R.id.singal_iv);
		singal_animation_img.setImageResource(R.drawable.signal_loading);
		singal_animationDrawable = (AnimationDrawable)singal_animation_img.getDrawable();
		singal_animationDrawable.start();
		
		mEditTextIpInput = (EditText)findViewById(R.id.ip_et);
		mEditTextPortInput = (EditText)findViewById(R.id.port_et);
		//mEditTextPortInput.setText(String.valueOf(ProjectEnvironment.INT_DEFAULT_PORT_COMMAND));
		/**
		 * 
		 * 此处有客户体验
		 * 
		 * */
		
		String oldIp = SharePersistent.getPerference(this,
				ProjectEnvironment.STRING_IP_KEY);
		//输入格式转换
		/*if (!StringUtils.isEmpty(oldIp)) {
			mEditTextIpInput.setText(oldIp);
		}*/
		/////////
		//连接
		/////////
		buttonLink = (ImageButton)findViewById(R.id.connect_btn);
		//连接效果
		buttonLink.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					((ImageButton)v).setImageDrawable(getResources().getDrawable(R.drawable.conn_start_press));
				}else if (event.getAction() == MotionEvent.ACTION_UP) {
					((ImageButton)v).setImageDrawable(getResources().getDrawable(R.drawable.conn_start));
				}
				return false;
			}
		});
		buttonLink.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				linkHostTask = new LinkHostTask();
				/**
				 * 
				 * 端口判断
				 * 
				 * */
				String portString = mEditTextPortInput.getText().toString();
				if (portString.matches("/^[0-9]{1,5}$/")||portString==""||portString==null) {
					try {
						mIntPort = Integer.parseInt(portString);
					} catch (Exception e) {
						// 端口输入错误
						Toast.makeText(
								ConnSettingActivity.this,
								getResources().getString(
										R.string.setting_activity_setting_port),
								Toast.LENGTH_SHORT).show();
						return;
					}
				}
				
				/**
				 * 
				 * ip判断
				 * 
				 * */
				String hostIp = mEditTextIpInput.getText().toString();
				
				Log.e("hostip-->", hostIp);
				if (StringUtils.isEmpty(hostIp)||
						!hostIp.matches(ProjectEnvironment.STRING_IP_REGX)) {
					//ip输入错误
					Toast.makeText(ConnSettingActivity.this,
							getResources().getString(R.string.setting_activity_setting_inputrtip),
							Toast.LENGTH_SHORT).show();
					
				}else {
					Log.e("execute", "execute之前");
					linkHostTask.execute(hostIp);
					ProjectEnvironment.STRING_HOST_IP = hostIp;//修改默认地址
					SharePersistent.savePerference(ConnSettingActivity.this,
							ProjectEnvironment.STRING_IP_KEY, hostIp);
				}
			}
		});
		
		buttonLink.requestFocus();
		mProgressDialog = new ProgressDialog(ConnSettingActivity.this);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//格式
		mProgressDialog.setCancelable(true);//设置点击Back返回键退出
		mProgressDialog.setCanceledOnTouchOutside(false);//设置在点击Dialog外是否取消Dialog进度条
		mProgressDialog.setMessage(ConnSettingActivity.this.getResources()
				.getString(R.string.setting_activity_setting_link_yourhost));
		mProgressDialog.setOnDismissListener(new OnDismissListener() {
			
			
			@Override
			public void onDismiss(DialogInterface dialog) {
				buttonLink.setEnabled(true);
				if (null!=linkHostTask) {
					linkHostTask.cancel(true);
				}
				
			}
		});
	}
	
	/**
	 * 三个泛型： 　　Param ，任务执行器需要的数据类型 　　Progress 后台计算中使用的进度单位数据类型 　　Result
	 * 后台计算返回结果的数据类型 　　有些参数是可以设置为不使用的，只要传递为Void型即可，比如AsyncTask 　　四个步骤：
	 * 　　onPreExecute()，执行预处理，它运行于UI线程，可以为后台任务做一些准备工作，比如绘制一个进度条控件。
	 * 　　doInBackground(Params...)，后台进程执行的具体计算在这里实现，doInBackground(Params...)
	 * 是AsyncTask的关键，此方法必须重载。在这个方法内可以使用 publishProgress(Progress...)改变当前的进度值。
	 * 　　onProgressUpdate(Progress...)，运行于UI线程。如果在doInBackground(Params...)
	 * 中使用了publishProgress(Progress...)，就会触发这个方法。在这里可以对进度条控件根据进度值做出具体的响应。
	 * 　　onPostExecute
	 * (Result)，运行于UI线程，可以对后台任务的结果做出处理，结果就是doInBackground(Params...)
	 * 的返回值。此方法也要经常重载，如果Result为null表明后台任务没有完成(被取消或者出现异常)。
	 * 
	 * 
	 * 
	 */
	class LinkHostTask extends AsyncTask<String, Integer, String> {

		@Override
		protected void onPreExecute() {
			// 预处理
			buttonLink.setEnabled(false);
			//Toast.makeText(ConnSettingActivity.this, "连接前", Toast.LENGTH_SHORT).show();
			mProgressDialog.show();
			super.onPreExecute();
		}
		@Override
		protected void onPostExecute(String result) {
			// 处理结果
			buttonLink.setEnabled(true);
			mProgressDialog.dismiss();
			if(Integer.parseInt(result)>0){//ConnecterPool.getConnectorPoolSize()
				//连接成功
				Toast.makeText(ConnSettingActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
				finish();
			}else {
				//连接失败
				Tools.ToastShow(ConnSettingActivity.this, R.string.setting_activity_setting_link_fail);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
//				Tools.startActivity(SettingActivity.this, MainActivity.class);
			}
		}
		@Override
		protected String doInBackground(String... params) {
			//后台计算
			String host = params[0];
			Log.e("host-->", host);
			try {
				ConnecterPool.clearPool();
				mIntPort = Integer.parseInt(mEditTextPortInput.getText().toString().trim());
				Log.e("port1-->", ((Integer)mIntPort).toString());
				Socket socket = new Socket(InetAddress.getByName(host), mIntPort);
				Log.e("ip-->", (InetAddress.getByName(host)).toString());
				Connecter connector = new Connecter(socket);
				
				Info_range.socket=socket;
				
				ConnecterPool.putConnecter(connector.getmStringRemoteHost(), connector);
				Log.e("pool size-->", ((Integer)ConnecterPool.getConnectorPoolSize()).toString());
				Log.e("port2-->", ((Integer)connector.getmIntRemotePort()).toString());
				Log.e("连接对象", connector.toString());
			} catch (Exception e) {
				Log.e(tag, "message:" + e.getMessage());
				cancel(true);
				e.printStackTrace();
			}
			return ((Integer)ConnecterPool.getConnectorPoolSize()).toString();
		}
		
	}
}


