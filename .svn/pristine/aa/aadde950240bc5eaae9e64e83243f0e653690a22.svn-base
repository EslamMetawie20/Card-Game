package cardmaster;

import java.util.Random;

import cardmaster.cards.*;

/**
 * Die CardFactory Klasse erzeugt Spielkarten für das Spiel.
 * Sie ermöglicht die Erstellung von zufälligen Karten, Kartenkombinationen und spezifischen Karten.
 */
public class CardFactory {

    /**
     * Erstellt eine Standard-CardFactory.
     * 
     * @return eine Standard-CardFactory
     */
    public static CardFactory getDefaultFactory() {
        return new CardFactory() {
            /**
             * Erzeugt eine zufällige Karte.
             * 
             * @return eine zufällig generierte Karte
             */
            @Override
            public Card createRandom() {
                Shape s = Shape.getRandom();

                Random r = new Random();
                int random = r.nextInt(5);
                switch (random) {
                    case 0:
                        return new ChanceCard(s);
                    case 1:
                        return new PaarCard(s);
                    case 2:
                        return new TripelCard(s);
                    case 3:
                        return new QuadrupelCard(s);
                    case 4:
                        return new KombiCard(s);
                    default:
                        throw new IllegalArgumentException(random + " is not a valid card");
                }
            }

            /**
             * Erzeugt eine zufällige Karte mit einer bestimmten Form.
             * 
             * @param s die Form der zu erstellenden Karte
             * @return eine zufällig generierte Karte mit der angegebenen Form
             */
            public Card createRandom(Shape s) {
                Random r = new Random();
                int random = r.nextInt(5);
                switch (random) {
                    case 0:
                        return new ChanceCard(s);
                    case 1:
                        return new PaarCard(s);
                    case 2:
                        return new TripelCard(s);
                    case 3:
                        return new QuadrupelCard(s);
                    case 4:
                        return new KombiCard(s);
                    default:
                        throw new IllegalArgumentException(random + " is not a valid card");
                }
            }
        };
    }

    /**
     * Erzeugt eine zufällige Karte.
     * 
     * @return eine zufällig generierte Karte
     */
    public Card createRandom() {
        Shape s = Shape.getRandom();
        return new ChanceCard(s);
    }

    /**
     * Kombiniert zwei Karten zu einer Kombinationskarte.
     * 
     * @param c1 die erste Karte
     * @param c2 die zweite Karte
     * @return die resultierende Kombinationskarte
     */
    public Card combine(Card c1, Card c2) {
        return new KombiCard(c1, c2);
    }

    /**
     * Erzeugt eine Karte mit einem bestimmten Namen und Form.
     * 
     * @param name der Name der zu erstellenden Karte
     * @param shape die Form der zu erstellenden Karte
     * @return die erstellte Karte
     * @throws NullPointerException wenn der Name oder die Form null sind
     * @throws IllegalArgumentException wenn der Name keine gültige Karte repräsentiert
     */
    public Card create(String name, Shape shape) {
        if (name == null || shape == null) {
            throw new NullPointerException();
        }
        switch (name) {
            case "Chance":
                return new ChanceCard(shape);
            case "Paar":
                return new PaarCard(shape);
            case "Tripel":
                return new TripelCard(shape);
            case "Quadrupel":
                return new QuadrupelCard(shape);
            case "Kombi":
                return new KombiCard(shape);
            default:
                throw new IllegalArgumentException(name + " is not a valid card");
        }
    }

    /**
     * Erzeugt eine zufällige Karte mit einer bestimmten Form.
     * 
     * @param shape die Form der zu erstellenden Karte
     * @return eine zufällig generierte Karte mit der angegebenen Form
     * @throws UnsupportedOperationException da die Methode nicht implementiert ist
     */
    public Card createRandom(Shape shape) {
        throw new UnsupportedOperationException("Unimplemented method 'createRandom'");
    }
}
