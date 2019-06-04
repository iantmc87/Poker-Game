/*
 * Author - Ian McManus
 * Version - 1.0.0
 * Description - Enum for the winning poker hands and win amount
 */

public enum HandRank {

	ROYALFLUSH("Royal Flush", 500),
	STRAIGHTFLUSH("Straight Flush", 80),
	FOUROFAKIND("Four of a Kind", 50),
	FULLHOUSE("Full House", 25),
	FLUSH("Flush", 15),
	STRAIGHT("Straight", 10),
	THREEOFAKIND("Three of a Kind", 5),
	TWOPAIR("Two Pair", 3),
	PAIR("Pair", 1),
	HIGHCARD("High Card", 0);
		
	private final String value;
	private final int winnings;
		
	private HandRank(final String newValue, final int newWinnings) {
		value = newValue;
		winnings = newWinnings;
	}//end enum constructor
		
	public String getValue() {
		
		return value;
	}//end get value method
	
	public int getWinnings() {
		
		return winnings;
	}//end get winnings method
	
}//end class

