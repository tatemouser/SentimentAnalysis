package cleanAndSort;

import java.io.IOException;
import java.util.StringTokenizer;
import scraper.Crawler;
import trieNodeTree.*;

// ADD BOOK VERSION AND TITLE OF SITE TO VISUALS
public class WordSearch {
	// Emotions: anger,anticipation,disgust,fear,joy,sadness,suprise,trust,negative,positive
	public static void main(String[] args) throws IOException {

		WordBank wordBank = new WordBank();
		
		/* 
		 * Total is a 10 digit long. Each digit represents an emotions point value
		 * Disgust = 100 and Fear = 1000, if disgust is used 3 times and fear 2 times, the total variable would be updated to 2300.
		 */
		long total = 0L;

		Scores score = new Scores();	
		score.createEmotionMap();
		
		
		
		/*
		 * INITIAL DISPLAY - Get link from user input to scrape.
		 * SCRAPER - Collect data from website and store to line string for scoring.
		 */
		Visuals newTab = new Visuals();
		String url = newTab.findLink();
		
		Crawler crawl = new Crawler();
		String str = crawl.run(url);
	    String text = str;
	    
	    
	    
	    /*
	     * SCORING - Analyze words and compare to word bank while adjusting the total value
	     */
        StringTokenizer tokenizer = new StringTokenizer(text, " \t\n\r\f.,;:?!\"'()");

        while (tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken();

            String formattedWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
            
            // Run word through word bank
            if(wordBank.doesWordExist(formattedWord)) {
	            // Reset digit in total variable if digit reaches 9.
	            if(String.valueOf(total).contains("9")) {
	            	total = (long) score.hasNine(total);
	            }
	            // Traditional increment to total if word is within the wordbank.
	            total += wordBank.getWordCode(formattedWord);
            }
        }
        // Take remaining values from total (Ex: 1837492002) and add to hashmap for visuals.
		score.wrapUp(total); 
		

		
		/*
		 * VISUALS - Search is complete, display results by creating new tab with chart.
		 */
		newTab.createVisuals(score.sortMap());
	}	
}
