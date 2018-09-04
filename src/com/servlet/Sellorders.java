package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crud.OrderCRUD;
import com.trade.InProgress;


@WebServlet("/sellorders")
public class Sellorders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		OrderCRUD ordercrud=new OrderCRUD();
		List<InProgress> ordersList=ordercrud.getSellOrders();
		request.setAttribute("ordersList", ordersList);
		
		//showing all the sell orders
		
		RequestDispatcher rd=request.getRequestDispatcher("/sellorders.jsp");
		rd.include(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
