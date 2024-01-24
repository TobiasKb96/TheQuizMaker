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

    //Method initialize is overridden and used to decide whether a New Topic is created
    //or it loads from the list, based on user input
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
        //saveTopicState method to save current question state or responses
        saveTopicState();
        //Conditional checks if there is a previous question
        if (currentIndex > 0) {
            //If yes, it decrements the currentIndex by 1 and loads Question
            currentIndex--;
            loadCurrentQuestion();
        }
    }

    public void saveTopicToFile(ActionEvent actionEvent) {
        saveTopicState();
        //Writes the topic data to a file in the JsonRWC class
        JsonRWC.toFile(topic);
        //Creates a new Object of Instance ScreenHandler
        ScreenHandler screenHandler = new ScreenHandler();
        data.setData("Edit");
        //Switches to the chooseTopicScreen
        screenHandler.switchScreen(actionEvent, "chooseTopicScreen");
    }

    public void correctAnswerInput(ActionEvent actionEvent) {

    }

    //Extracts info from UI input fields, updates the state of the current topic
    //and updates the currentIndex Q with extracted info
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
        //updates the topic with the new Question object at the current index
        topic.setQuestion(currentIndex, questionToSave);
    }

    //loadCurrentQuestion method populates UI elements with information
    // related to the current question within a topic.
    public void loadCurrentQuestion() {
        //Retrieves the correct answer index from the current question in the topic
        //and selects the corresponding ToggleButton in the correctToggleGroup
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
        saveTopicState();
        loadCurrentQuestion();
    }

    public void addNewQuestion(ActionEvent actionEvent) {
        topic.addQuestion(new Question());
        currentIndex = topic.questions.size()-1;
        saveTopicState();
        loadCurrentQuestion();
    }
}
