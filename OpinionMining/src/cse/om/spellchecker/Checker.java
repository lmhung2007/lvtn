package cse.om.spellchecker;

import java.util.HashSet;
import java.util.Set;

public class Checker {
	
	static Set<Character> vietnameseCharSet;
	
	static {
		vietnameseCharSet = new HashSet<>();
		
	}
	
	

	public static String[] correct(String[] sentences) {
		String[] result = new String[sentences.length];
		for (int i = 0; i < sentences.length; ++i) {
			result[i] = correctSentence(sentences[i].toLowerCase());
		}
		return result;
	}

	public static String correctSentence(String sentence) {
		String pattern = "[a-z]+";
		return sentence.replaceAll(pattern, "[word]");
	}
}
