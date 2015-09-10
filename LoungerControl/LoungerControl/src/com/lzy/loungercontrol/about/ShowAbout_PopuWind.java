package com.lzy.loungercontrol.about;
import com.lzy.loungercontrol.start.R;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;


public class ShowAbout_PopuWind extends PopupWindow {

	public ShowAbout_PopuWind(Context context) {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.about, null);
		this.setContentView(view);
		this.setWindowLayoutMode(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		this.setAnimationStyle(android.R.style.Animation);
		ColorDrawable dw = new ColorDrawable(0x76333571);
		this.setBackgroundDrawable(dw);
		this.setFocusable(true);
		this.update();
		this.setOutsideTouchable(true);
		this.setTouchable(true);
	}

}
