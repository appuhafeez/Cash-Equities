package com.crud;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.trade.InProgress;


public class OrderCRUD {

	private static Transaction transaction = null;
	private static Session session = null;
	static Configuration config=null; 
	static SessionFactory factory=null;
	public OrderCRUD() {

		config= new Configuration().configure("hibernate.cfg.xml");
		factory = config.buildSessionFactory();
		 session = factory.openSession();
		 transaction = session.beginTransaction();
	}
	
	public List<InProgress> getOrderInfo() {
		List<InProgress> orderinfo = null;
		try {
			 //orderinfo= session.createQuery("from InProgress").list();	
			 orderinfo=session.createCriteria(InProgress.class)
					 .addOrder(Order.desc("timeStamp"))
					 .add(Restrictions.like("status", "Exe")).list();
			 
			 
		}catch(Exception e) {
			System.out.println(e);
		}
		return orderinfo;
	}
	
	public List<InProgress> getBuyOrders() {
		List<InProgress> orderinfo = null;
		try {
			 //orderinfo= session.createQuery("from InProgress").list();	
			 orderinfo=session.createCriteria(InProgress.class)
					 .addOrder(Order.desc("timeStamp"))
					 .add(Restrictions.like("status", "Not Exe"))
					 .add(Restrictions.like("direction", "Buy")).list();
			 System.out.println(orderinfo.size());
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return orderinfo;
	}
	
	public List<InProgress> getSellOrders() {
		List<InProgress> orderinfo = null;
		try {
			 //orderinfo= session.createQuery("from InProgress").list();	
			 orderinfo=session.createCriteria(InProgress.class)
					 .addOrder(Order.desc("timeStamp"))
					 .add(Restrictions.like("status", "Not Exe"))
					 .add(Restrictions.like("direction", "Sell"))
					 .list();
			 System.out.println(orderinfo.size());
			 
		}catch(Exception e) {
			System.out.println(e);
		}
		return orderinfo;
	}
	
	
	public List<InProgress> getIndividualOrderInfo(String userId){
		List<InProgress> indiOrderInfo = null;
		System.out.println(userId);
		try {
			 //orderinfo= session.createQuery("from InProgress").list();	
			 indiOrderInfo=session.createCriteria(InProgress.class)
					 .add(Restrictions.like("userId", userId))
					 .add(Restrictions.like("status", "Not Exe"))
					 .addOrder(Order.desc("timeStamp")).list();
			

		}catch(Exception e) {
			System.out.println(e);
		}
		return indiOrderInfo;
	}
	
	
//	float getTotalAmount(String direction,float amount){
//		float brokerage=(amount*2)/100;
//		float totalAmount=0;
//		if(direction.equals("buy")){
//		totalAmount=amount+brokerage;
//		}
//		else if(direction.equals("sell"))
//		{
//		totalAmount=amount-brokerage;
//		}
//		return totalAmount;
//		}

}
