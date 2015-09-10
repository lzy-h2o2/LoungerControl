package com.LRKZ.DoSQL;
/**
 * 
 * 此类实现数据库的具体操作
 * 用户登录验证
 * */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.LRKZ.Admin_DBConn.DB_Connection;
import com.LRKZ.Info.Admins_info;
import com.LRKZ.Info.Advice_info;
import com.LRKZ.Info.Files_info;
import com.LRKZ.Info.Updatelog_info;
import com.LRKZ.Interface.Admins_DBInterface;

public class Admin_Login implements Admins_DBInterface {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	/* 管理员登录,核对密码 */
	@SuppressWarnings("null")
	@Override
	public Admins_info login(Admins_info admins_info) {
		// TODO Auto-generated method stub
		Admins_info aInfo = null;
		con = DB_Connection.getConnection();
		String sql = "select apsw,aid from admin where aid="
				+ admins_info.getId() + ";";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				aInfo = new Admins_info();
				aInfo.setPsw(rs.getString(1));
				aInfo.setId(rs.getString(2));
			} else {
				aInfo.setId("0");
			}
			DB_Connection.Close(con, ps, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aInfo;
	}

	/* 显示所有app文件 */
	@Override
	public List<Files_info> AllFiles() {
		// TODO Auto-generated method stub
		List<Files_info> list = null;
		list = new ArrayList<Files_info>();
		Files_info fInfo = null;
		con = DB_Connection.getConnection();
		String sql = "select * from appfile;";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				fInfo = new Files_info();
				fInfo.setAppId(rs.getString(2));
				fInfo.setAppName(rs.getString(3));
				fInfo.setAppSize(rs.getDouble(4));
				fInfo.setAppVersion(rs.getString(5));
				fInfo.setAppDownl(rs.getString(6));
				list.add(fInfo);
			}
			DB_Connection.Close(con, ps, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/* 删除app文件 */
	@Override
	public String DeleteFile(String x) {
		// TODO Auto-generated method stub
		con = DB_Connection.getConnection();
		System.out.println("删除的条件值：" + x);
		String sql = "delete from appfile where appid=?;";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, x);
			int i = ps.executeUpdate();
			System.out.println("删除是否成功：" + i);
			DB_Connection.Close(con, ps, rs);
			if (i > 0) {
				return "1";
			} else {
				return "0";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* 修改app文件 */
	@Override
	public String AlterFile(Files_info files_info) {
		// TODO Auto-generated method stub
		System.out.println("修改     start");
		con = DB_Connection.getConnection();
		String sql = "UPDATE appfile SET appname=?,appSize=?,appVersion=?,app=? WHERE appid=?;";
		System.out.println("修改语句："+sql);
        try {
			ps=con.prepareStatement(sql);
			ps.setString(1, files_info.getAppName());
			ps.setDouble(2, files_info.getAppSize());
			ps.setString(3, files_info.getAppVersion());
			ps.setString(4, files_info.getAppDownl());
			ps.setString(5, files_info.getAppId());
			int i=ps.executeUpdate();
			System.out.println("修改成功数目："+i);
			DB_Connection.Close(con, ps, rs);
			if (i>0) {
				return "1";
			}else {
				return "0";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("修改      end");
		return null;
	}

	/* 显示所有用户建议 */
	@Override
	public List<Advice_info> AllAdvice() {
		// TODO Auto-generated method stub
		List<Advice_info> list = null;
		list = new ArrayList<Advice_info>();
		Advice_info fInfo = null;
		con = DB_Connection.getConnection();
		String sql = "select * from useradvice;";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				fInfo = new Advice_info();
				fInfo.setUid(rs.getInt(1));
				fInfo.setUname(rs.getString(2));
				fInfo.setUtel(rs.getString(3));
				fInfo.setUemail(rs.getString(4));
				fInfo.setUadvice(rs.getString(5));
				list.add(fInfo);
			}
			DB_Connection.Close(con, ps, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/* 显示所有更新日志 */
	@Override
	public List<Updatelog_info> AllUpdatelog() {
		// TODO Auto-generated method stub
		List<Updatelog_info> list = null;
		list = new ArrayList<Updatelog_info>();
		Updatelog_info fInfo = null;
		con = DB_Connection.getConnection();
		String sql = "select * from state;";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				fInfo = new Updatelog_info();
				fInfo.setSid(rs.getString(1));
				fInfo.setAppid(rs.getString(2));
				
				// Date d=new Date(rs.getTime(3).getTime());
				fInfo.setSdate(rs.getString(3));
				fInfo.setSintro(rs.getString(4));
				fInfo.setSdetail(rs.getString(5));
				list.add(fInfo);
			}
			DB_Connection.Close(con, ps, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/* 修改反馈 */
	@Override
	public String AlterAdvice(Advice_info advice_info) {
		// TODO Auto-generated method stub
		con = DB_Connection.getConnection();
		String sql = "update useradvice set uname=?,utel=?,uemail=?,uadvice=? where uid=?;";

		System.out.println("修改语句：" + sql);
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, advice_info.getUname());
			ps.setString(2, advice_info.getUtel());
			ps.setString(3, advice_info.getUemail());
			ps.setString(4, advice_info.getUadvice());
			ps.setInt(5, advice_info.getUid());
			int i = ps.executeUpdate();
			System.out.println("修改成功数目：" + i);
			DB_Connection.Close(con, ps, rs);
			if (i > 0) {
				return "1";
			} else {
				return "0";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* 修改日志 */
	@Override
	public String AlterLog(Updatelog_info updatelog_info) {
		// TODO Auto-generated method stub
		System.out.println("修改     start");

		con = DB_Connection.getConnection();
		String sql = "update state set appid='" + updatelog_info.getAppid()
				+ "',sdate='" + updatelog_info.getSdate() + "',sintro='"
				+ updatelog_info.getSintro() + "',sdetail='"
				+ updatelog_info.getSdetail() + "' where sid='"
				+ updatelog_info.getSid() + "';";

		System.out.println("修改语句：" + sql);
		try {
			ps = con.prepareStatement(sql);
			/*
			 * ps.setString(1, updatelog_info.getAppid());
			 * 
			 * 
			 * 
			 * ps.setString(3, updatelog_info.getSintro()); ps.setString(4,
			 * updatelog_info.getSdetail()); ps.setString(5,
			 * updatelog_info.getSid());
			 */

			int i = ps.executeUpdate();

			System.out.println("修改成功数目：" + i);
			DB_Connection.Close(con, ps, rs);
			if (i > 0) {
				return "1";
			} else {
				return "0";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("修改      end");
		return null;
	}
    /*删除反馈*/
	@Override
	public String DeleteAdvice(int x) {
		// TODO Auto-generated method stub
		con = DB_Connection.getConnection();
		System.out.println("删除的条件值：" + x);
		String sql = "delete from useradvice where uid=?;";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, x);
			int i = ps.executeUpdate();
			System.out.println("删除是否成功：" + i);
			DB_Connection.Close(con, ps, rs);
			if (i > 0) {
				return "1";
			} else {
				return "0";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String DeleteLog(String x) {
		// TODO Auto-generated method stub
		con = DB_Connection.getConnection();
		System.out.println("删除的条件值：" + x);
		String sql = "delete from state where sid=?;";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, x);
			int i = ps.executeUpdate();
			System.out.println("删除是否成功：" + i);
			DB_Connection.Close(con, ps, rs);
			if (i > 0) {
				return "1";
			} else {
				return "0";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
