package cleanAndSort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Scores {

	private LinkedHashMap<String, Long> emotions = new LinkedHashMap<>();
	
	public void createEmotionMap() {
		emotions.put("anger", 0L);
		emotions.put("anticipation", 0L);
		emotions.put("disgust", 0L);
		emotions.put("fear", 0L);
		emotions.put("joy", 0L);
		emotions.put("sadness", 0L);
		emotions.put("suprise", 0L);
		emotions.put("trust", 0L);
		emotions.put("negative", 0L);
		emotions.put("positive", 0L);
	}
	
	// As an emotion is incremented 9 times, add 9 points to final hashmap for the emotion and reset digit in total
	public long hasNine(long num) {
        String numberString = String.valueOf(num);
        ArrayList<Integer> positions = new ArrayList<Integer>();
        // Find digit/digits with 9
        for(int i = 0; i < numberString.length(); i++) {
        	if(numberString.charAt(i) == '9') {
        		positions.add(i);
        	}
        }
        
        // Increment correct emotion in hashmap by 9,  
		while(positions.size() != 0) {
	        int position = positions.get(0);
	        String incrementEmotion = "";
	        
	        switch(position) {
		        case 9: incrementEmotion = "anger";              
		        	break;
		        case 8: incrementEmotion = "anticipation";
		        	break;
		        case 7: incrementEmotion = "disgust";
		        	break;
		        case 6: incrementEmotion = "fear";
		        	break;
		        case 5: incrementEmotion = "joy";
		        	break;
		        case 4: incrementEmotion = "sadness";
		        	break;
		        case 3: incrementEmotion = "suprise";
		        	break;
		        case 2: incrementEmotion = "trust";
		        	break;
		        case 1: incrementEmotion = "negative";
		        	break;
		        case 0: incrementEmotion = "positive";
		        	break;
	        }
	        
        	long initial = emotions.get(incrementEmotion);
        	emotions.replace(incrementEmotion, initial + 9);

	        positions.remove(0);
		}
		
		// Reset digit to 0, return new long
        String newString = numberString.replace('9', '0');
        return Long.parseLong(newString);
	}
	
	// When comparing ends, iterate through digits in total and add to final hashmap of emotions.
	public void wrapUp(long num) {
	    
		int i = 0;
		String emotionSum = "";
		
		// Increment correct emotion in hashmap based on digit in total
		while (num > 0) {
			long amount = num % 10;
		    num /= 10;
		    //System.out.println(amount);
		    switch(i) {
	        	case 9: emotionSum = "anger";              
        			break;
		        case 8: emotionSum = "anticipation";
		        	break;
		        case 7: emotionSum = "disgust";
		        	break;
		        case 6: emotionSum = "fear";
		        	break;
		        case 5: emotionSum = "joy";
		        	break;
		        case 4: emotionSum = "sadness";
		        	break;
		        case 3: emotionSum = "suprise";
		        	break;
		        case 2: emotionSum = "trust";
		        	break;
		        case 1: emotionSum = "negative";
		        	break;
		        case 0: emotionSum = "positive";
		        	break;
		    }
	        emotions.replace(emotionSum, emotions.getOrDefault(emotionSum, 0L) + amount);
		    i++;
		}	
	}
	
	// After comparing text and converting total to the hashmap, prepare the hashmap for visuals class.
	public HashMap<String, Long> sortMap() {
		// Sort converted hashmap from smallest to largest
        List<Map.Entry<String, Long>> entryList = new ArrayList<>(emotions.entrySet());
        Comparator<Map.Entry<String, Long>> valueComparator = Comparator.comparing(Map.Entry::getValue);
        entryList.sort(valueComparator);
		
        // Put the sorted entries into the original hashmap and get the total amount of matched words for converting to percentages.
        emotions.clear();
		int total = 0; 
        for (Map.Entry<String, Long> entry : entryList) {
        	emotions.put(entry.getKey(), entry.getValue());
        	total += entry.getValue();
        }
        
        // Convert to percentages
        for (HashMap.Entry<String, Long> entry : emotions.entrySet()) {
            emotions.replace(entry.getKey(), Math.round(entry.getValue() / (double) total * 100));
        }
        
        return emotions;
     }	
}
