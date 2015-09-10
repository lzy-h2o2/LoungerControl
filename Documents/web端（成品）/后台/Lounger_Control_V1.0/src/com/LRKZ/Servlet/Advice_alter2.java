package com.LRKZ.Servlet;
/**
 * 此servlet实现修改意见
 * */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.LRKZ.Info.Advice_info;
import com.LRKZ.Service.LRKZ_service;
@WebServlet("/Advice_alter2")
public class Advice_alter2 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public Advice_alter2() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8");
		LRKZ_service lrkz_service=new LRKZ_service();
		Advice_info advice_info=new Advice_info();
		String id=request.getParameter("uid");
		int uid=Integer.parseInt(id);
		advice_info.setUid(uid);
		advice_info.setUname(request.getParameter("uname"));
		advice_info.setUtel(request.getParameter("utel"));
		advice_info.setUemail(request.getParameter("uemail"));
		advice_info.setUadvice(request.getParameter("uadvice"));
		String an=lrkz_service.Alter_advice(advice_info);
		if (an.equals("ok")) {
			response.sendRedirect("message1.jsp");
		}else {
			response.sendRedirect("message1.jsp");
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
