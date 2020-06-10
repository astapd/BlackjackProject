/*
Aastha Neupane
This class consists of the black jack game program. The user can choose upto 5 players (including the dealer). This game is played with one deck i.e. 52 cards.
This program follows the basic rules of the game of black jack. At the beginning, each player including the dealer is dealt 2 cards. Both the cards of each player is displayed,
while only one of the dealer's card is exposed. Additional cards are dealt to each player, until they choose to hold, have a BlackJack or has busted. The game then moves on
to another player. Once all the players have their total scores, the dealer shows his  second hand and if its less than or equal to 16, he is dealt another card.
If the dealer has total score of 17, he holds. If the dealer has 21, he has a BlackJack. If he has a score of more than 21, he is busted.
Ace has 2 possible values; 1 point or 11 points. User can choose either during the course of his/her game. Jack, Queen and King are 10 points each. All other cards are of face value.
Each player plays against the dealer and the winner is chosen between the player and the dealer.

for this game;
The numbers 1 through 13 in the deck represent Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, jack, queen and king of HEARTS
The numbers 14 through 26 in the deck represent Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, jack, queen and king of CLUB
The numbers 27 through 39 in the deck represent Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, jack, queen and king of SPADE
The numbers 40 through 52 in the deck represent Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, jack, queen and king of DIAMOND

I have used the two arrays; suits and cards- to assign the respective suits and ranks to each of the 52 numbers in the deck array.
 */

package blackjack;

import java.util.Random;
import java.util.Scanner;

public class BlackJack {

	//	 declaring or initializing variables used in the program;
	static int value;
	static int suit;
	static int card;
	static int deckSize = 52;
	static int playerArraySize = 11;
	static int numPlayers, acePoint, acePointAgain;
	static int finalDealerTotal, finalPlayer1Total, finalPlayer2Total, finalPlayer3Total, finalPlayer4Total; 

	//	creating arrays of  suits and ranks of card
	//	These arrays are used in this program to display the dealt cards.
	static String [] suits = {"heart", "club", "spade", "diamond"};
	static String [] cards = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

	//	creating arrays of  all player's cards (max 4 players + 1 dealer) and total deck cards
	static int [] dealerCard =new int[playerArraySize];
	static int [] player1Card =new int[playerArraySize];
	static int [] player2Card =new int[playerArraySize];
	static int [] player3Card =new int[playerArraySize];
	static int [] player4Card =new int[playerArraySize];
	static int[] arrayDeck = new int[deckSize];

	//	instantiating Random and Scanner classes
	static Random ran = new Random(100788);
	static Scanner getData = new Scanner(System.in);

