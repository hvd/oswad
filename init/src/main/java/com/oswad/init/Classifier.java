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
    private WordMap spamMap;
    private WordMap nonSpamMap;
    private Map<String,Integer> documentCountMap = new HashMap<String,Integer>();
    private Map<String,Double> spamProbability;
    
	Thread classifierThread;
    boolean start = false;
    boolean isSpam = false;
	
    public Classifier(){
    	classifierThread = new Thread(this,"ClassifierThread");
    	classifierThread.start();
    	start = true;
    }


	@Override
	public void run() {
		String fileName = null;
		spamMap = train("/home/hkelkar/spam/spam");		
		nonSpamMap = train("/home/hkelkar/spam/easy_ham");

		documentCountMap.put("Good", nonSpamMap.getFileCount());
		documentCountMap.put("Spam", spamMap.getFileCount());		
		biasNonSpam(nonSpamMap);
		
		while (start) {
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
		}
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
		return new WordMap(pathToCorpus);
	}


	private boolean classify(String fileName) {
	
		return false;
	}
}
