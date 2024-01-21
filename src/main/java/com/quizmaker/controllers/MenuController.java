package com.quizmaker.controllers;

import com.quizmaker.dataManagment.DataSingelton;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Button;

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
        ScreenHandler screenHandler = new ScreenHandler();
        screenHandler.switchScreen(actionEvent, "chooseTopicScreen");


    }
}

