import java.util.Scanner;

/**
 * This class does basic natural language (human language) processing. The
 * program poses as a therapist and asks the user to type in descriptions of
 * their problems. The program looks for patterns of words and replies with a
 * prepared response based on the matched pattern.
 *
 * &lt;p&gt;Bugs: None known.
 *
 * @author Rebekah Klemm
 */

public class Eliza {

	/**
	 * This method randomly picks one of the strings from the list. If list is
	 * null or has 0 elements then null is returned.
	 * 
	 * @param list
	 *            An array of strings to choose from.
	 * @return One of the strings from the list.
	 */
	public static String chooseString(String[] list) {
		if (list == null || list.length == 0) {
			return null;
		}
		int index = ElizaConfig.RNG.nextInt(list.length);		
		return list[index];

	}

	/**
	 * If the word is found in the wordList then the index of the word is
	 * returned, otherwise -1 is returned. If there are multiple matches the
	 * index of the first matching word is returned. If either word or wordList
	 * is null then -1 is returned. (Update 2/17) A zero length string should
	 * not be treated differently then non-zero length strings. A null value
	 * within wordList should be ignored.
	 * 
	 * @param word
	 *            A word to search for in the list.
	 * @param wordList
	 *            The list of Strings in which to search.
	 * @return The index of list where word is found, or -1 if not found.
	 */
	public static int inList(String word, String[] wordList) {
		// count is the variable used to go through the loop each time
		for (int count = 0; count < wordList.length; count++) {
			if (word.equals(wordList[count])) {
				return count;
			}
		}
		return -1;
	}

	/**
	 * Combines the Strings in list together with a space between each and
	 * returns the resulting string. If list is null then null is returned.
	 * (Update 2/17) If the list has 0 elements then return a string with length
	 * 0 (""). If any elements within the list are null or zero length strings
	 * then ignore them since we don't want more than one space in a row.
	 * 
	 * @param list
	 *            An array of words to be combined.
	 * @return A string containing all the words with a space between each.
	 */
	public static String assemblePhrase(String[] list) {
		// Using variable sentence to return the combined words.

		// Check to see if the array is null.
		if (list == null) {
			return null;
		}
		// Check to see if the array is empty (has zero elements).
		else if (list.length == 0) {
			String sentence = "";
			return (sentence);
		} else {
			// Assign first element to variable sentence, which will
			// ultimately store the entire phrase.
			String sentence = list[0];

			// Set up loop to check each element
			for (int count = 1; count < list.length; count++) {

				// Check to see if the element is null or empty string
				if (list[count] == null || list[count].equals("")) {

					// Attach the current element to the end of the phrase
				} else {
					sentence = (sentence + " " + list[count]);
				}
			}
			return (sentence);
		}
	}

	/**
	 * Returns the longest sentence, based on the String length, from the array
	 * of sentences. Removes spaces from the beginning and end of each sentence
	 * before comparing lengths. If sentences is null or has a length of 0 then
	 * null is returned. (Update 2/16) In the case of equal length longest
	 * strings, return the string that has the lowest array index. Note: String
	 * trim method may be useful.
	 * 
	 * @param sentences
	 *            The array of sentences passed in.
	 * @return The longest sentence without spaces at the beginning or end.
	 */
	public static String findLongest(String[] sentences) {

		// Return null if passed a null array or an empty (zero length) array
		if (sentences == null || sentences.length == 0) {
			return null;
		}

		// Remove spaces from beginning and end of each sentence before
		// comparing lengths. Use k as counting variable.
		for (int k = 0; k < sentences.length; k++) {
			sentences[k] = sentences[k].trim();
		}

		// Assign the first sentence as longest and then replace it if any
		// other sentence is longer.
		String longest = sentences[0];
		for (int count = 0; count < sentences.length; count++) {
			if (sentences[count].length() > longest.length()) {
				longest = sentences[count];
			}
		}

		return longest;
	}

