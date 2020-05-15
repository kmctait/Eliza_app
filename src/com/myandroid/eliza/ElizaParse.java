package com.myandroid.eliza;

public class ElizaParse {

	private String blurb[] = {"Weizenbaum's Eliza Chat Program","Adapted for Android by Kevin McTait","***********************************"};
	
	public final String introMessage = "HI, I'M ELIZA. WHAT'S YOUR PROBLEM?";

	int n1 = 36;
	int n2 = 14;
	int n3 = 112;
	int wordInOutPairsIndex = 7;

	int s[] = new int[n1 + 1];
	int r[] = new int[n1 + 1];
	int n[] = new int[n1 + 1];
	
	String wordIn[] = new String[wordInOutPairsIndex];
	String wordOut[] = new String[wordInOutPairsIndex];

	public ElizaParse() {
		init();
	}
	
	void init() {
		int i;

		for (i = 0; i < wordInOutPairsIndex; i++) {
			wordIn[i] = wordInOutPairs[i][0];
			wordOut[i] = wordInOutPairs[i][1];
		}

		for (int j = 1; j <= n1; j++) {
			s[j] = rightReplies[j * 2 - 2];
			int l = rightReplies[j * 2 - 1];
			r[j] = s[j];
			n[j] = s[j] + l - 1;
		}
	}
	
	public String[] getBlurb() {
		return blurb;
	}

	public String handleLine(String input) {
		input = "  " + input.toUpperCase() + "  ";

		// Remove apostrophes = Line 210-210
		input = removeChar(input, '\'');

		if (input.indexOf("SHUT") >= 0) {
			return "O.K. IF YOU FEEL THAT WAY I'LL SHUT UP....";
		}

		if (input.equals(lastline)) {
			return "PLEASE DON'T REPEAT YOURSELF!";
		}

		lastline = input;

		int pos = 0;
		String c = "error";
		int k;
		String foundKeyword = "";
		boolean found = false;

		for (k = 0; k < n1; k++) {
			pos = input.indexOf(keywords[k]);
			if (pos >= 0) {
				if (k == 13) {
					// Should use regionmatches
					if (input.indexOf(keywords[29]) >= 0)
						k = 29;
				}
				foundKeyword = keywords[k];
				found = true;
				break;
			}
		}

		if (found) {
			c = input.substring(pos + foundKeyword.length() - 1);

			for (int i = 0; i < wordInOutPairsIndex; i++) {
				c = replaceString(c, wordIn[i], wordOut[i]);
			}
			// Remove extra spaces
			c = replaceString(c, "  ", " ");
		} else {
			k = 35;
		}

		foundKeyword = replies[r[k + 1]];

		if (++r[k + 1] > n[k + 1])
			r[k + 1] = s[k + 1];

		if (foundKeyword.charAt(foundKeyword.length() - 1) != '*') {
			return foundKeyword;
		} else {
			if (c.equals("   ")) {
				return "YOU WILL HAVE TO ELABORATE MORE FOR ME TO HELP YOU";
			} else {
				return (foundKeyword.substring(0, foundKeyword.length() - 1) + c);
			}
		}
	}

	// Remembers the last line typed.
	String lastline = "-";

	static String keywords[] = { 
		"CAN YOU ", 
		"CAN I ", 
		"YOU ARE ", 
		"YOU'RE ",
		"I DON'T ", 
		"I FEEL ", 
		"WHY DON'T YOU ", 
		"WHY CAN'T I ",
		"ARE YOU ", 
		"I CAN'T ", 
		"I AM ", 
		"I'M ", 
		"YOU ", 
		"I WANT ",
		"WHAT ", 
		"HOW ", 
		"WHO ", 
		"WHERE ", 
		"WHEN ", 
		"WHY ", 
		"NAME ",
		"CAUSE ", 
		"SORRY ", 
		"DREAM ", 
		"HELLO ", 
		"HI ", 
		"MAYBE ", 
		"NO",
		"YOUR ", 
		"ALWAYS ", 
		"THINK ", 
		"ALIKE ", 
		"YES ", 
		"FRIEND ",
		"COMPUTER", 
		"NOKEYFOUND" 
	};