	//main method
	public static void main(String[] args) {

		//		Creating object of the BlackJack class
		BlackJack blkjk = new BlackJack();

		//		Welcome statement and prompts on number of players
		System.out.println("Welcome to the game of BlackJack!!!\n");

		//		Prompt to start the game. If chosen "y" for yes the game will start. If chose "n" or any other key, that is taken as no and the program terminates without being played
		System.out.println("Are you ready to start the game? Please enter 'y' for Yes or 'n'(or any key other than 'y') for No: ");
		String begin = getData.next();

		//		Game begins if "y" was chosen
		if(begin.equalsIgnoreCase("y")) {
			System.out.println("Let's get started!\n");

			//			prompt for user to enter the number of players, including the dealer. The number of player (including dealer) for this game should not be more than 5
			System.out.println("Please enter the number of players (including dealer).\nNOTE: Total players should not be more than 5:");
			numPlayers = getData.nextInt();

			//		if condition to make sure the number of players is not more than 5 for this game. User gets one more chance to enter valid number of players.
			//		On third invalid entry, the program terminates.
			if(numPlayers>5) {
				System.out.println("For this game, the total players should not be more than 5. Please enter number of player (upto 5):");
				numPlayers = getData.nextInt();
				if(numPlayers>5) {
					System.out.println("Program terminated. Please restart the program with valid number of players (i.e. upto 5, including dealer).");
					System.exit(0);
				} //end of 1st if

			} // end of 2nd if

			//		filling the array arrayDeck with 52 cards. These 52 cards represent 13 cards each of each suit.
			//			for this game;
			//			The numbers 1 through 13 in the deck represent Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, jack, queen and king of HEARTS
			//			The numbers 14 through 26 in the deck represent Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, jack, queen and king of CLUB
			//			The numbers 27 through 39 in the deck represent Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, jack, queen and king of SPADE
			//			The numbers 40 through 52 in the deck represent Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, jack, queen and king of DIAMOND

			for(int i=0; i<deckSize; i++) {
				arrayDeck[i]= i+1;
			} // end of for

			//			for dealer's first 2 hands
			//			creating variables for dealer's total score of the first two cards their respective values
			int dealerTotal = 0;

			//			dealing 2 cards to the dealer
			for(int dIndex = 0;dIndex < 2; dIndex++){
				int indexFromDeck = (ran.nextInt(deckSize))-1;
				int dCard = arrayDeck[indexFromDeck];
				dealerCard[dIndex] = dCard;
				if(dIndex==0) {
					System.out.println("The dealer's card "+(dIndex+1)+" is: "+blkjk.cardSuitRank(dealerCard[dIndex])+"\n");
				} // end of if (index 0)
				else {
				} // end of else

				//				code to remove the card for main deck here and decrease deck size
				arrayDeck = blkjk.removeElement(arrayDeck, indexFromDeck);
				deckSize--;

				//				calculating the total of dealer's two cards. But its not displayed (printed).
				dealerTotal += blkjk.getValue(dealerCard[dIndex], "d", dealerTotal);
			} //end of for (dealing two cards to the dealer)

			//			for player1's first 2 hands
			//			creating variables for player1's total score of the first two cards their respective values
			int player1Total = 0;

			//			dealing 2 cards to the player1
			for(int pIndex = 0;pIndex < 2; pIndex++){
				int indexFromDeck = (ran.nextInt(deckSize))-1;
				int pCard = arrayDeck[indexFromDeck];
				player1Card[pIndex] = pCard;
				if(pIndex==0) {
					System.out.println("The player1's card "+(pIndex+1)+" is: "+blkjk.cardSuitRank(player1Card[pIndex]));
				} // end of if (index 0)
				else {
					System.out.println("The player1's card "+(pIndex+1)+" is: "+blkjk.cardSuitRank(player1Card[pIndex])+"\n");
				} // end of else

				//				code to remove the card for main deck here and decrease deck size
				arrayDeck = blkjk.removeElement(arrayDeck, indexFromDeck);
				deckSize--;

				//				calculating and printing the player's total of the two cards
				player1Total += blkjk.getValue(player1Card[pIndex], "p", player1Total);
				if(!(pIndex==0)) System.out.println("The player1's total is "+player1Total+"\n");
			} //end of for (dealing two cards to player 1)

			//			for player2's first 2 hands
			//			creating variables for player2's total score of the first two cards their respective values
			int player2Total = 0;
			//			dealing 2 cards to the player2
			for(int pIndex = 0;pIndex < 2; pIndex++){
				int indexFromDeck = (ran.nextInt(deckSize))-1;
				int pCard = arrayDeck[indexFromDeck];
				player2Card[pIndex] = pCard;
				if(pIndex==0) {
					System.out.println("The player2's card "+(pIndex+1)+" is: "+blkjk.cardSuitRank(player2Card[pIndex]));
				} // end of if (index 0)
				else {
					System.out.println("The player2's card "+(pIndex+1)+" is: "+blkjk.cardSuitRank(player2Card[pIndex])+"\n");
				} // end of else

				//				code to remove the card for main deck here and decrease deck size
				arrayDeck = blkjk.removeElement(arrayDeck, indexFromDeck);
				deckSize--;

				//				calculating and printing the total of the two cards
				player2Total += blkjk.getValue(player2Card[pIndex], "p", player2Total);
				if(!(pIndex==0)) System.out.println("The player2's total is "+player2Total+"\n");
			} //end of for (dealing two cards to player 2)

			//			for player3's first 2 hands
			//			creating variables for player3's total score of the first two cards their respective values
			int player3Total = 0;
			//			dealing 2 cards to the player3
			for(int pIndex = 0;pIndex < 2; pIndex++){
				int indexFromDeck = (ran.nextInt(deckSize))-1;
				int pCard = arrayDeck[indexFromDeck];
				player3Card[pIndex] = pCard;
				if(pIndex==0) {
					System.out.println("The player3's card "+(pIndex+1)+" is: "+blkjk.cardSuitRank(player3Card[pIndex]));
				} // end of if (index 0)
				else {
					System.out.println("The player3's card "+(pIndex+1)+" is: "+blkjk.cardSuitRank(player3Card[pIndex])+"\n");
				} // end of else

				//				code to remove the card for main deck here and decrease deck size
				arrayDeck = blkjk.removeElement(arrayDeck, indexFromDeck);
				deckSize--;

				//				calculating and printing the total of the two cards
				player3Total += blkjk.getValue(player3Card[pIndex], "p", player3Total);
				if(!(pIndex==0)) System.out.println("The player3's total is "+player3Total+"\n");
			} //end of for (dealing two cards to player 3)


			//			for player4's first 2 hands
			//			creating variables for player4's total score of the first two cards their respective values
			int player4Total = 0;
			//			dealing 2 cards to the player4
			for(int pIndex = 0;pIndex < 2; pIndex++){
				int indexFromDeck = (ran.nextInt(deckSize))-1;
				int pCard = arrayDeck[indexFromDeck];
				player4Card[pIndex] = pCard;
				if(pIndex==0) {
					System.out.println("The player4's card "+(pIndex+1)+" is: "+blkjk.cardSuitRank(player4Card[pIndex]));
				}
				else {
					System.out.println("The player4's card "+(pIndex+1)+" is: "+blkjk.cardSuitRank(player4Card[pIndex])+"\n");
				}

				//				code to remove the card for main deck here and decrease deck size
				arrayDeck = blkjk.removeElement(arrayDeck, indexFromDeck);
				deckSize--;

				//				calculating and printing the total of the two cards
				player4Total += blkjk.getValue(player4Card[pIndex], "p", player4Total);
				if(!(pIndex==0)) System.out.println("The player4's total is "+player4Total+"\n");
			} //end of for (dealing two cards to player 4)


			//		Dealing additional cards to player(s) (excluding the dealer)
			int playerTotal = 0;
			for(int p=1; p <numPlayers; p++ ) {
				switch (p) {
				//				In case of player 1
				case 1:
					playerTotal = player1Total;
					System.out.println("\nPlayer"+p+"'s turn:");
					System.out.println("__________________________\n");
					System.out.println("The player_1's first card (card 1) was "+blkjk.cardSuitRank(player1Card[0]));
					System.out.println("The player_1's second card (card 2) was "+blkjk.cardSuitRank(player1Card[1])+"\n");
					System.out.println("Player_1's total score right now is "+playerTotal+"\n");
					finalPlayer1Total = blkjk.dealPlayerCards(playerTotal, p, 2);
					System.out.println("\nThe final total score for player "+p+" is "+finalPlayer1Total+"\n");
					break;
					//					In case of player 2
				case 2:
					playerTotal = player2Total;
					System.out.println("\nPlayer"+p+"'s turn:");
					System.out.println("__________________________\n");
					System.out.println("The player_2's first card (card 1) was "+blkjk.cardSuitRank(player2Card[0]));
					System.out.println("The player_2's second card (card 2) was "+blkjk.cardSuitRank(player2Card[1])+"\n");
					System.out.println("Player_2's total score right now is "+playerTotal+"\n");
					finalPlayer2Total = blkjk.dealPlayerCards(playerTotal, p, 2);
					System.out.println("\nThe final total score for player "+p+" is "+finalPlayer2Total+"\n");
					break;
					//					In case of player 3
				case 3:
					playerTotal = player3Total;
					System.out.println("\nPlayer"+p+"'s turn:");
					System.out.println("__________________________\n");
					System.out.println("The player_3's first card (card 1) was "+blkjk.cardSuitRank(player3Card[0]));
					System.out.println("The player_3's second card (card 2) was "+blkjk.cardSuitRank(player3Card[1])+"\n");
					System.out.println("Player_3's total score right now is "+playerTotal+"\n");
					finalPlayer3Total = blkjk.dealPlayerCards(playerTotal, p, 2);
					System.out.println("\nThe final total score for player "+p+" is "+finalPlayer3Total+"\n");
					break;
					//					In case of player 4
				case 4:
					playerTotal = player4Total;
					System.out.println("\nPlayer"+p+"'s turn:");
					System.out.println("__________________________\n");
					System.out.println("The player_4's first card (card 1) was "+blkjk.cardSuitRank(player4Card[0]));
					System.out.println("The player_4's second card (card 2) was "+blkjk.cardSuitRank(player4Card[1])+"\n");
					System.out.println("Player_4's total score right now is "+playerTotal+"\n");
					finalPlayer4Total = blkjk.dealPlayerCards(playerTotal, p, 2);
					System.out.println("\nThe total final score for player "+p+" is "+finalPlayer4Total+"\n");
					break;
					//					default case
				default:
					System.out.println("Invalid number of players. There can only be upto 4 players and a dealer in this game.");
					break;
				} // end of switch case
			} // end of for (Dealing additional cards to player(s) (excluding the dealer))

			//			Dealing additional cards to the dealer
			System.out.println("Dealer's turn:");
			System.out.println("_________________\n");
			System.out.println("Let's see what the dealer's hands were.");
			System.out.println("\nThe dealer's card 1 was: "+blkjk.cardSuitRank(dealerCard[0])+"\n");
			System.out.println("Lets turn dealer's second card over!\n\nDealer's card 2 is: "+blkjk.cardSuitRank(dealerCard[1])+"\n");

			//			if his total score is more than 16 and less than 21. This will be his final total score
			if(dealerTotal>16 && dealerTotal<21) {
				finalDealerTotal = dealerTotal;
			} // end of if
			//			while his total score is less than 16, he is dealt another hand
			else {
				int dIndex = 2;
				do{
					finalDealerTotal = blkjk.dealDealerCards(dealerTotal, dIndex);
					dIndex++;
				} //end of do
				while(finalDealerTotal<=16);
			} //end of else

			System.out.println("The dealer's final total score is "+finalDealerTotal+"\n");

			//			Determining who the winner is:

			//			prompt for displaying the result
			System.out.println("THE RESULTS OF THE GAME ARE:");
			System.out.println("________________________________");
			//			going through each player
			for(int p=1; p <numPlayers; p++ ) {
				int finalPlayerTotal = 0;
				int[] playerCards =null;
				//				switch case to assign value of variable 'finalPlayerTotal' to players' respective total
				switch (p) {
				//				case for player 1
				case 1:
					finalPlayerTotal = finalPlayer1Total;
					playerCards = player1Card;
					break;
					//				case for player 2
				case 2:
					finalPlayerTotal = finalPlayer2Total;
					playerCards = player2Card;
					break;
					//				case for player 3
				case 3:
					finalPlayerTotal = finalPlayer3Total;
					playerCards = player3Card;
					break;
					//				case for player 4
				case 4:
					finalPlayerTotal = finalPlayer4Total;
					playerCards = player4Card;
					break;
					//					default case
				default:
					break;
				} // end of switch

				//				Printing out the result of the game;
				String gameWinner = blkjk.winner(p, finalPlayerTotal, finalDealerTotal);

				//				if both the dealer and the player bust and lose
				if(gameWinner=="None") {
					System.out.println("\nBETWEEN DEALER AND PLAYER_"+p+":");
					System.out.println("__________________________________");
					System.out.println("The dealer's cards are: ");
					for(int i=0; i<playerArraySize; i++) {
						if(dealerCard[i]!=0) {
							System.out.println(blkjk.cardSuitRank(dealerCard[i]));
						}
					}
					System.out.println("\nDealer's total score is "+finalDealerTotal);
					System.out.println("\nPlayer_"+p+"'s cards are: ");
					for(int i=0; i<playerArraySize; i++) {
						if(playerCards[i]!=0) {
							System.out.println(blkjk.cardSuitRank(playerCards[i]));
						}
					}
					System.out.println("\nThe player_"+p+"'s total score is "+finalPlayerTotal);
					System.out.println("\nThey were both busted.\nThus, NEITHER of them WIN!\n");
				} //end of if (no winner)

				//				if the game is tied
				else if(gameWinner=="Tied") {
					System.out.println("\nBETWEEN DEALER AND PLAYER_"+p+":");
					System.out.println("__________________________________");
					System.out.println("The dealer's cards are: ");
					for(int i=0; i<playerArraySize; i++) {
						if(dealerCard[i]!=0) {
							System.out.println(blkjk.cardSuitRank(dealerCard[i]));
						}
					}
					System.out.println("\nDealer's total score is "+finalDealerTotal);
					System.out.println("\nPlayer_"+p+"'s cards are: ");
					for(int i=0; i<playerArraySize; i++) {
						if(playerCards[i]!=0) {
							System.out.println(blkjk.cardSuitRank(playerCards[i]));
						}
					}
					System.out.println("\nThe player_"+p+"'s total score is "+finalPlayerTotal);
					//					if both the dealer and the player tied with blackjacks
					if(finalDealerTotal==21 && finalPlayerTotal==21) {
						System.out.println("\nBoth the dealer and the player_"+p+" have BLACKJACK.\nThus, its a PUSH i.e. they both TIED\\n");
					} // end of if (tied with blackjacks)
					//					if both tied without blackjacks
					else {
						System.out.println("\nThey both have the same score.\nThus, its a PUSH i.e. they both TIED\n");
					} // end of else (tied without blackjacks)

				} // end of if (both tied)

				//				if the dealer wins either because he has the greater hand or the player busted
				else if(gameWinner=="Dealer") {
					System.out.println("BETWEEN DEALER AND PLAYER_"+p+":");
					System.out.println("__________________________________");
					System.out.println("The dealer's cards are: ");
					for(int i=0; i<playerArraySize; i++) {
						if(dealerCard[i]!=0) {
							System.out.println(blkjk.cardSuitRank(dealerCard[i]));
						}
					}
					System.out.println("\nDealer's total score is "+finalDealerTotal);
					System.out.println("\nplayer_"+p+"'s cards are: ");
					for(int i=0; i<playerArraySize; i++) {
						if(playerCards[i]!=0) {
							System.out.println(blkjk.cardSuitRank(playerCards[i]));
						}
					}
					System.out.println("\nThe player_"+p+"'s total score is "+finalPlayerTotal);
					//					if the player busted
					if(finalPlayerTotal>21) {
						System.out.println("\nThe player_"+p+" was BUSTED!\nSORRY, PLAYER"+p+", YOU LOSE :(\n");
					} // end of if (player busted)

					//					if the dealer has the BlackJack
					else if(finalPlayerTotal==21) {
						System.out.println("\nThe dealer has a blackjack.\nSORRY, PLAYER"+p+", YOU LOSE :(\n");
					} // end of else if (dealer has blackjack)

					//					if the dealer has the higher score
					else {
						System.out.println("\nThe dealer has the higher score.\nSORRY, PLAYER"+p+", YOU LOSE :(\n");
					} // end of else (dealer has the higher score)

				} // end of if (dealer wins)

				//				if the player wins either because he has the greater hand or the dealer busted
				else if(gameWinner=="Player") {
					System.out.println("BETWEEN DEALER AND PLAYER_"+p+":");
					System.out.println("__________________________________");
					System.out.println("The dealer's cards are: ");
					for(int i=0; i<playerArraySize; i++) {
						if(dealerCard[i]!=0) {
							System.out.println(blkjk.cardSuitRank(dealerCard[i]));
						}
					}
					System.out.println("\nDealer's total score is "+finalDealerTotal);
					System.out.println("\nplayer_"+p+"'s cards are: ");
					for(int i=0; i<playerArraySize; i++) {
						if(playerCards[i]!=0) {
							System.out.println(blkjk.cardSuitRank(playerCards[i]));
						}
					}
					System.out.println("\nThe player_"+p+"'s total score is "+finalPlayerTotal);
					//					if the dealer busted
					if(finalDealerTotal>21) {
						System.out.println("\nThe dealer was BUSTED!\nCONGRATULATIONS PLAYER\"+p+\", YOU WIN!!! :)\n");
					} //end of if (dealer busted)

					//					if the player has the BlackJack
					else if(finalPlayerTotal==21) {
						System.out.println("\nCONGRATULATIONS PLAYER_"+p+", YOU HAVE A BLACKJACK! YOU WIN!!! :)\n");
					} // end of else if (player has the BlackJack)

					//					if the player has the higher score
					else {
						System.out.println("\nThe player_"+p+" has the higher score.\nCONGRATULATIONS PLAYER"+p+", YOU HAVE THE HIGHER SCORE! YOU WIN!!! :)\n");
					} // end of else (player has the higher score)

				} // end of if (player wins)

			} // end of for (going through each player)

		} // end of if condition i.e. entering 'y' to start the game

		//		if the user chose not to start the game at all.
		else {
			System.out.println("Sorry to see you go:( \nPlease come back to play anytime!");
		} // end of else condition i.e. entering 'n' or any other key to not start the game at all

	} // end of main class


