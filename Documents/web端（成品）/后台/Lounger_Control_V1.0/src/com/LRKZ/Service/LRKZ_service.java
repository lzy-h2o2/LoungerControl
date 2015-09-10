package com.LRKZ.Service;
/**
 * 此类实现接口操作
 * */
import java.util.List;

import com.LRKZ.DoSQL.Admin_Login;
import com.LRKZ.Info.Admins_info;
import com.LRKZ.Info.Advice_info;
import com.LRKZ.Info.Files_info;
import com.LRKZ.Info.Updatelog_info;

public class LRKZ_service {
	    
	/*登录判断*/
	public String login(Admins_info admins_info) {           
		Admin_Login admin_Login = null;
		admin_Login=new Admin_Login();
		Admins_info aInfo = null;
		aInfo=admin_Login.login(admins_info);
		if (admins_info != null) {
			if (aInfo != null) {
                if (aInfo.getId().equals("0")) {
					return "sqlnot";
				}
				if (!(aInfo.getId().equals(admins_info.getId()))) {
					return "user not";
				} else {
					if (aInfo.getPsw().equals(admins_info.getPsw())) {
						return "ok";
					}
					return "psw not";
				}
			}
			return "sqlnull";
		}
		return "inputnull";
	}
	/*判断获取所有app文件数据*/
	
	public List<Files_info> All_flie(){         
		Admin_Login admin_Login = null;
		admin_Login=new Admin_Login();
		return admin_Login.AllFiles();
	}
	
	/*判断删除app文件数据*/
	public String Delete_file(String x){        
		Admin_Login admin_Login = null;
		admin_Login=new Admin_Login();
		if (admin_Login.DeleteFile(x).equals("1")) {
			return "ok";
		}else {
			return "no";
		}
		
	}
	/*判断删除反馈文件数据*/
	public String Delete_advice(int x){        
		Admin_Login admin_Login = null;
		admin_Login=new Admin_Login();
		if (admin_Login.DeleteAdvice(x).equals("1")) {
			return "ok";
		}else {
			return "no";
		}
	}
	/*判断删除日志文件数据*/
	public String Delete_log(String x){        
		Admin_Login admin_Login = null;
		admin_Login=new Admin_Login();
		if (admin_Login.DeleteLog(x).equals("1")) {
			return "ok";
		}else {
			return "no";
		}
	}
	/*判断修改app文件数据*/
	public String Alter_file(Files_info files_info){       
		
		Admin_Login admin_Login = null;
		admin_Login=new Admin_Login();
		if (admin_Login.AlterFile(files_info).equals("1")) {
			
			return "ok";
		}else {
		
			return "no";
		}
		
	}
	/*判断获取所有反馈*/
	public List<Advice_info> All_advice(){  
		Admin_Login admin_Login = null;
		admin_Login=new Admin_Login();
		return admin_Login.AllAdvice();
		
	}
	/*判断获取所有日志*/
	public List<Updatelog_info> All_updatelog(){       
		System.out.println("log service start");
		Admin_Login admin_Login = null;
		admin_Login=new Admin_Login();
		return admin_Login.AllUpdatelog();
	}
	/*修改反馈*/
public String Alter_advice(Advice_info advice_info){       
		Admin_Login admin_Login = null;
		admin_Login=new Admin_Login();
		if (admin_Login.AlterAdvice(advice_info).equals("1")) {
			
			return "ok";
		}else {
		
			return "no";
		}
		
	}
/*修改日志*/
public String Alter_log(Updatelog_info updatelog_info){       
	Admin_Login admin_Login = null;
	admin_Login=new Admin_Login();

	if (admin_Login.AlterLog(updatelog_info).equals("1")) {
		
		return "ok";
	}else {
	
		return "no";
	}
	
}
}
