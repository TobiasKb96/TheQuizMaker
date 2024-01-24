package com.quizmaker.quizmaker.controller;

import com.quizmaker.quizmaker.model.Topic;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class NewCategoryController {
    @FXML
    public Button backBtn;
    @FXML
    public Button saveBtn;
    @FXML
    public TextField textField1;
    @FXML
    public TextField textField2;
    @FXML
    public TextField textField3;
    @FXML
    public TextField textField4;

    @FXML
    void backToMenu() throws IOException {
        URL url = Paths.get("src/main/resources/com/quizmaker/quizmaker/start-view.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        Scene newGameScene = new Scene(root);
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.setScene(newGameScene);
    }

    @FXML
    void saveCategory() throws IOException{

        Topic topic = new Topic();
        topic.newTopicScreen(textField1.getText(), textField2, textField3, textField4);
        System.out.println(topic.toString());
        textField1.clear();
        textField2.clear();
        textField3.clear();
        textField4.clear();
    }

}