	//	methods for this class:

	//	dealPlayerCards method to deal additional cards to each player
	public int dealPlayerCards(int pTotal, int pNum, int pIndex) {
		int [] playerCard = null;
		switch (pNum) {
		case 1:
			playerCard = player1Card;
			break;
		case 2:
			playerCard = player2Card;
			break;
		case 3:
			playerCard = player3Card;
			break;
		case 4:
			playerCard = player4Card;
			break;
		default:
			break;
		} // end of switch

		//		initializing boolean hold as false; meaning that the player is still taking cards
		boolean hold=false;

		//		while loop to deal additional cards
		while(pTotal<21 && hold==false) {

			//				prompt for user to either hold or be dealt another card
			System.out.println("Would player"+pNum+" like another hand? Please type 'Y' if you want another card or type 'N' if you want to hold onto your current hand:");
			String anotherHand = getData.next();

			//				if player chooses to be dealt another card, he is dealt additional cards until the decide to either hold or a are busted
			if(anotherHand.equalsIgnoreCase("y")) {
				hold = false;
				//					dealing a random number from the deck of remaining cards;
				int indexFromDeck = (ran.nextInt(deckSize))-1;
				int pCard = arrayDeck[indexFromDeck];
				playerCard[pIndex] = pCard;

				//					printing the suit and rank of the dealt card, using cardSuitRank method
				System.out.println("The player's card "+(pIndex+1)+" is: "+cardSuitRank(pCard));

				//				write code to remove the card for main deck here and decrease deck size
				arrayDeck = removeElement(arrayDeck, indexFromDeck);
				deckSize--;

				//					calculating the new total score of the player's total cards. "p" indicates player
				pTotal += getValue(playerCard[pIndex], "p", pTotal);
				System.out.println("\nThe new total for player"+pNum+" is "+pTotal+"\n");

				//					incrementing the index
				pIndex++;	
				playerArrays(pNum, playerCard);
			} // end of if

			else {
				//					hold turns true because the player has chosen to hold his current hands and the hand total 
				hold = true;
			} // end of else

		} // end of while

		//			if player's total card score is more than 21;
		if (pTotal> 21) {

			//			if he/she had an ace for which they had chosen value 11, they get a chance to change it to count as 1
			//			this assumes that the player did not accidentally chose 11 for two of the aces they were (or will be) dealt, since doing so will make their total way over just 22. 
			if(acePoint==11||acePointAgain==11) {

				System.out.println("Player"+pNum+", one of your card was an Ace and you chose 11 as its value.\nWith that value of Ace, you will be BUSTED.");
				System.out.println("Would you like to change the initial value of ace to 1? Please type 'Y' for yes or'N' for no.");
				String change = getData.next();

				if(change.equalsIgnoreCase("y")) {
					acePoint = 1;
					pTotal= pTotal-10;
					System.out.println("The new total for Player"+pNum+" is "+pTotal+"\n");
					//						calling the method dealPlayerCards recursively because the value of player's total has changed thus the player can draw additional cards
					return dealPlayerCards(pTotal, pNum, pIndex);
				} // end of if (change equals y)

				//					if player's total card score is more than 21 he/she has been busted
				else {
					System.out.println("Player"+pNum+", your total score is more than 21. You have BUSTED!\nBetter luck next time!");
				}	// end of else

			} // end of if (acePoint==11||acePointAgain==11)

			//			dealer is busted if his total card score is more than 21 and he either did not get an ace at all
			else {
				System.out.println("Player"+pNum+", your total score is more than 21. You have BUSTED!\nBetter luck next time!");
			} // end of else (dealer busted)

		} // end of if (pTotal>21)

		//		if player's total score is 21, he has a blackjack
		else if (pTotal==21) {
			System.out.println("Congratulations Player"+pNum+" you have the BlackJack!!");
		} // end of if (pTotal equals 21)

		return pTotal;

	} //end of dealPlayerCards method

