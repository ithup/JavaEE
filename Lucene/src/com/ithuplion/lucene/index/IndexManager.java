package com.ithuplion.lucene.index;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.FloatField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;
import org.junit.Test;

import com.ithuplion.lucene.dao.BookDao;
import com.ithuplion.lucene.dao.impl.BookDaoImpl;
import com.ithuplion.lucene.pojo.Book;

public class IndexManager {
	@Test
	public void createIndex() throws Exception{
		BookDao bookDao=new BookDaoImpl();
		//采集数据
		List<Book> list = bookDao.queryBooks();
		//将采集到的数据封装到Document对象中
		List<Document> docList=new ArrayList<Document>();
		Document document = null;
		for (Book book : list) {
			document=new Document();
			//图书ID
			//不分词、索引、存储StringField
			Field id=new StringField("id", book.getId().toString(), Store.YES);
			// 图书名称
			//分词、索引、存储TextField
			Field name=new TextField("name", book.getName(), Store.YES);
			// 图书价格
			//分词、索引、存储
			Field price=new FloatField("price", book.getPrice(), Store.YES);
			// 图书图片地址
			//不分词、不索引、存储StoredField
			Field pic=new StoredField("pic", book.getPic());
			// 图书描述
			//分词、索引、不存储
			Field description=new TextField("description", book.getDescription(), Store.YES);
			//设置boost值
			if(book.getId()==5){
				description.setBoost(100f);
			}
			// 将field域设置到Document对象中
			document.add(id);
			document.add(name);
			document.add(price);
			document.add(pic);
			document.add(description);
			//将document对象放到List集合中
			docList.add(document);
		}
		// 创建分词器，标准分词器
		Analyzer analyzer=new StandardAnalyzer();
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
		// 指定索引库的地址
		File indexFile=new File("D:\\javaee\\lucene\\indexFile\\");
		Directory directory = FSDirectory.open(indexFile);
		// 创建IndexWriter
		IndexWriter indexWriter=new IndexWriter(directory , indexWriterConfig );
		// 通过IndexWriter对象将Document写入到索引库中
		for (Document doc : docList) {
			indexWriter.addDocument(doc);
		}
		// 关闭indexWriter
		indexWriter.close();
	}
	@Test
	public void deleteIndex() throws Exception{
		IndexWriterConfig indexWriterConfig=new IndexWriterConfig(Version.LUCENE_4_10_3, new StandardAnalyzer());
		Directory directory=FSDirectory.open(new File("D:\\javaee\\lucene\\indexFile\\"));
		IndexWriter indexWriter=new IndexWriter(directory, indexWriterConfig);
		Term term=new Term("id", "1");
		indexWriter.deleteDocuments(term);
		indexWriter.close();
	}
	@Test
	public void deleteIndexAll() throws Exception{
		IndexWriterConfig indexWriterConfig=new IndexWriterConfig(Version.LUCENE_4_10_3, new StandardAnalyzer());
		Directory directory=FSDirectory.open(new File("D:\\javaee\\lucene\\indexFile\\"));
		IndexWriter indexWriter=new IndexWriter(directory, indexWriterConfig);
		indexWriter.deleteAll();
		indexWriter.close();
	}
	@Test
	public void updateIndex() throws Exception{
		IndexWriterConfig indexWriterConfig=new IndexWriterConfig(Version.LUCENE_4_10_3, new StandardAnalyzer());
		Directory directory=FSDirectory.open(new File("D:\\javaee\\lucene\\indexFile\\"));
		IndexWriter indexWriter=new IndexWriter(directory, indexWriterConfig);
		//第一个参数：指定查询条件
		//第二个参数：修改之后的对象
		//修改时如果根据查询条件，可以查询出结果，则将以前的删除，然后覆盖新的Document对象，如果没有查询出结果，则新增一个Document
		//修改流程：先查询，再删除，再添加
		Term term = new Term("name", "javaEE");
		Document doc=new Document();
		doc.add(new TextField("name", "SpringMVC",Store.YES));
		indexWriter.updateDocument(term, doc);
		indexWriter.close();
	}
}

