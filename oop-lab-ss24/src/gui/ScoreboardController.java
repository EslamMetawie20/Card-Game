package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import cardmaster.ScoreBoard;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ScoreboardController {

    @FXML
    private ListView<String> scoreboardListView;

    @FXML
    private TextField fileSourceTf;

    @FXML
    private Button exitBtn;

    public void initialize() {
        for (int i = 0; i < ScoreBoard.getInstance().size(); i++) {
            String scoreEntry = String.format("%d. %f P - %s",
                    ScoreBoard.getInstance().getPlace(i),
                    (double)ScoreBoard.getInstance().getScore(i),
                    ScoreBoard.getInstance().getInstant(i).toString());
            scoreboardListView.getItems().add(scoreEntry);
        }
    }

    @FXML
    public void handleSaveBtn() {
        String fileName = fileSourceTf.getText();
        try {
            ScoreBoard.getInstance().save(new FileOutputStream(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleLoadBtn() {
        String filePath = fileSourceTf.getText();

        try {
            ScoreBoard.getInstance().load(new FileInputStream(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < ScoreBoard.getInstance().size(); i++) {
            String scoreEntry = String.format("%d. %f P - %s",
                    ScoreBoard.getInstance().getPlace(i),
                    (double)ScoreBoard.getInstance().getScore(i),
                    ScoreBoard.getInstance().getInstant(i).toString());
            scoreboardListView.getItems().add(scoreEntry);
        }
    }

    @FXML
    public void handleExitBtn() {
        // Abrufen der Stage von der Schaltfläche
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        // Schließen der Stage
        stage.close();
    }
}
