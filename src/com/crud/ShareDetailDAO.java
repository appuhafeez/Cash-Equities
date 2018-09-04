package com.crud;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.trade.InProgress;
import com.trade.ShareDetail;
import com.trade.ShareInfo;
import com.trade.User;

public class ShareDetailDAO {

	private static Transaction transaction = null;
	private static Session session = null;
	static Configuration config=null; 
	
	public ShareDetailDAO(){
		config= new Configuration().configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		 session = factory.openSession();
		 transaction = session.beginTransaction();
	}
	
	public List<ShareDetail> getShareDetail() {
		List<ShareDetail> share = null;
//		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
//		String myDate = "2018-07-19 09:00:00.000000";
//		Date date = null;
//		try {
//			date = formatter.parse(myDate);
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		try {
			share= session.createCriteria(ShareDetail.class)
					//.add(Restrictions.like("timeStamp", date))
					.addOrder(Order.asc("securityCode")).list();
		}catch(Exception e) {
			//transaction.rollback();
			System.out.println(e);
		}
		return share;
	}
	
	public void addOrUpdate(ShareDetail sharedetail) {
		Configuration conf=new Configuration();
		conf.configure("hibernate.cfg.xml");
		
		// creating session
		SessionFactory factory=conf.buildSessionFactory();
		Session session=factory.openSession();
		
		// get the current hibernate session
		Transaction t=session.beginTransaction();
		// create a query  ... sort by last name
		
		session.saveOrUpdate(sharedetail);
		
		// return the results
		t.commit();
		factory.close();
	}
	
	public ShareDetail getStock(String stock) {
		Configuration conf=new Configuration();
		conf.configure("hibernate.cfg.xml");
		
		List<ShareDetail> shareDetail = null;
		// creating session
		SessionFactory factory=conf.buildSessionFactory();
		Session session=factory.openSession();
		
		// get the current hibernate session
		Transaction t=session.beginTransaction();
		
		shareDetail= session.createCriteria(ShareDetail.class)
				 .add(Restrictions.eq("securityCode", stock))
				 .list();	

//		Query<ShareDetail> theQuery = 
//				session.createQuery("FROM ShareDetail WHERE securityName='"+stock+"'",
//						ShareDetail.class);
//		
		
		
		
		// return the results
		t.commit();
		factory.close();
		
		return shareDetail.get(0);
	}
}
