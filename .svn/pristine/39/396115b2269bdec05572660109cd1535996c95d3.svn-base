package cardmaster.cards;

import cardmaster.CardFactory;
import cardmaster.DeckStack;
import cardmaster.Shape;

/**
 * Die KombiCard Klasse repräsentiert eine Kombination aus zwei Spielkarten.
 * Sie hat die gleiche Form wie die beiden Karten, die sie kombiniert.
 */
public class KombiCard extends Card {
    private Card c1, c2;

    /**
     * Erstellt eine KombiCard, die die angegebenen Karten kombiniert.
     * 
     * @param c1 die erste zu kombinierende Karte
     * @param c2 die zweite zu kombinierende Karte
     * @throws IllegalArgumentException wenn die Formen der beiden Karten nicht übereinstimmen
     */
    public KombiCard(Card c1, Card c2) {
        super(c1.getShape(), "Kombi");
        if (!c1.getShape().equals(c2.getShape())) {
            throw new IllegalArgumentException("Die beiden Karten haben unterschiedliche Formen: " + c1.toString() + c2.toString());
        }
        this.c1 = c1;
        this.c2 = c2;
    }

    /**
     * Erstellt eine KombiCard mit einer zufällig generierten Form.
     * 
     * @param shape die Form der zu erstellenden Karte
     */
    public KombiCard(Shape shape) {
        super(shape, "Kombi");
        this.c1 = CardFactory.getDefaultFactory().createRandom(shape);
        this.c2 = CardFactory.getDefaultFactory().createRandom(shape);
    }

    /**
     * Berechnet die Punkte, die diese KombiCard in einem bestimmten Ablagestapel bringt.
     * Die Punkte sind die Summe der Punkte der beiden kombinierten Karten.
     * 
     * @param deck der Ablagestapel, in dem sich die Karte befindet
     * @return die Punkte, die diese KombiCard bringt
     */
    @Override
    public double calculatePoints(DeckStack deck) {
        return c1.calculatePoints(deck) + c2.calculatePoints(deck);
    }

    @Override
    public String toString() {
        return c1.toString() + " + " + c2.toString();
    }
}