	/**
	 * Counts the number of times the substring is in the str. Does not count
	 * overlapping substrings separately. If either parameter substring or str
	 * is null then -1 is returned. (Update 2/17) If the substring is the empty
	 * string ("") then return the length of the string.
	 * 
	 * Note: String methods indexOf may be useful for finding whether substring
	 * occurs within str. One of the indexOf methods has a parameter that says
	 * where to start looking in the str. This might be useful to find the
	 * substring again in str, once you have found a substring once.
	 * 
	 * @param str
	 *            The string to be searched.
	 * @param substring
	 *            The string to be searched for within str.
	 * @return The number of times substring is found within str or -1 if either
	 *         parameter is null parameter.
	 */
	public static int howMany(String substring, String str) {
		
		// if either parameter substring or str is null, return -1
		if (substring == null || str == null) {
			return -1;
		} else if (substring.equals("") || str.equals("")) {
			return str.length();
		}
		
		// As long as the parameters are not null, check to see if the substring
		// is contained within the string:
		else if (str.contains(substring)) {
			int firstSubstring = 0;

			// look through the string and find the substring. Add one to
			// count. Replace the string with only what is remaining after
			// what has already been counted.
			int count = 0;
			for (count = 0; firstSubstring != -1; count++) {
				firstSubstring = str.indexOf(substring);
				if (firstSubstring == -1) {
					return count;
				} else {
					str = str.substring(firstSubstring + substring.length());
				}
			}
			return count;
		} else {
			return -1;
		}
	}

	/**
	 * This method performs the following processing to the userInput. -
	 * substitute spaces for all characters but (alphabetic characters, numbers,
	 * and ' , ! ? .). - Change (,!?.) to (!). Parenthesis not included. (Update
	 * 2/17) If the userInput is null then return null otherwise the array
	 * returned should be the same length as the userInput.
	 * 
	 * @param userInput
	 *            The characters that the user typed in.
	 * @return The character array containing the valid characters.
	 */
	public static char[] filterChars(String userInput) {
		// if userInput is null, return null
		if (userInput == null) {
			return null;
		}

		// allocate a character array the size of the userInput string
		char[] charArray;
		charArray = new char[userInput.length()];

		// copy characters from the string to the array, making the necessary
		// changes, using the variable named count to loop through the whole
		// string.
		for (int count = 0; count < userInput.length(); count++) {
			charArray[count] = userInput.charAt(count);
			if (!(Character.isLetterOrDigit(charArray[count]))) {
				if (charArray[count] == ',' || charArray[count] == '!' 
						|| charArray[count] == '?' || charArray[count] == '.'){
					charArray[count] = '!';
				} else if (charArray[count] == '\'') {
					charArray[count] = '\'';

				} else {
					charArray[count] = ' ';
				}
			}
		}
		return charArray;
	}

	/**
	 * Reduces all sequences of 2 or more spaces to 1 space within the
	 * characters array. If any spaces are removed then the same number of Null
	 * character '\u0000' will fill the elements at the end of the array.
	 * 
	 * @param characters
	 *            The series of characters that may have more than one space in
	 *            a row when calling. On return the array of characters will not
	 *            have more than one space in a row and there may be '\u0000'
	 *            characters at the end of the array.
	 */
	public static void removeDuplicateSpaces(char[] characters) {

		int unicodeNull = 0; // count how many times 2 spaces occur in a row

		// loop through each element of the array (variable i) and make
		// necessary changes.
		for (int i = 0; i < characters.length; i++) {
			if ((characters[i] == ' ') && (characters[i + 1] == ' ')) {
				unicodeNull = unicodeNull + 1;
				for (int k = i; k < characters.length - 1; k++) {
					characters[k] = characters[k + 1];
				}
				characters[characters.length - unicodeNull] = '\u0000';
				i = i - 1; // to re-check for more than two ' ' in a row.
			}
		}
	}

