import java.io.*;
import java.util.*;

public class Main {

	public static void main (String[] args) throws IOException {
		
        Ranks rank = Ranks.get("7"); 

	}
	
	 private enum Ranks {
	 	
	        TWO("2", 1),
	        THREE("3", 2),
	        FOUR("4", 3),
	        FIVE("5", 4),
	        SIX("6", 5),
	        SEVEN("7", 6),
	        EIGHT("8", 7),
	        NINE("9", 8),
	        TEN("10", 9),
	        JACK("J", 10),
	        QUEEN("Q", 20),
	        KING("K", 30),
	        ACE("A", 40);

	        private final String abbr;
	        private final int value;


	        Ranks(String label, int weight) {
	            abbr = label;
	            value = weight;
	        }


	        int weight() {
	            return value;
	        }


	        String label() {
	            return abbr;
	        }


	        static Ranks get(final String card) {
	            String trimmed = card.trim();
	            char mark = trimmed.charAt(0);

	            for (Ranks rank : values()) {
	                if (rank.abbr.charAt(0) == mark) {
	                    return rank;
	                }
	            }

	            throw new IllegalArgumentException("Unknown card rank \'" + mark + '\'');
	        }
	    }
	
	

}

