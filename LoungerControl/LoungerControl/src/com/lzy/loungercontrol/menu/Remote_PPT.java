package com.lzy.loungercontrol.menu;

import java.util.List;
import java.util.Map;

import com.lzy.loungercontrol.activity.BaseActivity;
import com.lzy.loungercontrol.activity.ConnSettingActivity;
import com.lzy.loungercontrol.adapter.SpinnerAdapter;
import com.lzy.loungercontrol.net.Connecter;
import com.lzy.loungercontrol.net.ConnecterPool;
import com.lzy.loungercontrol.net.INetCallBack;
import com.lzy.loungercontrol.start.R;
import com.lzy.loungercontrol.until.RemoteOperateImpl;
import com.lzy.loungercontrol.until.Tools;
import com.lzy.loungercontrol.untils.ProjectEnvironment;
import com.lzy.mouse.view.PuerMouseView;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Remote_PPT extends BaseActivity implements INetCallBack,SurfaceHolder.Callback {

	private Connecter connector;
	private PuerMouseView mPuerMouseView;
	private SeekBar seekBar;
	private Button mButtonPlay_Close;//播放或者关闭
	private Button mButtonClose;//关闭
	private Button mButtonPrevios;//前一页
	private Button mButtonNext;//后一页
	private boolean isPlay = false;
	private Spinner spinner;
	private Bitmap bitmapArrow;
	private RemoteOperateImpl mRemoteOperateImpl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_remote_ppt);
		
		/**
		 * 鼠标样式
		 * */
		spinner = (Spinner)findViewById(R.id.spinner1);
		List<Map<String, Object>> spinnerData = SpinnerAdapter.getSpinnerList();
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, spinnerData, R.layout.mouse_spinner
				, new String[]{"img","text"}, new int[]{R.id.image,R.id.text});
		spinner.setAdapter(simpleAdapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				String mouse_style_name = ((Map<String,Object>)spinner.getItemAtPosition(position)).get("text").toString();
				//String mouse_style_name2 = ((Map<String,Object>)spinner.getItemAtPosition(position)).get("img").toString();
				//Toast.makeText(Remote_PPT.this, mouse_style_name2, Toast.LENGTH_SHORT).show();
				if("激光笔".equals(mouse_style_name)){
					bitmapArrow = BitmapFactory.decodeResource(Remote_PPT.this.getResources(), R.drawable.red_dot);
					mRemoteOperateImpl.sendCommand(ProjectEnvironment.STRING_COMMAND_LASER_POINTER);
				}else if("涂鸦笔".equals(mouse_style_name)){
					bitmapArrow = BitmapFactory.decodeResource(Remote_PPT.this.getResources(), R.drawable.graffiti_pen_72);
					mRemoteOperateImpl.sendCommand(ProjectEnvironment.STRING_COMMAND__GRAFFITI_PEN);
				}else if("橡皮擦".equals(mouse_style_name)){
					bitmapArrow = BitmapFactory.decodeResource(Remote_PPT.this.getResources(), R.drawable.eraser);
					mRemoteOperateImpl.sendCommand(ProjectEnvironment.STRING_COMMAND_ERASER);
				}else if("标准".equals(mouse_style_name)){
					mRemoteOperateImpl.sendCommand(ProjectEnvironment.STRING_COMMAND_STANDARD);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		/**
		 * 
		 * 鼠标触摸板
		 * 
		 * */
		mPuerMouseView = (PuerMouseView)findViewById(R.id.puerMouseView);
		connector = ConnecterPool.getConnectorByKey(ConnecterPool.STRING_CKEY);
		if (null!=connector) {
			mRemoteOperateImpl = new RemoteOperateImpl(connector, this);
				
				
			ProjectEnvironment.BOOLEAN_LOCK_KEYBOAED = true;
			
		}else {
			Toast.makeText(this, "没有连接到电脑！",
					Toast.LENGTH_SHORT).show();
		}
		/**
		 * 
		 * 灵敏度
		 * 
		 * */
		seekBar = (SeekBar)findViewById(R.id.seekBar1);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (0 != progress) {
					ProjectEnvironment.INT_MOUSE_SENSE = progress;
				}
				
			}
		});
		/**
		 * 
		 * 鼠标移动显示
		 * 
		 * */
		
		mPuerMouseView.setRemoteOperateImpl(mRemoteOperateImpl);
		mPuerMouseView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("PureMouseActivity", "PureMouseActivity click");
				
			}
		});
		ProjectEnvironment.BOOLEAN_LOCK_KEYBOAED = true;
		/**
		 * 
		 * 播放/暂停
		 * 
		 * */
		mButtonPlay_Close = (Button)findViewById(R.id.play_btn);
		mButtonPlay_Close.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (null==mRemoteOperateImpl) {
					gotoSettingActivity();
				}else {
					if (isPlay) {//关闭状态
						isPlay = false;
						
						mRemoteOperateImpl.sendCommand(ProjectEnvironment.STRING_COMMAND_PPT_ESC);
						//Toast.makeText(Remote_PPT.this, "已关闭！", Toast.LENGTH_SHORT).show();
						mButtonPlay_Close.setText("从头播放");
					}else {//播放状态
						isPlay = true;
						mRemoteOperateImpl.sendCommand(ProjectEnvironment.STRING_COMMAND_PPT_F5);
						Log.e("F5", ProjectEnvironment.STRING_COMMAND_PPT_F5);
						mButtonPlay_Close.setText("结束放映");
					}
				}
				
			}
		});
		
		/**
		 * 
		 * 关闭
		 * 
		 * */
		mButtonClose = (Button)findViewById(R.id.close_btn);
		mButtonClose.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mRemoteOperateImpl.sendCommand(ProjectEnvironment.STRING_COMMAND_CLOSE);
				Toast.makeText(Remote_PPT.this, "已关闭！", Toast.LENGTH_SHORT).show();
			}
		});
		/**
		 * 
		 * 前一页
		 * 
		 * */
		mButtonPrevios = (Button)findViewById(R.id.page_pre_btn);
		mButtonPrevios.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (null==mRemoteOperateImpl) {
					gotoSettingActivity();
				}else {
					mRemoteOperateImpl.sendCommand(ProjectEnvironment.STRING_COMMAND_LEFT);
					Log.e("上一页", ProjectEnvironment.STRING_COMMAND_LEFT);
				}
				
			}

			
		});
		
		/**
		 * 
		 * 后一页
		 * 
		 * */
		mButtonNext = (Button) findViewById(R.id.page_next_btn);
		mButtonNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (null == mRemoteOperateImpl) {
					gotoSettingActivity();
				} else {
					mRemoteOperateImpl
							.sendCommand(ProjectEnvironment.STRING_COMMAND_RIGHT);
					Log.e("下一页", ProjectEnvironment.STRING_COMMAND_RIGHT);
				}
			}
		});
		
		/**
		 * 
		 * 从当前页播放
		 * 
		 * */
		mButtonClose = (Button)findViewById(R.id.play_location);
		mButtonClose.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				mRemoteOperateImpl.sendCommand(ProjectEnvironment.STRING_COMMAND_CURRENT_LOCATION);
				
				
			}
		});
	}

	private void gotoSettingActivity() {
		Tools.startActivity(this, ConnSettingActivity.class);
		
	}
	@Override
	public void OnStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnFinish() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnIntercepted(String source) {
		// TODO Auto-generated method stub
		Tools.doNetLost(this);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
	
	
}
