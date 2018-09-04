package com.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crud.MarketCRUD;
import com.crud.ShareDetailDAO;
import com.logic.GainerLoser;
import com.trade.ShareDetail;
import com.trade.ShareInfo;

/**
 * Servlet implementation class GainLose
 */
@WebServlet("/gainloss")
public class GainLose extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		MarketCRUD market=new MarketCRUD();
		List<ShareInfo> share=market.getShareInfo();
		ShareDetailDAO sharedetail=new ShareDetailDAO();
		List<ShareDetail> detail=sharedetail.getShareDetail();
		System.out.println("starting to gain");
		System.out.println(share.size()+""+detail.size());
		GainerLoser gainlose=new GainerLoser();
		List<Integer> order=gainlose.comparison(share,detail);
		request.setAttribute("max1", share.get(order.get(0)).getSecurityName());
		request.setAttribute("max2", share.get(order.get(1)).getSecurityName());
		request.setAttribute("min1", share.get(order.get(2)).getSecurityName());
		request.setAttribute("min2", share.get(order.get(3)).getSecurityName());
		
		
		//tejaswi's code 
		
		List<BigDecimal> PercentageList=new ArrayList<BigDecimal>();
		for(int i=0;i<4;i++) {
		BigDecimal start=detail.get(order.get(i)).getOpen();
		BigDecimal current= share.get(order.get(i)).getPriceOfSecurity();
		BigDecimal percentage=gainlose.calculatePercentage(start,current);
		PercentageList.add(percentage);
		}
		
		request.setAttribute("maxpercentage1", PercentageList.get(0));
		request.setAttribute("maxpercentage2",  PercentageList.get(1));
		request.setAttribute("minpercentage1",  PercentageList.get(2));
		request.setAttribute("minpercentage2",  PercentageList.get(3));
		
		System.out.println("sending to gain");
		
		RequestDispatcher rd=request.getRequestDispatcher("/GainLose.jsp");
		rd.include(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
