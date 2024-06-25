package gui;

import cardmaster.CardFactory;
import cardmaster.Game;
import cardmaster.ScoreBoard;
import cardmaster.Game.Mode;
import cardmaster.Item;
import cardmaster.cards.Card;
import cardmaster.cards.KombiCard;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GameController {

    private Game game;

    @FXML
    private Button buyBtn;

    @FXML
    private Button playCardBtn;

    @FXML
    private Button startBtn;

    @FXML
    private Button endShoppingBtn;

    @FXML
    private TextField roundTf;

    @FXML
    private TextField ownedCredits;

    @FXML
    private TextField drawpileCounter;

    @FXML
    private ListView<String> shopList;

    @FXML
    private ListView<String> deckStacks;

    @FXML
    private ListView<String> handList;

    @FXML
    public TextField currentModeTf;

    @FXML
    public void handleBuyBtn() {
        int index = shopList.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            this.game.buy(index);

            this.game.getCredits();
            this.updateshoplist();
            this.updatecredits();
            this.updateCounterOfDrawpile();

        }
    }

    @FXML
    public void handleStartBtn() {

        this.game = new Game(Integer.parseInt(roundTf.getText()), CardFactory.getDefaultFactory());

        this.updatemode();
        this.handList.setDisable(true);
        this.playCardBtn.setDisable(true);
        this.deckStacks.setDisable(true);

        this.shopList.setDisable(false);

        roundTf.setText(String.valueOf(game.getCurrentRound()));
        roundTf.setDisable(true);
        startBtn.setDisable(true);
        currentModeTf.setDisable(true);
        ownedCredits.setDisable(true);
        drawpileCounter.setDisable(true);
        startBtn.setDefaultButton(true);
        buyBtn.setDisable(false);
        endShoppingBtn.setDisable(false);
        updateshoplist();
        updatecredits();

        drawpileCounter.setText(String.valueOf(game.getDrawPile().countStack()));
        if (game.getMode() == Game.Mode.END) {
            startBtn.setDisable(false);
        }
    }

    @FXML
    public void deckstacks() {
        // to know every top card in the DecckStack
        for (int i = 0; i < game.getStacksCount(); i++) {
            this.deckStacks.getItems().add(game.getDeck().getDrawPiles()[i].seeTopCard().toString());
        }
    }

    @FXML
    public void handleEndShoppingBtn() {
        game.endShopping();
        this.updatemode();
        this.updateDeck();
        this.updateHand();
        this.updateCounterOfDrawpile();

        if (game.getMode() != Mode.SHOPPING) {
            buyBtn.setDisable(true);
            playCardBtn.setDisable(false);
            endShoppingBtn.setDisable(true);

            this.handList.setDisable(false);
            this.playCardBtn.setDisable(false);
            this.deckStacks.setDisable(false);

            this.shopList.setDisable(true);
        }

    }

    @FXML
    public void handlePlayCardBtn() {
        int indexHand = this.handList.getSelectionModel().getSelectedIndex();
        int indexDeck = this.deckStacks.getSelectionModel().getSelectedIndex();

        if (indexHand != -1 && indexDeck != -1) {
            Card card = game.getHandCard(indexHand);
            game.play(card, indexDeck);
            this.updatemode();
            this.updateDeck();
            this.updateCounterOfDrawpile();
        }
        if (game.getMode() == Mode.PLAYING) {
            this.updateHand();
        } else if (game.getMode() == Mode.SHOPPING) {
            this.handList.getItems().clear();
            if (game.getHand().getHand().length == 0) {
                this.endShoppingBtn.setDisable(false);
                this.buyBtn.setDisable(false);
                this.playCardBtn.setDisable(true);
                this.updateshoplist();
            }
        } else {
            this.endShoppingBtn.setDisable(true);
            this.buyBtn.setDisable(true);
            this.playCardBtn.setDisable(true);
            this.deckStacks.setDisable(true);
            this.handList.setDisable(true);
            this.shopList.setDisable(true);

            this.startBtn.setDisable(false);
            this.roundTf.setDisable(false);

            ScoreboardMain scoreboard = new ScoreboardMain();
            Stage scoreboardStage = new Stage();

            try {
                scoreboard.start(scoreboardStage);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void handleRoundTf() {

    }

    private void updateshoplist() {
        if (game.getMode() == Mode.SHOPPING) {
            shopList.getItems().clear();
            for (int i = 0; i < game.getShopItemCount(); i++) {
                Item card = game.getShop().getCards()[i];
                if (card instanceof KombiCard) {
                    card = (KombiCard) card;
                    this.shopList.getItems().add("KombiCard: " + card.toString() + "; Price: "
                            + game.getShop().getShopPrice()[i] + " Credits");
                } else {
                    this.shopList.getItems()
                            .add(card.toString() + "; Price: " + game.getShop().getShopPrice()[i] + " Credits");
                }
            }
        }
    }

    private void updateDeck() {
        this.deckStacks.getItems().clear();
        // this.deckstacks.getItems().add("Availble stacks " +
        // game.getDeck().getDrawPileDeckCounter());
        for (int i = 0; i < game.getDeck().getDrawPiles().length; i++) {
            if (!game.getDeck().getDrawPiles()[i].isEmpty()) {
                // this.deckstacks.getItems().add("Stack : " + (x + 1) + " " +
                // game.getDeck().getDrawPiles()[x]);
                this.deckStacks.getItems()
                        .add("Stack : " + (i + 1) + " " + game.getDeck().getDrawPiles()[i].seeTopCard().toString());
            } else {
                this.deckStacks.getItems().add("Stack : " + (i + 1) + " is empty");
            }
        }
    }

    private void updatecredits() {
        ownedCredits.setText(String.valueOf(game.getShop().getCredits()));
    }

    private void updatemode() {
        currentModeTf.clear();
        currentModeTf.setText(game.getMode().toString());
    }

    private void updateCounterOfDrawpile() {
        drawpileCounter.setText(String.valueOf(game.getDrawPile().countStack()));
    }

    public void updateHand() {
        handList.getItems().clear();
        for (int i = 0; i < this.game.getHandCardsCount(); i++) {
            this.handList.getItems().add(game.getHand().getHand()[i].toString());
        }
        this.updateCounterOfDrawpile();
    }

}
