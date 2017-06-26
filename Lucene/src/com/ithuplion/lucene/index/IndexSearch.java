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
			// 指定索引库的地址
			File indexFile = new File("D:\\javaee\\lucene\\indexFile\\");
			// 索引目录流对象
			FSDirectory directory = FSDirectory.open(indexFile);
			// 索引读取对象
			DirectoryReader reader = DirectoryReader.open(directory);
			// 创建IndexSearcher
			// 指定索引库的地址
			IndexSearcher indexSearch = new IndexSearcher(reader);
			// 通过searcher来搜索索引库
			// 第二个参数：指定需要显示的顶部记录的N条
			TopDocs topDocs = indexSearch.search(query, 10);
			// 根据查询条件匹配出的记录总数
			int count = topDocs.totalHits;
			System.out.println("匹配出的记录总数" + count);
			System.out.println("=====================================");
			// 根据查询条件匹配出的记录
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			for (ScoreDoc scoreDoc : scoreDocs) {
				// 获取文档的ID
				int docID = scoreDoc.doc;
				// 通过ID获取文档
				Document doc = indexSearch.doc(docID);
				System.out.println("商品ID:" + doc.get("id"));
				System.out.println("商品名:" + doc.get("name"));
				System.out.println("商品价格:" + doc.get("price"));
				System.out.println("商品图片" + doc.get("pic"));
				System.out.println("商品描述" + doc.get("description"));
				System.out.println("--------------------");
			}
			// 关闭资源
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void indexSearch() throws Exception {
		// 创建Query对象
		// 使用QueryParser搜索时，需要指定分词器，搜索时的分词器要和索引时的分词器一致
		// 第一个参数：默认搜索的域的名称
		QueryParser parser = new QueryParser("name", new StandardAnalyzer());
		// 通过queryparser来创建query对象
		// 参数：输入的lucene的查询语句(关键字一定要大写)
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
		// 数字范围查询
		// 创建NumericRangeQuery对象
		// 参数：域的名称、最小值、最大值、是否包含最小值、是否包含最大值
		Query query = NumericRangeQuery.newFloatRange("price", 55f, 60f, true, false);
		doSearch(query);
	}

	@Test
	public void booleanQuery(){
		//组合查询
        //BooleanQuery
		BooleanQuery query=new BooleanQuery();
		//创建TermQuery对象
		Query q1=new TermQuery(new Term("name","java"));
		//创建NumericRangeQuery对象
		//参数：域的名称、最小值、最大值、是否包含最小值、是否包含最大值
		Query q2=NumericRangeQuery.newFloatRange("price", 55f, 70f, false,true);
		//	组合关系代表的意思如下: 
		//		1、MUST和MUST表示“与”的关系，即“交集”。 
		//		2、MUST和MUST_NOT前者包含后者不包含。 
		//		3、MUST_NOT和MUST_NOT没意义 
		//		4、SHOULD与MUST表示MUST，SHOULD失去意义； 
		//		5、SHOUlD与MUST_NOT相当于MUST与MUST_NOT。 
		//		6、SHOULD与SHOULD表示“或”的概念。
		query.add(q1,Occur.SHOULD);
		query.add(q2,Occur.SHOULD);
		System.out.println(query);
		doSearch(query);
	}
	@Test
	public void multiFieldQueryParser() throws Exception{
		//多域查询
		String[] fields={"name","description"};
		Analyzer analyzer=new StandardAnalyzer();
		Map<String,Float> boosts=new HashMap<String,Float>();
		boosts.put("name", 200f);
		MultiFieldQueryParser parser=new MultiFieldQueryParser(fields, analyzer,boosts);
		Query query = parser.parse("技术");
		System.out.println(query);
		doSearch(query);
	}
}
