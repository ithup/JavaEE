package com.ithuplion.lucene.index;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

public class IndexSearch {
	private void doSearch(Query query) {
		try {
			// ָ��������ĵ�ַ
			File indexFile = new File("D:\\javaee\\lucene\\indexFile\\");
			// ����Ŀ¼������
			FSDirectory directory = FSDirectory.open(indexFile);
			// ������ȡ����
			DirectoryReader reader = DirectoryReader.open(directory);
			// ����IndexSearcher
			// ָ��������ĵ�ַ
			IndexSearcher indexSearch = new IndexSearcher(reader);
			// ͨ��searcher������������
			// �ڶ���������ָ����Ҫ��ʾ�Ķ�����¼��N��
			TopDocs topDocs = indexSearch.search(query, 10);
			// ���ݲ�ѯ����ƥ����ļ�¼����
			int count = topDocs.totalHits;
			System.out.println("ƥ����ļ�¼����" + count);
			System.out.println("=====================================");
			// ���ݲ�ѯ����ƥ����ļ�¼
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			for (ScoreDoc scoreDoc : scoreDocs) {
				// ��ȡ�ĵ���ID
				int docID = scoreDoc.doc;
				// ͨ��ID��ȡ�ĵ�
				Document doc = indexSearch.doc(docID);
				System.out.println("��ƷID:" + doc.get("id"));
				System.out.println("��Ʒ��:" + doc.get("name"));
				System.out.println("��Ʒ�۸�:" + doc.get("price"));
				System.out.println("��ƷͼƬ" + doc.get("pic"));
				System.out.println("��Ʒ����" + doc.get("description"));
				System.out.println("--------------------");
			}
			// �ر���Դ
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void indexSearch() throws Exception {
		// ����Query����
		// ʹ��QueryParser����ʱ����Ҫָ���ִ���������ʱ�ķִ���Ҫ������ʱ�ķִ���һ��
		// ��һ��������Ĭ���������������
		QueryParser parser = new QueryParser("name", new StandardAnalyzer());
		// ͨ��queryparser������query����
		// �����������lucene�Ĳ�ѯ���(�ؼ���һ��Ҫ��д)
		Query query = parser.parse("name:spring OR solr");
		doSearch(query);
	}

	@Test
	public void termQuery() {
		Query query = new TermQuery(new Term("name", "lucene"));
		doSearch(query);
	}

	@Test
	public void numericRangeQuery() {
		// ���ַ�Χ��ѯ
		// ����NumericRangeQuery����
		// ������������ơ���Сֵ�����ֵ���Ƿ������Сֵ���Ƿ�������ֵ
		Query query = NumericRangeQuery.newFloatRange("price", 55f, 60f, true, false);
		doSearch(query);
	}

	@Test
	public void booleanQuery(){
		//��ϲ�ѯ
        //BooleanQuery
		BooleanQuery query=new BooleanQuery();
		//����TermQuery����
		Query q1=new TermQuery(new Term("name","java"));
		//����NumericRangeQuery����
		//������������ơ���Сֵ�����ֵ���Ƿ������Сֵ���Ƿ�������ֵ
		Query q2=NumericRangeQuery.newFloatRange("price", 55f, 70f, false,true);
		//	��Ϲ�ϵ�������˼����: 
		//		1��MUST��MUST��ʾ���롱�Ĺ�ϵ�������������� 
		//		2��MUST��MUST_NOTǰ�߰������߲������� 
		//		3��MUST_NOT��MUST_NOTû���� 
		//		4��SHOULD��MUST��ʾMUST��SHOULDʧȥ���壻 
		//		5��SHOUlD��MUST_NOT�൱��MUST��MUST_NOT�� 
		//		6��SHOULD��SHOULD��ʾ���򡱵ĸ��
		query.add(q1,Occur.SHOULD);
		query.add(q2,Occur.SHOULD);
		System.out.println(query);
		doSearch(query);
	}
	@Test
	public void multiFieldQueryParser() throws Exception{
		//�����ѯ
		String[] fields={"name","description"};
		Analyzer analyzer=new StandardAnalyzer();
		Map<String,Float> boosts=new HashMap<String,Float>();
		boosts.put("name", 200f);
		MultiFieldQueryParser parser=new MultiFieldQueryParser(fields, analyzer,boosts);
		Query query = parser.parse("����");
		System.out.println(query);
		doSearch(query);
	}
}
