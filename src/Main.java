import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/*
 * Author - Ian McManus
 * Version - 1.0.0
 * Description - Main java file for running the poker program
 */

public class Main {
	
	private static Random random = new Random();
	private static final int shuffleCount = 1000;
	private static final int userCardCount = 2, dealerCardCount = 5;
	private static int rand, rand2;
	private static List<Card> deck = new ArrayList<>();
	private static Card temp;

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		String userInput = "reset";
		
		Dealer dealer = new Dealer();
		User user = new User();
				
		System.out.print("Welcome to poker");
		System.out.print("\nWhat is your name?");
		String userName = in.nextLine();
		user.setName(userName);
		
		System.out.print("\n*********HOW TO WIN*********\n");
		System.out.print("- Royal Flush: 500 * Bet \n");
		System.out.print("- Straight Flush: 80 * Bet\n");
		System.out.print("- Four of a Kind: 50 * Bet\n");
		System.out.print("- Full House: 25 * Bet\n");
		System.out.print("- Flush: 15 * Bet\n");
		System.out.print("- Straight: 10 * Bet\n");
		System.out.print("- Three of a Kind: 5 * Bet\n");
		System.out.print("- Two Pair: 3 * Bet\n");
		System.out.print("- Pair: 1 * Bet\n");
		System.out.print("******************************\n");
		
		//while loop continuing until user runs out balance and selects to exit
		while(!userInput.equalsIgnoreCase("exit")) {
			
			user.setBalance(1000);
			while(user.getBalance() > 0) {
				
				System.out.print("\n" + user.getName() + " your balance is £" + user.getBalance());
				System.out.print("\n\nPlease choose amount to bet in whole numbers\n");
				int userBet = in.nextInt();
				
				if(userBet > user.getBalance()) {
					System.out.print("You have insufficient funds, please choose a lower amount\n");
					userBet = in.nextInt();
				}
		
				user.setBalance(user.getBalance() - userBet);
				
				//for loop to add the cards to the deck
				for(Suit suit : Suit.values()) {
		
					for(Value value : Value.values()) {
						deck.add( new Card(suit, value));
					}
				}
			
				//shuffles the deck
				shuffle();
				
				List<Card> hand = new ArrayList<>();
				List<Card> dealerCards = new ArrayList<>();
				List<Card> userCards = new ArrayList<>();
				
				//deals the cards to the user
				deal(userCards, hand, userCardCount);
				user.setHand(userCards);
				
				//adds the card to the flop
				deal(dealerCards, hand, dealerCardCount);
				dealer.setDeal(dealerCards);
		
				System.out.print("Your Cards:\n\n");
				//for loop printing the users cards to console
				for(Card cards : user.getHand()) {
					System.out.println(cards.getValue() + " of " + cards.getSuit());
				}
		
				System.out.print("\nFlop:\n\n");
				//for loop printing the flop to the console
				for(Card card : dealer.getDeal()) {
					System.out.println(card.getValue() + " of " + card.getSuit());
				}
				
				int winnings = userBet * Hand.finalHand(hand).getWinnings();
				System.out.println("\nYou have a " + Hand.finalHand(hand).getValue());
				System.out.print("You have won: £" + winnings);
				
				int newBalance = winnings + user.getBalance();
				user.setBalance(newBalance);
			}//end while loop
		
			System.out.print("\n\nYou have used all of your balance");
			System.out.print("\nTo reset your balance type any key, otherwise type exit to leave\n\n");
			in.nextLine();
			userInput = in.nextLine();
		}//end while loop
		
		in.close();//closes scanner
		
		System.out.print("\n\nYou are now exiting poker.......");
	}
	
	/**
	 * method for dealing the cards
	 * @param cards - list array for user or dealer cards to add
	 * @param hand - list array for combining user and dealer cards
	 * @param cardCount - number of cards to deal
	 */
	public static void deal (List<Card> cards, List<Card> hand, int cardCount) {
		
		//for loop for adding cards to the deal
		for(int i = 0; i < cardCount; i++) {
			rand = random.nextInt(deck.size());
			cards.add(deck.get(rand));
			hand.add(deck.get(rand));
			deck.remove(rand);
		}//end for loop
		
	}//end deal method
	
	/**
	 * method for shuffling the deck
	 */
	public static void shuffle() {
		
		for(int i = 0; i < shuffleCount; i++) {
			rand = random.nextInt(deck.size());
			temp = deck.get(rand);
			rand2 = random.nextInt(deck.size());
			deck.set(rand, deck.get(rand2));
			deck.set(rand2, temp);
		}//end for loop
	}//end method for shuffling cards
	
}//end class