	//	dealDealerCards method to deal additional cards to the dealer
	public int dealDealerCards(int dTotal, int dIndex) {


		//		if dealer's current score is less than 16, he/she is dealt another card
		if(dTotal<=16) {
			//			since dealer's card is less than 17, he is automatically dealt another card
			System.out.println("Dealer is dealt another card:\n");

			//			dealing a random number from the deck of remaining cards;
			int indexFromDeck = (ran.nextInt(deckSize))-1;
			int dCard = arrayDeck[indexFromDeck];
			dealerCard[dIndex] = dCard;

			//			printing the suit and rank of the dealt card, using cardSuitRank method
			System.out.println("The dealer's card "+(dIndex+1)+" is: "+cardSuitRank(dCard));

			//				write code to remove the card for main deck here and decrease deck size
			arrayDeck = removeElement(arrayDeck, indexFromDeck);
			deckSize--;

			//			calculating the new total score of the dealer's total cards. "d" indicates dealer.
			dTotal += getValue(dealerCard[dIndex], "d", dTotal);
			System.out.println("Your total is "+dTotal);
			playerArrays(0, dealerCard);
			return dTotal;
		} // end of if ( dTotal <=16)

		//		dealer's score between 16 and 21 i.e. 17, 18, 19, 20
		else if(dTotal>16 && dTotal<21) {
			return dTotal;
		} // end of else if (dTotal>16 & dTotal<21)

		//		if dealer's total card score is more than 21 he/she has been busted
		else if (dTotal> 21) {
			//			dealer has busted if his/her score is more than 21
			System.out.println("Dealer, your score is more than 21. YOU HAVE BUSTED!");
			return dTotal;
		} // end of else if (dTotal > 21)

		//		if dealer's total score is 21, he/she has a blackjack
		else {
			System.out.println("Congratulations dealer you have the BlackJack!!");
			return dTotal;
		} // end of else

	} //end of method dealDealerCards

