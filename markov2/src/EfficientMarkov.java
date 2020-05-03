import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * @author Lindsay Maggioncalda
 * lnm22
 */

public class EfficientMarkov extends BaseMarkov {
	//new instance variables
	private Map<String, ArrayList<String>> myMap;
	

	//constructor with int parameter
	public EfficientMarkov(int order) {
		super(order);
		myRandom = new Random(RANDOM_SEED);
		myMap = new HashMap<String, ArrayList<String>>();
	}
	
	//default constructor
	public EfficientMarkov() {
		this(3);
	}
	
	@Override
	/**
	 * Uses a map to store every set of myOrder-character substrings and an ArrayList
	 * of characters that directly follows that substring. Each key is a substring and each value
	 * is a list of characters (type String) that directly follow that specific
	 * substring of myOrder characters.
	 * @param a String of text that comes from a file.
	 */
	public void setTraining(String text) {
		myText = text;
		myMap.clear();
		for (int k = 0; k < (text.length() - myOrder); k++) {
			String current = text.substring(k, k + myOrder);
			String followVal = text.substring(k + myOrder, k + myOrder + 1);
			if (! myMap.containsKey(current)) {
				ArrayList<String> listOfVal = new ArrayList<String>();
				listOfVal.add(followVal);
				myMap.put(current,listOfVal);
			
			}
			else {
				myMap.get(current).add(followVal);
			}
		}
		String lastGram = text.substring(text.length() - myOrder);
		if (! myMap.containsKey(lastGram)) {
			ArrayList<String> listOfVal = new ArrayList<String>();
			listOfVal.add(PSEUDO_EOS);
			myMap.put(lastGram,listOfVal);
			//System.out.println("PSEUDO");
		}
		else {
			myMap.get(lastGram).add(PSEUDO_EOS);
			//System.out.println("PSEUDO");
			
		}			
	}
	
	@Override
	/**
	 * Uses myMap, where each key is a substring with myOrder characters, and each value is 
     * an ArrayList of characters (type String) that follow that specific Word Gram in the text,
     * to return the list of words that follow a given Word Gram.
	 * @param key is a substring with myOrder characters
	 * @return an ArrayList of all the characters (type String) that follow that substring in the text
	 */
	public ArrayList<String> getFollows(String key){
		if (! myMap.containsKey(key)) {
			throw new NoSuchElementException(key+" not in map");
		}
		else {
			return myMap.get(key);
		}
	}
}
