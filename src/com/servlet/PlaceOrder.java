package com.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.trade.InProgress;
import com.trade.ShareInfo;
import com.trade.UserStockInfo;
import com.crud.InProgressDAO;
import com.crud.MarketCRUD;
import com.crud.UserStockInfoDAO;
import com.logic.OrderMatching;


@WebServlet("/PlaceOrder")
public class PlaceOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		OrderMatching orderMatching=new OrderMatching();
		InProgress inProgress=new InProgress();
		HttpSession session = request.getSession();
		String userId = session.getAttribute("userid").toString();
		
		String securityCode = request.getParameter("securityCode");
		String direction = request.getParameter("direction");
		long quantity = Long.parseLong(request.getParameter("quantity"));
		String priceOfSecurity = request.getParameter("priceOfSecurity");
//		UserStockInfoDAO userStockInfoDAO = new UserStockInfoDAO();
//		List<UserStockInfo> info1 = userStockInfoDAO.getDetails(userId);
//		boolean fl=false;
//		if(direction.equals("sell")) {
//		for(UserStockInfo temp: info1) {
//			if((temp.getSecurityCode().equals(securityCode) && temp.getTotalQuantity()>=quantity)) {
//				fl=true;
//				System.out.println("u can");
//			}
//		}
//		if(!fl) {
//			session.setAttribute("messages", "not enough stocks available");
//			System.out.println("u cant");
//			response.sendRedirect("market");
//		}
//		}
//			
//			
			
		Timestamp timestamp = new Timestamp(new Date().getTime());
		String currency = "INR";
		
		String status = "Not Exe";
		String tradeType = "Limit"; 
				//request.getParameter("tradeType");
		securityCode=request.getParameter("securityCode");
		MarketCRUD marketCRUD = new MarketCRUD();
		ShareInfo info = (ShareInfo) marketCRUD.getIndiShareInfo2(securityCode);
		
		//setting all the order details to place order
		
		
		inProgress.setCurrency(currency);
		inProgress.setDirection(direction);
		System.out.println(direction);
		System.out.println(inProgress.getDirection()+"   hello");
		inProgress.setPriceOfSecurity(new BigDecimal(priceOfSecurity));
		inProgress.setQuantity(quantity);
		inProgress.setSecurityCode(securityCode);
		inProgress.setSecurityName(info.getSecurityName()+" LTD");
		inProgress.setSecurityType(info.getSecurityType());
		inProgress.setStatus(status);
		inProgress.setTimeStamp(timestamp);
		inProgress.setUserId(userId);
		inProgress.setUserName(session.getAttribute("username").toString());
		inProgress.setRemainingQuantity(quantity);
		//inProgress.setTotalPrice(new BigDecimal((Long.parseLong(priceOfSecurity)*quantity)+""));
		inProgress.setTotalPrice(new BigDecimal(0.00));
		inProgress.setTradeType(tradeType);
		System.out.println("going to save");
		
		
		InProgressDAO save=new InProgressDAO();
		//save.addOrUpdate(inProgress);
		
		
		if(orderMatching.processOrder(inProgress)) {
			System.out.println("found");	
		}else {
			System.out.println("not found");
		}
		
		// redirect
		RequestDispatcher dispatcher=request.getRequestDispatcher("Portfolio");
		dispatcher.forward(request, response);
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
