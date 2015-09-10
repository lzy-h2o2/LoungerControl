package com.bhn.lc.rangefiles;

import com.lzy.loungercontrol.start.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RangeFileList extends Activity {

	private Button btn;
	private TextView tv;
	Handler myHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.file_range);
		btn=(Button) findViewById(R.id.receivebtn);
		tv=(TextView) findViewById(R.id.ac);
		myHandler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				switch (msg.what) {
				case 0:
					tv.setText("未选择文件！");
					tv.setTextColor(Color.rgb(255, 0, 0));
					break;
				case 1:
					tv.setText("文件传输完毕，位置在sdcard。");
					tv.setTextColor(Color.rgb(0, 0, 0));
				default:
					break;
				}
				super.handleMessage(msg);
			}
		};
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				tv.setText("文件位置在sdcard。");
				tv.setTextColor(Color.rgb(0, 0, 0));
				new Thread(new ReceiveName(myHandler)).start();
				
			}
		});
		
		
	}
}
