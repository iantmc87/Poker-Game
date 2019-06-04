/*
 * Author - Ian McManus
 * Version - 1.0.0
 * Description - Enum for the card suits
 */

public enum Suit {
	
	HEARTS (1),
	CLUBS (2),
	DIAMONDS (3),
	SPADES (4);
	
	private final int value;
	
	private Suit(final int newValue) {
		value = newValue;
	}//end enum constructor
	
	public int getValue() {
		return value;
	}//end get value method
	
}//end class
