package com.ithuplion.message;

import java.util.Date;

import com.ithuplion.domain.Message;

public class MessageDeom {
	public static void main(String[] args) {
		Message all[]=new Message[10];//对象数组，里面保存Message的对象
		Message m1 = new Message(1,"郭靖","java培训","java大数据培训",new Date());
		Message m2 = new Message(2,"黄蓉","jsp学习","jsp学习心得体会",new Date());
		Message m3 = new Message(3,"杨过","servlet学习","servlet容器",new Date());
		all[0]=m1;
	}
}
