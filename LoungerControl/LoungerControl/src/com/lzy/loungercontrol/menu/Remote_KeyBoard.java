package com.lzy.loungercontrol.menu;

import java.util.Map;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;

import com.lzy.loungercontrol.activity.BaseActivity;
import com.lzy.loungercontrol.adapter.KeyDataAdapter;
import com.lzy.loungercontrol.net.Connecter;
import com.lzy.loungercontrol.net.ConnecterPool;
import com.lzy.loungercontrol.net.INetCallBack;
import com.lzy.loungercontrol.start.R;
import com.lzy.loungercontrol.until.RemoteOperateImpl;
import com.lzy.loungercontrol.until.Tools;
import com.lzy.loungercontrol.untils.ProjectEnvironment;

public class Remote_KeyBoard extends BaseActivity implements OnClickListener{
	private Button[] button = new Button[90];
	private Connecter connecter;
	private RemoteOperateImpl mRemoteOperateImpl;
	private boolean altFlag = false;
	private boolean shifFlag = false;
	private boolean ctrlFlag = false;
	private boolean capsLk = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 强制横屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
				, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.keyboard);
		
		connecter = ConnecterPool.getConnectorByKey(ConnecterPool.STRING_CKEY);
		if (null!=connecter) {
			mRemoteOperateImpl = new RemoteOperateImpl(connecter, new INetCallBack() {
				
				@Override
				public void OnStart() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void OnIntercepted(String source) {
					Tools.doNetLost(Remote_KeyBoard.this);
					
				}
				
				@Override
				public void OnFinish() {
					// TODO Auto-generated method stub
					
				}
			});
			ProjectEnvironment.BOOLEAN_LOCK_KEYBOAED = true;

			final int[] keyID = {R.id.Esc_btn,R.id.f1_btn,R.id.f2_btn,R.id.f3_btn,R.id.f4_btn,R.id.f5_btn,R.id.f6_btn,R.id.f7_btn
					  ,R.id.f8_btn,R.id.f9_btn,R.id.f10_btn,R.id.f11_btn,R.id.f12_btn,R.id._0_btn,R.id._1_btn,R.id._2_btn
					  ,R.id._3_btn,R.id._4_btn,R.id._5_btn,R.id._6_btn,R.id._7_btn,R.id._8_btn,R.id._9_btn,R.id.A_btn
					  ,R.id.B_btn,R.id.C_btn,R.id.D_btn,R.id.E_btn,R.id.F_btn,R.id.G_btn,R.id.H_btn,R.id.I_btn
					  ,R.id.G_btn,R.id.K_btn,R.id.L_btn,R.id.M_btn,R.id.N_btn,R.id.O_btn,R.id.P_btn,R.id.Q_btn
					  ,R.id.R_btn,R.id.S_btn,R.id.T_btn,R.id.U_btn,R.id.V_btn,R.id.W_btn,R.id.X_btn,R.id.Y_btn
					  ,R.id.Z_btn,R.id.Delete_btn,R.id.Tab_btn,R.id.Caps_btn,R.id.Ctrl_btn,R.id.Shift_btn,R.id.Alt_btn,R.id.Space_btn
					  ,R.id.PageUp_btn,R.id.PaDown_btn,R.id.Up_btn,R.id.Down_btn,R.id.Left_btn,R.id.Right_btn,R.id.Win_btn,R.id.Gantan_btn
					  ,R.id.Baife_btn,R.id.Right02_btn,R.id.Backspace_btn,R.id.Enter_btn,R.id.fh1_btn,R.id.fh2_btn,R.id.fh3_btn,R.id.fh4_btn
					  ,R.id.fh5_btn,R.id.fh6_btn,R.id.fh7_btn};
			final String [] keyCode = {"ESC","F1","F2","F3","F4","F5","F6","F7"
				            ,"F8","F9","F10","F11","F12","0","1","2"
				            ,"3","4","5","6","7","8","9","A"
				            ,"B","C","D","E","F","G","H","I"
				            ,"J","K","L","M","N","O","P","Q"
				            ,"R","S","T","U","V","W","X","Y"
				            ,"Z","media","Tab","Caps","Ctrl","Shift","Alt","Space"
				            ,"PageUp","PageDown","Up","Down","Left","Right","Win","Gantan"
				            ,"Baifen","Right02","Backspace","Enter","fh1","fh2","fh3","fh4"
				            ,"fh5","fh6","fh7"};
			
			
			final Map<Integer, String> keyMap = KeyDataAdapter.addData(keyID, keyCode);
			Log.e("ESC", ((Integer)keyID[0]).toString());
			
			//初始化按键Button
			for (int i = 0; i < keyID.length; i++) {
				button[i] = (Button)findViewById(keyID[i]);
				button[i].setOnClickListener(this);
			}
				
			
		}
	}
	
	/**
	 * 事件发送
	 * 
	 * */
	@Override
	public void onClick(View v) {
		//初始化标记按键
		capsLk = false;
		ctrlFlag = false;
		altFlag = false;
		shifFlag = false;
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		case R.id._0_btn:
			mRemoteOperateImpl.sendCommand("_0");
			break;
		case R.id._1_btn:
			mRemoteOperateImpl.sendCommand("_1");
			break;
		case R.id._2_btn:
			mRemoteOperateImpl.sendCommand("_2");
			break;
		case R.id._3_btn:
			mRemoteOperateImpl.sendCommand("_3");
			break;
		case R.id._4_btn:
			mRemoteOperateImpl.sendCommand("_4");
			break;
		case R.id._5_btn:
			mRemoteOperateImpl.sendCommand("_5");
			break;
		case R.id._6_btn:
			mRemoteOperateImpl.sendCommand("_6");
			break;
		case R.id._7_btn:
			mRemoteOperateImpl.sendCommand("_7");
			break;
		case R.id._8_btn:
			mRemoteOperateImpl.sendCommand("_8");
			break;
		case R.id._9_btn:
			mRemoteOperateImpl.sendCommand("_9");
			break;
		case R.id.f2_btn:
			mRemoteOperateImpl.sendCommand("F2");
			break;
		case R.id.f3_btn:
			mRemoteOperateImpl.sendCommand("F3");
			break;
		case R.id.f4_btn:
			mRemoteOperateImpl.sendCommand("F4");
			break;
		case R.id.f5_btn:
			mRemoteOperateImpl.sendCommand("F5");
			break;
		case R.id.f6_btn:
			mRemoteOperateImpl.sendCommand("F6");
			break;
		case R.id.f7_btn:
			mRemoteOperateImpl.sendCommand("F7");
			break;
		case R.id.f8_btn:
			mRemoteOperateImpl.sendCommand("F8");
			break;
		case R.id.f9_btn:
			mRemoteOperateImpl.sendCommand("F9");
			break;
		case R.id.f10_btn:
			mRemoteOperateImpl.sendCommand("F10");
			break;
		case R.id.f11_btn:
			mRemoteOperateImpl.sendCommand("F11");
			break;
		case R.id.f12_btn:
			mRemoteOperateImpl.sendCommand("F12");
			break;
		case R.id.A_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("a");
			}else {
				mRemoteOperateImpl.sendCommand("A");
			}
			
			break;
		case R.id.B_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("b");
			}else {
				mRemoteOperateImpl.sendCommand("B");
			}
			break;
		case R.id.C_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("c");
			}else {
				mRemoteOperateImpl.sendCommand("C");
			}
			break;
		case R.id.D_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("d");
			}else {
				mRemoteOperateImpl.sendCommand("D");
			}
			break;
		case R.id.E_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("e");
			}else {
				mRemoteOperateImpl.sendCommand("E");
			}
			break;
		case R.id.F_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("f");
			}else {
				mRemoteOperateImpl.sendCommand("F");
			}
			break;
		case R.id.G_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("g");
			}else {
				mRemoteOperateImpl.sendCommand("G");
			}
			break;
		case R.id.H_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("h");
			}else {
				mRemoteOperateImpl.sendCommand("H");
			}
			break;
		case R.id.I_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("i");
			}else {
				mRemoteOperateImpl.sendCommand("I");
			}
			break;
		case R.id.J_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("j");
			}else {
				mRemoteOperateImpl.sendCommand("J");
			}
			break;
		case R.id.K_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("k");
			}else {
				mRemoteOperateImpl.sendCommand("K");
			}
			break;
		case R.id.L_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("l");
			}else {
				mRemoteOperateImpl.sendCommand("L");
			}
			break;
		case R.id.M_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("m");
			}else {
				mRemoteOperateImpl.sendCommand("M");
			}
			break;
		case R.id.N_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("n");
			}else {
				mRemoteOperateImpl.sendCommand("N");
			}
			break;
		case R.id.O_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("o");
			}else {
				mRemoteOperateImpl.sendCommand("O");
			}
			break;
		case R.id.P_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("p");
			}else {
				mRemoteOperateImpl.sendCommand("P");
			}
			break;
		case R.id.Q_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("q");
			}else {
				mRemoteOperateImpl.sendCommand("Q");
			}
			break;
			
		case R.id.R_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("r");
			}else {
				mRemoteOperateImpl.sendCommand("R");
			}
			break;
		case R.id.S_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("s");
			}else {
				mRemoteOperateImpl.sendCommand("S");
			}
			break;
		case R.id.T_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("t");
			}else {
				mRemoteOperateImpl.sendCommand("T");
			}
			break;
		case R.id.U_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("u");
			}else {
				mRemoteOperateImpl.sendCommand("U");
			}
			break;
		case R.id.V_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("v");
			}else {
				mRemoteOperateImpl.sendCommand("V");
			}
			break;
		case R.id.W_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("w");
			}else {
				mRemoteOperateImpl.sendCommand("W");
			}
			break;
		case R.id.X_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("x");
			}else {
				mRemoteOperateImpl.sendCommand("X");
			}
			break;
		case R.id.Y_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("y");
			}else {
				mRemoteOperateImpl.sendCommand("Y");
			}
			break;
		case R.id.Z_btn:
			if (!capsLk) {
				mRemoteOperateImpl.sendCommand("z");
			}else {
				mRemoteOperateImpl.sendCommand("Z");
			}
			break;
		case R.id.Tab_btn:
			mRemoteOperateImpl.sendCommand("Tab");
			break;
		
		case R.id.Caps_btn:
			capsLk = true;
			mRemoteOperateImpl.sendCommand("Caps");	
			break;
		case R.id.Ctrl_btn:
			ctrlFlag = true;
			break;
		case R.id.Shift_btn:
			shifFlag = true;
			break;
		case R.id.Alt_btn:
			altFlag = true;
			break;
		case R.id.Space_btn:
			mRemoteOperateImpl.sendCommand("Space");
			break;
		case R.id.PageUp_btn:
			mRemoteOperateImpl.sendCommand("PageUp");
			break;
		case R.id.PaDown_btn:
			mRemoteOperateImpl.sendCommand("PageDown");
			break;
		case R.id.Up_btn:
			mRemoteOperateImpl.sendCommand("Up");
			break;
		case R.id.Down_btn:
			mRemoteOperateImpl.sendCommand("Down");
			break;
		case R.id.Left_btn:
			mRemoteOperateImpl.sendCommand("Left");
			break;
		case R.id.Right_btn:
			mRemoteOperateImpl.sendCommand("Right");
			break;
		case R.id.Right02_btn:
			mRemoteOperateImpl.sendCommand("Right02");
			break;
		case R.id.Win_btn:
			mRemoteOperateImpl.sendCommand("Win");
			break;
		case R.id.Gantan_btn:
			mRemoteOperateImpl.sendCommand("Gantan");
			break;
		case R.id.Baife_btn:
			mRemoteOperateImpl.sendCommand("Baifen");
			break;
		case R.id.Backspace_btn:
			mRemoteOperateImpl.sendCommand("Backspace");
			break;
		case R.id.fh1_btn:
			mRemoteOperateImpl.sendCommand("Bolang");
			break;
		case R.id.fh2_btn:
			mRemoteOperateImpl.sendCommand("Zuojian");
			mRemoteOperateImpl.sendCommand("Douhao");
			break;
		case R.id.fh3_btn:
			mRemoteOperateImpl.sendCommand("Youjian");
			mRemoteOperateImpl.sendCommand("Juhao");
			break;
		case R.id.fh4_btn:
			mRemoteOperateImpl.sendCommand("Wenhao");
			break;
		case R.id.fh5_btn:
			mRemoteOperateImpl.sendCommand("Fenhao");
			mRemoteOperateImpl.sendCommand("Maohao");
			break;
		case R.id.fh6_btn:
			mRemoteOperateImpl.sendCommand("Shuangyinhao");
			mRemoteOperateImpl.sendCommand("Danyinhao");
			break;
		case R.id.fh7_btn:
			mRemoteOperateImpl.sendCommand("Shugang");
			break;
		case R.id.Delete_btn:
			mRemoteOperateImpl.sendCommand("Delete");
			break;
		case R.id.Enter_btn:
			mRemoteOperateImpl.sendCommand("Enter");
			break;
		case R.id.Esc_btn:
			mRemoteOperateImpl.sendCommand("ESC");
			break;

		default:
			break;
		}
		
	}
}
