package com.quizmaker.quizmaker.controller;

import com.quizmaker.quizmaker.JsonRWC;
import com.quizmaker.quizmaker.model.HighScore;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

public class GameOverWinController {

    List<HighScore> existingScores = JsonRWC.readScores("scores.json");
    private String score = existingScores.get(existingScores.size() - 1).toString();;
    @FXML
    public Label scoreLbl;
    @FXML
    public TextField playerNameText;
    @FXML
    public Button saveBtn;
    public void showScore(){
        scoreLbl.setText(score);
    }
    @FXML
    public void saveHighScore() throws IOException {
        String playerName = playerNameText.getText();
        JsonRWC.saveHighScore(playerName, score);
        URL url = Paths.get("src/main/resources/com/quizmaker/quizmaker/highscores-view.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        Scene newGameScene = new Scene(root);
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.setScene(newGameScene);
    }

    @FXML
    public void initialize(){
        showScore();
    }
}
