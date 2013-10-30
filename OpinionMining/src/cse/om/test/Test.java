package cse.om.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import vn.hus.nlp.tagger.VietnameseMaxentTagger;
import cse.om.spellchecker.Checker;
import cse.om.vswn.VietSentiWordNet;
import cse.om.vswn.VietSentiWordNet.Sensitivity;
import edu.stanford.nlp.ling.WordTag;

public class Test {
	static String[] sentences = {
		"tôi ko fải đi học",
		"dzề cái jề đj",
		"kon mèo đó đẹp, kao khôg thích thằg kia",
		"Mình bít quán ăn đó ở đâu rồi",
		"hôm nay trời đẹp wá",
		"cô ấy thật duyên dáng",
		"tôi rất vui mừng"
	};
	
	public static void main(String[] args) throws IOException {
		
		PrintWriter out = new PrintWriter("result.txt", "UTF-8");
		
		VietnameseMaxentTagger tagger = new VietnameseMaxentTagger();
		for (String sentence : sentences) {
			try {
				List<WordTag> list = tagger.tagText2(Checker.correctSentence(sentence.toLowerCase()));
				StringBuilder sb = new StringBuilder();
				for (WordTag wt : list) {
					String word = wt.word().replaceAll(" ", "_");
					String pos = wt.tag();
					Sensitivity senti = VietSentiWordNet.extract(word, pos);
					if (senti != Sensitivity.NOT_DETERMINED) {
						out.println(word + ": " + senti.getDescription());
					}
					sb.append(word + "/" + wt.tag() + " ");
				}
//				System.out.println(sb.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		out.close();
	}

}
