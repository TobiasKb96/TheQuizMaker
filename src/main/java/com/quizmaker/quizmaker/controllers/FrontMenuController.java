package com.quizmaker.quizmaker.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FrontMenuController {


    public Button playButton;
    public Button topicButton;

    public void startNewGame(ActionEvent actionEvent) {
    }

    public void switchToEditTopics(ActionEvent actionEvent) {
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