package cardmaster.upgrades;

import cardmaster.Game;

/**
 * Die DeckStackUpgrade Klasse repräsentiert ein Upgrade, das dem Spieler erlaubt, einen zusätzlichen Ablagestapel zu haben.
 */
public class DeckStackUpgrade extends Upgrade {

    /**
     * Erstellt ein neues DeckStackUpgrade mit dem angegebenen Zähler.
     * 
     * @param counter der Zähler für die Anzahl der Käufe des Upgrades
     */
    public DeckStackUpgrade(int counter) {
        super("Zusätzlicher Ablagestapel");
        this.setCounter(counter);
    }

    /**
     * Führt die Aktion des DeckStackUpgrades im Spiel aus.
     * Fügt dem Spiel einen neuen Ablagestapel hinzu.
     * 
     * @param game das aktuelle Spiel
     * @return true, wenn die Aktion erfolgreich war, ansonsten false
     */
    @Override
    public boolean action(Game game) {
        game.getDeck().addDrawPile();

        game.getShop().remove(this);

        this.setCounter(getCounter() + 1);
        return true;
    }

}
