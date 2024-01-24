package com.quizmaker.controllers;

import com.quizmaker.dataManagment.DataSingelton;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.Button;

public class MenuController {

    @FXML
    private Button playButton, topicButton;
    DataSingelton data = DataSingelton.getInstance();

    //If the Selected Data matches "Play", the switchToChooseTopic method is prompted
    //Which directs to the chooseTopicScreen FXML File
    public void startNewGame(ActionEvent actionEvent) {

        data.setData("Play");
        switchToChooseTopic(actionEvent);
    }

    //If the Selected Data matches "Edit", the switchToChooseTopic method is prompted
    //Which directs to the chooseTopicScreen FXML File
    public void switchToEditTopics(ActionEvent actionEvent) {
        data.setData("Edit");
        switchToChooseTopic(actionEvent);
    }
    //Creates a new instance of ScreenHandler
    //Switches to the chooseTopicScreen FXML File
    public void switchToChooseTopic(ActionEvent actionEvent){
        ScreenHandler screenHandler = new ScreenHandler();
        screenHandler.switchScreen(actionEvent, "chooseTopicScreen");
    }
}

