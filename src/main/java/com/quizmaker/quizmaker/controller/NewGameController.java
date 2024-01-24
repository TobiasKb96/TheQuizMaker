package com.quizmaker.quizmaker.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class NewGameController {
    private int numberOfRounds;

    @FXML
    public CheckBox fiveEctsCheckBox;

    @FXML
    public CheckBox tenEctsCheckBox;

    @FXML
    public CheckBox fifteenEctsCheckBox;

    @FXML
    public CheckBox othersCheckBox;

    @FXML
    void fiveEcts() throws IOException {
        URL url = Paths.get("src/main/resources/com/quizmaker/quizmaker/category-view.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        Scene newGameScene = new Scene(root);
        Stage stage = (Stage) fiveEctsCheckBox.getScene().getWindow();
        stage.setScene(newGameScene);
    }

    @FXML
    void tenEcts() throws IOException{
        URL url = Paths.get("src/main/resources/com/quizmaker/quizmaker/category-view.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        Scene newGameScene = new Scene(root);
        Stage stage = (Stage) fiveEctsCheckBox.getScene().getWindow();
        stage.setScene(newGameScene);
    }

    @FXML
    void fifteenEcts() throws IOException{
        URL url = Paths.get("src/main/resources/com/quizmaker/quizmaker/category-view.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        Scene newGameScene = new Scene(root);
        Stage stage = (Stage) fiveEctsCheckBox.getScene().getWindow();
        stage.setScene(newGameScene);
    }

    @FXML
    void others() throws IOException{
        URL url = Paths.get("src/main/resources/com/quizmaker/quizmaker/category-view.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        Scene newGameScene = new Scene(root);
        Stage stage = (Stage) fiveEctsCheckBox.getScene().getWindow();
        stage.setScene(newGameScene);
    }
}
