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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Classifier c = new Classifier(spamCorpusPath, nonSpamCorpusPath);
	}

}
