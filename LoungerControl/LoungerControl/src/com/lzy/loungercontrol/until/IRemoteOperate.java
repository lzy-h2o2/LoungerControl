package com.lzy.loungercontrol.until;

public interface IRemoteOperate {
	public String moveMouse(float x,float y);
	public String click(float x,float y);//有了mouseDown和MouseClick貌似可以不用这个了
	public String doubleClick(float x,float y);
	public String rightClick(float x, float y);
	public String keyDown(int keyCode);
	public String keyUp(int keyCode);
	public String mouseDown(float x,float y);
	public String mouseUp(float x,float y);
	public String mouseWheel(int x);
	public String sendCommand(String command);
	public String sendDosCommand(String command);
}
