package trieNodeTree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordBank {
	private Trie trie;
    public WordBank() throws IOException{
    	
        trie = new Trie();
    	
    	String line = "";
    	double code = 0;
    	
        try (BufferedReader br = new BufferedReader(new FileReader("src/wordBankCSVFile/WordData.csv"))) {
            while ((line = br.readLine()) != null) {
                //Find title word
                String firstWord = line.split(",")[0].trim().replaceAll("[^\\x00-\\x7F]", "");

                //Find descriptive words
                String[] words = Arrays.stream(line.split(",\\s*"))
                		.skip(1)
                        .filter(word -> !word.isEmpty() && !word.equals(","))
                        .map(String::trim)
                        .toArray(String[]::new);

                code = newCode(words);
            
                //Add words to trie
                trie.insert(firstWord, code);
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Testing 
        /*System.out.println(trie.getCode("abandon"));
        System.out.println(100000000+100000+1000);
        System.out.println(trie.getCode("adjunct"));
        System.out.println(1000000000);*/
    }
    
    
    
    public static double newCode(String[] words) {
    	//anger,anticipation,disgust,fear,joy,sadness,suprise,trust,negative,positive
    	double val = 0;
    	
    	for(int i = 0; i < words.length; i++) {

    		switch(words[i]) {
	    		case "anger": val+=1;
	    			break;
	    		case "anticipation": val+=10;
	    			break;
	    		case "disgust": val+=100; 
	    			break;
	    		case "fear": val+=1000;
	    			break;
	    		case "joy": val+=10000;
	    			break;
	    		case "sadness": val+=100000;
	    			break;
	    		case "surprise": val+=1000000;
	    			break;
	    		case "trust": val+=10000000;
	    			break;
	    		case "negative": val+=100000000;
	    			break;
	    		case "positive": val+=1000000000;
	    			break;
	    	}
    	}
    	return val;
    } 
    
    public boolean doesWordExist(String word) {
    	return trie.search(word);
    }
    
    public double getWordCode(String word) {
    	return trie.getCode(word);
    }
}
