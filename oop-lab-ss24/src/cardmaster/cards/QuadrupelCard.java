package cardmaster.cards;

import cardmaster.DeckStack;
import cardmaster.Shape;

/**
 * Die QuadrupelCard Klasse repräsentiert eine Spielkarte, die für jede Serie von vier Karten mit der gleichen Form Punkte vergibt.
 * 
 * Beispiele:
 * - Kreis - Kreis - Kreis - Kreis
 * - Stern - Stern - Stern - Stern
 */
public class QuadrupelCard extends Card {

    /**
     * Erstellt eine QuadrupelCard mit der angegebenen Form.
     * 
     * @param shape die Form der Karte
     */
    public QuadrupelCard(Shape shape) {
        super(shape, "Quadrupel");
    }

    /**
     * Berechnet die Punkte, die diese QuadrupelCard in einem bestimmten Ablagestapel bringt.
     * Für jede Serie von vier Karten mit der gleichen Form werden 10 Punkte vergeben.
     * 
     * @param d der Ablagestapel, in dem sich die Karte befindet
     * @return die Punkte, die diese QuadrupelCard bringt
     */
    @Override
    public double calculatePoints(DeckStack d) {
        double score = 0;
        int counter = 0;
        for (int i = 0; i < d.getDrawPiles().length; i++) {
            if (d.getDrawPiles()[i].getCards().length != 0) {
                if (d.getDrawPiles()[i].seeTopCard().getShape() == this.getShape()) {
                    counter++;
                }
            }
        }
        score += (counter / 4) * 10;
        return score;
    }
}
