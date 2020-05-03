/**
 * WordGram objects represent a k-gram of strings/words.
 * 
 * @author Lindsay Maggioncalda, lnm22
 *
 */

public class WordGram {
	
	private String[] myWords;   
	private String myToString;  // after turning myWords into a printable String
	private int myHash;         // after giving hash code based on String form of Word Gram

	/**
	 * Create WordGram using strings from a source. Take strings 
	 * starting at index start and take size words. It is an object
	 * that holds size words.
	 * @param source
	 * @param start
	 * @param size
	 */
	public WordGram(String[] source, int start, int size) {
		myWords = new String[size];
		//fill the array with values, starting at int start
		for (int k = 0; k < size; k++) {
			myWords[k] = source[start+k];
		}
		myToString = null;
		myHash = 0;
	}

	/**
	 * Return string at specific index in this WordGram
	 * @param index in range [0..length() ) for string 
	 * @return string at index
	 */
	public String wordAt(int index) {
		if (index < 0 || index >= this.length()) {
			throw new IndexOutOfBoundsException("bad index in wordAt "+index);
		}
		return myWords[index];
	}

	/**
	 * Finds the order of WordGram/how many words are in it
	 * Does this by finding the size of the array that stores the strings (myWords)
	 * @return
	 */
	public int length(){
		int size = myWords.length;
		return size;
	}


	@Override
	public boolean equals(Object o) {
		if (! (o instanceof WordGram) || o == null){
			return false;
		}
		WordGram wg = (WordGram) o;
		for (int k = 0; k < this.length(); k++) {
			if (! myWords[k].equals(wg.wordAt(k))){
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode(){
		if (myHash == 0) {
			myHash = this.toString().hashCode();
		}
		return myHash;
	}
	

	/**
	 * Create WordGram by shifting over one value in the array
	 * and adding String last as the final element in the WordGram
	 * @param last is last String of returned WordGram
	 * @return
	 */
	public WordGram shiftAdd(String last) {
		//make a new array to use as a source
		String[] newWords = new String[this.length()];
		for (int x = 0; x < this.length(); x++) {
			if (x == (this.length()-1)) {
				newWords[x] = null;
			}
			else{newWords[x] = myWords[x+1];			
			}
		}
		newWords[newWords.length-1] = last;
		//create WordGram using myWords as a source, have the starting point be 0, and the should should be the same)
		WordGram wg = new WordGram(newWords,0,this.length());
		return wg;
	}

	@Override
	public String toString(){
		if (myToString == null) {
			myToString = String.join(" ", myWords);	
		}
		return myToString;
	}
}
