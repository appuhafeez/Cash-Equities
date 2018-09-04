package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crud.InProgressDAO;
import com.trade.InProgress;


@WebServlet("/ShowModifyPage")
public class ShowModifyPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String orderId = request.getParameter("orderId");
		InProgressDAO inProgressDAO = new InProgressDAO();
		InProgress inProgress = inProgressDAO.getRow(Long.parseLong(orderId));
		
		//showing modified order in inProgress table
		
		request.setAttribute("modify", inProgress);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("modify.jsp");
		dispatcher.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
