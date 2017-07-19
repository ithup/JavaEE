package com.ithuplion.web;

import com.ithuplion.service.MessageServie;
import com.ithuplion.service.impl.MessageServiceImpl;

/*
 *  主程序类,作用,开启软件程序
 */
public class MainWeb {
	public static void main(String[] args) {
		MessageServie ms=new MessageServiceImpl();
		do{
			ms.menuRun();
		}while(true);
		
	}
}
