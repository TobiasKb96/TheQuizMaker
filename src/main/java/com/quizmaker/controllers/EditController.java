package com.quizmaker.controllers;

import com.quizmaker.Question;
import com.quizmaker.Topic;
import com.quizmaker.dataManagment.DataSingelton;
import com.quizmaker.dataManagment.JsonRWC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class EditController implements Initializable {

    @FXML
    private ToggleButton correctToggle1, correctToggle2, correctToggle3, correctToggle4;

    @FXML
    private TextField inputA1, inputA2, inputA3, inputA4, inputQuestion, inputTopicName;

    @FXML
    private ToggleGroup correctToggleGroup;

    @FXML
    private Label questionNumberLabel;

    private Topic topic;

    private int currentIndex = 0;

    DataSingelton data = DataSingelton.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(data.getData().equals("NewTopic")){
            topic = new Topic();
        }
        else topic = JsonRWC.fromFile(data.getData());

        // Listener to prevent unselecting
        correctToggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle == null) {
                // If the user tries to unselect, reselect the previous toggle
                correctToggleGroup.selectToggle(oldToggle);
            }
        });


        loadCurrentQuestion();
    }

    public void showNextQuestion(ActionEvent actionEvent) {
        saveTopicState();
        if(currentIndex < topic.questions.size()-1) currentIndex++;
        loadCurrentQuestion();
    }

    public void showPreviousQuestion(ActionEvent actionEvent) {
        saveTopicState();
        if (currentIndex > 0) {
            currentIndex--;
            loadCurrentQuestion();
        }
    }

    public void saveTopicToFile(ActionEvent actionEvent) {
        saveTopicState();
        JsonRWC.toFile(topic);
        ScreenHandler screenHandler = new ScreenHandler();
        data.setData("Edit");
        screenHandler.switchScreen(actionEvent, "chooseTopicScreen");
    }

    public void correctAnswerInput(ActionEvent actionEvent) {

    }

    public void saveTopicState() {
        String question = inputQuestion.getText();
        String a1 = inputA1.getText();
        String a2 = inputA2.getText();
        String a3 = inputA3.getText();
        String a4 = inputA4.getText();

        topic.setName(inputTopicName.getText());

        String correctAnswerString = ((ToggleButton) correctToggleGroup.getSelectedToggle()).getId().replaceAll("\\D", "");
        int correctAnswer = Integer.parseInt(correctAnswerString);

        Question questionToSave = new Question(question, a1, a2, a3, a4,correctAnswer);
        topic.setQuestion(currentIndex, questionToSave);
    }

    public void loadCurrentQuestion() {
        correctToggleGroup.getToggles().get(topic.getQuestions().get(currentIndex).getCorrectAnswer()-1).setSelected(true);
        inputA1.setText(topic.getQuestions().get(currentIndex).getA1());
        inputA2.setText(topic.getQuestions().get(currentIndex).getA2());
        inputA3.setText(topic.getQuestions().get(currentIndex).getA3());
        inputA4.setText(topic.getQuestions().get(currentIndex).getA4());
        inputQuestion.setText(topic.getQuestions().get(currentIndex).getQuestion());
        questionNumberLabel.setText("Question: " + (currentIndex + 1));
        inputTopicName.setText(topic.getName());
    }

    public void removeCurrentQuestion(ActionEvent actionEvent) {
        topic.removeQuestion(currentIndex);
        if(currentIndex > 0) currentIndex --;
        loadCurrentQuestion();
    }

    public void addNewQuestion(ActionEvent actionEvent) {
        topic.addQuestion(new Question());
        currentIndex = topic.questions.size()-1;
        loadCurrentQuestion();
    }
}
