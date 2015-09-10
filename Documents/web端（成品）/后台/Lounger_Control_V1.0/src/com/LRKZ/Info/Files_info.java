package com.LRKZ.Info;
/**
 * 此类描述文件的属性
 * */
public class Files_info {
    private String appId;
    private String appName;
    private double appSize;
    private String appVersion;//版本号 
    private String appDownl;//下载地址
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public double getAppSize() {
		return appSize;
	}
	public void setAppSize(double appSize) {
		this.appSize = appSize;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getAppDownl() {
		return appDownl;
	}
	public void setAppDownl(String appDownl) {
		this.appDownl = appDownl;
	}
}
