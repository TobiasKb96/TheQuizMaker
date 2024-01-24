package com.quizmaker.quizmaker.controller;

import com.quizmaker.quizmaker.JsonRWC;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class StartGameController {

    @FXML
    public Button newGameBtn;
    @FXML
    public Button highScoresBtn;
    @FXML
    public Button optionsBtn;
    @FXML
    public Button newCategoryBtn;

    @FXML
    public Button exitBtn;
    @FXML
    void newGame() throws IOException{
        URL url = Paths.get("src/main/resources/com/quizmaker/quizmaker/newgame-view.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        Scene newGameScene = new Scene(root);
        Stage stage = (Stage) newGameBtn.getScene().getWindow();
        stage.setScene(newGameScene);
    }

    @FXML
    void highScores() throws IOException{
        URL url = Paths.get("src/main/resources/com/quizmaker/quizmaker/highscores-view.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        Scene newGameScene = new Scene(root);
        Stage stage = (Stage) newGameBtn.getScene().getWindow();
        stage.setScene(newGameScene);
    }

    @FXML
    void options() throws IOException{
        URL url = Paths.get("src/main/resources/com/quizmaker/quizmaker/options-view.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        Scene newGameScene = new Scene(root);
        Stage stage = (Stage) newGameBtn.getScene().getWindow();
        stage.setScene(newGameScene);
    }

    @FXML
    void newCategory() throws IOException{
        URL url = Paths.get("src/main/resources/com/quizmaker/quizmaker/newcategory-view.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        Scene newGameScene = new Scene(root);
        Stage stage = (Stage) newGameBtn.getScene().getWindow();
        stage.setScene(newGameScene);
    }

    @FXML
    void closeApplication(){
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
        JsonRWC.clearScoresFile();
    }
}
