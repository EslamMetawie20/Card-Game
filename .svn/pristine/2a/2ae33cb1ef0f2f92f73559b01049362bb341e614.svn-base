package cardmaster;

import cardmaster.cards.Card;

/**
 * Die Hand Klasse repräsentiert die Hand eines Spielers im Spiel.
 */
public class Hand {
    private Card[] hand;
    private int maximumHandSize;

    /**
     * Erstellt eine neue leere Hand mit einer maximalen Handgröße von 4.
     */
    public Hand() {
        hand = new Card[0];
        maximumHandSize = 4;
    }

    /**
     * Fügt eine Karte zur Hand hinzu, wenn möglich.
     * 
     * @param card die Karte, die zur Hand hinzugefügt werden soll
     * @return true, wenn die Karte erfolgreich zur Hand hinzugefügt wurde, ansonsten false
     */
    public boolean addCard(Card card) {
        if (card == null || hand.length >= maximumHandSize) {
            return false;
        }

        Card[] tempCard = new Card[hand.length + 1];

        // Kopieren des Arrays
        if (hand.length > 0) {
            System.arraycopy(hand, 0, tempCard, 0, hand.length);
        }
        tempCard[hand.length] = card;
        hand = tempCard;

        return true;
    }

    /**
     * Entfernt eine Karte aus der Hand anhand ihres Indexes.
     * 
     * @param handCardIndex der Index der Karte, die entfernt werden soll
     * @return die entfernte Karte
     */
    public Card getHandCard(int handCardIndex) {
        Card temp = hand[handCardIndex];

        Card[] arrTemp = new Card[hand.length - 1];

        int indexCard = 0;

        for (int i = 0; i < hand.length; i++) {
            if (i == handCardIndex) {
                continue;
            }
            arrTemp[indexCard++] = hand[i];
        }

        hand = arrTemp;
        return temp;
    }

    /**
     * Gibt die Anzahl der Karten in der Hand zurück.
     * 
     * @return die Anzahl der Karten in der Hand
     */
    public int getHandCardsCount() {
        return hand.length;
    }

    /**
     * Gibt die Karten in der Hand zurück.
     * 
     * @return ein Array, das die Karten in der Hand enthält
     */
    public Card[] getHand() {
        return hand;
    }

    /**
     * Setzt die Karten in der Hand auf die angegebene Liste.
     * 
     * @param hand das Array, das die neuen Karten in der Hand enthält
     */
    public void setHand(Card[] hand) {
        this.hand = hand;
    }

    /**
     * Gibt die maximale Handgröße zurück.
     * 
     * @return die maximale Handgröße
     */
    public int getMaximumHandSize() {
        return maximumHandSize;
    }

    /**
     * Legt die maximale Handgröße fest.
     * 
     * @param maximumHandSize die neue maximale Handgröße
     */
    public void setMaximumHandSize(int maximumHandSize) {
        this.maximumHandSize = maximumHandSize;
    }

}
