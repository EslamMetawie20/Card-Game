package cardmaster.upgrades;

import cardmaster.Item;

/**
 * Die Upgrade Klasse repräsentiert ein Upgrade-Objekt im Spiel.
 * Upgrade-Objekte können gekauft werden und bieten verschiedene Verbesserungen im Spiel.
 */
public abstract class Upgrade implements Item {
    private String name;
    private int counter;

    /**
     * Erstellt ein neues Upgrade mit dem angegebenen Namen.
     * 
     * @param name der Name des Upgrades
     */
    public Upgrade(String name) {
        this.name = name;
        counter = 0;
    }

    /**
     * Generiert den Preis für das Upgrade basierend auf der Anzahl der bisherigen Käufe.
     * 
     * @param ownedCredits das aktuelle Guthaben des Spielers
     * @return der generierte Preis für das Upgrade
     */
    public int generatePrice(double ownedCredits) {
        return (counter * counter) + 1;
    }

    /**
     * Gibt eine textuelle Repräsentation dieses Upgrades zurück.
     * 
     * @return eine textuelle Repräsentation des Upgrades
     */
    @Override
    public String toString() {
        return "Karte: " + name + ", " + counter + " buycounter";
    }

    /**
     * Gibt den Zähler für die Anzahl der Käufe dieses Upgrades zurück.
     * 
     * @return der Zähler für die Anzahl der Käufe
     */
    public int getCounter() {
        return counter;
    }

    /**
     * Setzt den Zähler für die Anzahl der Käufe dieses Upgrades.
     * 
     * @param counter der neue Wert für den Zähler
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }
}
