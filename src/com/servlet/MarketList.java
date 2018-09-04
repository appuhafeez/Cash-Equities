package com.servlet;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.crud.LoginDAO;
import com.crud.MarketCRUD;
import com.crud.OrderCRUD;
import com.trade.InProgress;
import com.trade.ShareInfo;

/**
 * Servlet implementation class MarketList
 */
@WebServlet("/market")
public class MarketList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!LoginDAO.checkForLogin(request)) {
			response.sendRedirect("landing.jsp");
		}
		
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("username"); 
		//RandomGenerator rg;
		//rg= RandomGenerator.getNewInstance();
//		OrderCRUD ordercrud=new OrderCRUD();
//		List<InProgress> ordersList=ordercrud.getOrderInfo();
//		request.setAttribute("ordersList", ordersList);
		RequestDispatcher rd=request.getRequestDispatcher("/market.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
