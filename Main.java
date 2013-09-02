import java.io.*;
import java.util.*;

public class Main{
	public static void main(String args[]) throws IOException{
		File file = new File("Review_urls.txt");
		Crawler crawler = new Crawler();
		crawler.crawl(file);
		IndexReviews indexReviews = new IndexReviews();
		indexReviews.index();
		Classification classification = new Classification();
		classification.classify();
		GeneratingXML generatingXML = new GeneratingXML();
		generatingXML.xml();
	}
}