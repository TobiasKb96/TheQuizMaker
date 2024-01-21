package com.quizmaker.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;


import javafx.stage.Stage;

import java.io.IOException;

public class FrontMenuController {


    public Button playButton;
    public Button topicButton;

    public void startNewGame(ActionEvent actionEvent) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/quizmaker/gameScreen.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // Create a new scene with the SecondScreen.fxml content
        Scene scene = new Scene(parent);

        // Get the current stage and set the new scene
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }


    public void switchToEditTopics(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/quizmaker/topicOverview.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // Create a new scene with the SecondScreen.fxml content
        Scene scene = new Scene(parent);

        // Get the current stage and set the new scene
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
    /*

    @FXML
    private Button newGameButton;

    @FXML
    private Button editTopicsButton;

    @FXML
    private void handleNewGame(ActionEvent event) {
        System.out.println("New Game button clicked");
        // Add your code to start a new game here
    }

    @FXML
    private void handleEditTopics(ActionEvent event) {
        System.out.println("Edit Topics button clicked");
        // Add your code to open the topics editing screen here
    }
}
*/
/*
public class FrontMenuController {

    private GameLogic game;

    @FXML
    private Button newGame, topics, exit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        question.setStyle("-fx-background-color: yellow");
    }

    public void onNewGameClick(ActionEvent actionEvent) {
        newGame.setText("falsch");
        newGame.setStyle("-fx-background-color: yellow");
        game.zahl += 2;
        System.out.println(game.zahl);

    }
*/