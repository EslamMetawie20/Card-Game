package cardmaster;

import cardmaster.cards.Card;

/**
 * Represents a deck of cards.
 */
public class DeckStack {

    /**
     * Array to hold the cards in the deck.
     */
    private DrawPile[] drawPiles;
    private int cardCounter;
    private int arrayLength;

    /**
     * Constructs a new deck with a fixed size of 3 cards.
     */
    public DeckStack() {
        arrayLength = 3;
        drawPiles = new DrawPile[arrayLength];
        // Initialize
        for (int i = 0; i < drawPiles.length; i++) {
            drawPiles[i] = new DrawPile();
        }
        cardCounter = 0;
    }

    /**
     * Calculates the score of the deck based on certain criteria.
     *
     * @param indexDeck the index of the deck in the array
     * @return The calculated score of the deck.
     */
    public double calculateScore(int indexDeck) {
        return this.drawPiles[indexDeck].seeTopCard().calculatePoints(this);
    }

    /**
     * Places a card at the specified index in the deck.
     *
     * @param card The card to place.
     * @param index The index where the card should be placed.
     */
    public void placeCardAt(Card card, int index) {
        this.drawPiles[index].addCard(card);
        cardCounter++;
    }

    /**
     * Retrieves the shapes of the top cards of each draw pile.
     *
     * @return an array containing the shapes of the top cards of each draw pile
     */
    public Shape[] getTopShapes() {
        Shape[] shape = new Shape[drawPiles.length];
        for (int i = 0; i < drawPiles.length; i++) {
            if (drawPiles[i].getCards().length != 0) {
                shape[i] = drawPiles[i].seeTopCard().getShape();
            }
        }
        return shape;
    }

    /**
     * Retrieves all the cards from the deck and resets the deck.
     *
     * @return an array containing all the cards from the deck
     */
    public Card[] getAllCards() {
        int cardsCounter = cardCounter;
        if (cardsCounter == 0) {
            return null;
        }
        Card[] card = new Card[cardsCounter];
        int indexCard = 0;

        for (int i = 0; i < this.drawPiles.length; i++) {
            for (int j = 0; j < this.drawPiles[i].getCards().length; j++) {
                card[indexCard++] = drawPiles[i].getCards()[j];
            }
        }

        // resetting values
        drawPiles = new DrawPile[drawPiles.length];
        // Initialize
        for (int i = 0; i < drawPiles.length; i++) {
            drawPiles[i] = new DrawPile();
        }
        cardCounter = 0;
        return card;
    }

    /**
     * Adds a new draw pile to the deck.
     */
    public void addDrawPile() {
        this.drawPiles = new DrawPile[drawPiles.length + 1];
        // Initialize
        for (int i = 0; i < drawPiles.length; i++) {
            drawPiles[i] = new DrawPile();
        }
        arrayLength++;
    }

    /**
     * Retrieves the draw piles in the deck.
     *
     * @return an array containing the draw piles in the deck
     */
    public DrawPile[] getDrawPiles() {
        return drawPiles;
    }

    /**
     * Retrieves the number of cards in the deck.
     *
     * @return the number of cards in the deck
     */
    public int getCardCounter() {
        return cardCounter;
    }

    /**
     * Retrieves the number of draw piles in the deck.
     *
     * @return the number of draw piles in the deck
     */
    public int getDrawPileDeckCounter() {
        return drawPiles.length;
    }

    /**
     * Retrieves the length of the draw pile array.
     *
     * @return the length of the draw pile array
     */
    public int getArrayLength() {
        return arrayLength;
    }

    /**
     * Sets the length of the draw pile array.
     *
     * @param arrayLength the new length of the draw pile array
     */
    public void setArrayLength(int arrayLength) {
        this.arrayLength = arrayLength;
    }

}
