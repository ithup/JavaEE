package com.ithuplion.web;

import com.ithuplion.service.MessageServie;
import com.ithuplion.service.impl.MessageServiceImpl;

/*
 *  ��������,����,������������
 */
public class MainWeb {
	public static void main(String[] args) {
		MessageServie ms=new MessageServiceImpl();
		do{
			ms.menuRun();
		}while(true);
		
	}
}