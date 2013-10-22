/**
 * 
 */
package vn.hus.nlp.tagger.test;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import vn.hus.nlp.tagger.VietnameseMaxentTagger;
import vn.hus.nlp.utils.UTF8FileUtility;

/**
 * @author LE HONG Phuong, phuonglh@gmail.com
 * <br>
 * Apr 9, 2009, 7:34:28 PM
 * <br>
 * Test class for the Vietnamese tagger.
 */
public class VietnameseTaggerTest {
	
	public static void main(String[] args) throws IOException {
		String data = "samples/0.txt";
		String[] sentences = vn.hus.nlp.utils.UTF8FileUtility.getLines(data);
		
		PrintWriter out = new PrintWriter("result.txt", "UTF-8");
		
		VietnameseMaxentTagger tagger = new VietnameseMaxentTagger();
		for (String sentence : sentences) {
			try {
				String s = tagger.tagText(sentence);
				out.println(s);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		out.close();
	}
}