	static String wordInOutPairs[][] = { 
		{ " ARE ", " AM " }, 
		{ " WERE ", " WAS " },
		{ " YOU ", " I " }, 
		{ " YOUR", " MY " }, 
		{ " I'VE ", " YOU'VE " },
		{ " I'M ", " YOU'RE " }, 
		{ " ME ", " YOU " } 
	};

	static String replies[] = { 
		"DON'T YOU BELIEVE THAT I CAN*",
		"PERHAPS YOU WOULD LIKE TO BE LIKE ME*",
		"YOU WANT ME TO BE ABLE TO*", "PERHAPS YOU DON'T WANT TO*",
		"DO YOU WANT TO BE ABLE TO*", "WHAT MAKES YOU THINK I AM*",
		"DOES IT PLEASE YOU TO BELIEVE I AM*",
		"PERHAPS YOU WOULD LIKE TO BE*", "DO YOU SOMETIMES WISH YOU WERE*",
		"DON'T YOU REALLY*", "WHY DON'T YOU*",
		"DO YOU WISH TO BE ABLE TO*", "DOES THAT TROUBLE YOU*",
		"DO YOU OFTEN FEEL*", "DO YOU OFTEN FEEL*",
		"DO YOU ENJOY FEELING*", "DO YOU REALLY BELIEVE I DON'T*",
		"PERHAPS IN GOOD TIME I WILL*", "DO YOU WANT ME TO*",
		"DO YOU THINK YOU SHOULD BE ABLE TO*", "WHY CAN'T YOU*",
		"WHY ARE YOU INTERESTED IN WHETHER OR NOT I AM*",
		"WOULD YOU PREFER IF I WERE NOT*",
		"PERHAPS IN YOUR FANTASIES I AM*", "HOW DO YOU KNOW YOU CAN'T*",
		"HAVE YOU TRIED?", "PERHAPS YOU CAN NOW*",
		"DID YOU COME TO ME BECAUSE YOU ARE*", "HOW LONG HAVE YOU BEEN*",
		"DO YOU BELIEVE IT IS NORMAL TO BE*", "DO YOU ENJOY BEING*",
		"WE WERE DISCUSSING YOU--NOT ME.", "OH, I*",
		"YOU'RE NOT REALLY TALKING ABOUT ME, ARE YOU?",
		"WHAT WOULD IT MEAN TO YOU IF YOU GOT*", "WHY DO YOU WANT*",
		"SUPPOSE YOU SOON GOT*", "WHAT IF YOU NEVER GOT*",
		"I SOMETIMES ALSO WANT*", "WHY DO YOU ASK?",
		"DOES THAT QUESTION INTEREST YOU?",
		"WHAT ANSWER WOULD PLEASE YOU THE MOST?", "WHAT DO YOU THINK?",
		"ARE SUCH QUESTIONS ON YOUR MIND OFTEN?",
		"WHAT IS IT THAT YOU REALLY WANT TO KNOW?",
		"HAVE YOU ASKED ANYONE ELSE?",
		"HAVE YOU ASKED SUCH QUESTIONS BEFORE?",
		"WHAT ELSE COMES TO MIND WHEN YOU ASK THAT?",
		"NAMES DON'T INTEREST ME.",
		"I DON'T CARE ABOUT NAMES --PLEASE GO ON.",
		"IS THAT THE REAL REASON?",
		"DON'T ANY OTHER REASONS COME TO MIND?",
		"DOES THAT REASON EXPLAIN ANYTHING ELSE?",
		"WHAT OTHER REASONS MIGHT THERE BE?", "PLEASE DON'T APOLOGIZE!",
		"APOLOGIES ARE NOT NECESSARY.",
		"WHAT FEELINGS DO YOU HAVE WHEN YOU APOLOGIZE?",
		"DON'T BE SO DEFENSIVE!", "WHAT DOES THAT DREAM SUGGEST TO YOU?",
		"DO YOU DREAM OFTEN?", "WHAT PERSONS APPEAR IN YOUR DREAMS?",
		"ARE YOU DISTURBED BY YOUR DREAMS?",
		"HOW DO YOU DO ...PLEASE STATE YOUR PROBLEM.",
		"YOU DON'T SEEM QUITE CERTAIN.", "WHY THE UNCERTAIN TONE?",
		"CAN'T YOU BE MORE POSITIVE?", "YOU AREN'T SURE?",
		"DON'T YOU KNOW?", "ARE YOU SAYING NO JUST TO BE NEGATIVE?",
		"YOU ARE BEING A BIT NEGATIVE.", "WHY NOT?", "ARE YOU SURE?",
		"WHY NO?", "WHY ARE YOU CONCERNED ABOUT MY*",
		"WHAT ABOUT YOUR OWN*", "CAN YOU THINK OF A SPECIFIC EXAMPLE?",
		"WHEN?", "WHAT ARE YOU THINKING OF?", "REALLY, ALWAYS?",
		"DO YOU REALLY THINK SO?", "BUT YOU ARE NOT SURE YOU*",
		"DO YOU DOUBT YOU*", "IN WHAT WAY?",
		"WHAT RESEMBLANCE DO YOU SEE?",
		"WHAT DOES THE SIMILARITY SUGGEST TO YOU?",
		"WHAT OTHER CONNECTIONS DO YOU SEE?",
		"COULD THERE REALLY BE SOME CONNECTION?", "HOW?",
		"YOU SEEM QUITE POSITIVE.", "ARE YOU SURE?", "I SEE.",
		"I UNDERSTAND.", "WHY DO YOU BRING UP THE TOPIC OF FRIENDS?",
		"DO YOUR FRIENDS WORRY YOU?", "DO YOUR FRIENDS PICK ON YOU?",
		"ARE YOU SURE YOU HAVE ANY FRIENDS?",
		"DO YOU IMPOSE ON YOUR FRIENDS?",
		"PERHAPS YOUR LOVE FOR FRIENDS WORRIES YOU.",
		"DO COMPUTERS WORRY YOU?",
		"ARE YOU TALKING ABOUT ME IN PARTICULAR?",
		"ARE YOU FRIGHTENED BY MACHINES?", "WHY DO YOU MENTION COMPUTERS?",
		"WHAT DO YOU THINK MACHINES HAVE TO DO WITH YOUR PROBLEM?",
		"DON'T YOU THINK COMPUTERS CAN HELP PEOPLE?",
		"WHAT IS IT ABOUT MACHINES THAT WORRIES YOU?",
		"SAY, DO YOU HAVE ANY PSYCHOLOGICAL PROBLEMS?",
		"WHAT DOES THAT SUGGEST TO YOU?", "I SEE.",
		"I'M NOT SURE I UNDERSTAND YOU FULLY.",
		"COME COME ELUCIDATE YOUR THOUGHTS.", "CAN YOU ELABORATE ON THAT?",
		"THAT IS QUITE INTERESTING."
	};

