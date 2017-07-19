package com.ithuplion.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

//�������һ��JavaBean(�������Ҫ�ǽ����Խ��з�װ����)
public class Message implements Serializable{
	private Integer id;      //����ID
	private String userName;//������
	private String title;   //����
	private String contents;//����
	private Date   createTime;// ��������
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
		return "�����ˣ�" + userName + "\t����ʱ��:" + sdf.format(createTime) + "\n���Ա��⣺" + title+"\n��������:"+contents;
	}
}