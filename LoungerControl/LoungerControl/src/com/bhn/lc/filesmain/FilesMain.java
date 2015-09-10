package com.bhn.lc.filesmain;

import com.bhn.lc.localfiles.LocalFileList;
import com.bhn.lc.rangefiles.RangeFileList;
import com.lzy.loungercontrol.net.Connecter;
import com.lzy.loungercontrol.net.ConnecterPool;
import com.lzy.loungercontrol.net.INetCallBack;
import com.lzy.loungercontrol.until.RemoteOperateImpl;
import com.lzy.loungercontrol.until.Tools;
import com.lzy.loungercontrol.untils.ProjectEnvironment;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

public class FilesMain extends TabActivity implements INetCallBack {

	TabHost tabHost;
	private Connecter connector;
	private RemoteOperateImpl mRemoteOperateImpl;
	private LinearLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);
		connector = ConnecterPool.getConnectorByKey(ConnecterPool.STRING_CKEY);
		if (null!=connector) {
			mRemoteOperateImpl = new RemoteOperateImpl(connector, this);
			ProjectEnvironment.BOOLEAN_LOCK_KEYBOAED = true;
		}else {
			Toast.makeText(this, "没有连接到电脑！",
					Toast.LENGTH_SHORT).show();
		}
		tabHost = getTabHost();
		tabHost.addTab(tabHost.newTabSpec("id1").setIndicator("本地文件")
				.setContent(new Intent(this, LocalFileList.class)));
		tabHost.addTab(tabHost.newTabSpec("id2").setIndicator("远程文件")
				.setContent(new Intent(this, RangeFileList.class)));
		TabWidget tabWidget = this.getTabWidget();
		for (int i = 0; i < tabWidget.getChildCount(); i++) {

			TextView tv = (TextView) tabWidget.getChildAt(i).findViewById(
					android.R.id.title);
			tabWidget.getChildAt(i).getLayoutParams().height = 150;
			tv.setTextSize(20);
			ImageView iv = (ImageView) tabWidget.getChildAt(i).findViewById(
					android.R.id.icon);
		}
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
		Tools.doNetLost(FilesMain.this);
		
	}
}
