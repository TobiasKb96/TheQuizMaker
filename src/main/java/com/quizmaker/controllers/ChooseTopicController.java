package com.quizmaker.controllers;

import com.quizmaker.dataManagment.DataSingelton;
import com.quizmaker.dataManagment.JsonRWC;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/quizmaker/editScreen.fxml"));
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
    }

    public void deleteTopic(ActionEvent actionEvent) {
    }

    public void returnToMainMenu(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/quizmaker/menuScreen.fxml"));
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
    }

    public void startGame(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/quizmaker/gameScreen.fxml"));
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
    }

    private void setupOverviewMode(String overviewMode){
        if(overviewMode.equals("Play")){
            createTopicButton.setVisible(false);
            editTopicButton.setVisible(false);
            deleteTopicButton.setVisible(false);
        }
        else if (overviewMode.equals("Edit")){
            playButton.setVisible(false);
        }
    }
}
