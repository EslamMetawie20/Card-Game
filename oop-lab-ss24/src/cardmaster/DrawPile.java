package cardmaster;

import java.util.Random;

import cardmaster.cards.Card;

/**
 * The Stack class represents a stack of playing cards.
 */
public class DrawPile {
	/** The array of cards in the stack. */
	private Card[] cards;

	/**
	 * Constructs an empty stack of cards.
	 */
	public DrawPile() {
		cards = new Card[0];
	}

	/**
	 * Adds a card to the top of the stack.
	 *
	 * @param card The card to add to the stack.
	 */
	public boolean addCard(Card card) {
		// Create a new array with one more element than the current array
		Card[] tempCard = new Card[cards.length + 1];

		// Copying existing cards to the new array
		if (cards.length > 0) {
			for (int i = 0; i < cards.length; i++) {
				tempCard[i] = cards[i];
			}
		}

		// Add the new card to the end of the new array
		tempCard[cards.length] = card;
		// Update the reference to the array of cards
		cards = tempCard;
		return true;
	}

	/**
	 * Shuffles the cards in the stack.
	 */
	public void mix() {
		if (cards.length > 1) {
			for (int i = 0; i < cards.length; i++) {
				Random random=new Random();
				int index = random.nextInt(0,cards.length);
				// Swap the current card with a randomly selected card
				Card temp = cards[i];
				cards[i] = cards[index];
				cards[index] = temp;
			}
		}
	}

	/**
	 * Removes and returns the top card from the stack.
	 *
	 * @return The top card of the stack, or null if the stack is empty.
	 */
	public Card getTopCard() {
		if (cards.length > 0) {
			// Get the top card
			Card cardTemp = cards[0];
	
			// If there are more than one card, create a new array without the top card
			if (cards.length > 1) {
				Card[] temp = new Card[cards.length - 1];
				for (int i = 1; i < cards.length; i++) {
					temp[i - 1] = cards[i];
				}
				cards = temp;
			} else {
				// If there is only one card, create an empty array
				cards = new Card[0];
			}
	
			// Return the top card
			return cardTemp;
		}
		// Return null if there are no cards in the stack
		return null;
	}

	public Card seeTopCard() {
		return cards[cards.length - 1];
	}

	/**
	 * Gets all the cards in the stack.
	 *
	 * @return An array containing all the cards in the stack.
	 */
	public Card[] getCards() {
		return cards;
	}

	/**
	 * Counts the number of cards in the stack.
	 *
	 * @return The number of cards in the stack.
	 */
	public int countStack() {
		return cards.length;
	}

	public boolean isEmpty() {
		return this.countStack() == 0;
	}

	public void setCards(Card[] cards) {
		this.cards = cards;
	}

}
