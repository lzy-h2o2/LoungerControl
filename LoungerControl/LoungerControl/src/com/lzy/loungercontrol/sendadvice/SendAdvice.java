package com.lzy.loungercontrol.sendadvice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lzy.email.untils.MailSenderInfo;
import com.lzy.email.untils.SimpleMailSender;
import com.lzy.loungercontrol.activity.BaseActivity;
import com.lzy.loungercontrol.start.R;

public class SendAdvice extends BaseActivity{
	
	private EditText nameEditText;
	private EditText telEditText;
	private EditText emailEditText;
	private EditText adviceEditText;
	private Button sendButton;
	private Button quitButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_advice);
		setTitle("反馈意见");
		
		nameEditText = (EditText)findViewById(R.id.name_et);
		telEditText = (EditText)findViewById(R.id.tel_et);
		emailEditText = (EditText)findViewById(R.id.email_et);
		adviceEditText = (EditText)findViewById(R.id.advice_et);
		sendButton = (Button)findViewById(R.id.send_btn);
		quitButton = (Button)findViewById(R.id.quit_btn);		
		
		/**
		 * 按键监听
		 * 
		 * */
		sendButton.setOnClickListener(new OnClickListener() {
			
			

			@Override
			public void onClick(View v) {
				String name = nameEditText.getText().toString();
				String tel = telEditText.getText().toString();
				String email = emailEditText.getText().toString();
				String advice = adviceEditText.getText().toString();
				String content = name+":"+tel+":"+email+":"+advice;
				settingEmail(content);
				Log.e("意见内容", content);
				
				/**
				 * 邮箱格式
				 * */
				Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
				Matcher matcher = pattern.matcher(email);
				//System.out.println(matcher.matches());
				
				
				if (advice.equals("")||advice==null) {
					//Toast.makeText(SendAdvice.this, "124", Toast.LENGTH_SHORT).show();
					showNullDialog();
				}else {
					if (!matcher.matches()) {
						formatErrorDialog();
					}else {
						Toast.makeText(SendAdvice.this, "发送成功！！！", Toast.LENGTH_SHORT).show();
						SendAdvice.this.finish();
					}
					
				}
				
				
			}

			private void formatErrorDialog(){
				AlertDialog.Builder exitDialog = new Builder(SendAdvice.this);
				exitDialog.setTitle("邮箱校验");
				exitDialog.setIcon(R.drawable.confirm_title);
				exitDialog.setMessage("邮箱格式不正确！请检查输入...");
				exitDialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {

						dialog.dismiss();
						
					}
				});
				exitDialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						
					};
				});
				
				exitDialog.show();
			}
			private void showNullDialog() {
				AlertDialog.Builder exitDialog = new Builder(SendAdvice.this);
				exitDialog.setTitle("发送为空！");
				exitDialog.setIcon(R.drawable.confirm_title);
				exitDialog.setMessage("您确定要不留下点什么给我们？");
				exitDialog.setPositiveButton("没什么说的", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {

						SendAdvice.this.finish();
						
					}
				});
				exitDialog.setNegativeButton("打赏你们点", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						
					};
				});
				
				exitDialog.show();
				
			}
		});
		quitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SendAdvice.this.finish();
				
			}
		});
		
	}
	
	//触摸输入文本框以外的部分自动隐藏输入法
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {


		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			View view = getCurrentFocus();
			if (isShouldHideInput(view,ev)) {
				InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				if (imm != null) {
					imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
				}
			}
			return super.dispatchTouchEvent(ev);
		}
		if (getWindow().superDispatchTouchEvent(ev)) {
			return true;
		}
		return onTouchEvent(ev);
	}
	
	
	private boolean isShouldHideInput(View view, MotionEvent ev) {
		if (view !=null && (view instanceof EditText)) {
			int [] leftTop={0,0};
			//获取输入框当前位置
			view.getLocationInWindow(leftTop);
			int left = leftTop[0];
			int top = leftTop[1];
			int bottom = top+view.getHeight();
			int right = left+view.getWidth();
			if (ev.getX()>left && ev.getX()<bottom
					&& ev.getY()>top && ev.getY()<bottom) {
				//点击的是输入框区域保留edittext的事件
				return false;
			}else {
				return true;
			}
		}
		return false;
	}

	private void settingEmail(String content) {
		//设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.qq.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("1486549689@qq.com");
		mailInfo.setPassWord("wdx2lzy258hy");
		mailInfo.setFromAddress("1486549689@qq.com");
		mailInfo.setToAddresss("1486549689@qq.com");
		mailInfo.setSubject("意见反馈");
		mailInfo.setContent(content);
		
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendTextMail(mailInfo);//以文本方式发送
		//sms.sendHtmlMail(mailInfo);
	}
	
	
	
}
