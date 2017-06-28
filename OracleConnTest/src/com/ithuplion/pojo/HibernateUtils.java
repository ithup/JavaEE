package com.ithuplion.pojo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	// 会话工厂，整个程序只有一份
	private static SessionFactory sessionFactory;
	static {
		//1 加载配置
		Configuration configuration=new Configuration().configure();
		//2 获得工厂
		sessionFactory = configuration.buildSessionFactory();
		//3 关闭虚拟机时,释放SessionFactory
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			
			public void run() {
				System.out.println("虚拟机关闭，释放资源");
				sessionFactory.close();
			}
		}));
	}
	public static Session openSession(){
		return  sessionFactory.openSession();
	}
	public static Session getCurrentSession(){
		return  sessionFactory.getCurrentSession();
	}
}
