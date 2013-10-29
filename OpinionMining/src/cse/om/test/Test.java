package cse.om.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import vn.hus.nlp.tagger.VietnameseMaxentTagger;
import cse.om.spellchecker.Checker;
import cse.om.spellchecker.Dictionary;
import cse.om.vswn.VietSentiWordNet;
import edu.stanford.nlp.ling.WordTag;

public class Test {
	static String[] sentences = {
		"tôi ko fải đi học",
		"dzề cái jề đj",
		"kon mèo đó đẹp, kao khôg thích thằg kia",
		"Mình bít/bik/pit/pik quán ăn đó ở đâu rồi",
		"hôm nay zui wá"
	};
	
	public static void main(String[] args) throws IOException {
//		System.out.println(Dictionary.getDefination("dzễ"));
//		System.out.println(Checker.correctSentence("tôi đi học"));
//		System.out.println(Dictionary.isVietnameseCharacter('ư'));
//		
//		for (String s : sentences)
//			System.out.println(Checker.correctSentence(s.toLowerCase()));
		
		PrintWriter out = new PrintWriter("result.txt", "UTF-8");
		
		VietnameseMaxentTagger tagger = new VietnameseMaxentTagger();
		for (String sentence : sentences) {
			try {
				List<WordTag> list = tagger.tagText2(Checker.correctSentence(sentence.toLowerCase()));
				String[] a = tagger.tagText3(Checker.correctSentence(sentence.toLowerCase()));
				StringBuilder sb = new StringBuilder();
				for (WordTag wt : list) {
					sb.append(wt.word().replaceAll(" ", "_") + "/" + wt.tag() + " ");
				}
				System.out.println(sb.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		out.close();
	}

}
