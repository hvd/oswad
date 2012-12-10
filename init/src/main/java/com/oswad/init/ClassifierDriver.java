package com.oswad.init;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClassifierDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String spamCorpusPath = null;
		String nonSpamCorpusPath = null;
		System.out.print("Enter Path to Spam Corpus: ");
		BufferedReader fileReader = new BufferedReader(
				new InputStreamReader(System.in));
		try {
			spamCorpusPath = fileReader.readLine();
			System.out.print("Enter path to non-spam Corpus: ");
			nonSpamCorpusPath = fileReader.readLine();
			Classifier c = new Classifier(spamCorpusPath, nonSpamCorpusPath);
			System.out.print("Enter Path to Test Corpus: ");
			String testPath = fileReader.readLine();
			System.out.println("Probability that this document is good is" +" "+c.bayesProb(testPath+"/test1.txt","Good"));
			System.out.println("Probability that this document is spam is" +" "+c.bayesProb(testPath+"/test1.txt","Spam"));
			
			System.out.println("Probability that this document is good is" +" "+c.bayesProb(testPath+"/test2.txt","Good"));
			System.out.println("Probability that this document is spam is" +" "+c.bayesProb(testPath+"/test2.txt","Spam"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
