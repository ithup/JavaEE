package com.ithuplion.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

//该类就是一个JavaBean(组件，主要是将属性进行封装起来)
public class Message implements Serializable{
	private Integer id;      //留言ID
	private String userName;//留言人
	private String title;   //标题
	private String contents;//内容
	private Date   createTime;// 创建日期
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Message() {
	}
	public Message(Integer id, String userName, String title, String contents, Date createTime) {
		this.id = id;
		this.userName = userName;
		this.title = title;
		this.contents = contents;
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return "留言人：" + userName + "\t留言时间:" + sdf.format(createTime) + "\n留言标题：" + title+"\n留言内容:"+contents;
	}
}
