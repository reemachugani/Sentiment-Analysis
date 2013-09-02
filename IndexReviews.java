import java.io.*;
import java.util.*;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.FieldInfo.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class IndexReviews{

	public void index(){

		String indexPath = "index";

		Directory dir = FSDirectory.open(new File(indexPath));
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_31);
        IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_31, analyzer);
		iwc.setOpenMode(OpenMode.CREATE);
		IndexWriter writer = new IndexWriter(dir, iwc);

		for(int i=0; i < 100; i++){
			Document document = new Document();
			document.add(new Field("Review", Crawler.reviewList.get(i).getReview(), Field.Store.YES, Field.Index.ANALYSED, Field.TermVector.YES));
			document.add(new Field("Polarity", Crawler.reviewList.get(i).getPolarity(), Field.Store.YES, Field.Index.NOT_ANALYZED));
			
			writer.addDocument(document);
			System.out.println("*** Added : " + Crawler.reviewList.get(i).getDocID());
		}
		writer.optimize();
        writer.close();
	}
}