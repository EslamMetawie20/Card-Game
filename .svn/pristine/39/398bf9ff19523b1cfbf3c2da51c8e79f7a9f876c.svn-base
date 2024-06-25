package cardmaster.upgrades;

import cardmaster.Game;

/**
 * Die ShopUpgrade Klasse repräsentiert ein Upgrade, das dem Spieler erlaubt, eine weitere Karte im Shop zu haben.
 */
public class ShopUpgrade extends Upgrade {

    /**
     * Erstellt ein neues ShopUpgrade mit dem angegebenen Zähler.
     * 
     * @param counter der Zähler für die Anzahl der Käufe des Upgrades
     */
    public ShopUpgrade(int counter) {
        super("Weitere Karte im Shop");
        this.setCounter(counter);
    }

    /**
     * Führt die Aktion des ShopUpgrades im Spiel aus.
     * Erhöht die maximale Anzahl von Karten im Shop um eins.
     * 
     * @param game das aktuelle Spiel
     * @return true, wenn die Aktion erfolgreich war, ansonsten false
     */
    @Override
    public boolean action(Game game) {
        game.getShop().setArrayLength(game.getShop().getArrayLength() + 1);

        game.getShop().remove(this);

        this.setCounter(getCounter() + 1);
        return true;
    }

}
