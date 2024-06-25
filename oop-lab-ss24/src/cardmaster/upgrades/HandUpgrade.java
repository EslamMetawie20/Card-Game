package cardmaster.upgrades;

import cardmaster.Game;

/**
 * Die HandUpgrade Klasse repräsentiert ein Upgrade, das dem Spieler erlaubt, eine zusätzliche Handkarte zu halten.
 */
public class HandUpgrade extends Upgrade {

    /**
     * Erstellt ein neues HandUpgrade mit dem angegebenen Zähler.
     * 
     * @param counter der Zähler für die Anzahl der Käufe des Upgrades
     */
    public HandUpgrade(int counter) {
        super("Zusätzliche Handkarte");
        this.setCounter(counter);
    }

    /**
     * Führt die Aktion des HandUpgrades im Spiel aus.
     * Erhöht die maximale Handgröße des Spielers um eins.
     * 
     * @param game das aktuelle Spiel
     * @return true, wenn die Aktion erfolgreich war, ansonsten false
     */
    @Override
    public boolean action(Game game) {
        game.getHand().setMaximumHandSize(game.getHand().getMaximumHandSize() + 1);

        game.getShop().remove(this);

        this.setCounter(getCounter() + 1);
        return true;
    }

}
