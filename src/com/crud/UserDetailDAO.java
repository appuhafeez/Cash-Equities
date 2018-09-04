package com.crud;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.trade.InProgress;
import com.trade.UserDetail;
import com.trade.Wallet;

public class UserDetailDAO {

	private static Transaction transaction = null;
	private static Session session = null;
	static Configuration config=null; 
	public UserDetailDAO(){

		config= new Configuration().configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		 session = factory.openSession();
		 transaction = session.beginTransaction();
	}
	
	public void addOrUpdate(UserDetail userDetail) {
		Configuration conf=new Configuration();
		conf.configure("hibernate.cfg.xml");
		
		// creating session
		SessionFactory factory=conf.buildSessionFactory();
		Session session=factory.openSession();
		
		// get the current hibernate session
		Transaction t=session.beginTransaction();
		// create a query  ... sort by last name
		
		session.saveOrUpdate(userDetail);
		
		// return the results
		t.commit();
		factory.close();
	}
	
	
	public List<UserDetail> getWalletInfo(String userId) {
		List<UserDetail> userDetail = null;
		try {
			userDetail= session.createCriteria(UserDetail.class)
					.add(Restrictions.like("userId", userId)).list();
		}catch(Exception e) {
			System.out.println(e);
		}
		return userDetail;
	}
	
//	public UserDetail getWalletInfo(String userId) {
//		UserDetail userDetail = null;
//		try {
//			userDetail= (UserDetail)session.createCriteria(UserDetail.class)
//					.add(Restrictions.like("userId", userId));
//		}catch(Exception e) {
//			System.out.println(e);
//		}
//		return userDetail;
//	}
}
