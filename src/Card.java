/*
 * Author - Ian McManus
 * Version - 1.0.0
 * Description - Class for the card object
 */

public class Card {
	
	private Suit suit;
	private Value value;
	
	public Card(Suit suit, Value value) {
		
		this.suit = suit;
		this.value = value;
	}//end card constructor
	
	public Suit getSuit() {
		
		return suit;
	}//end get suit method
	
	public void setSuit(Suit suit) {
		
		this.suit = suit;
	}//end set suit method
	
	public Value getValue() {
		
		return value;
	}//end get value method
	
	public void setValue(Value value) {
		
		this.value = value;
	}//end set value method

}
