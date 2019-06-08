///////////////////////////////////////////////////////////////////////////////
// Note: I (Rebekah Klemm) did not create this file
//
//Config.java for Program2: Eliza (CS302, Spring 2016)
//
// This file contains constants that will be used to control parameters used by
// your Eliza program.  This file will not be handed in, because our own
// Config.java files will be used to test your program.  For this reason, your
// code must reference these constant values by the names defined below.
//
// DO NOT EDIT THE VARIABLE NAMES OR TYPES, OR ADD VARIABLES TO THIS FILE.
//
// Your program may assume that the constant values used to test your code are
// all of the correct type.
//
// Reference: Strings here are derived from:
//            http://www.chayden.net/eliza/instructions.txt
//			  The design here emphasizes the use of arrays, methods and 
//			  parameter passing without the use of a stack, keeping state 
//			  or more sophisticated pattern matching.
// 
///////////////////////////////////////////////////////////////////////////////

import java.util.Random;


public class ElizaConfig {
	
	/**
	 * The name of the program and user name used when displaying output
	 * from the program and prompting for user input.
	 */
	static final String PROGRAM_NAME = "Eliza: ";
	static final String USER_NAME = "Patient: ";
	/**
	 * Eliza says this when it starts.
	 */
	static final String INITIAL_MESSAGE = "How do you do. Please tell me your problem.";
	
	/**
	 * Eliza says this when it finishes.
	 */
	static final String FINAL_MESSAGE = "Goodbye. Thank you for talking to me.";

	/**
	 * If the user input contains any of these words then Eliza finishes.
	 */
	static final String [] QUIT_WORDS = {"bye","goodbye","quit"}; 
	
	/** 
	 * User input is scanned for the items in PRE_PROCESS_BEFORE and, if found,
	 * replaced with the corresponding item in PRE_PROCESS_AFTER.  Notice that
	 * one word may be replaced with multiple words. For example "i'm" is
	 * replaced with "i am".
	 * These are called parallel arrays since their elements correspond.
	 */
	static final String [] PRE_PROCESS_BEFORE = {"dont",  "cant",   "can't",  
			"want",   "need",   "noone",  "everybody", "wont", "recollect", 
			"dreamt",  "dreams", "maybe",   "how",  "when", "certainly", 
			"machine",  "computers", "were", "you're",  "i'm", "same",   
			"sorry" };
	static final String [] PRE_PROCESS_AFTER =  {"don't", "cannot", "cannot", 
			"desire", "desire", "nobody", "everyone",  "won't", "remember", 
			"dreamed", "dream",  "perhaps", "what", "what", "yes",       
			"computer", "computer",  "was",  "you are", "i am", "alike", 
			"apologize"};
	
	/** 
	 * Before user input is utilized in a response, it is scanned 
	 * for the items in POST_PROCESS_BEFORE and replaced with the items 
	 * with the same index in POST_PROCESS_AFTER.
	 */
	static final String [] POST_PROCESS_BEFORE = {"am",  "your", "me", 
			"myself",   "yourself", "i",   "you", "my",   "i'm"};
	static final String [] POST_PROCESS_AFTER =  {"are", "my",   "you", 
			"yourself", "myself",   "you", "I",   "your", "you are"};

