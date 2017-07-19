package com.ithuplion.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.ithuplion.MessageDao.MessageDao;
import com.ithuplion.MessageDaoImpl.MessageDaoImpl;
import com.ithuplion.domain.Message;
import com.ithuplion.service.MessageServie;

public class MessageServiceImpl implements MessageServie{
	Scanner input=new Scanner(System.in);
	private MessageDao md=new MessageDaoImpl();
	@Override
	public void menuRun() {
		System.out.println("************************************************");
		System.out.println("===========欢迎使用留言系统v1.0==============");
		System.out.println("----------------------主菜单-------------------------");
		System.out.println("\t1.显示留言信息");
		System.out.println("\t2.添加留言信息");
		System.out.println("\t3.查询留言信息");
		System.out.println("\t4.退出系统");
		System.out.println("************************************************");
		System.out.print("请选择菜单项：");
		int op = input.nextInt();
		switch(op){
		case 1:
			this.displayAll();
			break;
		case 2:
			this.addOne();
			break;
		case 3:
			this.findMessageById();
			break;
		case 4:
			System.out.println("退出系统");
			System.exit(0);
			break;//退出虚拟机
		}
	}
	@Override
	public void displayAll() {
		md.displayMessage();
	}

	@Override
	public void addOne() {
		System.out.print("请输入留言编号：");
		int id=input.nextInt();
		System.out.print("请输入留言人：");
		String username=input.next();
		System.out.print("请输入留言标题：");
		String title=input.next();
		System.out.print("请输入留言内容：\n");
		String content = input.next();
		Message message = new Message(id,username,title,content,new Date());
		md.addMessage(message);//添加留言信息
	}
	@Override
	public void findMessageById() {
		System.out.print("请输入留言编号：");
		int id=input.nextInt();
		Message message = md.findById(id);
		System.out.println("留言人："+message.getUserName()+"\t标题："+message.getTitle()+"\t留言内容："+message.getContents()+"\t留言日期："+message.getCreateTime());
	}

}
