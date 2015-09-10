package com.LRKZ.Interface;
/**
 * 此接口提供操作功能定义
 * */
import java.util.List;

import com.LRKZ.Info.Admins_info;
import com.LRKZ.Info.Advice_info;
import com.LRKZ.Info.Files_info;
import com.LRKZ.Info.Updatelog_info;

public interface Admins_DBInterface {
    public Admins_info login(Admins_info admins_info);//管理员登录
    public List<Files_info> AllFiles();//显示所有文件
    public String DeleteFile(String x);//删除文件
    public String DeleteAdvice(int x);//删除反馈
    public String DeleteLog(String x);//删除日志
    public String AlterFile(Files_info files_info);//修改文件
    public List<Advice_info> AllAdvice();//显示所有意见
    public List<Updatelog_info> AllUpdatelog();//显示更新日志
    public String AlterAdvice(Advice_info advice_info);//修改反馈
    public String AlterLog(Updatelog_info updatelog_info);//修改日志
}
