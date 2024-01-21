package com.quizmaker.controllers;

import com.quizmaker.dataManagment.DataSingelton;
import com.quizmaker.dataManagment.JsonRWC;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ChooseTopicController implements Initializable {


    @FXML
    private Button playButton, createTopicButton, deleteTopicButton, editTopicButton;

    @FXML
    private TextField topicOverviewLabel;

    @FXML
    private ListView<String> topicList;

    private String selectedTopic;

    DataSingelton data =  DataSingelton.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setupOverviewMode(data.getData());
        //Source https://www.youtube.com/watch?v=Pqfd4hoi5cc
        topicList.getItems().addAll(JsonRWC.getFileNames());
        topicList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                selectedTopic = topicList.getSelectionModel().getSelectedItem();
                data.setData(selectedTopic);
            }
        });
    }

    public void toEditScreen(ActionEvent actionEvent) {
        ScreenHandler screenHandler = new ScreenHandler();
        screenHandler.switchScreen(actionEvent, "editScreen");
    }

    public void deleteTopic(ActionEvent actionEvent) {
        JsonRWC.deleteFile(selectedTopic);
        topicList.getItems().remove(topicList.getSelectionModel().getSelectedItem());
    }

    public void returnToMainMenu(ActionEvent actionEvent) {
        ScreenHandler screenHandler = new ScreenHandler();
        screenHandler.switchScreen(actionEvent, "menuScreen");
}

    public void startGame(ActionEvent actionEvent) {
        ScreenHandler screenHandler = new ScreenHandler();
        screenHandler.switchScreen(actionEvent, "gameScreen");
    }

    private void setupOverviewMode(String overviewMode){
        if(overviewMode.equals("Play")){
            topicOverviewLabel.setText("Choose a topic to play!");
            createTopicButton.setVisible(false);
            editTopicButton.setVisible(false);
            deleteTopicButton.setVisible(false);
        }
        else if (overviewMode.equals("Edit")){
            topicOverviewLabel.setText("Modify or create a new topic!");
            playButton.setVisible(false);
        }
    }

    public void toCreateScreen(ActionEvent actionEvent) {
        data.setData("NewTopic");
        ScreenHandler screenHandler = new ScreenHandler();
        screenHandler.switchScreen(actionEvent, "editScreen");
    }
}
