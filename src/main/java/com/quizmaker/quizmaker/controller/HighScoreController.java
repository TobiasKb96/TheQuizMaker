package com.quizmaker.quizmaker.controller;

import com.google.gson.Gson;
import com.quizmaker.quizmaker.model.HighScore;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Paths;

public class HighScoreController {
    @FXML
    public TableView<HighScore> tableView;
    @FXML
    public TableColumn<HighScore, String> playerNameColumn;

    @FXML
    public TableColumn<HighScore, String> scoreColumn;

    @FXML
    public Button backBtn;

    @FXML
    void backToMenu() throws IOException {
        URL url = Paths.get("src/main/resources/com/quizmaker/quizmaker/start-view.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        Scene newGameScene = new Scene(root);
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.setScene(newGameScene);
    }

    @FXML
    public void initialize() {
        playerNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        scoreColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getScore()));

        Gson gson = new Gson();
        try (Reader reader = new FileReader("HighScores.json")) {
            HighScore[] scoreList = gson.fromJson(reader, HighScore[].class);
            if (scoreList == null) {
                scoreList = new HighScore[0];
            }
            ObservableList<HighScore> observableList = FXCollections.observableArrayList(scoreList);
            tableView.setItems(observableList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
