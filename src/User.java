import java.util.List;

/*
 * Author - Ian McManus
 * Version - 1.0.0
 * Description - Class for the user object
 */

public class User {
	
	
	private String name;
	private int balance;
	private List<Card> hand;
	
	public User() {
		
	}//end user constructor

	public User (String name, int balance, List<Card> hand) {
		
		this.name = name;
		this.balance = balance;
		this.hand = hand;
	}//end user constructor
	
	public String getName() {
		
		return name;
	}//end get name method
	
	public void setName(String name) {
		
		this.name = name;
	}//end set name method
	
	public int getBalance() {
		
		return balance;
	}//end get balance method
	
	public void setBalance(int balance) {
		
		this.balance = balance;
	}//end set balance method
	
	public List<Card> getHand() {
		
		return hand;
	}// end get hand method
	
	public void setHand(List<Card> hand) {
		
		this.hand = hand;
	}//end set hand method
}
