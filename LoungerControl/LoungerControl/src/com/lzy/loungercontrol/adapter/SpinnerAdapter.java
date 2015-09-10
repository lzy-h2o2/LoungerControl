package com.lzy.loungercontrol.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lzy.loungercontrol.start.R;

public class SpinnerAdapter {
	public SpinnerAdapter() {
		// TODO Auto-generated constructor stub
	}
	public static  List<Map<String, Object>> getSpinnerList() {
		
		List<Map<String, Object>> spinnerList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		Map<String, Object> map3 = new HashMap<String, Object>();
		Map<String, Object> map4 = new HashMap<String, Object>();
		map1.put("img", R.drawable.mouse_style_arrow);
		map1.put("text", "标准");
		map2.put("img", R.drawable.red_dot);
		map2.put("text", "激光笔");
		map3.put("img", R.drawable.graffiti_pen_72);
		map3.put("text", "涂鸦笔");
		map4.put("img", R.drawable.eraser);
		map4.put("text", "橡皮擦");
		
		
		spinnerList.add(map1);
		spinnerList.add(map2);
		spinnerList.add(map3);
		spinnerList.add(map4);
		
		return spinnerList;
		
	}
}
