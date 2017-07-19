package com.ithuplion.message;

import java.util.Date;

import org.junit.Test;

import com.ithuplion.MessageDao.MessageDao;
import com.ithuplion.MessageDaoImpl.MessageDaoImpl;
import com.ithuplion.domain.Message;

public class MessageTest {
	@Test
	public void test01(){
		MessageDao md=new MessageDaoImpl();
		Message messsage = new Message(1,"tom","hello","world",new Date());
		md.addMessage(messsage);
		Message message = md.findById(1);
		System.out.println(message);
	}
	@Test
	public void test02(){
		MessageDao md=new MessageDaoImpl();
		md.displayMessage();
	}
	@Test
	public void test03(){
		MessageDao md=new MessageDaoImpl();
		boolean f = md.deleteMessage(3);
		System.out.println(f);
	}
	@Test
	public void test04(){
		MessageDao md=new MessageDaoImpl();
		Message message = md.findById(1);
		System.out.println(message);
	}
	@Test
	public void test05(){
		MessageDao md=new MessageDaoImpl();
		Message messsage = new Message(1,"tom","hello","world",new Date());
		md.addMessage(messsage);
		Message message3 = md.findById(1);
		System.out.println(message3);
		System.out.println("---------------------------");
		Message message = new Message(3,"jack","good","good nice",new Date());
		boolean f = md.modifyMessage(1, message);
		System.out.println(f);
		System.out.println("-----------------");
		Message message2 = md.findById(3);
		System.out.println(message2);
	}
}
