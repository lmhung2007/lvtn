package cse.om.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// Hỗ trợ thao tác với file UTF-8
public class UTF8File {

	public static String[] getAllLines(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(fileName), "UTF-8"));
		
		List<String> lines = new ArrayList<>();
		while (br.ready())
			lines.add(br.readLine());
		br.close();
		
		String[] result = new String[lines.size()];
		lines.toArray(result);
		return result;
	}
}
