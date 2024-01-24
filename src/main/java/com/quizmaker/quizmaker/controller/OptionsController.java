package com.quizmaker.quizmaker.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class OptionsController {
    @FXML
    public Button audioBtn;
    @FXML
    public Button controlBtn;
    @FXML
    public Button resetBtn;
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
    void audioControl(){
        openPopup();
    }
    @FXML
    void showControl(){
        openPopup();
    }
    @FXML
    void resetGame(){
        openPopup();
    }
    private void openPopup() {
        try {
            URL url = Paths.get("src/main/resources/com/quizmaker/quizmaker/audio-view.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root1 = (Parent) loader.load();
            Stage newStage = new Stage();
            newStage.setTitle("Audio Control");
            newStage.setScene(new Scene(root1));
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
