package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScoreboardMain extends Application {

    @Override
    public void start(Stage scoreboardStage) throws Exception {
        try {
            FXMLLoader l = new FXMLLoader(getClass().getResource("ScoreboardFXMLView.fxml"));
            Parent root = l.load();

            Scene scene = new Scene(root);
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            scoreboardStage.setScene(scene);
            scoreboardStage.setTitle("Card Game - Scoreboard");
            scoreboardStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