	//	method playerArrays to assign the respective dealt cards to each player (including the dealer);
	public int [] playerArrays(int playerNum, int[] CardArray) {
		//		0 represents the dealer and 1, 2, 3 and 4 represent player1, 2, 3 and 4 respectively
		if(playerNum==0) {
			dealerCard = CardArray;
			return dealerCard;
		} // end of if

		else if(playerNum==1) {
			player1Card = CardArray;
			return player1Card;
		} // end of if else

		else if(playerNum==2) {
			player2Card = CardArray;
			return player2Card;
		} // end of if else

		else if (playerNum==3) {
			player3Card = CardArray;
			return player3Card;
		} // end of if else

		else if(playerNum==4) {
			player4Card = CardArray;
			return player4Card;
		} // end of if else

		else {
			System.out.println("Somethig is worng with your program. Fix and retry.");
			return null;
		} // end of else

	} // end of playerArrays method

	public int getValue(int cardNum, String player, int total){

		//		if the card is jack, queen or king
		if(cardNum>=10 && cardNum<=13 || cardNum>=23 && cardNum<=26 || cardNum>=36 && cardNum<=39 || cardNum>=49 && cardNum<=52){
			value=10;
		} // end of if

		//		if card is Ace
		else if(cardNum==1 ||cardNum==14 || cardNum==27 || cardNum==40){
			//			if its any of the player's card;
			if(player=="p") { 
				//			the player can chose the value of the ace as either 1 or 11
				System.out.println("Please choose if you want to assign 1 point or 11 points for Ace");
				acePoint = getData.nextInt();
				if (acePoint == 1) value = 1;
				else if (acePoint == 11) value=11;
				//			if the user entered any other value, he gets one more chance to chose between 1 and 11
				else {
					System.out.println("Invalid choice of points for Ace. Please re-enter your choice (1 or 11):");
					acePointAgain = getData.nextInt();
					if (acePointAgain == 1) value = 1;
					else if (acePointAgain == 11) value=11;
					//				second invalid entry will automatically assign the value of ace as 11
					else {
						System.out.println("Invalid entry again. Your ace has been automatically assigned the value 11");
						acePoint =11;
						value = 11;
					} // end of else

				}	// end of else

			} // end of if (player equals p)

			//			if its dealer's card;
			else if(player == "d") {
				if((total+11)<=21) value = 11;
				else value = 1;
			} // end of else (player is dealer)

		} // end of else if (random number represent ace)

		//		if generated random number is between 2 and 10, the value is face value
		else if(cardNum>1 && cardNum<10){
			value=cardNum;
		} // end of else if (random number is between 2 and 10)

		//		if the generated random number is not between 2 and 10 but still represents a number card, the value of the card is its modulus with 13
		else {
			value = cardNum%13;
		} // end of else

		return value;
	} // end of method getValue


