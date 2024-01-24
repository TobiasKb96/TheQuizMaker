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
import javafx.scene.paint.Color;
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

        //Reads a topic Object from the File
        Topic gameTopic = JsonRWC.fromFile(data.getData());

        //Retrieves the list of Questions from gameTopic
        questions = gameTopic.getQuestions();

        //Shuffles them
        Collections.shuffle(questions);

        //Calls the loadQuestion method
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
                greyOutAnswer(i);
            }
        }
        // Mark the 50/50 Joker as used
        halfHalfJoker.setDisable(true);
    }


    //Method for Pass Question Joker
    public void passQuestion(ActionEvent actionEvent) {
        //Increases the current Question Index by 1
        currentIndex++;

        //Calls the loadQuestion method
        loadQuestion();

        //Disables the Joker button
        passJoker.setDisable(true);
    }

    //greyOutAnswer method helpful for the 50/50 jokers
    //if called on the incorrect answers, the button gets disabled
    public void greyOutAnswer(int answerIndex) {
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

    public void clickAnswer(ActionEvent actionEvent) {
        //Returns the button that was clicked and the id of it
        String buttonID = ((Button) actionEvent.getSource()).getId();

        //Prints the ID of the clicked button to the console.
        System.out.println(buttonID);

        //Using the checkAnswer method, evaluates if answer is correct
        checkAnswer(buttonID);
    }

    //Updates the UI with info related to the current Question
    private void loadQuestion() {
        //Enables all Answer buttons when new Question gets loaded to avoid bugs from Jokers
        A1.setDisable(false);
        A2.setDisable(false);
        A3.setDisable(false);
        A4.setDisable(false);

        //Updates the UI Question Element with the text of the current Q
        question.setText(questions.get(currentIndex).getQuestion());
        //Sets the correct Answers Text
        A1.setText(questions.get(currentIndex).getA1());
        A2.setText(questions.get(currentIndex).getA2());
        A3.setText(questions.get(currentIndex).getA3());
        A4.setText(questions.get(currentIndex).getA4());
    }

    //Method for evaluating whether Answer is correct or not
    private void checkAnswer(String buttonID) {
        //Checks whether the ID of the clicked button contains the
        // string of the correct answer index
        // If true, it increases the score and currentIndex by 1
        if (buttonID.contains(String.valueOf(questions.get(currentIndex).getCorrectAnswer()))) {
            System.out.println("correct");
            score++;
            currentIndex++;
            //TODO: change correct animation

            if (currentIndex > questions.size() - 1) {
                //If all Questions have been answered, prints Winning message
                infoLabel.setText("You Won!");
                //The gameBoard is disabled
                gameBoard.setVisible(false);
                //And the finishedScreen is enabled
                finishedScreen.setVisible(true);

                //If not all Q have been answered, calls the loadQuestion method
            } else loadQuestion();

            //If the ID of the clicked button is not correct
        } else {
            System.out.println("False");
            //Print Losing Message
            infoLabel.setText("You Lost!");
            gameBoard.setVisible(false);
            finishedScreen.setVisible(true);
        }
    }

    public void returnToFrontMenu(ActionEvent actionEvent) {
        //A new Screenhandler instance gets created
        ScreenHandler screenhandler = new ScreenHandler();

        //That directs to the menuScreen fxml file
        screenhandler.switchScreen(actionEvent, "menuScreen");
    }
}