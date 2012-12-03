package com.oswad.init;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WordMap implements Map<String,Integer>{
	private Map<String, Integer> wordCountMap = new HashMap<String, Integer>();

	/**
	 * @param args
	 */
	public WordMap(String trainingSetFileName) {
		// TODO Auto-generated method stub
		try {
			FileInputStream is = new FileInputStream(trainingSetFileName);
			DataInputStream in = new DataInputStream(is);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String incoming;
			Integer init = new Integer(0);

			while ((incoming = br.readLine()) != null) {
				String[] buffer = incoming.split("\\s+");
				for (String s : buffer) {
					if (!wordCountMap.containsKey(s.toLowerCase())) {
						wordCountMap.put(s.toLowerCase(), init);
					} else {
						wordCountMap.put(s.toLowerCase(), init++);
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
}