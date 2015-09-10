package com.lzy.loungercontrol.net;

import java.util.HashMap;
import java.util.Map.Entry;
import com.lzy.loungercontrol.net.Connecter;

/**
 * 本类是网络连接池
 * */
public class ConnecterPool {
	public static final String STRING_CKEY="ConnecterPool";
	//功能扩展部分
	public static HashMap<String, Connecter> mapConnectorPool = new HashMap<String, Connecter>();
	public static void putConnecter(String key,Connecter connector) {
		//参数传进来的key，留待后用
		clearPool();
		mapConnectorPool.put(STRING_CKEY, connector);
	}
	/**
	 * 根据ip地址连接
	 * */
	public static Connecter getConnectorByKey(String key){
		return mapConnectorPool.get(key);
	}
	/**
	 * 获取连接池大小
	 * */
	public static int getConnectorPoolSize(){
    	return mapConnectorPool.size();
    }
	
	public static void clearPool() {
		for(Entry<String,Connecter> entry:mapConnectorPool.entrySet()){
    		entry.getValue().killSelf();
    	}
    	mapConnectorPool.clear();
		
	}
}
