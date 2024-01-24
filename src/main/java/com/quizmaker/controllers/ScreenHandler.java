package com.quizmaker.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenHandler {

    //Method for switching between Screens
    public void switchScreen(ActionEvent actionEvent, String fxmlFileName){
        //Initializes and FXMLLoader instance, specifying the location
        //(the directory) and name parameter
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/quizmaker/" + fxmlFileName + ".fxml"));
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


//TODO :
// write switch Scene funktion e.g.:
// public static void switchScene(String fxmlFile){ ... }
// Replace Scene switching methodes in controller classes

    // old code to switch screens
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
