package com.oswad.init;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.oswad.init.WordMap;

/**
 * Classifier for Wordpress Content
 *
 */
public class Classifier implements Runnable
{	

    private String spamCorpusPath;
    private String nonSpamCorpusPath;
	private WordMap spamMap;


	private WordMap nonSpamMap;
    private Map<String,Integer> documentCountMap = new HashMap<String,Integer>();
	private Map<String,Double> spamProbability;
    private final String good = "Good";
    private final String spam = "Spam";
    
	private Thread classifierThread;
    boolean start = false;
    boolean isSpam = false;
	
    public Classifier(String spamCorpusPath, String nonSpamCorpusPath){
    	this.spamCorpusPath = spamCorpusPath;
    	this.nonSpamCorpusPath = nonSpamCorpusPath;
    	classifierThread = new Thread(this,"ClassifierThread");
    	classifierThread.start();
    	start = true;
    }


	@Override
	public void run() {
		String fileName = null;
		spamMap = train(spamCorpusPath);		
		nonSpamMap = train(nonSpamCorpusPath);

		documentCountMap.put(good, nonSpamMap.getFileCount());
		documentCountMap.put(spam, spamMap.getFileCount());		
		biasNonSpam(nonSpamMap);
		
	/*	while (start) {
			System.out.print("Enter Filename of content to test: ");
			BufferedReader fileReader = new BufferedReader(
					new InputStreamReader(System.in));
			try {
				fileName = fileReader.readLine();
				if(fileName.equals("q") || fileName.equals("Q")){
					System.out.println("Exiting Classifier");
					start = false;
					continue;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			isSpam = classify(fileName);
			if(isSpam){
				System.out.println("Content is spam");
			}
			else{
				System.out.println("Content is non-spam");
			}
		}*/
	}
	
	
	/** Bias the map of Non spam word counts to double of what actual 
	 * count is.
	 * @param nonSpamMap2
	 */
	private void biasNonSpam(WordMap nonSpamMap) {
		for(Map.Entry<String, Integer> k : nonSpamMap.entrySet()){
			k.setValue(k.getValue()*2);
		}

	}


	private WordMap train(String pathToCorpus) {
		return new WordMap(pathToCorpus,true);
	}

	/**
	 * Given a category, what is the probability with which 
	 * a word can appear in it
	 */
	public double wordProbability(String word, String category){
		double catCount = 1.0;
		double wordCount = 1.0;
		catCount = documentCountMap.get(category);
		if(category.equals(good)){
			if(getNonSpamMap().containsKey(word)){
			wordCount = getNonSpamMap().get(word);
			}
		}
		else if(category.equals(spam)){
			if(getSpamMap().containsKey(word)){
			wordCount = getSpamMap().get(word);
			}
		}
		
		return Math.min(wordCount/catCount,1);
		
	}
	
	public double weightedProbability(String word, String category){
		double weight = 1.0;
		double assumedProb = 0.5;
		double wordProbability= wordProbability(word,category);
		
		double totalCount = (getSpamMap().containsKey(word) ? getSpamMap().get(word) : 0)+(getNonSpamMap().containsKey(word) ? getNonSpamMap().get(word) : 0);
		
		double weightedProb = ((totalCount*wordProbability) + (weight*assumedProb))/(weight+totalCount);	
		return weightedProb;
		
	}
	
	public double documentProbability(String pathToDocument, String category){
		double prob = 1;
		Map<String,Integer> wordMap = new WordMap(pathToDocument,false);
		for(String s : wordMap.keySet()){
			prob = prob*weightedProbability(s,category);
		}
		
		return prob;		
	}
	
    /** Given a document, what is the probability with which it fits in the current category.
     * @param pathToDocument
     * @param category
     * @return
     */
    public double bayesProb(String pathToDocument, String category){
    	double categoryCount = getDocumentCountMap().get(category);
    	double totalCount = getSpamMap().getFileCount() + getNonSpamMap().getFileCount();
    	double categoryProb = categoryCount/totalCount;
    	double docProb = documentProbability(pathToDocument, category);
    	return docProb*categoryProb;
    }
	
	public WordMap getSpamMap() {
		return spamMap;
	}

    public WordMap getNonSpamMap() {
		return nonSpamMap;
	}
    
    public Map<String, Integer> getDocumentCountMap() {
		return documentCountMap;
	}
    
	public boolean classify(String fileName) {
	
		return false;
	}
	
	
}
