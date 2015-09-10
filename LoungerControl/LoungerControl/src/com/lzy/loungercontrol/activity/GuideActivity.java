package com.lzy.loungercontrol.activity;

/**
 * 
 * 本类为引导页面
 * 
 * */

import java.util.ArrayList;

import com.lzy.loungercontrol.adapter.MyPagerAdapter;
import com.lzy.loungercontrol.start.R;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class GuideActivity extends Activity {
	
	//翻页控件
	private ViewPager myViewPager;
	
	//底部小圆点
	private ImageView mPage0;
	private ImageView mPage1;
	private ImageView mPage2;
	private ImageView mPage3;
	private ImageView mPage4;
	private ImageView mPage5;
	private ImageView mPage6;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		
		//加载小圆点
		myViewPager = (ViewPager)findViewById(R.id.viewpager);
        
        
        myViewPager.setOnPageChangeListener(new MyOnPageChangeListener());        
        mPage0 = (ImageView)findViewById(R.id.page0);
        mPage1 = (ImageView)findViewById(R.id.page1);
        mPage2 = (ImageView)findViewById(R.id.page2);
        mPage3 = (ImageView)findViewById(R.id.page3);
        mPage4 = (ImageView)findViewById(R.id.page4);
        mPage5 = (ImageView)findViewById(R.id.page5);
        mPage6 = (ImageView)findViewById(R.id.page6);
        
        
        /*
         * 加载分页界面
         */
        LayoutInflater mLi = LayoutInflater.from(this);
		
        View view1 = mLi.inflate(R.layout.guide_one, null);
        View view2 = mLi.inflate(R.layout.guide_two, null);
        View view3 = mLi.inflate(R.layout.guide_three, null);
        View view4 = mLi.inflate(R.layout.guide_four, null);
        View view5 = mLi.inflate(R.layout.guide_five, null);
        View view6 = mLi.inflate(R.layout.guide_six, null);
		View view7 = mLi.inflate(R.layout.guide_seven, null);
		
		//装载分页
		final ArrayList<View> views = new ArrayList<View>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        views.add(view5);
        views.add(view6);   
        views.add(view7);
        
        //翻页事件监听
		myViewPager = (ViewPager)findViewById(R.id.viewpager);
		myViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
          
          /*
        	 * 每个页面的Title数据存放到ArrayList集合中
        	 * 可以在ViewPager适配器中调用展示
        	 */
          final ArrayList<String> titles = new ArrayList<String>();
          titles.add("tab1");
          titles.add("tab2");
          titles.add("tab3");
          titles.add("tab4");
          titles.add("tab5");
          titles.add("tab6");
          titles.add("tab7");
          
        //填充ViewPager的数据适配器
        MyPagerAdapter mPagerAdapter = new MyPagerAdapter(views,titles);
  		myViewPager.setAdapter(mPagerAdapter);
  		
  		
	}
	
	public void startClick(View view) {
		//设置已经引导
		setGuided();
		//跳转
		Intent mIntent = new Intent();
		mIntent.setClass(GuideActivity.this, MainActivity.class);
		GuideActivity.this.startActivity(mIntent);
		GuideActivity.this.finish();
	}
	
	private static final String SHAREDPREFERENCES_NAME = "my_pref";
	private static final String KEY_GUIDE_ACTIVITY = "guide_activity";
	private void setGuided() {
		SharedPreferences setting =getSharedPreferences(SHAREDPREFERENCES_NAME, 0);
		SharedPreferences.Editor editor = setting.edit();
		editor.putString(KEY_GUIDE_ACTIVITY, "false");
		editor.commit();
		
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {
    	
    	
		public void onPageSelected(int page) {
			
			//翻页时当前page,改变当前状态圆点图片
			switch (page) {
			case 0:				
				mPage0.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
				mPage1.setImageDrawable(getResources().getDrawable(R.drawable.page));
				break;
			case 1:
				mPage1.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
				mPage0.setImageDrawable(getResources().getDrawable(R.drawable.page));
				mPage2.setImageDrawable(getResources().getDrawable(R.drawable.page));
				break;
			case 2:
				mPage2.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
				mPage1.setImageDrawable(getResources().getDrawable(R.drawable.page));
				mPage3.setImageDrawable(getResources().getDrawable(R.drawable.page));
				break;
			case 3:
				mPage3.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
				mPage4.setImageDrawable(getResources().getDrawable(R.drawable.page));
				mPage2.setImageDrawable(getResources().getDrawable(R.drawable.page));
				break;
			case 4:
				mPage4.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
				mPage3.setImageDrawable(getResources().getDrawable(R.drawable.page));
				mPage5.setImageDrawable(getResources().getDrawable(R.drawable.page));
				break;
			case 5:
				mPage5.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
				mPage4.setImageDrawable(getResources().getDrawable(R.drawable.page));
				mPage6.setImageDrawable(getResources().getDrawable(R.drawable.page));
				break;
			case 6:
				mPage6.setImageDrawable(getResources().getDrawable(R.drawable.page_now));
				mPage5.setImageDrawable(getResources().getDrawable(R.drawable.page));
				break;
			}
		}
		
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		
		public void onPageScrollStateChanged(int arg0) {
		}
	}
}






	

