package cse.om.test;

import java.io.IOException;

import cse.om.spellchecker.Dictionary;

public class Test {
	
	public static void main(String[] args) throws IOException {
		Dictionary dict = new Dictionary();
		System.out.println(dict.getDefination("zữ"));
	}

}
