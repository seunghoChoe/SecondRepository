package server;

import java.util.Scanner;
import java.util.StringTokenizer;

public class PrtocolParser {
	private String message;
	private MyMethod method;
	
	public PrtocolParser(String message) {
		this.message = message;
		
		this.parse();
	}

	private void parse() {
		Scanner scan = new Scanner(message);
		while(scan.hasNext()) {
			String line = scan.nextLine();
			if(!line.equals("end")) {
				StringTokenizer tokenizer = new StringTokenizer(line, "=");
				String attribute = tokenizer.nextToken();
				tokenizer.nextToken();
				String value = tokenizer.nextToken();
				
			}
		}
	}
	
	class MyMethod {
		private String name;
	}
}
