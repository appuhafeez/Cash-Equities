package com.crud;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.trade.User;

public class LoginDAO {

	//@Autowired
	//private SessionFactory sessionFactory;

	public User doLogin(String userName,String password) {

		Configuration conf=new Configuration();
		conf.configure("hibernate.cfg.xml");
		
		// creating session
		SessionFactory factory=conf.buildSessionFactory();
		Session session=factory.openSession();
		
		// get the current hibernate session
		//Session currentSession = factory.getCurrentSession();
		Transaction t=session.beginTransaction();
		// create a query  ... sort by last name
		Query<User> theQuery = 
				session.createQuery("from User WHERE userId='"+userName+"' AND password='"+password+"'",
						User.class);

		// execute query and get result list
		List<User> Users = theQuery.getResultList();
		if(Users.size()<1) {
			t.commit();
			factory.close();
			return null;
		}
		
		// return the results
		t.commit();
		factory.close();
		return Users.get(0);
	}
	public static boolean checkForLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session!=null && session.getAttribute("userid")!=null && session.getAttribute("username")!=null) {
			if(session.getAttribute("userid").equals("")) {
				return false;
			}else {
				return true;
			}
		}else {
			return false;
		}
	}
}