	/**
	 * Looks for each word in words within the wordList. If any of the words are
	 * found then true is returned, otherwise false.
	 * 
	 * @param words
	 *            List of words to look for.
	 * @param wordList
	 *            List of words to look through.
	 * @return Whether one of the words was found in wordList.
	 */
	public static boolean findAnyWords(String[] words, String[] wordList) {
		

		// Look for each word in the array in the wordList
		for (int i = 0; i < words.length; i++) {
			int inList = Eliza.inList(words[i], wordList);
			if (inList != -1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method performs the following processing to the userInput and
	 * returns the longest resulting sentence. 1) Converts all characters to
	 * lower case See String toLowerCase() method. 2) Removes any extra spaces
	 * at the beginning or end of the input See String trim() method. 3)
	 * Substitute spaces for all characters but alphabetic characters, numbers,
	 * and ',.!? and then substitute ! for ,.? See filterChars method. 4) Reduce
	 * all sequences of 2 or more spaces to be one space. See
	 * removeDuplicateSpaces method. 5) Divide input into separate sentences
	 * based on ! Construct a String from a character array with String str =
	 * new String( arrayName); See String split method. Example: String[]
	 * sentences = str.split("!"); 6) Determine which sentence is longest in
	 * terms of characters and return it. See findLongest method.
	 * 
	 * (Update 2/17) If the userInput is null then null should be returned.
	 * 
	 * @param userInput
	 *            The characters that the user typed in.
	 * @return The longest sentence from the input.
	 */
	public static String initialProcessing(String userInput) {
		if (userInput == null) {
			return null;
		}

		// convert all characters to lower case
		userInput = userInput.toLowerCase();

		// remove extra spaces at beginning or end
		userInput = userInput.trim();

		// call filterChars method
		char[] userInputArray = Eliza.filterChars(userInput);

		// call removeDuplicateSpaces
		Eliza.removeDuplicateSpaces(userInputArray);

		// Divide input into separate sentences based on !
		String str = new String(userInputArray);
		String[] sentences = str.split("!");

		// Call findLongest method
		String longest = Eliza.findLongest(sentences);
		return longest;
	}

	/**
	 * This method creates a new words list replacing any words it finds in the
	 * beforeList with words in the afterList. An array of the resulting words
	 * is returned. (Update 2/17) If any parameter is null, return null.
	 * 
	 * @param words
	 *            List of words to look through.
	 * @param beforeList
	 *            List of words to look for.
	 * @param afterList
	 *            List of words to replace, if the corresponding word in the
	 *            before list is found in the words array.
	 * @return The new list of words with replacements.
	 */
	public static String[] replacePairs(String[] words, String[] beforeList, 
			String[] afterList) {
		
		// If any parameter is null, return null
		if (words == null || beforeList == null || afterList == null) {
			return null;
		}

		// Search through []words and determine if a word is found that matches
		// the words in []beforeList. If a match is found, replace it with
		// the corresponding word in []afterList.
		for (int i = 0; i < words.length; i++) {
			int match = Eliza.inList(words[i], beforeList);
			if (match != -1) {
				words[i] = afterList[match];
			}
		}

		// Combine the strings in []words together with a space between each
		// and return the resulting phrase (using assemblePhrase method).
		String strWords = Eliza.assemblePhrase(words);

		// Create an array that splits the words at each space
		String[] replacePairs = strWords.split(" ");
		return replacePairs;
	}

	/**
	 * Checks to see if the pattern matches the sentence. In other words, checks
	 * to see that all the words in the pattern are in the sentence in the same
	 * order as the pattern. If the pattern matches then this method returns the
	 * phrases before, between and after the pattern words. If the pattern does
	 * not match then null is returned.
	 * 
	 * (Update 2/17) If any parameter is null, return null.
	 * 
	 * @param pattern
	 *            The words to match, in order, in the sentence.
	 * @param sentence
	 *            Each word in the sentence.
	 * @return The phrases before, between and after the pattern words or null
	 *         if the pattern does not match the sentence.
	 */
	public static String[] findPatternInSentence(String[] pattern, 
			String[] sentence) {
		// Check for null
		if (pattern == null || sentence == null) {
			return null;
		}
		
	//Check to see if there is a pattern match		
		// check every word in the sentence against every word in the pattern.
		for (int patInd = 0, sentInd = 0; patInd < pattern.length 
				&& sentInd < sentence.length;) {
			//if the sentence word matches the pattern word
			if (sentence[sentInd].equals(pattern[patInd])) {
				
				// if you're at the end of the sentence but the pattern isn't
				// finished, return null				
				if (sentInd == sentence.length - 1 
						&& patInd != pattern.length - 1) {
					return null;
				}
				
				//if you can add one to pattern index and sentence index, do so
				if (patInd < pattern.length && sentInd < sentence.length) {
					patInd++;
					sentInd++;
				}
				
			//if the sentence word does not match the pattern word
			} else {
				//check to see if you're at the end of the sentence, return null
				if (sentInd == sentence.length - 1) {
					return null;
				//otherwise, check the next sentence word
				} else {
					sentInd++;
				}
			}
		}

	//If the program hasn't yet returned null, we know there is a pattern 
		// match, so do the following:
		
		// Create an array of user phrases that is one longer than the number
		// of pattern words and initialize to "".
		String[] userPhrases = new String[pattern.length + 1];
		for (int j = 0; j < pattern.length + 1; j++) {
			userPhrases[j] = "";
		}
		
		// Set a pattern index to the first pattern word
		int patternIndex = 0;

		// Set a phrase index to the first phrase word
		int phraseIndex = 0;

		// For each word in the sentence
		for (int k = 0; k < sentence.length; k++) {
			// if the sentence word is the same as the current pattern word then
			// increment pattern index and phrase index
			if (patternIndex < pattern.length 
					&& sentence[k].equals(pattern[patternIndex])) {
				patternIndex++;
				phraseIndex++;

				// otherwise, append the sentence word to the current
				// userphrase.
			} else {
				if (userPhrases[phraseIndex] == "") {
					userPhrases[phraseIndex] = sentence[k];
				} else {
					userPhrases[phraseIndex] = userPhrases[phraseIndex] 
							+ " " + sentence[k];
				}
			}
		}
		return userPhrases;
	}

	/**
	 * Replaces words in the phrase if they are in the
	 * ElizaConfig.POST_PROCESS_BEFORE with the corresponding words from
	 * ElizaConfig.POST_PROCESS_AFTER.
	 * 
	 * @param phrase
	 *            One or more words separated by spaces.
	 * @return A string composed of the words from phrase with replacements.
	 *         (Update 2/17) If the parameter is null then return null.
	 */
	public static String prepareUserPhrase(String phrase) {
		if (phrase == null) {
			return null;
		}
		// use the string split method to separate words in the phrase using " "
		String[] split = phrase.split(" ");

		// Call replacePairs method to look through and replace words using
		// the POST_PROCESS arrays.
		String[] replaced = Eliza.replacePairs(split, 
				ElizaConfig.POST_PROCESS_BEFORE, ElizaConfig.POST_PROCESS_AFTER);

		// Call assemblePhrase method to reassemble the word list into one
		// String.
		String prepareUserPhrase = Eliza.assemblePhrase(replaced);
		return prepareUserPhrase;
	}

	/**
	 * Prepares a response based on the draftResponse. If draftResponse contains
	 * <1>, <2>, <3> or <4> then the corresponding userPhrase is substituted for
	 * the <1>, <2>, etc. The userPhrase however must be prepared by exchanging
	 * words that are in ElizaConfig.POST_PROCESS_BEFORE with the corresponding words
	 * from ElizaConfig.POST_PROCESS_AFTER. (Update 2/17) If draftResponse is null,
	 * then return null. If userPhrases is null then return draftResponse.
	 * 
	 * @param draftResponse
	 *            A response sentence potentially containing <1>, <2> etc.
	 * @param userPhrases
	 *            An array of phrases from the user input.
	 * @return A string composed of the words from sentence with replacements.
	 */
	public static String prepareResponse(String draftResponse, 
			String[] userPhrases) {
		
		//check for null
		if (draftResponse == null) {
			return null;
		}
		if (userPhrases == null) {
			return draftResponse;
		}

		//if there are no replacement symbols, return draftResponse
		if (!draftResponse.contains("<1>") && !draftResponse.contains("<2>") 
				&& !draftResponse.contains("<3>") 
				&& !draftResponse.contains("<4>")) {
			return draftResponse;
		}

		// Call the String contains method to determine if a draftResponse
		// contains <1>, <2>, etc. and replace it with the correct (updated)
		// userPhrase
		String replace;
		if (draftResponse.contains("<1>")) {
			replace = Eliza.prepareUserPhrase(userPhrases[0]);
			draftResponse = draftResponse.replaceFirst("<1>", replace);
		}
		if (draftResponse.contains("<2>")) {
			replace = Eliza.prepareUserPhrase(userPhrases[1]);
			draftResponse = draftResponse.replaceFirst("<2>", replace);
		}
		if (draftResponse.contains("<3>")) {
			replace = Eliza.prepareUserPhrase(userPhrases[2]);
			draftResponse = draftResponse.replaceFirst("<3>", replace);
		}
		if (draftResponse.contains("<4>")) {
			replace = Eliza.prepareUserPhrase(userPhrases[3]);
			draftResponse = draftResponse.replaceFirst("<4>", replace);
		}

		return draftResponse;

	}

	/**
	 * Looks through ElizaConfig.RESPONSE_TABLE to find the first pattern that
	 * matches the words. When a pattern is matched then a response is randomly
	 * chosen from the corresponding list of responses. If the response has a
	 * parameter denoted with <1>, <2> etc. the parameter is replaced with the
	 * 0th, 1st, etc String from the user phrases returned by the
	 * findPatternInSentence method. (Update 2/17) If words parameter is null
	 * then choose a response from NO_MATCH_RESPONSES and return.
	 * 
	 * @param words
	 *            The words of a sentence.
	 * @return The completed response ready to be shown to the user.
	 */
	public static String matchResponse(String[] words) {
		// if words parameter is null then return a response from
		// NO_MATCH_RESPONSE
		if (words[0] == null) {
			String randomResponse 
			= Eliza.chooseString(ElizaConfig.NO_MATCH_RESPONSES);
			return randomResponse;
			
		}

		// for each response set in the RESPONSE TABLE
		for (int i = 0; i < ElizaConfig.RESPONSE_TABLE.length; i++) {
			// for (int j = 0; j < ElizaConfig.RESPONSE_TABLE[i].length; j++){
			// if the pattern matches the words (findPatternInSentence)
			String[] pattern = 
					Eliza.findPatternInSentence(ElizaConfig.RESPONSE_TABLE[i][0], 
							words);

			// choose a draft response from the response set (chooseString)
			if (pattern != null) {
				String draftResponse = 
						Eliza.chooseString(ElizaConfig.RESPONSE_TABLE[i][1]);

				// Prepare the response by replacing <1>, <2>, etc with user
				// phrases found by matching the input to the pattern
				// (prepareResponse)
				String response = Eliza.prepareResponse(draftResponse, pattern);
				return response;
			}

			// if you reach the end of the response table without finding a
			// pattern choose a response from No_MATCH_RESPONSES
			if (i == ElizaConfig.RESPONSE_TABLE.length - 1) {
				String randomResponse 
				= Eliza.chooseString(ElizaConfig.NO_MATCH_RESPONSES);
				return randomResponse;				
			}
			
		}

		return null;
	}

	

	/**
	 * Takes the input as a parameter and returns a response. If any of the
	 * QUIT_WORDS are found then null is returned. (Update 2/17) If userInput is
	 * null then return null;
	 * 
	 * @param userInput
	 *            The problem sentence(s) the user types in.
	 * @return A response string to be shown to the user or null if a QUIT_WORD
	 *         is found.
	 */
	public static String processInput(String userInput) {
		//If user input is null, return null
		if(userInput == null){
			return null;
		}
		
		// Do initial processing including removing punctuation and
		// selecting the longest sentence.
		String sentence = Eliza.initialProcessing(userInput);

		// Split the sentence into a list of words.
		String[] split = sentence.split(" ");

		// If any of the ElizaConfig.QUIT_WORDS are found then return null.
		if (Eliza.findAnyWords(split, ElizaConfig.QUIT_WORDS)) {
			return null;
		}

		// Replace any words in ElizaConfig.PRE_PROCESS_BEFORE with corresponding
		// words in ElizaConfig.PRE_PROCESS_AFTER
		String [] updatedSplit = Eliza.replacePairs(split, 
				ElizaConfig.PRE_PROCESS_BEFORE, 	ElizaConfig.PRE_PROCESS_AFTER);

		// Call matchResponse to obtain a response.
		String response = Eliza.matchResponse(updatedSplit);

		return response;
	}

	/**
	 * This method displays an INITIAL_MESSAGE, accepts typed input, calls the
	 * processInput method, and prints out the response (of processInput) until
	 * the response is null at which point the FINAL_MESSAGE is shown and the
	 * program terminates.
	 */
	public static void interactive() {

		// Display INITIAL_MESSAGE
		System.out.println("Eliza: " + ElizaConfig.INITIAL_MESSAGE);
		System.out.print("Patient: ");
		// Create a scanner
		Scanner scnr = new Scanner(System.in);
		String response;
		do {
			String input = scnr.nextLine();

			// Call the processInput method
			response = Eliza.processInput(input);

			// Print out the response of processInput until the response is null
			if (response != null) {
				System.out.println("Eliza: " + response);
				System.out.print("Patient: ");

			}
		} while (response != null);

		// when the response is null, show FINAL_MESSAGE		
		System.out.println("Eliza: " + ElizaConfig.FINAL_MESSAGE);
		
		
		// Close the scanner
		scnr.close();
	}

	/**
	 * Program execution starts here.
	 * 
	 * @param args
	 *            unused
	 */
	public static void main(String[] args) {

		interactive();

	}
}
