package cardmaster;

import cardmaster.cards.Card;
import cardmaster.cards.ChanceCard;

/**
 * Klasse für die Durchführung eines Spiels. Ein neues Spiel beginnt in Runde 1
 * im Modus {@link Mode#SHOPPING}. Nun muss mindestens eine Karte gekauft
 * werden. Dafür können die Methode für den Shopping-Modus (siehe unten)
 * verwendet werden. Der Nutzer dieser Klasse muss {@link #endShopping()}
 * aufrufen, um in den Playing-Modus ({@link Mode#PLAYING}) zu wechseln. Nun
 * werden die Methoden für den Playing-Modus verwendet, um wiederholt eine Karte
 * aus der Hand zu spielen ({@link #play(ChanceCard, int)}). Beim Wechseln in
 * den Playing-Modus und nach Legen einer Karte, wird die Hand wieder
 * aufgefüllt, sofern möglich. Wurde die letzte Karte aus der Hand gelegt, wird
 * entweder, falls das die letzte Runde war, in den End-Modus ({@link Mode#END})
 * gewechselt oder zurück in den Shopping-Modus. Beim Starten des Shopping-Modus
 * wird der Shop mit komplett zufälligen neuen Gegenständen gefüllt. Am Anfang
 * passen fünf Gegenstände in den Shop.
 */
public class Game {

    private Shop shop;
    private DrawPile drawPile;
    private DeckStack deck;
    private Hand hand;
    private int maxRounds;
    private int currentRound;
    private Mode mode;
    private CardFactory factory;

    /**
     * Erzeugt ein neues Spiel.
     *
     * @param maxRounds Anzahl der Runden. Muss mindestens {@code 1} sein.
     */
    public Game(int maxRounds) {
        if (maxRounds <= 0) {
            throw new IllegalArgumentException(
                    "Die Anzahl der Runden (maxRounds) muss größer als 0 sein. " + maxRounds + " Runden nicht erlaubt");
        }

        factory = new CardFactory();
        this.mode = Mode.SHOPPING;
        this.currentRound = 1;
        this.maxRounds = maxRounds;
        drawPile = new DrawPile();
        deck = new DeckStack();
        this.shop = new Shop(factory);
        this.hand = new Hand();
    }

    public Game(int maxRounds, CardFactory f) {
        if (maxRounds <= 0) {
            throw new IllegalArgumentException(
                    "Die Anzahl der Runden (maxRounds) muss größer als 0 sein. " + maxRounds + " Runden nicht erlaubt");
        }

        factory = f;
        this.mode = Mode.SHOPPING;
        this.currentRound = 1;
        this.maxRounds = maxRounds;
        drawPile = new DrawPile();
        deck = new DeckStack();
        this.shop = new Shop(factory);
        this.hand = new Hand();
    }
    // Allgemein verwendbare Methoden.

    /**
     * Gibt den aktuellen Punktestand zurück.
     */
    public double getCredits() {
        return shop.getCredits(); // TODO
    }

    /**
     * Gibt den aktuellen Modus zurück.
     */
    public Mode getMode() {
        return this.mode; // TODO
    }

    /**
     * Gibt die Anzahl an Ablagestapel zurück.
     */
    public int getStacksCount() {
        return deck.getArrayLength();
    }

    // Methoden für den Shopping-Modus
    /**
     * Gibt zurück, ob der Shop leer gekauft wurde.
     */
    public boolean isShopEmpty() {
        if (this.mode != Mode.SHOPPING) {
            throw new IllegalCallException("isShopEmpty", this.mode.toString(), Mode.SHOPPING.toString());
        }
        return shop.isShopEmpty(); // TODO
    }

    /**
     * Gibt die Anzahl der noch im Shop verfügbaren Gegenstände an.
     */
    public int getShopItemCount() {
        if (this.mode != Mode.SHOPPING) {
            throw new IllegalCallException("getShopItemCount", this.mode.toString(), Mode.SHOPPING.toString());
        }
        return shop.getShopItemCount(); // TODO
    }

