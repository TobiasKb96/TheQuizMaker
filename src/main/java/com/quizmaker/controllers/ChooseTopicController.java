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

    //Method for Initializing the controller
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setupOverviewMode(data.getData());

        //Source https://www.youtube.com/watch?v=Pqfd4hoi5cc
        //Adds all Json File Names to the topic List
        topicList.getItems().addAll(JsonRWC.getFileNames());

        //Adds a change listener to the selected item property of topicList.
        // It listens for changes in the selected item and takes action accordingly.
        topicList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            //changed method gets triggered when selected items in topiclist changes
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                selectedTopic = topicList.getSelectionModel().getSelectedItem();
                data.setData(selectedTopic);
            }
        });
    }

    //toEditScreen method creates a new instance of ScreenHandler and calls
    //the switchScreen method using the id of the Java fx file
    public void toEditScreen(ActionEvent actionEvent) {
        ScreenHandler screenHandler = new ScreenHandler();
        screenHandler.switchScreen(actionEvent, "editScreen");
    }
    //The deleteTopic method deleted a topic by using the deleteFile method
    //with selectedTopic as parameter
    public void deleteTopic(ActionEvent actionEvent) {
        JsonRWC.deleteFile(selectedTopic);
        //Deleting from the UI: First it gets the topic with Getter Method
        //Removes the currently selected item from the list. The selected item
        // is obtained using topicList.getSelectionModel().getSelectedItem
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
    //The setupOverviewMethod adjusts the UI based on pressed button
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
