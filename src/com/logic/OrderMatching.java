package com.logic;

import java.math.BigDecimal;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.crud.InProgressDAO;
import com.crud.MarketCRUD;
import com.crud.UserDetailDAO;
import com.crud.UserHistoryDAO;
import com.crud.UserStockInfoDAO;
import com.trade.InProgress;
import com.trade.ShareInfo;
import com.trade.UserDetail;
import com.trade.UserHistory;
import com.trade.UserStockInfo;

public class OrderMatching {
	public boolean processOrder(InProgress inProgress) {
		PrioriryList prioriryList=new PrioriryList();

		InProgressDAO inProgressDAO=new InProgressDAO();
		inProgressDAO.addOrUpdate(inProgress);
		// limit order
		List<InProgress> orderList=null;
		if(inProgress.getDirection().equals("buy")) {
			orderList = prioriryList.sell(inProgress.getSecurityCode(),inProgress.getUserId());
		}else {
			orderList = prioriryList.buy(inProgress.getSecurityCode(),inProgress.getUserId());
		}
		System.out.println(orderList.size());
		if(inProgress.getTradeType().equals("Limit")) {
			doLimitMatch(inProgress,orderList);
		}else {
			doMarketOrder(inProgress,orderList);
		}

		return false;
	}
	public boolean doLimitMatch(InProgress current,List<InProgress> orderList) {
		//Pranav Logic
		InProgressDAO inProgressDAO = new InProgressDAO();
		UserHistoryDAO userHistoryDAO = new UserHistoryDAO();
		UserDetailDAO userdetail=new UserDetailDAO();
		UserStockInfoDAO userstockinfo=new UserStockInfoDAO();
		System.out.println("inside limit ");

		//sell part
		if(current.getDirection().equals("sell")) {
			for(int i=0;i<orderList.size();i++) {
				//if money of selling is lesser or equal
				if((orderList.get(i).getPriceOfSecurity().compareTo(current.getPriceOfSecurity())>=0)) {
					// if quantity available is greater
					if(orderList.get(i).getRemainingQuantity()>=current.getRemainingQuantity()) {
						//match done
						BigDecimal totalPrice=orderList.get(i).getPriceOfSecurity().multiply(new BigDecimal(current.getRemainingQuantity()));
						orderList.get(i).setRemainingQuantity(orderList.get(i).getRemainingQuantity()-current.getRemainingQuantity());
						current.setRemainingQuantity(current.getRemainingQuantity()-current.getRemainingQuantity());
						current.setStatus("Exe");
						if(orderList.get(i).getRemainingQuantity()==0) {
							orderList.get(i).setStatus("Exe");
						}else {
							orderList.get(i).setStatus("Par");
						}
						orderList.get(i).add(totalPrice);
						current.add(totalPrice);
						//commit
						inProgressDAO.addOrUpdate(current);
						inProgressDAO.addOrUpdate(orderList.get(i));
						
						//wallet 
						List<UserDetail> userseller=userdetail.getWalletInfo(current.getUserId());
						List<UserDetail> userbuyer=userdetail.getWalletInfo(orderList.get(i).getUserId());
						BigDecimal sellerbalance=userseller.get(0).getWalletBalance();
						sellerbalance=sellerbalance.add(totalPrice);
						userseller.get(0).setWalletBalance(sellerbalance);
						BigDecimal buyerbalance=userbuyer.get(0).getWalletBalance();
						buyerbalance=buyerbalance.subtract(totalPrice);
						userbuyer.get(0).setWalletBalance(buyerbalance);

						userdetail.addOrUpdate(userbuyer.get(0));
						userdetail.addOrUpdate(userseller.get(0));
						
						//stockinfo
						List<UserStockInfo> buyer=userstockinfo.getDetails(orderList.get(i).getUserId(),orderList.get(i).getSecurityCode());
						if(buyer==null) {
							UserStockInfo buyerInfo=new UserStockInfo(orderList.get(i).getUserId(),orderList.get(i).getSecurityName(),orderList.get(i).getSecurityCode(),orderList.get(i).getSecurityType(),current.getRemainingQuantity(),new BigDecimal(0.00));
						    userstockinfo.addOrUpdate(buyerInfo);
						}else {
							long quant=buyer.get(0).getTotalQuantity();
							quant+=current.getRemainingQuantity();
							buyer.get(0).setTotalQuantity(quant);
							userstockinfo.addOrUpdate(buyer.get(0));
						}
						List<UserStockInfo> seller=userstockinfo.getDetails(current.getUserId(),current.getSecurityCode());
						if(seller==null) {
							UserStockInfo sellerInfo=new UserStockInfo(current.getUserId(),current.getSecurityName(),current.getSecurityCode(),current.getSecurityType(),current.getRemainingQuantity(),new BigDecimal(0.00));
						    userstockinfo.addOrUpdate(sellerInfo);
						}else {
							long quant=seller.get(0).getTotalQuantity();
							quant-=current.getRemainingQuantity();
							seller.get(0).setTotalQuantity(quant);
							userstockinfo.addOrUpdate(seller.get(0));
						}
						
						
						
						
						//UserHistory Part
						UserHistory transaction=new UserHistory(orderList.get(i).getUserId(), orderList.get(i).getOrderId(),current.getUserId(),
								current.getOrderId(), current.getSecurityName(), current.getSecurityCode(),
								current.getSecurityType(), current.getQuantity(),orderList.get(i).getPriceOfSecurity(), 
								new Timestamp(new Date().getTime()), current.getCurrency());

						userHistoryDAO.addOrUpdate(transaction);
						UserStockInfo userStockInfo=new UserStockInfo(current.getUserId(),current.getSecurityName(),current.getSecurityCode(),current.getSecurityType(), current.getQuantity(),current.getPriceOfSecurity());
						//userStockInfoDAO.updateStockInfoTable
						UserStockInfoDAO userStockInfoDAO=new UserStockInfoDAO();
						userStockInfoDAO.addOrUpdate(userStockInfo);
						//commited
						return true;
					}else {
						//if the stock quantity is partially available
						BigDecimal totalPrice=orderList.get(i).getPriceOfSecurity().multiply(new BigDecimal(orderList.get(i).getRemainingQuantity()));
						current.setRemainingQuantity(current.getRemainingQuantity()-orderList.get(i).getRemainingQuantity());
						orderList.get(i).setRemainingQuantity(current.getRemainingQuantity()-orderList.get(i).getRemainingQuantity());
						if(orderList.get(i).getRemainingQuantity()==0) {
							orderList.get(i).setStatus("Exe");
						}else {
							orderList.get(i).setStatus("Par");
						}
						if(current.getRemainingQuantity()>0) {
							current.setStatus("Par");
						}else {
							current.setStatus("Exe");
						}
						//since only orderlist quantity is sold
						orderList.get(i).add(totalPrice);
						current.add(totalPrice);
						//commit
						inProgressDAO.addOrUpdate(current);
						inProgressDAO.addOrUpdate(orderList.get(i));
						
						//wallet 
						List<UserDetail> userseller=userdetail.getWalletInfo(current.getUserId());
						List<UserDetail> userbuyer=userdetail.getWalletInfo(orderList.get(i).getUserId());
						BigDecimal sellerbalance=userseller.get(0).getWalletBalance();
						sellerbalance=sellerbalance.add(totalPrice);
						userseller.get(0).setWalletBalance(sellerbalance);
						BigDecimal buyerbalance=userbuyer.get(0).getWalletBalance();
						buyerbalance=buyerbalance.subtract(totalPrice);
						userbuyer.get(0).setWalletBalance(buyerbalance);

						userdetail.addOrUpdate(userbuyer.get(0));
						userdetail.addOrUpdate(userseller.get(0));
						
						//stockinfo
						List<UserStockInfo> buyer=userstockinfo.getDetails(orderList.get(i).getUserId(),orderList.get(i).getSecurityCode());
						if(buyer==null) {
							UserStockInfo buyerInfo=new UserStockInfo(orderList.get(i).getUserId(),orderList.get(i).getSecurityName(),orderList.get(i).getSecurityCode(),orderList.get(i).getSecurityType(),orderList.get(i).getRemainingQuantity(),new BigDecimal(0.00));
						    userstockinfo.addOrUpdate(buyerInfo);
						}else {
							long quant=buyer.get(0).getTotalQuantity();
							quant+=orderList.get(i).getRemainingQuantity();
							buyer.get(0).setTotalQuantity(quant);
							userstockinfo.addOrUpdate(buyer.get(0));
						}
						List<UserStockInfo> seller=userstockinfo.getDetails(current.getUserId(),current.getSecurityCode());
						if(seller==null) {
							UserStockInfo sellerInfo=new UserStockInfo(current.getUserId(),current.getSecurityName(),current.getSecurityCode(),current.getSecurityType(),orderList.get(i).getRemainingQuantity(),new BigDecimal(0.00));
						    userstockinfo.addOrUpdate(sellerInfo);
						}else {
							long quant=seller.get(0).getTotalQuantity();
							quant-=orderList.get(i).getRemainingQuantity();
							seller.get(0).setTotalQuantity(quant);
							userstockinfo.addOrUpdate(seller.get(0));
						}
						
						
						//UserHistory Part
						UserHistory transaction=new UserHistory(orderList.get(i).getUserId(), orderList.get(i).getOrderId(),current.getUserId(),
								current.getOrderId(), current.getSecurityName(), current.getSecurityCode(),
								current.getSecurityType(), current.getQuantity(),orderList.get(i).getPriceOfSecurity(), 
								new Timestamp(new Date().getTime()), current.getCurrency());

						userHistoryDAO.addOrUpdate(transaction);
						UserStockInfo userStockInfo=new UserStockInfo(current.getUserId(),current.getSecurityName(),current.getSecurityCode(),current.getSecurityType(), current.getQuantity(),current.getPriceOfSecurity());
						//userStockInfoDAO.updateStockInfoTable
						UserStockInfoDAO userStockInfoDAO=new UserStockInfoDAO();
						userStockInfoDAO.addOrUpdate(userStockInfo);
						//commited	
					}
				}
			}
		}else {
			System.out.println("buy order");
			//Buy Order 
			for(int i=0;i<orderList.size();i++) {
				System.out.println();
				//if seller willing to sell at low price
				if((orderList.get(i).getPriceOfSecurity().compareTo(current.getPriceOfSecurity())<=0)) {
					System.out.println("value is high");
					// if quantity available is greater
					if(orderList.get(i).getRemainingQuantity()>=current.getRemainingQuantity()) {
						System.out.println("match done");
						//match done
						BigDecimal totalPrice=current.getPriceOfSecurity().multiply(new BigDecimal(current.getRemainingQuantity()));
						orderList.get(i).setRemainingQuantity(orderList.get(i).getRemainingQuantity()-current.getRemainingQuantity());
						current.setRemainingQuantity(current.getRemainingQuantity()-current.getRemainingQuantity());
						current.setStatus("Exe");
						if(orderList.get(i).getRemainingQuantity()==0) {
							orderList.get(i).setStatus("Exe");
						}else {
							orderList.get(i).setStatus("Par");
						}
						orderList.get(i).add(totalPrice);
						current.add(totalPrice);

						//commit
						inProgressDAO.addOrUpdate(current);
						inProgressDAO.addOrUpdate(orderList.get(i));
						
						//wallet 
						List<UserDetail> userbuyer=userdetail.getWalletInfo(current.getUserId());
						List<UserDetail> userseller=userdetail.getWalletInfo(orderList.get(i).getUserId());
						BigDecimal sellerbalance=userseller.get(0).getWalletBalance();
						sellerbalance=sellerbalance.add(totalPrice);
						userseller.get(0).setWalletBalance(sellerbalance);
						BigDecimal buyerbalance=userbuyer.get(0).getWalletBalance();
						buyerbalance=buyerbalance.subtract(totalPrice);
						userbuyer.get(0).setWalletBalance(buyerbalance);

						userdetail.addOrUpdate(userbuyer.get(0));
						userdetail.addOrUpdate(userseller.get(0));
						
						//stockinfo
						List<UserStockInfo> seller=userstockinfo.getDetails(orderList.get(i).getUserId(),orderList.get(i).getSecurityCode());
						List<UserStockInfo> buyer=userstockinfo.getDetails(current.getUserId(),current.getSecurityCode());
						if(buyer==null) {
							UserStockInfo buyerInfo=new UserStockInfo(current.getUserId(),current.getSecurityName(),current.getSecurityCode(),current.getSecurityType(),current.getRemainingQuantity(),new BigDecimal(0.00));
						    userstockinfo.addOrUpdate(buyerInfo);
						}else {
							long quant=buyer.get(0).getTotalQuantity();
							quant+=current.getRemainingQuantity();
							buyer.get(0).setTotalQuantity(quant);
							userstockinfo.addOrUpdate(buyer.get(0));
						}
						if(seller==null) {
						    UserStockInfo sellerInfo=new UserStockInfo(orderList.get(i).getUserId(),orderList.get(i).getSecurityName(),orderList.get(i).getSecurityCode(),orderList.get(i).getSecurityType(),orderList.get(i).getRemainingQuantity(),new BigDecimal(0.00));
						    userstockinfo.addOrUpdate(sellerInfo);
						}else {
							long quant=seller.get(0).getTotalQuantity();
							quant-=orderList.get(i).getRemainingQuantity();
							seller.get(0).setTotalQuantity(quant);
							userstockinfo.addOrUpdate(seller.get(0));
						}

						//UserHistory Part
						UserHistory transaction=new UserHistory(current.getUserId(),
								current.getOrderId(), orderList.get(i).getUserId(), orderList.get(i).getOrderId(),current.getSecurityName(), current.getSecurityCode(),
								current.getSecurityType(), current.getQuantity(),current.getPriceOfSecurity(), 
								new Timestamp(new Date().getTime()), current.getCurrency());

						userHistoryDAO.addOrUpdate(transaction);
						
						UserStockInfo userStockInfo=new UserStockInfo(current.getUserId(),current.getSecurityName(),current.getSecurityCode(),current.getSecurityType(), current.getQuantity(),current.getPriceOfSecurity());
						//userStockInfoDAO.updateStockInfoTable
						UserStockInfoDAO userStockInfoDAO=new UserStockInfoDAO();
						userStockInfoDAO.addOrUpdate(userStockInfo);
						//commited
						
						return true;
						
					}else {
						//if the stock quantity is partially available
						BigDecimal totalPrice=current.getPriceOfSecurity().multiply(new BigDecimal(orderList.get(i).getRemainingQuantity()));
						current.setRemainingQuantity(current.getRemainingQuantity()-orderList.get(i).getRemainingQuantity());
						orderList.get(i).setRemainingQuantity(orderList.get(i).getRemainingQuantity()-orderList.get(i).getRemainingQuantity());
						if(orderList.get(i).getRemainingQuantity()==0) {
							orderList.get(i).setStatus("Exe");
						}else {
							orderList.get(i).setStatus("Par");
						}
						if(current.getRemainingQuantity()>0) {
							current.setStatus("Par");
						}else {
							current.setStatus("Exe");
						}
						//since only orderlist quantity is sold
						orderList.get(i).add(totalPrice);
						current.add(totalPrice);
						//commit
						inProgressDAO.addOrUpdate(current);
						inProgressDAO.addOrUpdate(orderList.get(i));
						
						//wallet 
						List<UserDetail> userseller=userdetail.getWalletInfo(current.getUserId());
						List<UserDetail> userbuyer=userdetail.getWalletInfo(orderList.get(i).getUserId());
						BigDecimal sellerbalance=userseller.get(0).getWalletBalance();
						sellerbalance=sellerbalance.add(totalPrice);
						userseller.get(0).setWalletBalance(sellerbalance);
						BigDecimal buyerbalance=userbuyer.get(0).getWalletBalance();
						buyerbalance=buyerbalance.subtract(totalPrice);
						userbuyer.get(0).setWalletBalance(buyerbalance);

						userdetail.addOrUpdate(userbuyer.get(0));
						userdetail.addOrUpdate(userseller.get(0));
						
						//stockinfo
						List<UserStockInfo> seller=userstockinfo.getDetails(orderList.get(i).getUserId(),orderList.get(i).getSecurityCode());
						List<UserStockInfo> buyer=userstockinfo.getDetails(current.getUserId(),current.getSecurityCode());

						if(buyer==null) {
							UserStockInfo buyerInfo=new UserStockInfo(current.getUserId(),current.getSecurityName(),current.getSecurityCode(),current.getSecurityType(),orderList.get(i).getRemainingQuantity(),new BigDecimal(0.00));
						    userstockinfo.addOrUpdate(buyerInfo);
						}else {
							long quant=buyer.get(0).getTotalQuantity();
							quant+=orderList.get(i).getRemainingQuantity();
							buyer.get(0).setTotalQuantity(quant);
							userstockinfo.addOrUpdate(buyer.get(0));
						}
						if(seller==null) {
							
						    UserStockInfo sellerInfo=new UserStockInfo(orderList.get(i).getUserId(),orderList.get(i).getSecurityName(),orderList.get(i).getSecurityCode(),orderList.get(i).getSecurityType(),orderList.get(i).getRemainingQuantity(),new BigDecimal(0.00));
						    userstockinfo.addOrUpdate(sellerInfo);
						}else {
							long quant=seller.get(0).getTotalQuantity();
							quant-=orderList.get(i).getRemainingQuantity();
							seller.get(0).setTotalQuantity(quant);
							userstockinfo.addOrUpdate(seller.get(0));
						}
						
						
						//UserHistory Part
						UserHistory transaction=new UserHistory(orderList.get(i).getUserId(), orderList.get(i).getOrderId(),current.getUserId(),
								current.getOrderId(), current.getSecurityName(), current.getSecurityCode(),
								current.getSecurityType(), orderList.get(i).getQuantity(),current.getPriceOfSecurity(), 
								new Timestamp(new Date().getTime()), current.getCurrency());

						userHistoryDAO.addOrUpdate(transaction);
						
						UserStockInfo userStockInfo=new UserStockInfo(current.getUserId(),current.getSecurityName(),current.getSecurityCode(),current.getSecurityType(), current.getQuantity(),current.getPriceOfSecurity());
						//userStockInfoDAO.updateStockInfoTable
						UserStockInfoDAO userStockInfoDAO=new UserStockInfoDAO();
						userStockInfoDAO.addOrUpdate(userStockInfo);
						//commited	
					}
				}
			}
		}





		//Hafeez Logic
		/*InProgressDAO inProgressDAO = new InProgressDAO();
		UserHistoryDAO userHistoryDAO = new UserHistoryDAO();
		if(current.getDirection().equals("sell")) {
			for(int i=0;i<orderList.size();i++) {
				if(!(orderList.get(i).getPriceOfSecurity().compareTo(current.getPriceOfSecurity())<=0)) {
					if(i!=0) {
						if(orderList.get(i-1).getQuantity()>current.getQuantity()) {
							// complete order
							inProgressDAO.delete(orderList.get(i-1));
							UserHistory userHistory= 

	new UserHistory(orderList.get(i-1).getUserId(), orderList.get(i-1).getOrderId()+"", current.getUserId(),
		current.getOrderId()+"", current.getSecurityName(), current.getSecurityCode(),
		current.getSecurityType(), current.getQuantity(),new BigDecimal(current.getCurrency()), 
		new Timestamp(new Date().getTime()), current.getCurrency());
							userHistoryDAO.addOrUpdate(userHistory);

							InProgress temp = orderList.get(i-1);
							temp.setQuantity(temp.getQuantity()-current.getQuantity());
							inProgressDAO.addOrUpdate(temp);


							return true;
						}else if(orderList.get(i-1).getQuantity()==current.getQuantity()) {
							// complete order
							// inProgressDAO.delete(orderList.get(i-1));
							orderList.get(i-1).setStatus("completed");
							inProgressDAO.addOrUpdate(orderList.get(i-1));
							UserHistory userHistory= 

	new UserHistory(orderList.get(i-1).getUserId(), orderList.get(i-1).getOrderId()+"", current.getUserId(),
		current.getOrderId()+"", current.getSecurityName(), current.getSecurityCode(),
		current.getSecurityType(), current.getQuantity(),new BigDecimal(current.getCurrency()), 
		new Timestamp(new Date().getTime()), current.getCurrency());
							userHistoryDAO.addOrUpdate(userHistory);

							return true;
						}else {
							// partial

						}
					}
				}
			}
		}else {
			for(int i=0;i<orderList.size();i++) {
				if(orderList.get(i).getPriceOfSecurity().compareTo(current.getPriceOfSecurity())>=0) {
					if(i!=0) {
						if(orderList.get(i-1).getQuantity()>=current.getQuantity()) {
							if(i!=0) {
								// selling code

								if(orderList.get(i-1).getQuantity()>current.getQuantity()) {
									// complete order
									inProgressDAO.delete(orderList.get(i-1));
									UserHistory userHistory= 

			new UserHistory(orderList.get(i-1).getUserId(), orderList.get(i-1).getOrderId()+"", current.getUserId(),
				current.getOrderId()+"", current.getSecurityName(), current.getSecurityCode(),
				current.getSecurityType(), current.getQuantity(),new BigDecimal(current.getCurrency()), 
				new Timestamp(new Date().getTime()), current.getCurrency());
									userHistoryDAO.addOrUpdate(userHistory);

									InProgress temp = orderList.get(i-1);
									temp.setQuantity(temp.getQuantity()-current.getQuantity());
									inProgressDAO.addOrUpdate(temp);


									return true;
								}else if(orderList.get(i-1).getQuantity()==current.getQuantity()) {
									// complete order
									inProgressDAO.delete(orderList.get(i-1));
									UserHistory userHistory= 

			new UserHistory(orderList.get(i-1).getUserId(), orderList.get(i-1).getOrderId()+"", current.getUserId(),
				current.getOrderId()+"", current.getSecurityName(), current.getSecurityCode(),
				current.getSecurityType(), current.getQuantity(),new BigDecimal(current.getCurrency()), 
				new Timestamp(new Date().getTime()), current.getCurrency());
									userHistoryDAO.addOrUpdate(userHistory);

									return true;
								}else {
									// partial
								}
							}
							return true;
						}else {
							// partial
						}
					}else {

					}
				}
			}
		}*/

		return false;
	}
	public boolean doMarketOrder(InProgress current,List<InProgress> orderList) {
		InProgressDAO inProgressDAO=new InProgressDAO();
		UserHistoryDAO userHistoryDAO=new UserHistoryDAO();
		UserDetailDAO userdetail=new UserDetailDAO();
		UserStockInfoDAO userstockinfo=new UserStockInfoDAO();
		MarketCRUD market=new MarketCRUD();
		ShareInfo share=new ShareInfo();

		for(int i=0;i<orderList.size();i++) {
			share =market.getIndiShareInfo(current.getSecurityName());
			if(orderList.get(i).getRemainingQuantity()==current.getRemainingQuantity()) {
				current.setStatus("Exe");
				orderList.get(i).setStatus("Exe");
				BigDecimal totalPrice=share.getPriceOfSecurity().multiply(new BigDecimal(orderList.get(i).getRemainingQuantity()));
				current.setRemainingQuantity(current.getRemainingQuantity());
				orderList.get(i).setRemainingQuantity(current.getRemainingQuantity());
				orderList.get(i).setPriceOfSecurity(share.getPriceOfSecurity());
				current.setPriceOfSecurity(share.getPriceOfSecurity());
				orderList.get(i).add(totalPrice);
				current.add(totalPrice);

				inProgressDAO.addOrUpdate(current);
				inProgressDAO.addOrUpdate(orderList.get(i));

				if(current.getDirection().equals("buy")) {
					//wallet 
					List<UserDetail> userbuyer=userdetail.getWalletInfo(current.getUserId());
					List<UserDetail> userseller=userdetail.getWalletInfo(orderList.get(i).getUserId());
					BigDecimal sellerbalance=userseller.get(0).getWalletBalance();
					sellerbalance=sellerbalance.add(totalPrice);
					userseller.get(0).setWalletBalance(sellerbalance);
					BigDecimal buyerbalance=userbuyer.get(0).getWalletBalance();
					buyerbalance=buyerbalance.subtract(totalPrice);
					userbuyer.get(0).setWalletBalance(buyerbalance);

					userdetail.addOrUpdate(userbuyer.get(0));
					userdetail.addOrUpdate(userseller.get(0));
					
					//stockinfo
					List<UserStockInfo> seller=userstockinfo.getDetails(orderList.get(i).getUserId(),orderList.get(i).getSecurityCode());
					List<UserStockInfo> buyer=userstockinfo.getDetails(current.getUserId(),current.getSecurityCode());
					if(buyer==null) {
						UserStockInfo buyerInfo=new UserStockInfo(current.getUserId(),current.getSecurityName(),current.getSecurityCode(),current.getSecurityType(),current.getRemainingQuantity(),new BigDecimal(0.00));
					    userstockinfo.addOrUpdate(buyerInfo);
					}else {
						long quant=buyer.get(0).getTotalQuantity();
						quant+=current.getRemainingQuantity();
						buyer.get(0).setTotalQuantity(quant);
						userstockinfo.addOrUpdate(buyer.get(0));
					}
					if(seller==null) {
					    UserStockInfo sellerInfo=new UserStockInfo(orderList.get(i).getUserId(),orderList.get(i).getSecurityName(),orderList.get(i).getSecurityCode(),orderList.get(i).getSecurityType(),orderList.get(i).getRemainingQuantity(),new BigDecimal(0.00));
					    userstockinfo.addOrUpdate(sellerInfo);
					}else {
						long quant=seller.get(0).getTotalQuantity();
						quant-=orderList.get(i).getRemainingQuantity();
						seller.get(0).setTotalQuantity(quant);
						userstockinfo.addOrUpdate(seller.get(0));
					}

					UserHistory userHistory=
							new UserHistory( current.getUserId(),current.getOrderId(),orderList.get(i).getUserId(),
									orderList.get(i).getOrderId(), current.getSecurityName(), current.getSecurityCode(),
									current.getSecurityType(), current.getQuantity(),current.getPriceOfSecurity(), 
									new Timestamp(new Date().getTime()), current.getCurrency());

					userHistoryDAO.addOrUpdate(userHistory);
				}else {
					//wallet 
					List<UserDetail> userseller=userdetail.getWalletInfo(current.getUserId());
					List<UserDetail> userbuyer=userdetail.getWalletInfo(orderList.get(i).getUserId());
					BigDecimal sellerbalance=userseller.get(0).getWalletBalance();
					sellerbalance=sellerbalance.add(totalPrice);
					userseller.get(0).setWalletBalance(sellerbalance);
					BigDecimal buyerbalance=userbuyer.get(0).getWalletBalance();
					buyerbalance=buyerbalance.subtract(totalPrice);
					userbuyer.get(0).setWalletBalance(buyerbalance);

					userdetail.addOrUpdate(userbuyer.get(0));
					userdetail.addOrUpdate(userseller.get(0));
					
					//stockinfo
					List<UserStockInfo> buyer=userstockinfo.getDetails(orderList.get(i).getUserId(),orderList.get(i).getSecurityCode());
					if(buyer==null) {
						UserStockInfo buyerInfo=new UserStockInfo(orderList.get(i).getUserId(),orderList.get(i).getSecurityName(),orderList.get(i).getSecurityCode(),orderList.get(i).getSecurityType(),current.getRemainingQuantity(),new BigDecimal(0.00));
					    userstockinfo.addOrUpdate(buyerInfo);
					}else {
						long quant=buyer.get(0).getTotalQuantity();
						quant+=current.getRemainingQuantity();
						buyer.get(0).setTotalQuantity(quant);
						userstockinfo.addOrUpdate(buyer.get(0));
					}
					List<UserStockInfo> seller=userstockinfo.getDetails(current.getUserId(),current.getSecurityCode());
					if(seller==null) {
						UserStockInfo sellerInfo=new UserStockInfo(current.getUserId(),current.getSecurityName(),current.getSecurityCode(),current.getSecurityType(),current.getRemainingQuantity(),new BigDecimal(0.00));
					    userstockinfo.addOrUpdate(sellerInfo);
					}else {
						long quant=seller.get(0).getTotalQuantity();
						quant-=current.getRemainingQuantity();
						seller.get(0).setTotalQuantity(quant);
						userstockinfo.addOrUpdate(seller.get(0));
					}
					UserHistory userHistory=
							new UserHistory(orderList.get(i).getUserId(), orderList.get(i).getOrderId(), current.getUserId(),
									current.getOrderId(), current.getSecurityName(), current.getSecurityCode(),
									current.getSecurityType(), current.getQuantity(),current.getPriceOfSecurity(), 
									new Timestamp(new Date().getTime()), current.getCurrency());

					userHistoryDAO.addOrUpdate(userHistory);
				}
				return true;
			}else if(orderList.get(i).getRemainingQuantity()>current.getRemainingQuantity()) {
				current.setStatus("Exe");
				orderList.get(i).setStatus("Par");
				BigDecimal totalPrice=share.getPriceOfSecurity().multiply(new BigDecimal(current.getRemainingQuantity()));
				orderList.get(i).setRemainingQuantity(current.getRemainingQuantity());
				current.setRemainingQuantity(current.getRemainingQuantity());
				orderList.get(i).setPriceOfSecurity(share.getPriceOfSecurity());
				current.setPriceOfSecurity(share.getPriceOfSecurity());
				orderList.get(i).add(totalPrice);
				current.add(totalPrice);
				inProgressDAO.addOrUpdate(current);
				// inProgressDAO.addOrUpdate(orderList.get(i));

				inProgressDAO.addOrUpdate(orderList.get(i));

				if(current.getDirection().equals("buy")) {
					//wallet 
					List<UserDetail> userbuyer=userdetail.getWalletInfo(current.getUserId());
					List<UserDetail> userseller=userdetail.getWalletInfo(orderList.get(i).getUserId());
					BigDecimal sellerbalance=userseller.get(0).getWalletBalance();
					sellerbalance=sellerbalance.add(totalPrice);
					userseller.get(0).setWalletBalance(sellerbalance);
					BigDecimal buyerbalance=userbuyer.get(0).getWalletBalance();
					buyerbalance=buyerbalance.subtract(totalPrice);
					userbuyer.get(0).setWalletBalance(buyerbalance);

					userdetail.addOrUpdate(userbuyer.get(0));
					userdetail.addOrUpdate(userseller.get(0));
					
					//stockinfo
					List<UserStockInfo> seller=userstockinfo.getDetails(orderList.get(i).getUserId(),orderList.get(i).getSecurityCode());
					List<UserStockInfo> buyer=userstockinfo.getDetails(current.getUserId(),current.getSecurityCode());
					if(buyer==null) {
						UserStockInfo buyerInfo=new UserStockInfo(current.getUserId(),current.getSecurityName(),current.getSecurityCode(),current.getSecurityType(),current.getRemainingQuantity(),new BigDecimal(0.00));
					    userstockinfo.addOrUpdate(buyerInfo);
					}else {
						long quant=buyer.get(0).getTotalQuantity();
						quant+=current.getRemainingQuantity();
						buyer.get(0).setTotalQuantity(quant);
						userstockinfo.addOrUpdate(buyer.get(0));
					}
					if(seller==null) {
					    UserStockInfo sellerInfo=new UserStockInfo(orderList.get(i).getUserId(),orderList.get(i).getSecurityName(),orderList.get(i).getSecurityCode(),orderList.get(i).getSecurityType(),orderList.get(i).getRemainingQuantity(),new BigDecimal(0.00));
					    userstockinfo.addOrUpdate(sellerInfo);
					}else {
						long quant=seller.get(0).getTotalQuantity();
						quant-=orderList.get(i).getRemainingQuantity();
						seller.get(0).setTotalQuantity(quant);
						userstockinfo.addOrUpdate(seller.get(0));
					}

					UserHistory userHistory=
							new UserHistory( current.getUserId(),current.getOrderId(),orderList.get(i).getUserId(),
									orderList.get(i).getOrderId(), current.getSecurityName(), current.getSecurityCode(),
									current.getSecurityType(), current.getQuantity(),current.getPriceOfSecurity(), 
									new Timestamp(new Date().getTime()), current.getCurrency());

					userHistoryDAO.addOrUpdate(userHistory);
				}else {
					//wallet 
					List<UserDetail> userseller=userdetail.getWalletInfo(current.getUserId());
					List<UserDetail> userbuyer=userdetail.getWalletInfo(orderList.get(i).getUserId());
					BigDecimal sellerbalance=userseller.get(0).getWalletBalance();
					sellerbalance=sellerbalance.add(totalPrice);
					userseller.get(0).setWalletBalance(sellerbalance);
					BigDecimal buyerbalance=userbuyer.get(0).getWalletBalance();
					buyerbalance=buyerbalance.subtract(totalPrice);
					userbuyer.get(0).setWalletBalance(buyerbalance);

					userdetail.addOrUpdate(userbuyer.get(0));
					userdetail.addOrUpdate(userseller.get(0));
					
					//stockinfo
					List<UserStockInfo> buyer=userstockinfo.getDetails(orderList.get(i).getUserId(),orderList.get(i).getSecurityCode());
					if(buyer==null) {
						UserStockInfo buyerInfo=new UserStockInfo(orderList.get(i).getUserId(),orderList.get(i).getSecurityName(),orderList.get(i).getSecurityCode(),orderList.get(i).getSecurityType(),current.getRemainingQuantity(),new BigDecimal(0.00));
					    userstockinfo.addOrUpdate(buyerInfo);
					}else {
						long quant=buyer.get(0).getTotalQuantity();
						quant+=current.getRemainingQuantity();
						buyer.get(0).setTotalQuantity(quant);
						userstockinfo.addOrUpdate(buyer.get(0));
					}
					List<UserStockInfo> seller=userstockinfo.getDetails(current.getUserId(),current.getSecurityCode());
					if(seller==null) {
						UserStockInfo sellerInfo=new UserStockInfo(current.getUserId(),current.getSecurityName(),current.getSecurityCode(),current.getSecurityType(),current.getRemainingQuantity(),new BigDecimal(0.00));
					    userstockinfo.addOrUpdate(sellerInfo);
					}else {
						long quant=seller.get(0).getTotalQuantity();
						quant-=current.getRemainingQuantity();
						seller.get(0).setTotalQuantity(quant);
						userstockinfo.addOrUpdate(seller.get(0));
					}
					UserHistory userHistory=
							new UserHistory(orderList.get(i).getUserId(), orderList.get(i).getOrderId(), current.getUserId(),
									current.getOrderId(), current.getSecurityName(), current.getSecurityCode(),
									current.getSecurityType(), current.getQuantity(),current.getPriceOfSecurity(), 
									new Timestamp(new Date().getTime()), current.getCurrency());

					userHistoryDAO.addOrUpdate(userHistory);
				}
				return true;
			} else {
				current.setStatus("Par");
				orderList.get(i).setStatus("Exe");
				
				BigDecimal totalPrice=share.getPriceOfSecurity().multiply(new BigDecimal(orderList.get(i).getRemainingQuantity()));
				orderList.get(i).setRemainingQuantity(orderList.get(i).getRemainingQuantity());
				current.setRemainingQuantity(orderList.get(i).getRemainingQuantity());
				orderList.get(i).setPriceOfSecurity(share.getPriceOfSecurity());
				current.setPriceOfSecurity(share.getPriceOfSecurity());
				orderList.get(i).add(totalPrice);
				current.add(totalPrice);
				inProgressDAO.addOrUpdate(current);
				// inProgressDAO.addOrUpdate(orderList.get(i));
				inProgressDAO.addOrUpdate(orderList.get(i));

				if(current.getDirection().equals("buy")) {
					
					//wallet 
					List<UserDetail> userseller=userdetail.getWalletInfo(current.getUserId());
					List<UserDetail> userbuyer=userdetail.getWalletInfo(orderList.get(i).getUserId());
					BigDecimal sellerbalance=userseller.get(0).getWalletBalance();
					sellerbalance=sellerbalance.add(totalPrice);
					userseller.get(0).setWalletBalance(sellerbalance);
					BigDecimal buyerbalance=userbuyer.get(0).getWalletBalance();
					buyerbalance=buyerbalance.subtract(totalPrice);
					userbuyer.get(0).setWalletBalance(buyerbalance);

					userdetail.addOrUpdate(userbuyer.get(0));
					userdetail.addOrUpdate(userseller.get(0));
					
					//stockinfo
					List<UserStockInfo> seller=userstockinfo.getDetails(orderList.get(i).getUserId(),orderList.get(i).getSecurityCode());
					List<UserStockInfo> buyer=userstockinfo.getDetails(current.getUserId(),current.getSecurityCode());

					if(buyer==null) {
						UserStockInfo buyerInfo=new UserStockInfo(current.getUserId(),current.getSecurityName(),current.getSecurityCode(),current.getSecurityType(),orderList.get(i).getRemainingQuantity(),new BigDecimal(0.00));
					    userstockinfo.addOrUpdate(buyerInfo);
					}else {
						long quant=buyer.get(0).getTotalQuantity();
						quant+=orderList.get(i).getRemainingQuantity();
						buyer.get(0).setTotalQuantity(quant);
						userstockinfo.addOrUpdate(buyer.get(0));
					}
					if(seller==null) {
						
					    UserStockInfo sellerInfo=new UserStockInfo(orderList.get(i).getUserId(),orderList.get(i).getSecurityName(),orderList.get(i).getSecurityCode(),orderList.get(i).getSecurityType(),orderList.get(i).getRemainingQuantity(),new BigDecimal(0.00));
					    userstockinfo.addOrUpdate(sellerInfo);
					}else {
						long quant=seller.get(0).getTotalQuantity();
						quant-=orderList.get(i).getRemainingQuantity();
						seller.get(0).setTotalQuantity(quant);
						userstockinfo.addOrUpdate(seller.get(0));
					}
					UserHistory userHistory=
							new UserHistory( current.getUserId(),current.getOrderId(),orderList.get(i).getUserId(),
									orderList.get(i).getOrderId(), current.getSecurityName(), current.getSecurityCode(),
									current.getSecurityType(), current.getQuantity(),current.getPriceOfSecurity(), 
									new Timestamp(new Date().getTime()), current.getCurrency());

					userHistoryDAO.addOrUpdate(userHistory);
				}else {
					//wallet 
					List<UserDetail> userseller=userdetail.getWalletInfo(current.getUserId());
					List<UserDetail> userbuyer=userdetail.getWalletInfo(orderList.get(i).getUserId());
					BigDecimal sellerbalance=userseller.get(0).getWalletBalance();
					sellerbalance=sellerbalance.add(totalPrice);
					userseller.get(0).setWalletBalance(sellerbalance);
					BigDecimal buyerbalance=userbuyer.get(0).getWalletBalance();
					buyerbalance=buyerbalance.subtract(totalPrice);
					userbuyer.get(0).setWalletBalance(buyerbalance);

					userdetail.addOrUpdate(userbuyer.get(0));
					userdetail.addOrUpdate(userseller.get(0));
					
					//stockinfo
					List<UserStockInfo> buyer=userstockinfo.getDetails(orderList.get(i).getUserId(),orderList.get(i).getSecurityCode());
					if(buyer==null) {
						UserStockInfo buyerInfo=new UserStockInfo(orderList.get(i).getUserId(),orderList.get(i).getSecurityName(),orderList.get(i).getSecurityCode(),orderList.get(i).getSecurityType(),orderList.get(i).getRemainingQuantity(),new BigDecimal(0.00));
					    userstockinfo.addOrUpdate(buyerInfo);
					}else {
						long quant=buyer.get(0).getTotalQuantity();
						quant+=orderList.get(i).getRemainingQuantity();
						buyer.get(0).setTotalQuantity(quant);
						userstockinfo.addOrUpdate(buyer.get(0));
					}
					List<UserStockInfo> seller=userstockinfo.getDetails(current.getUserId(),current.getSecurityCode());
					if(seller==null) {
						UserStockInfo sellerInfo=new UserStockInfo(current.getUserId(),current.getSecurityName(),current.getSecurityCode(),current.getSecurityType(),orderList.get(i).getRemainingQuantity(),new BigDecimal(0.00));
					    userstockinfo.addOrUpdate(sellerInfo);
					}else {
						long quant=seller.get(0).getTotalQuantity();
						quant-=orderList.get(i).getRemainingQuantity();
						seller.get(0).setTotalQuantity(quant);
						userstockinfo.addOrUpdate(seller.get(0));
					}
					UserHistory userHistory=
							new UserHistory(orderList.get(i).getUserId(), orderList.get(i).getOrderId(), current.getUserId(),
									current.getOrderId(), current.getSecurityName(), current.getSecurityCode(),
									current.getSecurityType(), current.getQuantity(),current.getPriceOfSecurity(), 
									new Timestamp(new Date().getTime()), current.getCurrency());

					userHistoryDAO.addOrUpdate(userHistory);
				}
				return true;
			}
		}
		return false;
	}
}
