package com.crud;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.trade.UserHistory;
import com.trade.UserStockInfo;

public class UserStockInfoDAO {
	public void addOrUpdate(UserStockInfo userStockInfo) {
		Configuration conf=new Configuration();
		conf.configure("hibernate.cfg.xml");
		
		// creating session
		SessionFactory factory=conf.buildSessionFactory();
		Session session=factory.openSession();
		
		// get the current hibernate session
		Transaction t=session.beginTransaction();
		// create a query  ... sort by last name
		
		session.saveOrUpdate(userStockInfo);
		
		// return the results
		t.commit();
		factory.close();
	}
	public void delete(long id) {
		Configuration conf=new Configuration();
		conf.configure("hibernate.cfg.xml");
		
		// creating session
		SessionFactory factory=conf.buildSessionFactory();
		Session session=factory.openSession();
		
		// get the current hibernate session
		Transaction t=session.beginTransaction();
		// create a query  ... sort by last name
		
		UserStockInfo temp=session.get(UserStockInfo.class, id);
		
		session.delete(temp);
		
		// session.saveOrUpdate(userStockInfo);
		
		// return the results
		t.commit();
		factory.close();
	}
	public List<UserStockInfo> getDetails(String userid){
		Configuration conf=new Configuration();
		conf.configure("hibernate.cfg.xml");
		
		// creating session
		SessionFactory factory=conf.buildSessionFactory();
		Session session=factory.openSession();
		
		// get the current hibernate session
		// Transaction t=session.beginTransaction();
		// create a query  ... sort by last name
		
		System.out.println("userid: "+userid);
		
		List<UserStockInfo> details = (List<UserStockInfo>) session.createQuery("FROM UserStockInfo WHERE userId='"+userid+"'").list();
		
		// session.flush();
		// return the results
		// t.commit();
		factory.close();
		if(details.size() == 0) {
			return null;
		}
		return details;
	}
	
	public List<UserStockInfo> getDetails(String userid,String securityCode){
		Configuration conf=new Configuration();
		conf.configure("hibernate.cfg.xml");
		
		// creating session
		SessionFactory factory=conf.buildSessionFactory();
		Session session=factory.openSession();
		
		// get the current hibernate session
		// Transaction t=session.beginTransaction();
		// create a query  ... sort by last name
		
	
		
		List<UserStockInfo> details = (List<UserStockInfo>) session.createQuery("FROM UserStockInfo WHERE userId='"+userid+"'").list();
		
		
		factory.close();
		if(details.size() == 0) {
			return null;
		}
		return details;
	}

}
