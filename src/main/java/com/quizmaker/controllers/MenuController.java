package com.quizmaker.controllers;

import com.quizmaker.dataManagment.DataSingelton;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;


import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button playButton, topicButton;
    DataSingelton data = DataSingelton.getInstance();

    public void startNewGame(ActionEvent actionEvent) {

        data.setData("Play");
        switchToChooseTopic(actionEvent);
    }


    public void switchToEditTopics(ActionEvent actionEvent) {
        data.setData("Edit");
        switchToChooseTopic(actionEvent);
    }

    public void switchToChooseTopic(ActionEvent actionEvent){
        Screenhandler screenhandler = new Screenhandler();
        screenhandler.switchScreen(actionEvent, "chooseTopicScreen");
        /*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/quizmaker/chooseTopicScreen.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(parent);

        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);

         */
    }
}

