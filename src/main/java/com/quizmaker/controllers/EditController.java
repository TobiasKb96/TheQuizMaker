package com.quizmaker.controllers;

import com.quizmaker.Question;
import com.quizmaker.Topic;
import com.quizmaker.dataManagment.DataSingelton;
import com.quizmaker.dataManagment.JsonRWC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EditController implements Initializable {

    @FXML
    private ToggleButton correctToggle1, correctToggle2, correctToggle3, correctToggle4;
    @FXML
    private TextField inputA1, inputA2, inputA3, inputA4, inputQuestion, inputTopicName;

    @FXML
    private ToggleGroup correctToggleGroup;
    private Topic topic;

    private int currentIndex = 0;

    DataSingelton data = DataSingelton.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        topic = JsonRWC.fromFile(data.getData());

        loadCurrentQuestion();
    }

    public void showNextQuestion(ActionEvent actionEvent) {
        saveQuestion();
        currentIndex++;
        loadCurrentQuestion();
    }

    public void showPreviousQuestion(ActionEvent actionEvent) {
        saveQuestion();
        if (currentIndex > 0) {
            currentIndex--;
            loadCurrentQuestion();
        }
    }

    public void saveTopic(ActionEvent actionEvent) {
        saveQuestion();
    }

    public void correctAnswerInput(ActionEvent actionEvent) {

    }

    public void saveQuestion() {
        String question = inputQuestion.getText().substring(inputQuestion.getText().lastIndexOf(": ")+2);
        String a1 = inputA1.getText().substring(inputA1.getText().lastIndexOf(": ")+2);
        String a2 = inputA2.getText().substring(inputA2.getText().lastIndexOf(": ")+2);
        String a3 = inputA3.getText().substring(inputA3.getText().lastIndexOf(": ")+2);
        String a4 = inputA4.getText().substring(inputA4.getText().lastIndexOf(": ")+2);
        String correctAnswerString = ((ToggleButton) correctToggleGroup.getSelectedToggle()).getId().replaceAll("\\D", "");
        int correctAnswer = Integer.parseInt(correctAnswerString);
        Question questionToSave = new Question(question, a1, a2, a3, a4,correctAnswer);
        topic.setQuestion(currentIndex, questionToSave);
    }

    public void loadCurrentQuestion() {
        correctToggleGroup.getToggles().get(topic.getQuestions().get(currentIndex).getCorrectAnswer()-1).setSelected(true);
        inputA1.setText("A1: " + topic.getQuestions().get(currentIndex).getA1());
        inputA2.setText("A2: " + topic.getQuestions().get(currentIndex).getA2());
        inputA3.setText("A3: " + topic.getQuestions().get(currentIndex).getA3());
        inputA4.setText("A4: " + topic.getQuestions().get(currentIndex).getA4());
        inputQuestion.setText("Question: " + topic.getQuestions().get(currentIndex).getQuestion());
        inputTopicName.setText("Topic name: " + topic.getName());
    }




}
