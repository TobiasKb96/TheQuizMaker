package com.quizmaker.controllers;

import com.quizmaker.dataManagment.DataSingelton;
import com.quizmaker.dataManagment.JsonRWC;
import com.quizmaker.Question;
import com.quizmaker.Topic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class GameController implements Initializable {

    @FXML
    private Button A1, A2, A3, A4, question, returnButton, halfHalfJoker, passJoker;

    @FXML
    private Label infoLabel;

    @FXML
    private AnchorPane finishedScreen, gameBoard, allLifes;

    private List<Question> questions = new ArrayList<>();
    private int currentIndex = 0;

    private int score = 0;

    DataSingelton data = DataSingelton.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Topic gameTopic = JsonRWC.fromFile(data.getData());
        questions = gameTopic.getQuestions();
        Collections.shuffle(questions);
        loadQuestion();
    }

    public void removeHalfAnswers(ActionEvent actionEvent) {
    }

    public void passQuestion(ActionEvent actionEvent) {
    }

    public void clickAnswer(ActionEvent actionEvent) {
        String buttonID = ((Button) actionEvent.getSource()).getId();
        System.out.println(buttonID);
        checkAnswer(buttonID);
    }

    private void loadQuestion() {
        question.setText(questions.get(currentIndex).getQuestion());
        A1.setText(questions.get(currentIndex).getA1());
        A2.setText(questions.get(currentIndex).getA2());
        A3.setText(questions.get(currentIndex).getA3());
        A4.setText(questions.get(currentIndex).getA4());
    }

    private void checkAnswer(String buttonID) {
        if (buttonID.contains(String.valueOf(questions.get(currentIndex).getCorrectAnswer()))) {
            System.out.println("correct");
            score++;
            currentIndex++;
            //TODO: change correct animation
            if (currentIndex > questions.size() - 1) {
                infoLabel.setText("You Won!");
                gameBoard.setVisible(false);
                finishedScreen.setVisible(true);

            } else loadQuestion();
        } else {
            System.out.println("False");
            infoLabel.setText("You Lost!");
            gameBoard.setVisible(false);
            finishedScreen.setVisible(true);
        }
    }

    public void returnToFrontMenu(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/quizmaker/menuScreen.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // Create a new scene with the SecondScreen.fxml content
        Scene scene = new Scene(parent);

        // Get the current stage and set the new scene
        Stage stage = (Stage) A1.getScene().getWindow();
        stage.setScene(scene);
    }
}
        /*

        @FXML
        private Button A1;

        @FXML
        private Button A2;

        @FXML
        private Button A3;

        @FXML
        private Button A4;

        // Initialize a list to store the answer buttons
        private List<Button> answerButtons;

        @FXML
        private void initialize() {
            // Initialize the answerButtons list
            answerButtons = new ArrayList<>(List.of(A1, A2, A3, A4));
        }

        @FXML
        private void handleAnswerClick(ActionEvent event) {
            // Handle the click event for the answer buttons
            Button clickedButton = (Button) event.getSource();

            // Change the color of the clicked button to blue
            clickedButton.setStyle("-fx-background-color: blue;");
        }

        @FXML
        private void removeHalfAnswers(ActionEvent event) {
            // Randomly select two buttons to be greyed out
            Collections.shuffle(answerButtons, new Random());

            // Grey out the first two buttons in the shuffled list
            for (int i = 0; i < 2; i++) {
                answerButtons.get(i).setDisable(true);
                answerButtons.get(i).setStyle("-fx-background-color: grey;");
            }
        }

        @FXML
        private void handlePassQuestion(ActionEvent event) {
            // Logic for passing the question
            System.out.println("Question passed");
            // Add your code for handling the question pass event
        }
         */
