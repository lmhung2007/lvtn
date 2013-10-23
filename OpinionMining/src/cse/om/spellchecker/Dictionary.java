package cse.om.spellchecker;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cse.om.util.UTF8File;

public class Dictionary {

	private final static String DICTIONARYPATH = "resources/dictionary.txt";
	private final static String CHARSETPATH = "resources/vietcharset.txt";
	private static Map<String, String> dict;
	private static Set<Character> charSet;
	private static Set<Character> vowels, consonants;

	static {
		try {
			readDictionaryFromFile(DICTIONARYPATH);
			readCharSetFromFile(CHARSETPATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private String changeProject(String currentPath, String newProject) {
		int i = currentPath.lastIndexOf(File.separatorChar);
		return currentPath.substring(0, i + 1) + newProject;
	}

	private static void readDictionaryFromFile(String filePath)
			throws IOException {
		String[] lines = UTF8File.getAllLines(filePath);
		dict = new HashMap<>();
		for (String line : lines) {
			String[] data = line.split("\t");
			if (data.length >= 2)
				dict.put(data[0], data[1]);
		}
	}

	public static String getDefination(String word) {
		if (dict.containsKey(word))
			return dict.get(word);
		else
			return word;
	}

	private static void readCharSetFromFile(String filePath) throws IOException {
		String[] lines = UTF8File.getAllLines(CHARSETPATH);
		charSet = new HashSet<>();
		vowels = new HashSet<>();
		consonants = new HashSet<>();
		for (int i = 0; i < lines.length; ++i) {
			String line = lines[i];
			for (char c : line.toCharArray())
				if (c != ' ' && c != '\t') {
					charSet.add(c);
					if (i == 0) vowels.add(c);
					else if (i == 1) consonants.add(c);
				}
		}
	}
	
	public static boolean isVietnameseCharacter(char c) {
		return charSet.contains(c);
	}
	
	public static boolean isVowel(char c) {
		return vowels.contains(c);
	}
	
	public static boolean isConsonant(char c) {
		return consonants.contains(c);
	}
}
