import java.util.List;

/*
 * Author - Ian McManus
 * Version - 1.0.0
 * Description - Class for the dealer object
 */

public class Dealer {

	List<Card> deal;
	
	public Dealer( ) {
		
	}//end dealer constructor
	
	public List<Card> getDeal() {
		
		return deal;
	}//end get deal method
	
	public void setDeal(List<Card> deal) {
		
		this.deal = deal;
	}//end set deal method
	
} //end class
