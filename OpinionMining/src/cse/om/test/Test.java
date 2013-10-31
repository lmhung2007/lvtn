package cse.om.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vn.hus.nlp.tagger.VietnameseMaxentTagger;
import cse.om.spellchecker.Checker;
import cse.om.util.UTF8File;
import cse.om.vswn.VietSentiWordNet;
import cse.om.vswn.VietSentiWordNet.Sensitivity;
import edu.stanford.nlp.ling.WordTag;

public class Test {
	static final String input = "comment.txt";
	static final String output = "result.txt";
	static String[] sentences;
	
	public static void main(String[] args) throws IOException {
	
		PrintWriter out = new PrintWriter(output, "UTF-8");
		
		VietnameseMaxentTagger tagger = new VietnameseMaxentTagger();
		sentences = UTF8File.getAllLines(input);
		for (String sentence : sentences) {
			try {
				List<WordTag> list = tagger.tagText2(Checker.correctSentence(sentence.toLowerCase()));
				StringBuilder sb = new StringBuilder();
				List<String> wordList = new ArrayList<>();
				for (WordTag wt : list) {
					String word = wt.word().replaceAll(" ", "_").toLowerCase();
					String pos = wt.tag().toLowerCase();
					wordList.add(word + "#" + pos);
					sb.append(word + "/" + pos + " ");
				}
				String[] words = new String[wordList.size()];
				wordList.toArray(words);
				Map<String, Sensitivity> map = VietSentiWordNet.extract(words);
				for (String word : map.keySet()) {
					out.println(word + ": " + map.get(word).getDescription());
				}
				System.out.println(sb.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		out.close();
	}

}
