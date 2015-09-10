package com.LRKZ.Servlet;
/**
 * 此servlet显示日志信息
 * */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.LRKZ.Info.Advice_info;
import com.LRKZ.Info.Updatelog_info;
import com.LRKZ.Service.LRKZ_service;
@WebServlet("/ShowUpdateLog")
public class ShowUpdateLog extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowUpdateLog() {
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
		System.out.println("showadvicees start");
        List<Updatelog_info> list=new ArrayList<Updatelog_info>();
		LRKZ_service lrkz_service=new LRKZ_service();
		list=lrkz_service.All_updatelog();
		HttpSession session=request.getSession();
		session.setAttribute("updatelog", list);
		session.setMaxInactiveInterval(200);
		response.sendRedirect("page/log/update_log.jsp");
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
