package com.servlet;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crud.LoginDAO;
import com.crud.ShareDetailDAO;
import com.logic.PrioriryList;
import com.trade.InProgress;
import com.trade.ShareDetail;
import com.trade.UserDetail;

/**
 * Servlet implementation class company
 */
@WebServlet("/company")
public class company extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!LoginDAO.checkForLogin(request)) {
			response.sendRedirect("landing.jsp");
		}
		System.out.println("display check");
		String stock = request.getParameter("stock");
		String id=request.getParameter("id");
		System.out.println("blah blah blah"+stock);
		PrioriryList prioriryList = new PrioriryList();
		List<InProgress> buylist= prioriryList.buyTopFive(id);
		List<InProgress> selllist=prioriryList.sellTopFive(id);
		
		ShareDetailDAO shareDetailDAO = new ShareDetailDAO();
		
		ShareDetail shareDetail = shareDetailDAO.getStock(id);
		
		
		//displays the quote and stock detail in the company page
		request.setAttribute("shareDetails", shareDetail);
		request.setAttribute("buylist", buylist);
		request.setAttribute("selllist", selllist);
		System.out.println(buylist.size());
		RequestDispatcher dispatcher=request.getRequestDispatcher("/company.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

