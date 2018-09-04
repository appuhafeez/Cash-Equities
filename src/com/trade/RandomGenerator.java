package com.trade;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

class NewThread implements Runnable
{
	private static Transaction transaction = null;
	private static Session session = null;
	static Configuration config=null; 
	String name;
	Thread t;
	NewThread(String name){
		config= new Configuration().configure("hibernate.cfg.xml");
		SessionFactory factory = config.buildSessionFactory();
		 session = factory.openSession();
		 transaction = session.beginTransaction();
		this.name=name;
		this.t=new Thread(this,name);
		this.t.start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
		for(int i=0;i<7;i++) {	
		if(name.equals("thread"+i)) {
			while(true) {
			BigDecimal zero=BigDecimal.valueOf(0.0);
			Random rand = new Random();
			float  n = rand.nextInt(50) + 1;
			int choice=rand.nextInt(2);
		
			List prod= session.createQuery("from ShareInfo").list();
			ShareInfo row=new ShareInfo();
			row=(ShareInfo)prod.get(i);
			BigDecimal change=BigDecimal.valueOf(n/100);
			BigDecimal price=row.getPriceOfSecurity();
			BigDecimal newValue;
			if(choice==0) {
				newValue=price.add(change);
				row.setChangedPrice(change);
			}
			else {
				newValue=price.subtract(change);
				row.setChangedPrice(change.negate());
			}
			if(newValue.compareTo(zero)>0)
			row.setPriceOfSecurity(newValue);
			session.saveOrUpdate(row);
			session.flush();
			transaction.commit();
			transaction = session.beginTransaction();
			t.sleep(15000);
			}
		}
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
	
}
}
public class RandomGenerator {
	
	static RandomGenerator rg;
	
	public static RandomGenerator getNewInstance() {
		if(rg==null) {
			rg=new RandomGenerator();
			return rg;
		}else {
			return rg;
		}
	}
	private RandomGenerator(){
		try {
			
		for(int i=0;i<7;i++) {
			new NewThread("thread"+i);
		}
		try {
		      Thread.sleep(10000);
		    } catch (InterruptedException e) {
		      System.out.println("Main thread Interrupted");
		    }
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
		
}
	
	
	
	


