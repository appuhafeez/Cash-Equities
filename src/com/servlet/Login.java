package com.servlet;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.crud.LoginDAO;
import com.trade.User;



/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher=request.getRequestDispatcher("landing.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		response.setContentType("html/text");
		System.out.println("Login");
		LoginDAO dao=new LoginDAO();
		if(request!=null) {
			System.out.println(" request not null ");
			String userid=request.getParameter("userid");
			String password=request.getParameter("password");
			User loginDetails=null;
			if(userid!=null && password!=null) {
				System.out.println(" inside user");
				System.out.println(userid+" | "+password);
				loginDetails= dao.doLogin(userid,password);
				if(loginDetails!=null) {
					// return "index";
					HttpSession session=request.getSession();
					session.setAttribute("userid", loginDetails.getUserId());
					session.setAttribute("username", loginDetails.getUserName());
//					RequestDispatcher dispatcher=request.getRequestDispatcher("market.jsp");
//					dispatcher.forward(request, response);
					
					response.sendRedirect("market");
				}else {
					RequestDispatcher dispatcher=request.getRequestDispatcher("landing.jsp");
					dispatcher.forward(request, response);
				}
			}else {
				HttpSession session=request.getSession();
				session.setAttribute("userid", loginDetails.getUserId());
				session.setAttribute("username", loginDetails.getUserName());
				RequestDispatcher dispatcher=request.getRequestDispatcher("landing.jsp");
				dispatcher.forward(request, response);
			}

		}
		else {
		RequestDispatcher dispatcher=request.getRequestDispatcher("landing.jsp");
		dispatcher.forward(request, response);
		}
	}

}
