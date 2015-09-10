package com.LRKZ.Servlet;
/**
 * 此servlet实现文件修改
 * */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import javax.swing.JOptionPane;

import com.LRKZ.Info.Files_info;
import com.LRKZ.Service.LRKZ_service;

public class File_Alter extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public File_Alter() {
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
		System.out.println("alter let start");
		Files_info files_info=new Files_info();
		files_info.setAppId(request.getParameter("appid"));
		files_info.setAppName(request.getParameter("appname"));
		double asize=Double.parseDouble(request.getParameter("appsize"));
		files_info.setAppSize(asize);
		files_info.setAppVersion(request.getParameter("appversion"));
		files_info.setAppDownl(request.getParameter("appdownl"));
		System.out.println("let set ok");
		LRKZ_service lrkz_service=new LRKZ_service();
		/*ServletContext scContext = this.getServletContext();
		String path = scContext.getRealPath("success_af.jsp");*/
		if (lrkz_service.Alter_file(files_info).equals("ok")) {
			response.sendRedirect("message1.jsp");
		}else {
			response.sendRedirect("message2.jsp");
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
