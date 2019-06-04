import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Author - Ian McManus
 * Version - 1.0.0
 * Description - Class for checking if user has a winning hand
 */

public class Hand {
	
	private static List<Integer> value;
	
	/**
	 * method for checking cards against each potential winning combination
	 * @param hand - user/dealer combined hand
	 * @return hand rank enum
	 */
	public static HandRank finalHand (List<Card> hand) {
		
		if(isRoyalFlush(hand)) {
			
			return HandRank.ROYALFLUSH;
			
		} else if(isStraightFlush(hand)){
			
			return HandRank.STRAIGHTFLUSH;
			
		} else if(isFlush(hand)) {
					
			return HandRank.FLUSH;
			
		} else if(isFourKind(hand)) {
			
			return HandRank.FOUROFAKIND;
			
		} else if(isStraight(hand)) {
			
			return HandRank.STRAIGHT;
			
		} else if (isFullHouse(hand)) {
			
			return HandRank.FULLHOUSE;
			
		} else if (isThreeKind(hand)){
			
			return HandRank.THREEOFAKIND;
			
		} else if (isTwoPair(hand)){
			
			return HandRank.TWOPAIR;
			
		} else if(isPair(hand)) {
			
			return HandRank.PAIR;
			
		} else {
			
			return HandRank.HIGHCARD;
		}
		
		
	}//end method for checking and returning hand
	
	/**
	 * method for checking if hand a royal flush
	 * @param hand - user/dealer combined hand
	 * @return boolean if true or not
	 */
	public static boolean isRoyalFlush(List<Card> hand) {
		if(isFlush(hand)) {
								
			int max = 0;
			int curr = 0;
			int currKey = 0;
			List<Card> royal = new ArrayList<>();
			Set<Integer> unique = new HashSet<>(cardValue(hand));
			
			//for loop checking frequency of card values
			for(Integer card : unique) {
				curr = Collections.frequency(value, card);
				if(curr > max) {
					max = curr;
					currKey = card;
				}
			}
			
			//for loop for adding any cards included in the highest frequency flush
			for(Card card : hand) {
				if(card.getSuit().getValue() == currKey) {
					royal.add(card);
				}
			}
			
			boolean ace = false, king = false, queen = false, jack = false, ten = false;
			
			//for looping checking if the flush includes A,K,Q,J,10
			for(Card card : royal) {
				switch(card.getValue().getValue()) {
				case 1:
					ace = true;
					break;
				case 10:
					ten = true;
					break;
				case 11:
					jack = true;
					break;
				case 12:
					queen = true;
					break;
				case 13:
					king = true;
					break;
				}
			}
			
			return (ace && king && queen && jack && ten);
			
		} else {
			
			return false;
		}
	}//end method for checking if winning hand a royal flush
	
	/**
	 * method for checking if hand a straight flush
	 * @param hand - user/dealer combined hand
	 * @return boolean if true or not
	 */
	public static boolean isStraightFlush(List<Card> hand) {
		
		//sorts the cards into value order
		Collections.sort(hand, byRank);
		int cardsRow = 0;
		int position = 0;
		boolean straight = false;
		
		//while loop until cards been iterated through or straight returned
		while(position < hand.size() - 1 && !straight) {
			
			//if statement checking if cards are a straight and flush
			if((hand.get(position + 1).getValue().getValue() - hand.get(position).getValue().getValue() == 1) && 
					hand.get(position + 1).getSuit().getValue() == hand.get(position).getSuit().getValue()) {
				cardsRow++;
				
				//check if four of kind
				if(cardsRow == 4) {
					
					straight = true;
				} else {
					
					position++;
				}
				
			} else {
				cardsRow = 0;
				position++;
			}
		}//end while loop
		
		return straight;
	}//end method for checking if winning hand a straight flush

	/**
	 * method for checking if hand four of a kind
	 * @param hand - user/dealer combined hand
	 * @return boolean if true or not
	 */
	public static boolean isFourKind (List<Card> hand) {
					
		int curr = 0;
		Set<Integer> unique = new HashSet<>(cardValue(hand));
		
		//for loop checking frequency of card values
		for(Integer card : unique) {
			curr = Collections.frequency(value, card);
			
			//check if four of any card values
			if(curr == 4) {
				return true;
			}
		}
		
		return false;
	}//end method for checking if winning hand four of a kind
	
