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
		//�ɼ�����
		List<Book> list = bookDao.queryBooks();
		//���ɼ��������ݷ�װ��Document������
		List<Document> docList=new ArrayList<Document>();
		Document document = null;
		for (Book book : list) {
			document=new Document();
			//ͼ��ID
			//���ִʡ��������洢StringField
			Field id=new StringField("id", book.getId().toString(), Store.YES);
			// ͼ������
			//�ִʡ��������洢TextField
			Field name=new TextField("name", book.getName(), Store.YES);
			// ͼ��۸�
			//�ִʡ��������洢
			Field price=new FloatField("price", book.getPrice(), Store.YES);
			// ͼ��ͼƬ��ַ
			//���ִʡ����������洢StoredField
			Field pic=new StoredField("pic", book.getPic());
			// ͼ������
			//�ִʡ����������洢
			Field description=new TextField("description", book.getDescription(), Store.YES);
			//����boostֵ
			if(book.getId()==5){
				description.setBoost(100f);
			}
			// ��field�����õ�Document������
			document.add(id);
			document.add(name);
			document.add(price);
			document.add(pic);
			document.add(description);
			//��document����ŵ�List������
			docList.add(document);
		}
		// �����ִ�������׼�ִ���
		Analyzer analyzer=new StandardAnalyzer();
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
		// ָ��������ĵ�ַ
		File indexFile=new File("D:\\javaee\\lucene\\indexFile\\");
		Directory directory = FSDirectory.open(indexFile);
		// ����IndexWriter
		IndexWriter indexWriter=new IndexWriter(directory , indexWriterConfig );
		// ͨ��IndexWriter����Documentд�뵽��������
		for (Document doc : docList) {
			indexWriter.addDocument(doc);
		}
		// �ر�indexWriter
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
		//��һ��������ָ����ѯ����
		//�ڶ����������޸�֮��Ķ���
		//�޸�ʱ������ݲ�ѯ���������Բ�ѯ�����������ǰ��ɾ����Ȼ�󸲸��µ�Document�������û�в�ѯ�������������һ��Document
		//�޸����̣��Ȳ�ѯ����ɾ���������
		Term term = new Term("name", "javaEE");
		Document doc=new Document();
		doc.add(new TextField("name", "SpringMVC",Store.YES));
		indexWriter.updateDocument(term, doc);
		indexWriter.close();
	}
}

