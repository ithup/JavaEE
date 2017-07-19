package com.ithuplion.service;

/**
 * 建立业务层的接口
 * 
 * @author acer
 *
 */
public interface MessageServie {
	/**
	 * 系统菜单
	 */
	public void menuRun();
	/**
	 * 显示所有信息
	 */
	public void displayAll();
	/**
	 * 添加留言
	 */
	public void addOne();
	/**
	 * 根据编号查询留言信息
	 * @param id
	 * @return
	 */
	public void findMessageById();
}
