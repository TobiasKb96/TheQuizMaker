package com.quizmaker.controllers;

import com.quizmaker.dataManagment.DataSingelton;
import com.quizmaker.dataManagment.JsonRWC;
import com.quizmaker.Question;
import com.quizmaker.Topic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class GameController implements Initializable {

    @FXML
    public Circle life1, life2, life3;
    @FXML
    private Button A1, A2, A3, A4, returnButton, halfHalfJoker, passJoker;
    @FXML
    private Label question, scoreLabel, infoLabel;

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
            // Identify the incorrect answers
            List<Integer> incorrectAnswers = new ArrayList<>();

            // Loop through the possible answer choices (1 to 4).
            // Exclude the correct answer from the list of incorrect answers.
            for (int i = 1; i <= 4; i++) {
                if (i != questions.get(currentIndex).getCorrectAnswer()) {
                    incorrectAnswers.add(i);
                }
            }

            // Shuffle the list of incorrect answers to randomize
            // Select two incorrect answers to grey out
            Collections.shuffle(incorrectAnswers);
            incorrectAnswers = incorrectAnswers.subList(0, 2);

            // Display the results to the user
            System.out.println("50/50 Joker used: Two incorrect answers are now greyed out.");

            // Display each answer choice (1 to 4):
            // If the answer choice is in the list of incorrect answers, mark it as greyed out.
            // Otherwise, display the correct and remaining incorrect answers normally.
            for (int i = 1; i <= 4; i++) {
                if (incorrectAnswers.contains(i)) {
                    // Grey out the incorrect answers
                    disableAnswer(i);
                    }
            }
            // Mark the 50/50 Joker as used
            halfHalfJoker.setDisable(true);
        }


    public void passQuestion(ActionEvent actionEvent) {
        currentIndex ++;
        loadQuestion();
        passJoker.setDisable(true);
    }

    public void disableAnswer(int answerIndex) {
        switch (answerIndex) {
            case 1:
                A1.setDisable(true);
                break;
            case 2:
                A2.setDisable(true);
                break;
            case 3:
                A3.setDisable(true);
                break;
            case 4:
                A4.setDisable(true);
                break;
            default:
                // Handle invalid answer index
                break;
        }
    }

    public void greenAnswer(int answerIndex) {
        switch (answerIndex) {
            case 1:
                A1.setStyle("-fx-background-color: #8bf66d");
                break;
            case 2:
                A2.setStyle("-fx-background-color: #8bf66d");
                break;
            case 3:
                A3.setStyle("-fx-background-color: #8bf66d");
                break;
            case 4:
                A4.setStyle("-fx-background-color: #8bf66d");
                break;
            default:
                break;
        }
    }

    public void clickAnswer(ActionEvent actionEvent) {
        Button clickedButton = ((Button) actionEvent.getSource());
        checkAnswer(clickedButton);
    }

    private void loadQuestion() {

        A1.setDisable(false);
        A2.setDisable(false);
        A3.setDisable(false);
        A4.setDisable(false);

        question.setText(questions.get(currentIndex).getQuestion());

        A1.setText(questions.get(currentIndex).getA1());
        A2.setText(questions.get(currentIndex).getA2());
        A3.setText(questions.get(currentIndex).getA3());
        A4.setText(questions.get(currentIndex).getA4());

        //
    }

    private void checkAnswer(Button button) {
        //
        if (button.getId().contains(String.valueOf(questions.get(currentIndex).getCorrectAnswer()))) {
            score += currentIndex+1;
            scoreLabel.setText("Score: " + score);
            currentIndex++;
            //TODO: change correct animation
            if (currentIndex > questions.size() - 1) {
                infoLabel.setText("You Won!");
                gameBoard.setVisible(false);
                finishedScreen.setVisible(true);

            } else loadQuestion();
        } else {
            button.setStyle("-fx-background-color: #f66d6d");
            greenAnswer(questions.get(currentIndex).getCorrectAnswer());
            System.out.println("False");
            infoLabel.setText("You Lost!");
            gameBoard.setDisable(true);
            finishedScreen.setVisible(true);
        }
    }

    public void returnToFrontMenu(ActionEvent actionEvent) {
        ScreenHandler screenhandler = new ScreenHandler();
        screenhandler.switchScreen(actionEvent, "menuScreen");
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
