package com.quizmaker.quizmaker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ScreenController implements Initializable {

    private GameLogic game;

    @FXML
    private Button a1, a2, a3, a4, question;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        question.setStyle("-fx-background-color: yellow");
    }

    public void onA3Click(ActionEvent actionEvent) {
        a3.setText("falsch");
        a3.setStyle("-fx-background-color: yellow");
        game.zahl += 2;
        System.out.println(game.zahl);

    }

    public void onA4Click(ActionEvent actionEvent) {
    }

    public void onA2Click(ActionEvent actionEvent) {
    }

    public void onA1Click(ActionEvent actionEvent) {
    }
}