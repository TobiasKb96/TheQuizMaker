package com.quizmaker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.Override;
import java.lang.String;

//Class MainApp extends JavaFX Application class
public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader is used to load menuScreen.fxml
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("menuScreen.fxml"));
        //Parent object represents the root node of the scene graph
        Parent root = fxmlLoader.load();
        //A Scene is created with the specified root node and dimensions
        Scene scene = new Scene(root, 600,400);
        //The Stage is configured with the created Scene and set to non-resizable
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args) {

        //Initializes the javaFX runtime and starts the JavaFX application
        launch();

    }
}