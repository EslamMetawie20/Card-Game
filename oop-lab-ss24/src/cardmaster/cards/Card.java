package cardmaster.cards;

import java.util.Random;

import cardmaster.DeckStack;
import cardmaster.Game;
import cardmaster.Item;
import cardmaster.Shape;


/**
 * <h1>Punkte</h1> 0.5 Punkte für jeden nicht leeren Ablagestapel und 0.5 Punkte
 * für jede einzigartige Form.
 * <p>
 * Beispiele:
 * 
 * <pre>
 *   -
 *   - Stern < gelegt
 *   -
 * </pre>
 * 
 * 0.5 für nicht leere Stapel + 0.5 für die Form Stern
 * 
 * <pre>
 * -Kreis - Stern < gelegt - Kreis
 * </pre>
 * 
 * 1.5 für nicht leere Stapel + 1.0 für die Formen Kreis und Stern.
 */



/**
 * Die abstrakte Klasse Card repräsentiert eine Spielkarte im Spiel.
 * Eine Karte hat eine bestimmte Form und einen Namen.
 * Außerdem kann sie Punkte generieren und Aktionen im Spiel ausführen.
 */
public abstract class Card implements Item {
    private Shape shape;
    private String name;

    /**
     * Erstellt eine neue Karte mit der angegebenen Form und dem angegebenen Namen.
     * 
     * @param shape die Form der Karte
     * @param name der Name der Karte
     */
    public Card(Shape shape, String name) {
        this.shape = shape;
        this.name = name;
    }

    /**
     * Generiert den Preis der Karte basierend auf dem aktuellen Guthaben des Spielers.
     * 
     * @param ownedCredits das aktuelle Guthaben des Spielers
     * @return der generierte Preis der Karte
     */
    public int generatePrice(double ownedCredits) {
        Random random = new Random();
        double price = (((ownedCredits / 4) * (random.nextDouble(0.4) + 0.8)) + random.nextInt(-1, 1));
        if (price < 1) {
            return 1;
        } else {
            price = Math.round(price);
            return (int) price;
        }
    }

    /**
     * Führt die Aktion der Karte im Spiel aus.
     * Entfernt die Karte aus dem Shop und fügt sie dem Ablagestapel hinzu.
     * 
     * @param game das aktuelle Spiel
     * @return true, wenn die Aktion erfolgreich war, ansonsten false
     */
    @Override
    public boolean action(Game game) {
        game.getShop().remove(this);
        return game.getDrawPile().addCard(this);
    }

    /**
     * Berechnet die Punkte, die die Karte in einem bestimmten Ablagestapel bringt.
     * 
     * @param d der Ablagestapel, in dem sich die Karte befindet
     * @return die Punkte, die die Karte in dem Ablagestapel bringt
     */
    public abstract double calculatePoints(DeckStack d);

    /**
     * Gibt die Form der Karte zurück.
     * 
     * @return die Form der Karte
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * Gibt den Namen der Karte zurück.
     * 
     * @return der Name der Karte
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen der Karte.
     * 
     * @param name der neue Name der Karte
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setzt die Form der Karte.
     * 
     * @param shape die neue Form der Karte
     */
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    /**
     * Überprüft, ob eine andere Karte gleich dieser Karte ist.
     * 
     * @param obj die andere Karte, die überprüft werden soll
     * @return true, wenn die Karten gleich sind, ansonsten false
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Card)) {
            return false;
        }
        Card otherCard = (Card) obj;

        return otherCard.name.equals(this.name) && otherCard.shape.equals(this.shape);
    }

    /**
     * Berechnet den Hash-Code dieser Karte.
     * 
     * @return der Hash-Code der Karte
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Gibt eine textuelle Repräsentation dieser Karte zurück.
     * 
     * @return eine textuelle Repräsentation der Karte
     */
    @Override
    public String toString() {
        return "Karte: " + name + ", " + shape.toString();
    }
}
