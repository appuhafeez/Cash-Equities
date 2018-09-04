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

import com.crud.OrderCRUD;
import com.trade.InProgress;

/**
 * Servlet implementation class IndividualOrder
 */
@WebServlet("/individualorder")
public class IndividualOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String userId=(String) session.getAttribute("userid");
		
		//get individual order list to show values in inprogress as not executed
		OrderCRUD order=new OrderCRUD();
		List<InProgress> indiOrderList=order.getIndividualOrderInfo(userId);	
		request.setAttribute("ordersList", indiOrderList);
		RequestDispatcher rd=request.getRequestDispatcher("/individualorder.jsp");
		rd.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
