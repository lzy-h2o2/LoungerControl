package com.LRKZ.Servlet;
/**
 * 此servlet显示意见信息
 * */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.LRKZ.Info.Advice_info;
import com.LRKZ.Service.LRKZ_service;
@WebServlet("/ShowAdvcie")
public class ShowAdvice extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public ShowAdvice() {
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

		/*doPost(request, response);*/
		/*request.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8");*/
		System.out.println("showadvicees start");
        List<Advice_info> list=new ArrayList<Advice_info>();
		LRKZ_service lrkz_service=new LRKZ_service();
		list=lrkz_service.All_advice();
		HttpSession session=request.getSession();
		session.setAttribute("advice", list);
		session.setMaxInactiveInterval(200);
		System.out.println("go to jsp");
		response.sendRedirect("page/advice/advice.jsp");
		/*response.sendRedirect("welcome.jsp");*/
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
		doGet(request, response);
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
