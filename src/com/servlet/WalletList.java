package com.servlet;


import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.osgi.application.ApplicationContext;

import com.crud.UserDetailDAO;
import com.crud.WalletCRUD;
import com.trade.UserDetail;
import com.trade.Wallet;

@WebServlet("/walletlist")
public class WalletList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	double amount=0.0;
	BigDecimal netamount=new BigDecimal(0.0);
	String buttonType;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request,response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("blah blah");
		HttpSession session=request.getSession();
		String userId= (String) session.getAttribute("userid");
		System.out.println("checking for user"+userId);

		UserDetailDAO userDetailDao=new UserDetailDAO();
		List<UserDetail> userDetail=userDetailDao.getWalletInfo(userId);
		WalletCRUD walletcrud1=new WalletCRUD();
		
		
		List<Wallet> walletList=walletcrud1.getWalletInfo(userId);
		request.setAttribute("walletList", walletList);
		RequestDispatcher rd=request.getRequestDispatcher("/wallet.jsp");
		rd.forward(request, response);
	
		System.out.println(userDetail);

		
		session.setAttribute("availableBalance", userDetail.get(0).getWalletBalance());
		

		System.out.println("check"+request.getParameter("amount_deposit"));
		System.out.println("check"+request.getParameter("amount_withdraw"));

		if(request.getParameter("amount_deposit")!=null) {
		 amount=Double.parseDouble(request.getParameter("amount_deposit"));
		 netamount = new BigDecimal(request.getParameter("netamount_deposit"));
		 System.out.println(amount + " first!!"+netamount);
		 request.setAttribute("amount_deposit", amount);
		 request.setAttribute("netamount_deposit", netamount);
		 System.out.println(userDetail.get(0).getUserId()+"  "+userDetail.get(0).getWalletBalance()+"  "+userDetail.get(0).getFullName());
		
		 
		 userDetail.get(0).setWalletBalance(userDetail.get(0).getWalletBalance().add(netamount));
		 session.setAttribute("availableBalance", userDetail.get(0).getWalletBalance());
		 System.out.println(userDetail.get(0).getWalletBalance());
		 userDetailDao.addOrUpdate(userDetail.get(0));
		 
		 
		 Wallet w =new Wallet(userDetail.get(0).getAccountNumber(),userDetail.get(0).getUserId(),userDetail.get(0).getFullName(),"deposit",new Timestamp(new Date().getTime()),netamount);
		 WalletCRUD walletCrud=new WalletCRUD();
		 walletCrud.addOrUpdate(w);
		 
//		 List<Wallet> walletList1=walletCrud.getWalletInfo(userId);
//			request.setAttribute("walletList", walletList1);
		 
		request.setAttribute("availableBalance", userDetail.get(0).getWalletBalance());
//		response.setIntHeader("Refresh", 5);
		RequestDispatcher rd1=request.getRequestDispatcher("/wallet.jsp");
		rd1.forward(request, response);
	
	
		}
		else if(request.getParameter("amount_withdraw")!=null) {
			 amount=Double.parseDouble(request.getParameter("amount_withdraw"));
			 netamount = new BigDecimal(request.getParameter("netamount_withdraw"));
			 System.out.println(amount + " second!!"+netamount);
			 request.setAttribute("amount_withdraw", amount);
			 request.setAttribute("netamount_withdraw", netamount);
			 if(netamount.compareTo(userDetail.get(0).getWalletBalance())==-1) {
				 System.out.println("Inside withdraw");
				 System.out.println(userDetail.get(0).getWalletBalance());
				 userDetail.get(0).setWalletBalance(userDetail.get(0).getWalletBalance().subtract(netamount));
				 System.out.println(userDetail.get(0).getWalletBalance());
				 userDetailDao.addOrUpdate(userDetail.get(0));
				 
				 Wallet w =new Wallet(userDetail.get(0).getAccountNumber(),userDetail.get(0).getUserId(),userDetail.get(0).getFullName(),"withdraw",new Timestamp(new Date().getTime()),netamount);
				 WalletCRUD walletCrud=new WalletCRUD();
				 walletCrud.addOrUpdate(w);
//				 
//				 List<Wallet> walletList1=walletCrud.getWalletInfo(userId);
//					request.setAttribute("walletList", walletList1);
				 session.setAttribute("availableBalance", userDetail.get(0).getWalletBalance());
	//			 request.setAttribute("availableBalance", userDetail.get(0).getWalletBalance());
//					response.setIntHeader("Refresh", 5);
				 RequestDispatcher rd1=request.getRequestDispatcher("/wallet.jsp");
					rd1.forward(request, response);
				
			 }
			 
			}
			

	}

}
