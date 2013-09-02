
import java.io.*;
import java.util.*;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.*;

public class Classification{

	// public static Map<Integer, Classes> classList = new HashMap<Integer, Classes>();
	public static double COUNTER = 0.0;
    public static double INSTANCES = 0.0;	

    public void classify() throws IOException, ParseException{

    	IndexReader reader = IndexReader.open(FSDirectory.open(new File("index")));
        IndexSearcher searcher = new IndexSearcher(reader);
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_31);
        QueryParser parser = new QueryParser(Version.LUCENE_31, "Review", analyzer);

  //       classList.put<1, new Classes());
		// classList.put<0, new Classes());
		// classList.put<-1, new Classes());

		int length = Crawler.reviewList.size();
		for(int i = 100; i < length; i++){
			int t_class;
			double r_stars = Crawler.reviewList.get(i).getStars();
			
			if(r_stars < 2.0){
    			t_class = -1;
    		}else if(r_stars > 3.0){
    			t_class = 1);				                    			
    		}else{
    			t_class = 0;				                    			
    		}

    		Query query = parser.parse(Crawler.reviewList.get(i).getReview());

            TopScoreDocCollector results = TopScoreDocCollector.create(5, true);
            searcher.search(query, results);
            ScoreDoc[] hits = results.topDocs().scoreDocs;

            TreeMap<Integer, Integer> doc_classes = new TreeMap<Integer, Integer>();
            Document doc;
            int doc_class;

            for (ScoreDoc hit : hits) {
                doc = searcher.doc(hit.doc);
                doc_class = doc.get("Polarity");
                int value;
                boolean found = false;

                for (Map.Entry<Integer, Integer> entry : doc_classes.entrySet()) {
                    if (entry.getKey().equals(doc_class)) {
                        value = entry.getValue();
                        entry.setValue(value + 1);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    doc_classes.put(doc_class, 1);
                }

            }

            // if (doc_classes.isEmpty()) {
            //     classList.get(q_class).update(0.0, 0.0, 1.0);
            // }

            
            // classList.get(q_class).updateInstances();

            int largest = 0;
            int largestValue = 0;

            for (Map.Entry<Integer, Integer> entry : doc_classes.entrySet()) {
                if (entry.getValue() > largestValue) {
                    largestValue = entry.getValue();
                    largest = entry.getKey();
                }
            }
            
            INSTANCES++;

            if (largest.equals(t_class)) {
                // classList.get(q_class).update(1.0, 0.0, 0.0);
                COUNTER++; 
            }
            Crawler.reviewList.get(i).setPolarity(largest);
                // else {
            //     // classList.get(largest).update(0.0, 1.0, 0.0); 
            //     // classList.get(q_class).update(0.0, 0.0, 1.0);
            // }

		}

		System.out.println();
		
        System.out.println("ACCURACY: "+(COUNTER*100/INSTANCES));

    }
}