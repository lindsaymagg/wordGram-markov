import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * @author Lindsay Maggioncalda
 * lnm22
 */


public class EfficientWordMarkov extends BaseWordMarkov {
	//new instance variables
		private Map<WordGram, ArrayList<String>> myMap;
		

		//constructor with int parameter
		public EfficientWordMarkov(int order) {
			super(order);
			myRandom = new Random(RANDOM_SEED);
			myMap = new HashMap<WordGram, ArrayList<String>>();
		}
		
		//default constructor
		public EfficientWordMarkov() {
			this(3);
		}
		
		@Override
		/**
		 * Splits text into words, and stores each word in an array myWords.
		 * Then, uses a map to store every set of myOrder words (a Word Gram) and an ArrayList
		 * of words that directly follows that Word Gram. Each key is a Word Gram and each value
		 * is a list of words that follow that specific myOrder-order Word Gram.
		 * @param a String of text that comes from a file.
		 *
		 */
		public void setTraining(String text) {
			myWords = text.split("\\s+");
			myMap.clear();
			for (int k = 0; k < (myWords.length - myOrder); k++) {
				WordGram current = new WordGram(myWords, k, myOrder);
				String followVal = myWords[k+myOrder];
				if (! myMap.containsKey(current)) {
					ArrayList<String> listOfVal = new ArrayList<String>();
					listOfVal.add(followVal);
					myMap.put(current,listOfVal);
				
				}
				else {
					myMap.get(current).add(followVal);
				}
			}
			WordGram lastWGram = new WordGram(myWords, myWords.length - myOrder, myOrder);
			if (! myMap.containsKey(lastWGram)) {
				ArrayList<String> listOfVal = new ArrayList<String>();
				listOfVal.add(PSEUDO_EOS);
				myMap.put(lastWGram,listOfVal);
				//System.out.println("PSEUDO");
			}
			else {
				myMap.get(lastWGram).add(PSEUDO_EOS);
				//System.out.println("PSEUDO");
			}			
		}
		
		@Override
		/**
		 * Uses myMap, where each key is a Word Gram of order myOrder and each value is 
		 * an ArrayList of words that follow that specific Word Gram in the text, to return
		 * the list of words that follow a given Word Gram.
		 * @param key is a Word Gram 
		 * @return an ArrayList of all the words that follow that Word Gram in the text
		 *
		 */
		public ArrayList<String> getFollows(WordGram key){
			if (! myMap.containsKey(key)) {
				throw new NoSuchElementException(key+" not in map");
			}
			else {
				return myMap.get(key);
			}
		}
	
	
}