	/**
	 * The following RESPONSE_TABLE is a list of response sets.
	 * Each response set is composed of a pattern and a list of 
	 * responses.
	 * {  
	 *	 {{ patternA1 }, 
	 *         { responseA1, responseA2, responseA3}},
	 *	 {{ patternB1 }, 
	 *         { responseB1, responseB2, responseB3}}
	 * }
	 *
	 * A pattern contains one or more words that are looked for within the
	 * user's typed in sentence. When a pattern matches then a response is 
	 * selected from the corresponding response list. <1>, <2> etc from the 
	 * response are replaced with phrases from the user's sentence.
  	*/
	static final String [][][] RESPONSE_TABLE = {
		{{"computer"}, 
			{ "Do computers worry you?", "Why do you mention computers?", "What do you think machines have to do with your problem?", "Don't you think computers can help people?", "What about machines worrys you?", "What do you think about machines?"}},

		{{"name"}, 
			{ "I am not interested in names.", "I don't care about names -- please continue."}},
		
		{{"alike"}, 
			{"In what way?", "What resemblence do you see?", "What does that similarity suggest to you?", "What other connections do you see?", "What do you suppose that resemblence means?", "What is the connection, do you suppose?", "Could here really be some connection?", "How?"}},
		
		{{"you", "remind", "me", "of"}, 
			{ "In what way?", "What resemblence do you see?", "What does that similarity suggest to you?", "What other connections do you see?", "What do you suppose that resemblence means?", "What is the connection, do you suppose?", "Could here really be some connection?", "How?"}},
		
		{{"you", "are"}, 
			{ "What makes you think I am <3>?", "Does it please you to believe I am <3>?", "Do you sometimes wish you were <3>?", "Perhaps you would like to be <3>."}},
		
		{{"you", "me"}, 
			{ "Why do you think I <2> you?", "You like to think I <2> you -- don't you?", "What makes you think I <2> you?", "Really, I <2> you?", "Do you wish to believe I <2> you?", "Suppose I did <2> you -- what would that mean?", "Does someone else believe I <2> you?"}},
		
		{{"i", "you"},
				{"Why do you think you <2> me?"}},
		
		{{"i", "dreamed"}, 
			{ "Really, <3>?", "Have you ever fantasized <3> while you were awake?", "Have you ever dreamed <3> before?"}},
		
		{{"dream"}, 
			{ "What does that dream suggest to you?", "Do you dream often?", "What persons appear in your dreams?", "Do you believe that dreams have something to do with your problems?"}},
		
		{{"apologize"}, 
			{ "Please don't apologize.", "Apologies are not necessary.", "Apologies are not required."}},
		
		{{"perhaps"}, 
			{ "You don't seem quite certain.", "Why the uncertain tone?", "Can't you be more positive?", "You aren't sure?", "Don't you know?"}},
		
		{{"hello"}, 
			{ "How do you do.  Please state your problem.", "Hi. What seems to be your problem?"}},

		{{"hi"}, 
			{ "How do you do.  Please state your problem.", "Hi. What seems to be your problem?"}},

		{{"hey"}, 
			{ "How do you do.  Please state your problem.", "Hi. What seems to be your problem?"}},

		{{"heya"}, 
			{ "How do you do.  Please state your problem.", "Hi. What seems to be your problem?"}},

		{{"always"}, 
			{ "Can you think of a specific example?", "When?", "What incident are you thinking of?", "Really, always?"}},

		{{"because"}, 
			{ "Is that the real reason?", "Don't any other reasons come to mind?", "Does that reason seem to explain anything else?", "What other reasons might there be?"}},

		{{"what"}, 
			{ "Why do you ask?", "Does that question interest you?", "What is it you really wanted to know?", "Are such questions much on your mind?", "What answer would please you most?", "What do you think?", "What comes to mind when you ask that?", "Have you asked such questions before?", "Have you asked anyone else?"}},

		{{"yes"}, 
			{ "You seem to be quite positive.", "You are sure.", "I see.", "I understand."}},
		
		{{"no"}, 
			{ "Are you saying no just to be negative?", "You are being a bit negative.", "Why not?", "Why 'no'?"}},

		{{"i", "remember"}, 
				{ "Do you often think of <3>?", "Does thinking of <3> bring anything else to mind?", "What else do you recollect?", "Why do you recollect <3> just now?", "What in the present situation reminds you of <3>?", "What is the connection between me and <3>?"}},

		{{"my", "family"}, 
			{ "Tell me more about your family.", "Who else in your family <3>?", "Your family?", "What else comes to mind when you think of your family?"}},
		{{"my", "mother"}, 
			{ "Tell me more about your family.", "Who else in your family <3>?", "Your mother?", "What else comes to mind when you think of your mother?"}},
		{{"my", "mom"}, 
			{ "Tell me more about your family.", "Who else in your family <3>?", "Your mom?", "What else comes to mind when you think of your mom?"}},
		{{"my", "father"}, 
			{ "Tell me more about your family.", "Who else in your family <3>?", "Your father?", "What else comes to mind when you think of your father?"}},
		{{"my", "dad"}, 
			{ "Tell me more about your family.", "Who else in your family <3>?", "Your dad?", "What else comes to mind when you think of your dad?"}},
		{{"my", "sister"}, 
			{ "Tell me more about your family.", "Who else in your family <3>?", "Your sister?", "What else comes to mind when you think of your sister?"}},
		{{"my", "brother"}, 
			{ "Tell me more about your family.", "Who else in your family <3>?", "Your brother?", "What else comes to mind when you think of your brother?"}},
		{{"my", "wife"}, 
			{ "Tell me more about your family.", "Who else in your family <3>?", "Your wife?", "What else comes to mind when you think of your wife?"}},
		{{"my", "children"}, 
			{ "Tell me more about your family.", "Who else in your family <3>?", "Your children?", "What else comes to mind when you think of your children?"}},
		{{"my", "child"}, 
			{ "Tell me more about your family.", "Who else in your family <3>?", "Your child?", "What else comes to mind when you think of your child?"}},
		
		{{"do", "you", "remember"}, 
			{ "Did you think I would forget <4>?", "Why do you think I should recall <4> now?", "What about <4>?", "You mentioned <4>?"}},
		
		{{"are", "you"}, 
			{ "Why are you interested in whether I am <3> or not?", "Would you prefer if I weren't <3>?", "Perhaps I am <3> in your fantasies.", "Do you sometimes think I am <3>?"}},

		{{"was", "i"}, 
			{ "What if you were <3>?", "Do you think you were <3>?", "Were you <3>?", "What would it mean if you were <3>?", "What does <3> suggest to you?"}},

		{{"i", "was"}, 
			{ "Were you really?", "Why do you tell me you were <3> now?", "Perhaps I already know you were <3>."}},

		{{"was", "you"}, 
			{ "Would you like to believe I was <3>?", "What suggests that I was <3>?", "What do you think?", "Perhaps I was <3>.", "What if I had been <3>?"}},
		
		{{ "i", "am", "happy"}, 
			{ "How have I helped you to be happy?", "Has your treatment made you happy?", "What makes you happy just now?", "Can you explain why you are suddenly happy?"}},

		{{"i", "am", "elated"}, 
			{ "How have I helped you to be elated?", "Has your treatment made you elated?", "What makes you elated just now?", "Can you explain why you are suddenly elated?"}},

		{{"i", "am", "glad"}, 
			{ "How have I helped you to be glad?", "Has your treatment made you glad?", "What makes you glad just now?", "Can you explain why you are suddenly glad?"}},

		{{"i", "am", "better"}, 
			{ "How have I helped you to be better?", "Has your treatment made you better?", "What makes you better just now?", "Can you explain why you are suddenly better?"}},
		
		{{"i", "am", "sad"}, 
			{ "I am sorry to hear that you are sad.", "Do you think that coming here will help you not to be sad?", "I'm sure it's not pleasant to be sad.", "Can you explain what made you sad?"}},

		{{"i", "am", "unhappy"}, 
			{ "I am sorry to hear that you are unhappy.", "Do you think that coming here will help you not to be unhappy?", "I'm sure it's not pleasant to be unhappy.", "Can you explain what made you unhappy?"}},

		{{"i", "am", "depressed"}, 
			{ "I am sorry to hear that you are depressed.", "Do you think that coming here will help you not to be depressed?", "I'm sure it's not pleasant to be depressed.", "Can you explain what made you depressed?"}},

		{{"i", "am", "sick"}, 
			{ "I am sorry to hear that you are sick.", "Do you think that coming here will help you not to be sick?", "I'm sure it's not pleasant to be sick.", "Can you explain what made you sick?"}},

		{{"i", "am"}, 
			{ "Is it because you are <3> that you came to me?", "How long have you been <3>?", "Do you believe it is normal to be <3>?", "Do you enjoy being <3>?"}},

		{{"i", "don't"}, 
			{ "Don't you really <3>?", "Why don't you <3>?", "Do you wish to be able to <3>?", "Does that trouble you?"}},

		{{"do", "i", "feel"}, 
			{ "Tell me more about such feelings.", "Do you often feel <4>?", "Do you enjoy feeling <4>?", "Of what does feeling <4> remind you?"}},
		
		{{"am", "i"}, 
			{ "Do you believe you are <3>?", "Would you want to be <3>?", "Do you wish I would tell you you are <2>?", "What would it mean if you were <3>?"}},

		{{"i", "desire"}, 
			{"What would it mean to you if you got <3>?", "Why do you want <3>?", "Suppose you got <3> soon?", "What if you never got <3>?", "What would getting <3> mean to you?", "What does wanting <3> have to do with this discussion?"}},

		{{"i", "cannot"},
			{"How do you think that you can't <3>?", "Have you tried?", "Perhaps you could <3> now.", "Do you really want to be able to <3>?"}},

		{{"i", "belief", "i"},
			{"Do you really think so?", "But you are not sure you <4>.", "Do you really doubt you <4>?"}},
		{{"i", "feel", "i"},
			{"Do you really think so?", "But you are not sure you <4>.", "Do you really doubt you <4>?"}},
		{{"i", "think", "i"},
			{"Do you really think so?", "But you are not sure you <4>.", "Do you really doubt you <4>?"}},
		{{"i", "believe", "i"},
			{"Do you really think so?", "But you are not sure you <4>.", "Do you really doubt you <4>?"}},
		{{"i", "wish", "i"},
			{"Do you really think so?", "But you are not sure you <4>.", "Do you really doubt you <4>?"}},

		{{"i", "belief", "you"},
			{"In what way?", "What resemblence do you see?", "What does that similarity suggest to you?", "What other connections do you see?", "What do you suppose that resemblence means?", "What is the connection, do you suppose?", "Could here really be some connection?", "How?"}},
		{{"i", "feel", "you"},
			{"In what way?", "What resemblence do you see?", "What does that similarity suggest to you?", "What other connections do you see?", "What do you suppose that resemblence means?", "What is the connection, do you suppose?", "Could here really be some connection?", "How?"}},
		{{"i", "think", "you"},
			{"In what way?", "What resemblence do you see?", "What does that similarity suggest to you?", "What other connections do you see?", "What do you suppose that resemblence means?", "What is the connection, do you suppose?", "Could here really be some connection?", "How?"}},
		{{"i", "believe", "you"},
			{"In what way?", "What resemblence do you see?", "What does that similarity suggest to you?", "What other connections do you see?", "What do you suppose that resemblence means?", "What is the connection, do you suppose?", "Could here really be some connection?", "How?"}},
		{{"i", "wish", "you"},
			{"In what way?", "What resemblence do you see?", "What does that similarity suggest to you?", "What other connections do you see?", "What do you suppose that resemblence means?", "What is the connection, do you suppose?", "Could here really be some connection?", "How?"}},
		{{"you", "remind", "me", "of"},
			{"In what way?", "What resemblence do you see?", "What does that similarity suggest to you?", "What other connections do you see?", "What do you suppose that resemblence means?", "What is the connection, do you suppose?", "Could here really be some connection?", "How?"}},

		{{"i"}, 
			{ "You say <2>?", "Can you elaborate on that?", "Do you say <2> for some special reason?", "That's quite interesting."}},
		
		{{"am"}, 
				{ "Why do you say 'am'?", "I don't understand that." }},

		{{"you"}, 
				{ "We were discussing you -- not me.", "Oh, I <2>?", "You're not really talking about me -- are you?", "What are your feelings now?"}},

		{{"are"}, 
				{ "Did you think they might not be <2>?", "Would you like it if they were not <2>?", "What if they were not <2>?", "Possibly they are <2>."}},
				
		{{"your"}, 
				{ "Why are you concerned over my <2>?", "What about your own <2>?", "Are you worried about someone else's <2>?", "Really, my <2>?"}},

		{{"if"}, 
				{ "Do you think its likely that <2>?", "Do you wish that <2>?", "What do you know about <2>?", "Really, if <2>?"}},
				
		{{"my"}, 
				{ "Your <2>?", "Why do you say your <2>?",  "Is it important that your <2>?", "Lets discuss further why your <2>."}},
		
		{{"everyone"},
				{ "Can you think of anyone in particular?", "Who, for example?", "Are you thinking of a very special person?", "Who, may I ask?", "Someone special perhaps?", "You have a particular person in mind, don't you?", "Who do you think you're talking about?"}},

		{{"nobody"},
				{ "Can you think of anyone in particular?", "Who, for example?", "Are you thinking of a very special person?", "Who, may I ask?", "Someone special perhaps?", "You have a particular person in mind, don't you?", "Who do you think you're talking about?"}}

	};
	
	/**
	 * When the user input doesn't match any of the patterns in the 
	 * RESPONSE_TABLE then one of the following responses is randomly selected.
	 */
	static final String [] NO_MATCH_RESPONSES = 
		{"I'm not sure I understand you fully.", "Please go on.", 
				"What does that suggest to you?", 
				"Do you feel strongly about discussing such things?"};			
	
    /** 
     * Used to seed the java.util.Random object for generating
     * random numbers. By seeding the Random generator, we can predict
     * the "pseudo-random" values that will be generated. This 
     * predictability aids in program development and allows for automated
     * grading.
     * Update 2/17
     */	
	static final int SEED = 123;
	
    /** 
     * Use the RNG named constant in your methods that require 
     * randomly choosing a response. In this project only one method, 
     * chooseString, requires the random number generator.
	 */
	static final Random RNG = new Random( SEED);
}
