package cse.om.vswn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import cse.om.util.UTF8File;

public class VietSentiWordNet {

	private static final String _pathToVSWN = "resources/VietSentiWordnet_ver1.0.txt";
	private static final String _pathToNegativeWord = "resources/negative.txt";
	private static Map<String, Sensitivity> _dict;
	private static Set<String> _negativeWord;

	static {
		try {
			init();
			readNegativeWord();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void init() throws Exception {

		_dict = new HashMap<String, Sensitivity>();
		HashMap<String, Vector<Double>> temp = new HashMap<String, Vector<Double>>();
		BufferedReader csv = null;

		try {
			csv = new BufferedReader(new FileReader(_pathToVSWN));
		} catch (FileNotFoundException e) {
			System.out.println("Không tìm thấy file " + _pathToVSWN + "!");
		}

		if (csv == null)
			return;

		String line = "";
		while ((line = csv.readLine()) != null) {
			if (line.charAt(0) == '#') continue;
			
			String[] data = line.split("\t");
			Double score = Double.parseDouble(data[2]) - Double.parseDouble(data[3]);
			String[] words = data[4].split(" ");

			String prev = "";
			for (String w : words) {
				if (w.contains("#")) {
					String[] w_n = (prev + w).split("#");
					w_n[0] += "#" + data[0];
					if (w_n[0].equals("vui#a")) {
						prev = "";
					}
					prev = "";

					int index = Integer.parseInt(w_n[1]) - 1;
					Vector<Double> v;
					if (temp.containsKey(w_n[0]))
						v = temp.get(w_n[0]);
					else
						v = new Vector<>();
					for (int i = v.size(); i < index; ++i)
						v.add(0.0);
					v.add(index, score);
					temp.put(w_n[0], v);
				} else {
					prev = prev + w + "_";
				}
			}
		}

		Set<String> keys = temp.keySet();
		for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
			String word = (String) iterator.next();
			Vector<Double> v = temp.get(word);
			double score = 0.0, sum = 0.0;
			for (int i = 0; i < v.size(); ++i) {
				score += (1.0 / (i + 1)) * v.get(i);
				sum += 1.0 / (i + 1);
			}
			score /= sum;

			Sensitivity s;
			if (score >= 0.75)
				s = Sensitivity.STRONG_POSITIVE;
			else if (score > 0.25 && score <= 0.5)
				s = Sensitivity.POSITIVE;
			else if (score > 0 && score >= 0.25)
				s = Sensitivity.WEAK_POSITIVE;
			else if (score < 0 && score >= -0.25)
				s = Sensitivity.WEAK_NEGATIVE;
			else if (score < -0.25 && score >= -0.5)
				s = Sensitivity.NEGATIVE;
			else if (score <= -0.75)
				s = Sensitivity.STRONG_NEGATIVE;
			else
				s = Sensitivity.NORMAL;
			_dict.put(word, s);
		}

		csv.close();
	}

	private static void readNegativeWord() throws IOException {
		_negativeWord = new HashSet<>();
		String[] words = UTF8File.getAllLines(_pathToNegativeWord);
		for (String w : words) {
			if (w.length() > 0)
				_negativeWord.add(w);
		}
	}

	public static Sensitivity extract(String word, String pos) {
		return extract(word + "#" + pos);
	}
	
	public static Sensitivity extract(String word) {
		Sensitivity s = _dict.get(word);
		if (s != null)
			return s;
		else
			return Sensitivity.NOT_DETERMINED;
	}
	
	public static Map<String, Sensitivity> extract(String[] words) {
		Map<String, Sensitivity> map = new HashMap<>();
		String prev = "";
		for (String word : words) {
			String[] w = word.split("#");
			Sensitivity senti = extract(word);
			if (senti != Sensitivity.NOT_DETERMINED) {
				if (_negativeWord.contains(prev)) {
					map.put(prev + " " + w[0], senti.getOpposite());
				} else {
					map.put(w[0], senti);
				}
			}
			prev = w[0];
		}
		return map;
	}
	
	public static enum Sensitivity {
		
		STRONG_POSITIVE	( 3, "strong_positive", "strong positive"),
		POSITIVE		( 2, "positive", "positive"),
		WEAK_POSITIVE	( 1, "weak_positive", "weak positive"),
		NORMAL			( 0, "normal", "normal"),
		WEAK_NEGATIVE	(-1, "weak_negative", "weak negative"),
		NEGATIVE		(-2, "negative", "negative"),
		STRONG_NEGATIVE	(-3, "strong_negative", "strong negative"),
		NOT_DETERMINED	(-4, "not_determined", "not determined");
		
		private int code;
		private String label;
		private String description;
		
		private Sensitivity(int code, String label, String description) {
			this.code = code;
			this.label = label;
			this.description = description;
		}
		
		public int getCode() {
			return code;
		}
		
		public String getLabel() {
			return label;
		}
		
		public String getDescription() {
			return description;
		}
		
		public Sensitivity getOpposite() {
			switch (this) {
			case NEGATIVE:
				return POSITIVE;
			case POSITIVE:
				return NEGATIVE;
			case STRONG_NEGATIVE:
				return STRONG_POSITIVE;
			case STRONG_POSITIVE:
				return STRONG_NEGATIVE;
			case WEAK_NEGATIVE:
				return WEAK_POSITIVE;
			case WEAK_POSITIVE:
				return WEAK_NEGATIVE;
			default:
				return NORMAL;
			}
		}
	}
}
