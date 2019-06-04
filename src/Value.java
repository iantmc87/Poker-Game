/*
 * Author - Ian McManus
 * Version - 1.0.0
 * Description - Enum for the card values
 */

public enum Value {
	
	King (13),
	Queen (12),
	Jack (11),
	Ten (10),
	Nine (9),
	Eight (8),
	Seven (7),
	Six (6),
	Five (5),
	Four (4),
	Three (3),
	Two (2),
	Ace (1);
	
	private final int value;
	
	private Value(final int newValue) {
		
		value = newValue;
	}//end enum constructor
	
	public int getValue() {
		
		return value;
	}//end get value method
	
}//end class
