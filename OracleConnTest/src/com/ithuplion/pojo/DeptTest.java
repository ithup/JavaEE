package com.ithuplion.pojo;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeptTest {
	public static void main(String[] args) {
		Session session = HibernateUtils.openSession();
		Transaction transaction = session.beginTransaction();
		Dept dept = (Dept) session.get(Dept.class, 10);
		System.out.println(dept);
		transaction.commit();
		session.close();
	}
}
