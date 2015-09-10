package com.LRKZ.Info;
/**
 * 此类描述更新日志的属性
 * */
public class Updatelog_info {
    private String sid;
    private String appid;
    private String sdate;
    private String sintro;
    private String sdetail;
    
	public String getSintro() {
		return sintro;
	}
	public void setSintro(String sintro) {
		this.sintro = sintro;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getSdetail() {
		return sdetail;
	}
	public void setSdetail(String sdetail) {
		this.sdetail = sdetail;
	}
}
