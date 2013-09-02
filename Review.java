import java.io.*;
import java.util.*;

public class Review{
	int docID;
	double stars;
	String url;
	String date;
	String userUrl;
	String review;
	int polarity;
	double confidence;

	public Review(){
		docID = 0;
		stars = 0.0;
		url = "";
		date = "";
		userUrl = "";
		review = "";
		polarity = -2;
		confidence = 0.0;		
	}

	public void setFields(int docID, double stars, String url, String date, String userUrl, String review){
		this.docID = docID;
		this.stars = stars;
		this.url = url;
		this.date = date;
		this.userUrl = userUrl;
		this.review = review;
	}

	public void setPolarity(int polarity){
		this.polarity = polarity;
	}

	public void setConfidence(double confidence){
		this.confidence = confidence;
	}

	public String getReview(){
		return this.review;
	}

	public String getUrl(){
		return this.url;
	}

	public String getUserUrl(){
		return this.userUrl;
	}

	public String getDate(){
		return this.date;
	}

	public double getStars(){
		return this.stars;
	}

	public double getConfidence(){
		return this.confidence;
	}

	public int getPolarity(){
		return this.polarity;
	}

	public int getDocID(){
		return this.docID;
	}

	public void printReviews(){
		System.out.println("Document : " + this.docID);
		System.out.println("Stars : " + this.stars);
		System.out.println("URL : " + this.url);
		System.out.println("Date : " + this.date);
		System.out.println("User URL : " + this.userUrl);
		System.out.println("Review : " + this.review);
		System.out.println("Polarity : " + this.polarity);
		System.out.println("Confidence : " + this.confidence);
		System.out.println("----------------------------------");
		System.out.println();
	}
}