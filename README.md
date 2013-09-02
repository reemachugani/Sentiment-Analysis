Sentiment Analysis of Electrolux Product Reviews
====================================
Goal  
----
Building a sentiment classifier on product reviews of Electrolux. 

Software used
-------------
* Crawler – manual
* XML Processor – REGEX
* Tokenizer – Apache Lucene, WEKA stringtowordvector filter. 
* Classifier – WEKA, libSVM

Data set
--------
1024 reviews from Amazon.com for Electrolux home appliances.

Steps taken for classification
------------------------------
1. Stored the URLs of the review pages for the 5 Electrolux products in a text file, which is read one at a time to be crawled for extracting the reviews.
2. Everytime a URL is read, I’ve used REGEX to scan the page for reviews from <id=“productReviews”> tag. The link to the NEXT page is read from the pagination bar of the page to crawl the subsequent reviews.
3. Review class is used to store all the review objects with the attributes of DocID, stars, url, date, user, review, polarity, confidence.
4. An ArrayList<Review> is maintained to store all the review objects.
5. I tried 2 approaches for classification –
  1. Using Apache Lucene for tokenizing and classifying using the KNN Algorithm.
  2. Using WEKA for tokenizing and classifying using SVM (Linear Kernel).
6. Using Apache Lucene
  1. Indexed 50% of the reviews using the TF.IDF scheme
  2. Classified remaining test data using the KNN Algorithm with k = 5
  3. The average accuracy obtained was equal to 74.5%.
7. Using WEKA
  1. Constructed .arff files for training and test data
  2. Tokenized and vectorized the review instances using WEKA’s stringtowordvector filter. I used the default stemmer and the default tokenizing algorithm
   (weka.core.tokenizers.WordTokenizer).
  3. Performed 10-fold cross validation to determine the best machine learning parameters.
  4. Classification using SVM (Linear Kernel).
  5. Initially, the first 500 reviews were taken as training data. However, the precision and recall for Class 0 and Class -1 were low due to the low number of review instances representing the particular classes. Therefore, I increased the number of reviews in the training data for both the classes to achieve a more balanced training set with 300 instances for Class 1, 100 instances for Class 0 and 100 instances for Class -1.
  6. The results of the classification were then appended to the existing XML file.
  7. The average accuracy obtained using LibSVM was 84.32%. The average confidence value was 0.82.

￼￼
