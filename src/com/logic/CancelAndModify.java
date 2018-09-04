package com.logic;

import com.crud.InProgressDAO;
import com.trade.InProgress;

public class CancelAndModify {
	public void cancel(String id) {
		InProgressDAO inProgressDAO =  new InProgressDAO();
		InProgress inProgress = inProgressDAO.getRow(Long.parseLong(id));
		//changing status to cancel
		System.out.println("Cancel class");
		inProgress.setStatus("Can");
		System.out.println(inProgress.getStatus());
		inProgressDAO.addOrUpdate(inProgress);
	}
	
	public void modify(InProgress inProgress) {
		InProgressDAO inProgressDAO = new InProgressDAO();
		InProgress existing = inProgressDAO.getRow(inProgress.getOrderId());
		//changing the modified quantity, price and status
		existing.setStatus("inprogress");
		existing.setRemainingQuantity(inProgress.getQuantity());
		existing.setPriceOfSecurity(inProgress.getPriceOfSecurity());
		existing.setQuantity(inProgress.getQuantity());
		
		// call order matching
		
		inProgressDAO.addOrUpdate(existing);
		
	}
}
