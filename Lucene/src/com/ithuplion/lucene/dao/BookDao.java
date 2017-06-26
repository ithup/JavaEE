package com.ithuplion.lucene.dao;

import java.util.List;

import com.ithuplion.lucene.pojo.Book;

public interface BookDao {
	public List<Book> queryBooks();
}
