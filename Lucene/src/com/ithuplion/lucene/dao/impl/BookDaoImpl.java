package com.ithuplion.lucene.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ithuplion.lucene.dao.BookDao;
import com.ithuplion.lucene.pojo.Book;
public class BookDaoImpl implements BookDao {

	@Override
	public List<Book> queryBooks() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		// 图书列表
		List<Book> list = new ArrayList<Book>();
		try {
			// 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 连接数据库
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lucene", "root", "tiger");
			String sql="select * from book";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Book book=new Book();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				book.setPrice(rs.getFloat("price"));
				book.setPic(rs.getString("pic"));
				book.setDescription(rs.getString("description"));
				list.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
