package com.bhn.lc.localfiles;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.bhn.lc.filespublic.FileAdapter;
import com.bhn.lc.filespublic.FileInfo;
import com.bhn.lc.rangefiles.Info_range;
import com.lzy.loungercontrol.activity.ConnSettingActivity;
import com.lzy.loungercontrol.net.Connecter;
import com.lzy.loungercontrol.net.ConnecterPool;
import com.lzy.loungercontrol.net.INetCallBack;
import com.lzy.loungercontrol.start.R;
import com.lzy.loungercontrol.until.RemoteOperateImpl;
import com.lzy.loungercontrol.until.Tools;
import com.lzy.loungercontrol.untils.ProjectEnvironment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LocalFileList extends ListActivity implements INetCallBack {
	ProgressDialog mProgressDialog;
	private Send send;
	private TextView _filePath;
	private List<FileInfo> _files = new ArrayList<FileInfo>();
	private String _rootPath = FileUtil.getSDPath();// 获取SD路径
	private String _currentPath = _rootPath;
	private final String TAG = "Main";
	private final int MENU_RENAME = Menu.FIRST;
	private final int MENU_COPY = Menu.FIRST + 3;
	private final int MENU_MOVE = Menu.FIRST + 4;
	private final int MENU_DELETE = Menu.FIRST + 5;
	private final int MENU_INFO = Menu.FIRST + 6;
	private final int MENU_TRAN = Menu.FIRST + 7;
	private BaseAdapter adapter = null;
	RemoteOperateImpl remoteOperateImpl = null;
	private Connecter connector;
	Handler handler;
	String x = "南哥哥";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.e("南哥哥", "path是..." + _rootPath);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.file_list);

		mProgressDialog = new ProgressDialog(LocalFileList.this);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 格式
		mProgressDialog.setCancelable(true);// 设置点击Back返回键退出
		mProgressDialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
		mProgressDialog.setMessage("正在传送。。。");
		mProgressDialog.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface dialog) {

				if (null != send) {
					send.cancel(true);
				}

			}
		});

		connector = ConnecterPool.getConnectorByKey(ConnecterPool.STRING_CKEY);
		if (null != connector) {
			remoteOperateImpl = new RemoteOperateImpl(connector, this);
			ProjectEnvironment.BOOLEAN_LOCK_KEYBOAED = true;
		} else {

		}

		_filePath = (TextView) findViewById(R.id.file_path);
		// 注册长按菜单
		registerForContextMenu(getListView());
		// 绑定数据
		adapter = new FileAdapter(this, _files);
		setListAdapter(adapter);// 设置list到列表上，listview使用的是@id/android:list
		// 获取当前目录的文件列表
		Log.e(x, "onCreate currentPath 1:" + _currentPath);
		viewFiles(_currentPath); // 1111 初始值为sdcard的位置

	}

	/** 长按菜单 **/
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		AdapterView.AdapterContextMenuInfo info = null;

		try {
			info = (AdapterView.AdapterContextMenuInfo) menuInfo;
		} catch (ClassCastException e) {
			Log.e(TAG, "bad menuInfo", e);
			return;
		}

		FileInfo f = _files.get(info.position);
		menu.setHeaderIcon(getResources().getDrawable(
				R.drawable.ic_menu_archive));
		menu.setHeaderTitle(f.Name);
		menu.add(0, MENU_RENAME, 1, getString(R.string.file_rename));
		menu.add(0, MENU_COPY, 2, getString(R.string.file_copy));
		menu.add(0, MENU_MOVE, 3, getString(R.string.file_move));
		menu.add(0, MENU_DELETE, 4, getString(R.string.file_delete));
		menu.add(0, MENU_TRAN, 5, "上传");
		menu.add(0, MENU_INFO, 6, getString(R.string.file_info));

	}

	/** 长按菜单事件处理 **/
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		FileInfo fileInfo = _files.get(info.position);
		final File f = new File(fileInfo.Path);

		switch (item.getItemId()) {
		case MENU_RENAME:
			FileActivityHelper.renameFile(LocalFileList.this, f,
					renameFileHandler);
			return true;
		case MENU_COPY:
			pasteFile(f.getPath(), "COPY");
			return true;
		case MENU_MOVE:
			new AlertDialog.Builder(LocalFileList.this)
					.setMessage("是否剪切？")
					.setCancelable(false)
					.setPositiveButton("是",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									pasteFile(f.getPath(), "MOVE");
								}
							})
					.setNegativeButton("否",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
								}
							}).show();
			return true;
		case MENU_DELETE:
			new AlertDialog.Builder(LocalFileList.this)
					.setMessage("是否删除？")
					.setCancelable(false)
					.setPositiveButton("是",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									FileUtil.deleteFile(f);
									viewFiles(_currentPath);
								}
							})
					.setNegativeButton("否",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
								}
							}).show();

			return true;
		case MENU_INFO:
			FileActivityHelper.viewFileInfo(LocalFileList.this, f);
			return true;
		case MENU_TRAN:
			Log.e(x, "上传按钮");
			if (f.isDirectory()) {
				Toast.makeText(LocalFileList.this, "抱歉，不能上传文件夹。",
						Toast.LENGTH_SHORT).show();
			} else {
				Send send = new Send();
				send.execute(fileInfo.Path);
			}
		default:
			return super.onContextItemSelected(item);
		}
	}

	class Send extends AsyncTask<String, Integer, String> {

		@Override
		protected void onPreExecute() {
			// 预处理
			

			mProgressDialog.show();
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(String result) {
			// 处理结果

			mProgressDialog.dismiss();
			if (result == null) {// ConnecterPool.getConnectorPoolSize()
				// 上传成功
				Toast.makeText(LocalFileList.this, "上传成功！存放在电脑D盘根目录。", Toast.LENGTH_LONG)
						.show();

			} else {
				// 连接失败
				Tools.ToastShow(LocalFileList.this,
						R.string.setting_activity_setting_link_fail);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
				// Tools.startActivity(SettingActivity.this,
				// MainActivity.class);
			}
		}

		@Override
		protected String doInBackground(String... params) {
			// 后台计算
			String host = params[0];
			File fil = new File(host);
			Log.e("host-->", host);
			try {
				UploadFile uploadFile = new UploadFile();
				uploadFile.send(fil);
			} catch (Exception e) {
				// Log.e(tag, "message:" + e.getMessage());
				cancel(true);
				e.printStackTrace();
			}
			return null;
		}

	}

	/** 行被点击事件处理 **/
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		FileInfo f = _files.get(position);
		if (f.IsDirectory) {// 不是文件夹
			viewFiles(f.Path);// 获取该目录下所有文件
		} else {
			openFile(f.Path);// 打开文件
			// f.Path是空的
		}
	}

	/** 重定义返回键事件 **/
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 拦截back按键
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			File f = new File(_currentPath);
			String parentPath = f.getParent();
			if (parentPath != null) {
				viewFiles(parentPath);
			} else {
				exit();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/** 获取从PasteFile传递过来的路径 **/
	@Override
	// 黏贴
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (Activity.RESULT_OK == resultCode) {
			Bundle bundle = data.getExtras();
			if (bundle != null && bundle.containsKey("CURRENTPATH")) {
				String v = bundle.getString("CURRENTPATH");
				Log.e(x, "黏贴:" + v);
				viewFiles(v);
			}
		}
	}

	/** 创建菜单 **/
	// 点击菜单键弹出的菜单
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = this.getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}

	/** 菜单事件 **/
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.mainmenu_home:
			viewFiles(_rootPath);
			break;
		case R.id.mainmenu_refresh:
			viewFiles(_currentPath);
			break;
		case R.id.mainmenu_createdir:
			FileActivityHelper.createDir(LocalFileList.this, _currentPath,
					createDirHandler);
			break;
		case R.id.mainmenu_exit:
			exit();
			break;
		default:
			break;
		}
		return true;
	}

	/** 获取该目录的路径以及所有文件 **/
	private void viewFiles(String filePath) { // 2222
		ArrayList<FileInfo> tmp = FileActivityHelper.getFiles(
				LocalFileList.this, filePath);
		if (tmp != null) {
			// 清空数据
			_files.clear();
			_files.addAll(tmp);
			tmp.clear();

			// 设置当前目录
			Log.e(x, "viewFiles filePath :" + filePath);
			_currentPath = filePath;
			_filePath.setText(filePath);

			// this.onContentChanged();
			adapter.notifyDataSetChanged();// notifyDataSetChanged方法通过一个外部的方法控制
											// 如果适配器的内容改变时需要强制调用getView来刷新每个Item的内容
		}
	}

	/** 打开文件 **/
	private void openFile(String path) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);

		File f = new File(path);
		String type = FileUtil.getMIMEType(f.getName());
		intent.setDataAndType(Uri.fromFile(f), type);
		startActivity(intent);
	}

	/** 重命名回调委托 **/
	private final Handler renameFileHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0)
				viewFiles(_currentPath);
		}
	};

	/** 创建文件夹回调委托 **/
	private final Handler createDirHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0)
				viewFiles(_currentPath);
		}
	};

	/** 粘贴文件 **/
	private void pasteFile(String path, String action) {
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		bundle.putString("CURRENTPASTEFILEPATH", path);
		bundle.putString("ACTION", action);
		intent.putExtras(bundle);
		intent.setClass(LocalFileList.this, PasteFile.class);
		// 打开一个Activity并等待结果
		startActivityForResult(intent, 0);
	}

	/** 退出程序 **/
	private void exit() {
		finish();
		/*
		 * new AlertDialog.Builder(LocalFileList.this)
		 * .setMessage(R.string.confirm_exit) .setCancelable(false)
		 * .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		 * 
		 * @Override public void onClick(DialogInterface dialog, int which) {
		 * LocalFileList.this.finish(); android.os.Process
		 * .killProcess(android.os.Process.myPid()); System.exit(0); } })
		 * .setNegativeButton("No", new DialogInterface.OnClickListener() {
		 * 
		 * @Override public void onClick(DialogInterface dialog, int which) {
		 * dialog.cancel(); } }).show();
		 */
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

	}
}
