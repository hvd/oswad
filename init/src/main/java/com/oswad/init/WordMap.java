package com.oswad.init;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WordMap implements Map<String,Integer>{
	private Map<String, Integer> wordCountMap = new HashMap<String, Integer>();
	int fileCount = 0;

	/**
	 * @param args
	 */
	public WordMap(String pathToCorpus) {
		// Iterate over all the files under that directory and add
		// the counts to the wordMap
		try {
			final File directory = new File(pathToCorpus);
			fileCount = directory.listFiles().length;
			for (File f : directory.listFiles()) {
				FileInputStream is = new FileInputStream(f);
				DataInputStream in = new DataInputStream(is);
				BufferedReader br = new BufferedReader(
						new InputStreamReader(in));
				String incoming;
				Integer init = new Integer(1);

				while ((incoming = br.readLine()) != null) {
					String[] buffer = incoming.split("\\s+");
					for (String s : buffer) {
						String lower = s.toLowerCase();

						if (!wordCountMap.containsKey(lower)) {
							wordCountMap.put(lower, init);
						} else {
							Integer i = wordCountMap.get(lower);
							i++;
							wordCountMap.put(lower, i);
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, Integer> getWordCountMap() {
		return wordCountMap;
	}

	public void setWordCountMap(Map<String, Integer> wordCount) {
		this.wordCountMap = wordCount;
	}

	@Override
	public void clear() {
		wordCountMap.clear();
		
	}

	@Override
	public boolean containsKey(Object key) {
		return wordCountMap.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return wordCountMap.containsValue(value);
	}

	@Override
	public Set<java.util.Map.Entry<String, Integer>> entrySet() {
		return wordCountMap.entrySet();
	}

	@Override
	public Integer get(Object key) {
		return wordCountMap.get(key);
	}

	@Override
	public boolean isEmpty() {
		return wordCountMap.isEmpty();
	}

	@Override
	public Set<String> keySet() {
		return wordCountMap.keySet();
	}

	@Override
	public Integer put(String key, Integer value) {
		return wordCountMap.put(key, value);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Integer> m) {
		wordCountMap.putAll(m);
		
	}

	@Override
	public Integer remove(Object key) {
		return wordCountMap.remove(key);
	}

	@Override
	public int size() {
		return wordCountMap.size();
	}

	@Override
	public Collection<Integer> values() {
		return wordCountMap.values();
	}
	
	public int getFileCount() {
		return fileCount;
	}

}
