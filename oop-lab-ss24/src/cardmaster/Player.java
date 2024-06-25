package cardmaster;

import cardmaster.Game.Mode;
import cardmaster.cards.Card;
import java.util.Scanner;

/**
 * Hilfsklasse zur Interaktion mit dem Spiel.
 */
public class Player {

    Scanner sc;

    public Player() {
        sc = new Scanner(System.in);
    }

    /**
     * Wird aufgerufen, wenn mit dem Shop interagiert wird. Gibt entweder den
     * Index ({@code 0 .. game.getShopItemCount()} des zu kaufenden Items zurück
     * oder eine negative Zahl, wenn nichts weiter gekauft werden soll.
     *
     * @param game das Spiel.
     * @return Der index des Items oder eine negative Zahl, um in den
     * Playing-Modus zu wechseln.
     */
    public int shop(Game game) {
        System.out.println("Folgende Karten stehen im Shop zur Verfügung: ");
        Shop shop = game.getShop();
        Item[] itemArray = shop.getCards();
        for (int i = 0; i < game.getShopItemCount(); i++) {
            System.out.println("(" + i + ")" + ": " + itemArray[i] + ", " + shop.getShopPrice()[i] + " Credits");
        }
        System.out.println("(-1) Spielen");
        System.out.println("Verfügbare Credits: " + shop.getCredits());
        System.out.print(">>> ");
        int index = sc.nextInt();
        if (index < 0) {
            return -1; // Beendet den Shopping-Modus
        }
        return index;

    }

    /**
     * Es soll die nächste Karte gelegt werden. Verwenden Sie die Methoden in
     * {@link Game}, um die Handkarten abzufragen und legen sie dann mit
     * {@link Game#play(Card, int)} eine Karte.
     *
     * @param game das Spiel.
     */
    public void playCard(Game game) {
        System.out.println("=====================================");
        System.out.println("Folgende Karten stehen zur Verfügung?");
        Card[] card = game.getHand().getHand();
        for (int i = 0; i < game.getHandCardsCount(); i++) {
            System.out.println("(" + i + ")" + ": " + card[i].toString());
        }
        System.out.println("Wählen Sie aus: ");
        System.out.print(">>> ");
        int choiceCard = sc.nextInt();

        System.out.println();
        System.out.println("=====================================");
        System.out.println("Wählen Sie ein Platz, wo Sie ihre Karte platzieren möchten: ");
        DeckStack deck = game.getDeck();

        for (int i = 0; i < deck.getDrawPiles().length; i++) {
            DrawPile deckDrawPile = deck.getDrawPiles()[i];
            if (deckDrawPile.getCards().length > 0) {
                Card deckCard = deckDrawPile.seeTopCard();
                System.out.println("(" + i + ")" + ": " + deckCard);
            } else {
                System.out.println("(" + i + ")" + " Leer");
            }
        }

        System.out.print(">>> ");
        int choiceDeck = sc.nextInt();

        game.play(game.getHandCard(choiceCard), choiceDeck);
    }

    /**
     * Das Spiel ist zu Ende und Sie bekommen den endgültigen Punktestand
     * mitgeteilt.
     *
     * @param credits der endgültige Punktestand.
     */
    public void end(double credits) {
    }

    /**
     * Wird mit einem neuen Spiel aufgerufen und spielt dann bis zum Ende.
     *
     * @param game das neue Spiel.
     */
    public void run(Game game) {
        while (true) {
            int shopItemIndex;
            do {
                while (!game.isShopEmpty() && (shopItemIndex = shop(game)) >= 0
                        && shopItemIndex < game.getShopItemCount()) {
                    game.buy(shopItemIndex);
                }
            } while (game.isDrawPileEmpty()); // Mindestens eine Karte ist notwendig
            game.endShopping();

            while (game.getMode() == Mode.PLAYING) {
                playCard(game);
            }
            if (game.getMode() == Mode.END) {
                end(game.getCredits());
                return;
            }
        }
    }

}
