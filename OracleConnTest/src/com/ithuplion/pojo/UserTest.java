package com.ithuplion.pojo;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserTest {
	public static void main(String[] args) {
		User user=new User();
		user.setUsername("tom");
		user.setPassword("123");
		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
		session.close();
	}
}
