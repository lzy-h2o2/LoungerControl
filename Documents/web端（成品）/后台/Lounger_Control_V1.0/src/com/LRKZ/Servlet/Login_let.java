package com.LRKZ.Servlet;
/**
 * 管理员登录*/
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.LRKZ.Info.Admins_info;
import com.LRKZ.Service.LRKZ_service;

/**
 * Servlet implementation class Login_let
 */
@WebServlet("/Login_let")
public class Login_let extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login_let() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8");
		Admins_info admins_info=new Admins_info();
		String id=request.getParameter("user");
//		int id2=Integer.parseInt(id);
		String name=request.getParameter("psw");
		admins_info.setId(id);
		admins_info.setPsw(name);
		LRKZ_service lrkz_service=new LRKZ_service();
		if (lrkz_service.login(admins_info).equals("ok")) {
			response.sendRedirect("admin_work.jsp");
		}else {
			response.sendRedirect("index.jsp");
		}
	}

}
