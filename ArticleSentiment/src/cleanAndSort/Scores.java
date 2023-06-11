package cleanAndSort;

public class Scores {
	//anger,anticipation,disgust,fear,joy,sadness,suprise,trust,negative,positive
	private int anger;
	private int anticipation;
	private int disgust;
	private int fear;
	private int joy;
	private int sadness;
	private int suprise;
	private int trust;
	private int negative;
	private int positive;
	public Scores() {
		this.anger = 0;
		this.anticipation = 0;
		this.disgust = 0;
		this.fear = 0;
		this.joy = 0;
		this.sadness = 0;
		this.suprise = 0;
		this.trust = 0;
		this.negative = 0;
		this.positive = 0;
	}
	
	//increment values then reset emotion position in total since it is at nine
	public int hasNine(int num) {
        String numberString = String.valueOf(num);
        int position = numberString.indexOf('9');
    	//anger,anticipation,disgust,fear,joy,sadness,suprise,trust,negative,positive
        switch(position) {
	        case 9: anger++;
	        	break;
	        case 8: anticipation++;
	        	break;
	        case 7: disgust++;
	        	break;
	        case 6: fear++;
	        	break;
	        case 5: joy++;
	        	break;
	        case 4: sadness++;
	        	break;
	        case 3: suprise++;
	        	break;
	        case 2: trust++;
	        	break;
	        case 1: negative++;
	        	break;
	        case 0: positive++;
	        	break;
        }
        //reset emotion position value in total variable and return for further usage
        String newString = numberString.replace('9', '0');
        return Integer.parseInt(newString);
	}
	
	public void wrapUp(int num) {
		int i = 0;

		while (num > 0) {
			int amount = num % 10;
		    num /= 10;
		    //System.out.println(amount);
		    switch(i) {
		   		case 0: anger+=amount;
		        	break;
		        case 1: anticipation+=amount;
		        	break;
		        case 2: disgust+=amount;
		        	break;
		        case 3: fear+=amount;
		        	break;
		        case 4: joy+=amount;
		        	break;
		        case 5: sadness+=amount;
		        	break;
		        case 6: suprise+=amount;
		        	break;
		        case 7: trust+=amount;
		        	break;
		        case 8: negative+=amount;
		        	break;
		        case 9: positive+=amount;
		        	break;
		    }
		    i++;
		}
	}
	
	public void printResults() {
		int total = anger+anticipation+disgust+fear+joy+sadness+suprise+trust+negative+positive;
		System.out.println(anger%total + "% emotion of anger.");
		System.out.println(anticipation%total + "% emotion of anticipation.");
		System.out.println(disgust%total + "% emotion of disgust.");
		System.out.println(fear%total + "% emotion of fear.");
		System.out.println(joy%total + "% emotion of joy.");
		System.out.println(sadness%total + "% emotion of sadness.");
		System.out.println(suprise%total + "% emotion of surprise.");
		System.out.println(trust%total + "% emotion of trust.");
		System.out.println(negative%total + "% emotion of negativity.");
		System.out.println(positive%total + "% emotion of positivity.");
	}
}
