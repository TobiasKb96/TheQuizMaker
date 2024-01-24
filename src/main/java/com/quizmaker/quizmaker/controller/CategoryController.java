package com.quizmaker.quizmaker.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class CategoryController {
    @FXML
    private Button doneBtn;
    @FXML
    public Button backBtn;

    @FXML
    void backToMenu() throws IOException {
        URL url = Paths.get("src/main/resources/com/quizmaker/quizmaker/start-view.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        Scene newGameScene = new Scene(root);
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.setScene(newGameScene);
    }

    @FXML
    void changeToGameFrame() throws IOException{
        URL url = Paths.get("src/main/resources/com/quizmaker/quizmaker/game-view.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        Scene newGameScene = new Scene(root);
        Stage stage = (Stage) doneBtn.getScene().getWindow();
        stage.setScene(newGameScene);
    }
}
