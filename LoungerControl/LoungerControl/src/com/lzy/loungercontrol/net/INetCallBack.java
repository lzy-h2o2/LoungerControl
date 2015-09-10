package com.lzy.loungercontrol.net;

public interface INetCallBack {
	public void OnStart();
	public void OnFinish();
	public void OnIntercepted(String source);
}