    /**
     * Gibt eine benutzerfreundliche Darstellung für die Konsole des Gegenstands
     * mit index {@code shopItemIndex} zurück.
     *
     * @param shopItemIndex index aus dem Intervall
     * {@code [0, this.getShopItemCount())}.
     */
    public String getShopItemDescription(int shopItemIndex) {
        if (this.mode != Mode.SHOPPING) {
            throw new IllegalCallException("getShopItemDescription", this.mode.toString(), Mode.SHOPPING.toString());
        }
        return shop.getCards()[shopItemIndex].toString(); // TODO
    }

    /**
     * Gibt den Preis des Gegenstands mit index {@code shopItemIndex} zurück.
     *
     * @param shopItemIndex index aus dem Intervall
     * {@code [0, this.getShopItemCount())}.
     */
    public int getShopItemPrice(int shopItemIndex) {
        if (this.mode != Mode.SHOPPING) {
            throw new IllegalCallException("getShopItemPrice", this.mode.toString(), Mode.SHOPPING.toString());
        }
        return shop.getShopItemPrice(shopItemIndex); // TODO
    }

    /**
     * Versucht den Gegenstand an index {@code shopItemIndex} zu kaufen und
     * entfernt den Gegenstand bei Erfolg aus dem Sortiment.
     * <p>
     * Bei erfolgreichem Kauf können sich die Indexe der Gegenstände ändern.
     * Alle vorherigen Rückgaben von {@link #getShopItemCount} und
     * {@link #getShopItemDescription(int)} sind nicht mehr gültig.
     *
     * @param shopItemIndex index aus dem Intervall
     * {@code [0, this.getShopItemCount())}.
     * @return Ob der Gegenstand gekauft wurde. Bei einem zu geringen
     * Punktestand hingegen {@code false}.
     */
    public boolean buy(int shopItemIndex) {
        if (this.mode != Mode.SHOPPING) {
            throw new IllegalCallException("buy", this.mode.toString(), Mode.SHOPPING.toString());
        }
        Item card = shop.getCardsAtIndex(shopItemIndex);

        if (card == null) {
            return false;
        }

        card.action(this);

        return true;
    }

    /**
     * Gibt zurück, ob auf dem Nachziehstapel keine Karte liegt.
     * <p>
     * Dies ist am Anfang des Spiels der Fall. Da diese Methode nur im Modus
     * {@link Mode#SHOPPING} verwendet werden darf, ist die Rückgabe nach dem
     * ersten Kartenkauf immer {@code true}.
     */
    public boolean isDrawPileEmpty() {
        if (this.mode != Mode.SHOPPING) {
            throw new IllegalCallException("isDrawPileEmpty", this.mode.toString(), Mode.SHOPPING.toString());
        }
        return drawPile.isEmpty(); // TODO
    }

    /**
     * Beendet die Shop-Interaktion und wechselt in den Playing-Modus. Wurde
     * noch nie eine Karte gekauft, passiert nichts, da mindestens eine Karte
     * fürs Spielen notwendig ist.
     */
    public void endShopping() {
        if (this.mode != Mode.SHOPPING) {
            throw new IllegalCallException("endShopping", this.mode.toString(), Mode.SHOPPING.toString());
        }
        if (drawPile.countStack() > 0) {
            this.mode = Mode.PLAYING;
            shop.endShopping();
            drawPile.mix();
            refillHandFromdrawPile();
        }
    }

    // Methoden für den Playing-Modus
    /**
     * Gibt zurück, wie viele Karten aktuell auf der Hand gehalten werden.
     */
    public int getHandCardsCount() {
        if (this.mode != Mode.PLAYING) {
            throw new IllegalCallException("getHandCardsCount", this.mode.toString(), Mode.PLAYING.toString());
        }
        return hand.getHandCardsCount(); // TODO
    }