	/**
	 * method for checking if hand a full house
	 * @param hand - user/dealer combined hand
	 * @return boolean if true or not
	 */
	public static boolean isFullHouse(List<Card> hand) {
	
		int curr = 0;
		int twoCount = 0, threeCount = 0;
		Set<Integer> unique = new HashSet<>(cardValue(hand));
		
		//for loop checking frequency of card values
		for(Integer card : unique) {
			curr = Collections.frequency(value, card);
			if (curr == 3){
				threeCount++;
			}
			if(curr >= 2) {
				twoCount++;
			}
			
			//check if there is a card value count of three and two
			if(threeCount >= 1 && twoCount >= 2) {
				return true;
			}
		}
			
		return false;
		
	}//end method for checking if winning hand a full house
	
	/**
	 * method for checking if hand a flush
	 * @param hand - user/dealer combined hand
	 * @return boolean if true or not
	 */
	public static boolean isFlush(List<Card> hand) {
		int heartCount = 0,
			diamondCount = 0,
			spadeCount = 0,
			clubCount = 0;
		
		//for loop iterating through the cards
		for(Card card : hand) {
			
			//checking which suit the card is
			if(card.getSuit().getValue() == 1) {
				heartCount++;
			} else if(card.getSuit().getValue() == 2) {
				clubCount++;
			} else if(card.getSuit().getValue() == 3) {
				diamondCount++;
			} else if(card.getSuit().getValue() == 4) {
				spadeCount++;
			}
			
		}//end for loop
		
		return(heartCount >= 5 || diamondCount >= 5 || spadeCount >= 5 || clubCount >=5);
	}//end method for checking if winning hand a flush
	
	/**
	 * method for checking if hand a straight
	 * @param hand - user/dealer combined hand
	 * @return boolean if true or not
	 */
	public static boolean isStraight(List<Card> hand) {
		
		//sorts card into value order
		Collections.sort(hand, byRank);
		int cardsRow = 0;
		int position = 0;
		boolean straight = false;
		
		//while loop until cards been iterated through or straight returned
		while(position < hand.size() - 1 && !straight) {
			
			//checking if cards are a straight
			if(hand.get(position + 1).getValue().getValue() - hand.get(position).getValue().getValue() == 1) {
				cardsRow++;
				
				//check if 5 cards are in a row
				if(cardsRow == 4) {
					
					straight = true;
				} else {
					
					position++;
				}
			} else {
				
				cardsRow = 0;
				position++;
			}
		}//end while loop
		return straight;
	}//end method for checking if winning hand a straight
	
	/**
	 * method for checking if hand three of a kind
	 * @param hand - user/dealer combined hand
	 * @return boolean if true or not
	 */
	public static boolean isThreeKind (List<Card> hand) {
		
		int curr = 0;
		Set<Integer> unique = new HashSet<>(cardValue(hand));
		
		//for loop checking frequency of card values
		for(Integer card : unique) {
			curr = Collections.frequency(value, card);
			
			//check if there is three of a kind
			if(curr == 3) {
				return true;
			}
		}
			
		return false;
		
	}//end method for checking if winning hand three of a kind

	/**
	 * method for checking if hand two pair
	 * @param hand - user/dealer combined hand
	 * @return boolean if true or not
	 */
	public static boolean isTwoPair(List<Card> hand) {
		
		int curr = 0;
		int count = 0;
		Set<Integer> unique = new HashSet<>(cardValue(hand));
		
		//for loop checking frequency of card values
		for(Integer card : unique) {
			curr = Collections.frequency(value, card);
			if (curr == 2){
				count++;
			}
			//check if two pairs have been counted
			if(count == 2) {
				return true;
			}
		}
			
		return false;
				
	}//end method for checking if winning hand a two pair
	
	/**
	 * method for checking if hand a pair
	 * @param hand - user/dealer combined hand
	 * @return boolean if true or not
	 */
	public static boolean isPair (List<Card> hand) {
				
		int curr = 0;
		Set<Integer> unique = new HashSet<>(cardValue(hand));
		
		//for loop checking frequency of card values
		for(Integer card : unique) {
			curr = Collections.frequency(value, card);
			
			//check if its a pair and Jacks or Better
			if(curr == 2 && (card == 1 || card >= 11)) {
				return true;
			}
		}
			
		return false;
	}//end method for checking if winning hand a pair
	
	/**
	 * method for getting just the card values to check for frequency
	 * @param hand - user/dealer combined hand
	 * @return List array with just the card values
	 */
	public static List<Integer> cardValue (List<Card> hand) {
		value = new ArrayList<Integer>();
		for(Card card : hand) {
			value.add(card.getValue().getValue());
		}
		
		return value;
	}
	
	//used with collection sort to order cards by value
	public static Comparator<Card> byRank = (Card left, Card right) -> {
	    if (left.getValue().getValue() < right.getValue().getValue()) {
	        return -1;
	    } else {
	        return 1;
	    }
	};
	
}//end class
