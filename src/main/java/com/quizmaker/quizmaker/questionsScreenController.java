package com.quizmaker.quizmaker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

    public class questionsScreenController {

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
    }

}