    /**
     * Gibt die Handkarte mit index {@code handCardIndex} zurück.
     *
     * @param handCardIndex index aus dem Intervall
     * {@code [0, this.getHandCardsCount())}.
     */
    public Card getHandCard(int handCardIndex) {
        if (this.mode != Mode.PLAYING) {
            throw new IllegalCallException("getHandCard", this.mode.toString(), Mode.PLAYING.toString());
        }
        return hand.getHand()[handCardIndex];
    }

    /**
     * Legt die Handkarte auf den Ablagestapel mit index {@code stackIndex}.
     * Stellen Sie mit assert sicher, dass die Karte auch wirklich auf der Hand
     * ist.
     * <p>
     * Die Indexe der Karten kann sich hierbei ändern. Vor allem wird eine Karte
     * - wenn möglich - nachgezogen. Dadurch sind alle vorherigen Rückgaben von
     * {@link #getHandCardsCount()} und {@link #getHandCard(int)} nicht mehr
     * gültig.
     *
     * @param card die zu legende Handkarte.
     * @param stackIndex index aus dem Intervall
     * {@code [0, this.getStacksCount())}.
     */
    public void play(Card card, int stackIndex) {
        if (this.mode != Mode.PLAYING) {
            throw new IllegalCallException("play", this.mode.toString(), Mode.PLAYING.toString());
        }
        if (stackIndex < 0 || stackIndex >= this.getStacksCount()) {
            throw new IndexOutOfBoundsException(
                    stackIndex + "negative stackIndex are invalid. Please enter a number between 0 and "
                    + (this.getStacksCount() - 1));
        }

        Integer indexOnHand = -1;
        for (int i = 0; i < hand.getHandCardsCount(); i++) {
            if (hand.getHand()[i] == card) {
                indexOnHand = i;
            }
        }

        if (indexOnHand == -1) {
            throw new IllegalArgumentException("Following card is not in the hand: " + card.toString());
        }

        deck.placeCardAt(hand.getHandCard(indexOnHand), stackIndex);
        // add credits
        shop.setCredits(shop.getCredits() + deck.calculateScore(stackIndex));
        refillHandFromdrawPile();

        // change mode
        if (hand.getHandCardsCount() == 0) {
            this.mode = Mode.SHOPPING;
            this.currentRound++;

            Card[] temp = deck.getAllCards();
            for (int i = 0; i < temp.length; i++) {
                drawPile.addCard(temp[i]);
            }
        }

        if (this.maxRounds < this.currentRound) {
            this.mode = Mode.END;
            ScoreBoard.getInstance().add(this.shop.getCredits());
            return;
        }
    }

    private void refillHandFromdrawPile() {
        // Refill hand
        while (hand.getHandCardsCount() < hand.getMaximumHandSize()) {
            Card temp = drawPile.getTopCard();
            if (temp == null) {
                break;
            }
            hand.addCard(temp);
        }
    }

    /**
     * Liefert die Form der auf den Ablagestapeln liegenden Karten. An Index
     * {@code i} ist die Form für den Stapel mit index {@code i} oder
     * {@code null}. {@code I} ist aus de Intervall
     * {@code [0, this.getStacksCount())}.
     */
    public Shape[] getTopShapes() {
        if (this.mode != Mode.PLAYING) {
            throw new IllegalCallException("getTopShapes", this.mode.toString(), Mode.PLAYING.toString());
        }
        return deck.getTopShapes();
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public DrawPile getDrawPile() {
        return drawPile;
    }

    public void setDrawPile(DrawPile drawPile) {
        this.drawPile = drawPile;
    }

    public DeckStack getDeck() {
        return deck;
    }

    public void setDeck(DeckStack deck) {
        this.deck = deck;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public int getMaxRounds() {
        return maxRounds;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public enum Mode {
        SHOPPING, PLAYING, END;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public static void main(String[] args) {
        Game game = new Game(10);
        Player player = new Player();
        player.run(game);
    }
}
