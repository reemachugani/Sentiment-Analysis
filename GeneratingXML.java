import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
 
public class GeneratingXML {
 
	public void xml() {
 
	  try {
 
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		int length = Crawler.reviewList.size();

		// root element
		Document document = docBuilder.newDocument();
		Element rootElement = document.createElement("ElectroluxReviews");
		document.appendChild(rootElement);

		Element doc;
		Element stars;
		Element url;
		Element date;
		Element userUrl;
		Element review;
		Element polarity;
		Element confidence;

		// generating doc elements for all reviews
		for(int i = 0; i < length; i++){
			doc = document.createElement("doc");
			rootElement.appendChild(doc);
			doc.setAttribute("id", Integer.toString(Crawler.reviewList.get(i).getDocID()));

			stars = document.createElement("stars");
			stars.appendChild(document.createTextNode(Double.toString(Crawler.reviewList.get(i).getStars())));
			doc.appendChild(stars);

			url = document.createElement("url");
			url.appendChild(document.createTextNode(Crawler.reviewList.get(i).getUrl()));
			doc.appendChild(url);

			date = document.createElement("date");
			date.appendChild(document.createTextNode(Crawler.reviewList.get(i).getDate()));
			doc.appendChild(date);

			userUrl = document.createElement("user");
			userUrl.appendChild(document.createTextNode(Crawler.reviewList.get(i).getUserUrl()));
			doc.appendChild(userUrl);

			review = document.createElement("review");
			review.appendChild(document.createTextNode(Crawler.reviewList.get(i).getReview()));
			doc.appendChild(review);

			polarity = document.createElement("polarity");
			polarity.appendChild(document.createTextNode(Integer.toString(Crawler.reviewList.get(i).getPolarity())));
			doc.appendChild(polarity);

			confidence = document.createElement("confidence");
			confidence.appendChild(document.createTextNode(Double.toString(Crawler.reviewList.get(i).getConfidence())));
			doc.appendChild(confidence);		

		}
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File("file.xml"));
 
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
 
		transformer.transform(source, result);
 
		System.out.println("File saved!");
 
	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
 }

}