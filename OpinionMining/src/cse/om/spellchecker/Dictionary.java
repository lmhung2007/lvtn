package cse.om.spellchecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Dictionary {
	
	private final static String FILEPATH = "resources/dictionary.txt";
	static Map<String, String> dict;
	
	static {
		dict = new HashMap<>();
		try {
			readDictionaryFromFile(FILEPATH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	@SuppressWarnings("unused")
	private String changeProject(String currentPath, String newProject) {
		int i = currentPath.lastIndexOf(File.separatorChar);
		return currentPath.substring(0, i + 1) + newProject;
	}
	
	private static void readDictionaryFromFile(String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(FILEPATH), "UTF-8"));
		while (br.ready()) {
			String line = br.readLine();
			String[] data = line.split("\t");
			if (data.length >= 2)
				dict.put(data[0], data[1]);
		}
		br.close();
	}

	public static String getDefination(String word) {
		if (dict.containsKey(word))
			return dict.get(word);
		else
			return word;
	}
}
