package cleanAndSort;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import trieNodeTree.*;


/* Find the word within the word bank, move values for word forward to score class
 *
 */

public class WordSearch {
	public static void main(String[] args) throws IOException {
		Visuals v = new Visuals();
		v.run();
        //See if article words are in word bank
		Test test = new Test();
		        		
		int total = 0;

		Scores score = new Scores();		
		
		try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/tates/OneDrive/Desktop/Small Text.txt"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        StringTokenizer tokenizer = new StringTokenizer(line, " \t\n\r\f.,;:?!\"'()");

		        while (tokenizer.hasMoreTokens()) {
		            String word = tokenizer.nextToken();

		            // Remove punctuation and convert to lowercase
		            String formattedWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
		            
		            //run word through word bank
		            if(test.doesWordExist(formattedWord)) {
			            //increment and reset
			            if(String.valueOf(total).contains("9")) {
			            	total = score.hasNine(total);
			            } else {
			            	//increment
			            	total += test.getWordCode(formattedWord);
			            }
		            }
		        }
		    }
			score.wrapUp(total);
			score.printResults();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}	
}
