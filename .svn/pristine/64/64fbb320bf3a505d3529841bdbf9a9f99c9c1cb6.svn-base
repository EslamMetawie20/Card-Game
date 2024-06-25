package cardmaster;

/**
 * Das Item-Interface definiert Methoden, die von spielbaren Objekten im Spiel implementiert werden müssen.
 */
public interface Item {
    /**
     * Generiert den Preis für das Objekt basierend auf dem aktuellen Guthaben des Spielers.
     * 
     * @param ownedCredits das aktuelle Guthaben des Spielers
     * @return der generierte Preis für das Objekt
     */
    public int generatePrice(double ownedCredits);

    /**
     * Führt die Aktion des Objekts im Spiel aus.
     * 
     * @param game das aktuelle Spiel
     * @return true, wenn die Aktion erfolgreich war, ansonsten false
     */
    public boolean action(Game game);
}
