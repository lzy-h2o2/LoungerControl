package com.bhn.lc.filespublic;

import com.lzy.loungercontrol.start.R;



/** 文件信息 **/

public class FileInfo {
	public String Name;
	public String Path;
	public long Size;
	public boolean IsDirectory = false;
	public int FileCount = 0;
	public int FolderCount = 0; 

	public int getIconResourceId() {//文件前显示的图标样式
		if (IsDirectory) {
			return R.drawable.folder;
		}
		return R.drawable.doc;
	}
}