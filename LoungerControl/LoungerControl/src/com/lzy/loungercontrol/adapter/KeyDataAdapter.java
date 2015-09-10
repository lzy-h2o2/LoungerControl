package com.lzy.loungercontrol.adapter;

import java.util.HashMap;
import java.util.Map;

public class KeyDataAdapter {

	public static Map<Integer, String> addData(int[] keyId, String[] keyCode) {
		Map<Integer, String> keyMap = new HashMap<Integer,String>();
		for (int i = 0; i < keyId.length; i++) {
			keyMap.put(keyId[i], keyCode[i]);
		}
		return keyMap;
	}
}
