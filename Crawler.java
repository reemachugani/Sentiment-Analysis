	import java.io.*;
	import java.util.*;
	import java.net.*;

	public class Crawler{
		
		public static ArrayList<Review> reviewList = new ArrayList<Review>();
		public static int docIDCounter = 0;
		public static int humanLabeledCounter = 0;

		public void crawl(File file) throws IOException{
			BufferedReader input = new BufferedReader(new FileReader(file));

			try{
					String pageUrl = null;

				while((pageUrl = input.readLine()) != null){
					// System.out.println("** page URL : "+pageUrl);
					URL url = new URL(pageUrl);
	        		URLConnection conn = url.openConnection();
	        		// System.out.println("***URL connection***");
			        try {
			            BufferedReader buff = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			            boolean eof = false;
			            int k;
			            while (!eof) {
			                String line = buff.readLine();
			                if(line.contains("table id=\"productReviews\"")){
			                	// System.out.println("*** Table entered");
			                	while(!eof){
			                		line = buff.readLine();
			                		if (line == null || line.contains("</table>")) {
			                    		eof = true;
			               			} else {
					                    if(line.contains("<!-- BOUNDARY -->")){
					                    	
					                    	Review revObj = new Review();
					                    	line = buff.readLine();
					                    	revObj.docID = ++docIDCounter;
					                    	revObj.url = pageUrl;
					                    	
					                    	while(!line.contains("out of 5 stars")){
					                    		line = buff.readLine();
					                    	}
					                    	k = line.indexOf("out of 5 stars");
					                    	revObj.stars = Double.parseDouble(line.substring(k-4, k-1));

					                    	while(!line.contains("<nobr>")){
					                    	 	line = buff.readLine();
					                    	}
					                    	k = line.indexOf("<nobr>");
					                    	revObj.date = line.substring(k+6, line.indexOf("</nobr>"));

					                    	while(!line.contains("By&nbsp;")){
					                    		line = buff.readLine();
					                    	}
					                    	k = line.indexOf("http://");
					                    	revObj.userUrl = line.substring(k, line.indexOf("\"", k));

					                    	while(!line.contains("This review is from:")){
					                    		line = buff.readLine();
					                    	}
					                    	String[] tokens = {};
					                    	while(line.contains("This review is from:") || line.contains("</div>") || (tokens.length < 2)){
					                    		line = buff.readLine();
					                    		tokens = line.split(" ");
					                    	}
					                    	revObj.review = line;

					                    	if(humanLabeledCounter < 100){
					                    		if(revObj.stars < 2.0){
					                    			revObj.setPolarity(-1);
					                    		}else if(revObj.stars > 3.0){
					                    			revObj.setPolarity(1);				                    			
					                    		}else{
					                    			revObj.setPolarity(0);				                    			
					                    		}

					                    		revObj.setConfidence(1.0);
					                    		humanLabeledCounter++;
					                    	}
					                    	// revObj.printReviews();
					                    	reviewList.add(revObj);
					                    }
			                		}
			                	}
			                }
			                if (line == null) {
			                    eof = true;
			                } 
			            }
			            buff.close();
			        }catch(IOException ex){
						ex.printStackTrace();
					}
			    } 
			}
			finally{
				input.close();
			}
		}
	}