	// These are the mysterious numbers that keep track of which
	// replies have been used and so one. Clever but hard to read.
	final static int rightReplies[] = { 1, 3, 4, 2, 6, 4, 6, 4, 10, 4, 14, 3,
			17, 3, 20, 2, 22, 3, 25, 3, 28, 4, 28, 4, 32, 3, 35, 5, 40, 9, 40,
			9, 40, 9, 40, 9, 40, 9, 40, 9, 49, 2, 51, 4, 55, 4, 59, 4, 63, 1,
			63, 1, 64, 5, 69, 5, 74, 2, 76, 4, 80, 3, 83, 7, 90, 3, 93, 6, 99,
			7, 106, 6 };

	//Utility function that removes a char from a String.
	public static String removeChar(String s, char c) {
		if (s == null)
			return s;
		int p;
		while ((p = s.indexOf(c)) >= 0) {
			s = s.substring(0, p - 1) + s.substring(p + 1);
		}
		return s;
	}

	// Utility function that replaces all occurrences of a specific string with
	// another string.
	public static String replaceString(String s, String oldstring,
			String newstring) {
		int pos;
		while ((pos = s.indexOf(oldstring)) >= 0) {
			s = s.substring(0, pos) + newstring
					+ s.substring(pos + oldstring.length());
		}
		return s;
	}
}