	//	method to remove the dealt number from the original deck array
	public int[] removeElement(int[] arr, int index){

		// If the array is empty or the index is not in array range, return the original array 
		if (arr == null || index < 0 || index >= deckSize) {
			return arr; 
		} // end of if

		// Create another array of size one less 
		int[] anotherArray = new int[deckSize - 1]; 

		// Copy the elements except the index from original array to the other array 
		for (int i = 0, j = 0; i < deckSize; i++) {

			// if the index is the removal element index 
			if (i == index) {
				continue; 
			} // end of if (i equals index)

			// if the index is not the removal element index 
			anotherArray[j++] = arr[i]; 
		} // end of for

		// return the resultant array 
		return anotherArray; 
	} // end of removeElement method


	//	method to evaluate the card that is dealt by the random number generator
	public String valueToCard(int card) {
		//		in case the card index is not found, it will return -1 (which is originally assigned assuming that it will change) 
		int cardIndex = -1;
		//		if the random number generated represented ace
		if(card==1||card==14||card==27||card==40) {
			cardIndex = 0;
			return cards[0];
		} // end of if (number represents ace)

		//		if the random number generated numbers between 2 and 10
		else if(card>1 && card<=10) {
			cardIndex = card-1;
			return cards[card-1];
		} // end of else if (number between 2 and 10)

		//		if the random number generated represented Jack
		else if(card==11||card==24||card==37||card==50) {
			cardIndex = 10;
			return cards[10];
		} // end of else if (number represent Jack)

		//		if the random number generated represented Queen
		else if (card==12||card==25||card==38||card==51) {
			cardIndex = 11;
			return cards[11];
		} // end of else if (number represent queen)

		//		if the random number generated represented King
		else if(card==13||card==26||card==39||card==52) {
			cardIndex = 12;
			return cards[12];
		} // end of else if (number represent king)

		//		if the random number generated represented number cards between 2 and 10
		else if(card>=15 && card<=23 || card>=28 && card<=36 || card>=41 && card<=49) {
			int rank = card%13;
			cardIndex = rank-1;
		} // end of else if (number represent card between 2 and 10)

		return cards[cardIndex];
	} // end of valueToCard method


