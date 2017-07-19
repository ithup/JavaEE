package com.ithuplion.MessageDaoImpl;
import java.util.Date;

/**
 * 实现类：主要实现接口中所有的抽象方法
 */
import com.ithuplion.MessageDao.MessageDao;
import com.ithuplion.domain.Message;

public class MessageDaoImpl implements MessageDao{
//	Message messsage1 = new Message(1,"tom","hello","world",new Date());
//	Message messsage2 = new Message(2,"rose","study","world",new Date());
//	Message all[]={messsage1,messsage2};//对象数组，里面保存Message的对象
	Message all[]=new Message[100];//对象数组，里面保存Message的对象
	@Override
	public void addMessage(Message message) {
		//添加留言信息
		//int i=0;//此时i表示数组元素的下标
		/*while(true){
			if(all[i] == null){
				all[i] = message;
				System.out.println(all[i]);
				break;
			}else{
				i++;
			}
		}*/
		//for循环
		for(int k=0;k<1000;k++){
			if(all[k] == null){
				all[k] = message;
				System.out.println("添加成功");
				break;
			}
		}
	}

	@Override
	public void displayMessage() {
		for(int i=0;i<all.length;i++){
			if(all[i] != null){
				System.out.println(all[i]);
			}
		}
	}

	@Override
	public Message findById(int id) {
		Message message = null;
		for(int i=0;i<all.length;i++){
			if(all[i].getId() == id){
				message = all[i];
				break;
			}
		}
		return message;
	}

	@Override
	public boolean modifyMessage(int id, Message message) {
		boolean f=false;
		/*for(int i=0;i<all.length;i++){
			if(all[i].getId() == id){
				all[i]=message;
				f=true;
			}
		}*/
		Message me2 = this.findById(id);
		if(me2!=null){
			me2.setUserName(message.getUserName());
			me2.setTitle(message.getTitle());
			me2.setContents(message.getContents());
			me2.setCreateTime(new Date());
			f=true;
		}
		return f;
	}

	@Override
	public boolean deleteMessage(int id) {
		boolean f=false;
		/*for(int i=0;i<all.length;i++){
			if(all[i].getId() == id){
				all[i]=null;
				f=true;
			}
		}*/
		Message message = this.findById(id);
		if(message!=null){
//			message.
		}
		return f;
	}

}
