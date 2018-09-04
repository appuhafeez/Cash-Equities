package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crud.MarketCRUD;
import com.trade.ShareInfo;


@WebServlet("/ticker")
public class Ticker extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		MarketCRUD marketcrud=new MarketCRUD();
		List<ShareInfo> securityList=marketcrud.getShareInfo();
		request.setAttribute("securityList", securityList);
		
		//showing stock list in the scrolling ticker
		RequestDispatcher rd=request.getRequestDispatcher("/scrollticker.jsp");
		rd.include(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
