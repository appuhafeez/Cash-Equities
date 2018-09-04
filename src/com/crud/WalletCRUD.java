package com.crud;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.trade.UserHistory;
import com.trade.Wallet;


public class WalletCRUD {

	private static Transaction transaction = null;
	private static Session session = null;
	static Configuration config=null; 
	public WalletCRUD() {
		config= new Configuration().configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		 session = factory.openSession();
		 transaction = session.beginTransaction();
	}
	
	public List<Wallet> getWalletInfo(String userId) {
		List<Wallet> walletinfo = null;

		try {
			walletinfo=session.createCriteria(Wallet.class)
					.add(Restrictions.like("userId", userId))
					 .addOrder(Order.desc("timeStamp")).list();
		}catch(Exception e) {
			System.out.println(e);
		}
		return walletinfo;
	}
	
	public void addOrUpdate(Wallet wallet) {
		Configuration conf=new Configuration();
		conf.configure("hibernate.cfg.xml");
		
		// creating session
		SessionFactory factory=conf.buildSessionFactory();
		Session session=factory.openSession();
		
		// get the current hibernate session
		Transaction t=session.beginTransaction();
		// create a query  ... sort by last name
		
		session.saveOrUpdate(wallet);
		
		// return the results
		t.commit();
		factory.close();
	}
	
}
