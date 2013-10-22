package cse.om.test;

import java.io.IOException;

import cse.om.spellchecker.Checker;
import cse.om.spellchecker.Dictionary;

public class Test {
	
	public static void main(String[] args) throws IOException {
		System.out.println(Dictionary.getDefination("zữ"));
		System.out.println(Checker.correctSentence("tôi đi học"));
		System.out.println(Dictionary.isVietnameseCharacter('ư'));
	}

}
