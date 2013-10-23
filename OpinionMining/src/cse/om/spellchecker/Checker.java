package cse.om.spellchecker;

public class Checker {
	
	static final String ie = "i í ì ỉ ĩ ị	e é è ẻ ẽ ẹ	ê ế ề ể ễ ệ";
	static final String o = "o ó ò ỏ õ ọ	ô ố ồ ổ ỗ ộ	ơ ớ ờ ở ỡ ợ";

	// sentence input phải ở dạng lower case
	public static String[] correct(String[] sentences) {
		String[] result = new String[sentences.length];
		for (int i = 0; i < sentences.length; ++i) {
			result[i] = correctSentence(sentences[i]);
		}
		return result;
	}

	public static String correctSentence(String sentence) {
		char[] c = sentence.toCharArray();
		StringBuilder sb = new StringBuilder();
		StringBuilder word = new StringBuilder();
		for (int i = 0; i < c.length; ++i) {
			if (Dictionary.isVietnameseCharacter(c[i])) {
				word.append(c[i]);
			} else {
				if (word.length() > 0)
					sb.append(correctWord(word.toString()));
				word.delete(0, word.length());
				sb.append(c[i]);
			}
		}
		if (word.length() > 0)
			sb.append(correctWord(word.toString()));
		return sb.toString();
	}

	private static String correctWord(String word) {
		word= Dictionary.getDefination(word);
		word = correctJ(word);
		word = correctZ(word);
		word = correctF(word);
		word = correctK(word);
		word = correctNG(word);
		word = correctW(word);
		word = correctC(word);
		return word;
	}

	private static String correctC(String word) {
		if (word.length() >= 2 && word.charAt(0) == 'c' && ie.indexOf(word.charAt(1)) >= 0) {
			word = "k" + word.substring(1);
		}
		return word;
	}

	private static String correctW(String word) {
		if (word.startsWith("wu"))
			word = "qu" + word.substring(2);
		else if (word.startsWith("w"))
			word = "qu" + word.substring(1);
		return word;
	}

	private static String correctNG(String word) {
		int n = word.length();
		if (n >= 2 && word.charAt(n - 1) == 'g' && Dictionary.isVowel(word.charAt(n - 2))) {
			word = word.replaceFirst("g$", "ng");
		}
		return word;
	}

	private static String correctK(String word) {
		if (word.length() < 2) return word;
		if (word.charAt(0) == 'k' && o.indexOf(word.charAt(1)) >= 0) {
			word = "c" + word.substring(1);
		}
		return word;
	}

	private static String correctF(String word) {
		word = word.replaceFirst("^f", "ph");
		return word;
	}

	private static String correctZ(String word) {
		word = word.replaceFirst("^dz", "v");
		word = word.replaceFirst("^z", "v");
		return word;
	}

	private static String correctJ(String word) {
		word = word.replaceFirst("^j", "gi");
		word = word.replaceAll("j", "i");
		return word;
	}
}
