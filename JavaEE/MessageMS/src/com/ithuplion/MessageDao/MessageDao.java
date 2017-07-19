package com.ithuplion.MessageDao;

import com.ithuplion.domain.Message;

/**
 * 接口：是一种标准
 * 		接口中只能包含：常量及抽象方法
 * @author acer
 *
 */
public interface MessageDao {
	/**
	 * 1.添加留言
	 * @param message
	 */
	public void addMessage(Message message);
	/**
	 * 2.显示所有留言信息
	 */
	public void displayMessage();
	/**
	 * 按id来查找留言信息
	 * @param id
	 * @return
	 */
	public Message findById(int id);
	/**
	 * 根据id来修改留言信息
	 * @param id
	 * @param message
	 * @return
	 */
	public boolean modifyMessage(int id ,Message message);
	/**
	 * 按id列删除留言信息
	 * @param id
	 * @return
	 */
	public boolean deleteMessage(int id);
}