	//	method to convert the random number to respective representation card, displaying the card rank and suit 
	public String cardSuitRank(int card) {
		String dealtCard = null;
		//		if the generated number is less than 13, suit is heart and the card rank is evaluated using the valueToCard method
		if(card<=13) {
			String suit = suits[0];
			String rank = valueToCard(card);
			dealtCard = rank+" of "+suit;
		} // end of if (card <=13)

		//		if the generated number is between 14 and 26, suit is club and the card rank is evaluated using the valueToCard method
		else if (card>13 && card<=26) {
			String suit = suits[1];
			String rank = valueToCard(card);
			dealtCard = rank+" of "+suit;
		} // end of else if (number is between 14 and 26)

		//		if the generated number is between 27 and 39, suit is spade and the card rank is evaluated using the valueToCard method
		else if (card>26 && card<=39) {
			String suit = suits[2];
			String rank = valueToCard(card);
			dealtCard = rank+" of "+suit;
		} // end of else if (number is between 27 and 39)

		//		if the generated number is between 40 and 52, suit is diamond and the card rank is evaluated using the valueToCard method
		else if (card>39 && card<=52) {
			String suit = suits[3];
			String rank = valueToCard(card);
			dealtCard = rank+" of "+suit;
		} // end of else if (number is between 40 and 52)

		return dealtCard;
	} // end of cardSuitRank method


	//	method to determine the winner
	public String winner(int playerNum, int playerTotalFinal, int dealerTotalFinal ) {
		String winner = null;
		//		if both the dealer and the player busted, they both lose
		if(playerTotalFinal>21 && dealerTotalFinal>21) {
			winner = "None";
		} // end of if (both lose)

		//		if the dealer did not bust but still has a higher score than the player
		else if(playerTotalFinal>21 && !(dealerTotalFinal>21) || playerTotalFinal < dealerTotalFinal) {
			winner = "Dealer";
		} // end of else if (dealer wins)

		//		if the player did not bust but still has a higher score than the dealer
		else if(playerTotalFinal<=21 && dealerTotalFinal>21 || playerTotalFinal> dealerTotalFinal) {
			winner = "Player";
		} // end of else if (player wins)

		//		If both are tied
		else if(playerTotalFinal == dealerTotalFinal) {
			winner = "Tied";
		} // end of else if (both tied)

		return winner;
	} // end of winner method

} // end of BlackJack class

