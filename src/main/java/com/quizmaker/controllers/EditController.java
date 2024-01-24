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

    //Method initialize is overridden and used to decide whether a New Topic is created
    //or it loads from the list, based on user input
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(data.getData().equals("NewTopic")){
            topic = new Topic();
        }
        else topic = JsonRWC.fromFile(data.getData());

        loadCurrentQuestion();
    }

    public void showNextQuestion(ActionEvent actionEvent) {

        saveTopicState();
        //The index of the current question in the list in increased by 1
        currentIndex++;
        //Adds a new question if currentIndex >= the number of questions in the topic
        if(topic.questions.size() <= currentIndex) topic.questions.add(new Question());
        //Calls the loadCurrentQuestion method
        //Responsible for calling and loading the next Question in the List
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
    //
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
        //Saves the Question and Answers info in Strings
        String question = inputQuestion.getText().substring(inputQuestion.getText().lastIndexOf(": ")+2);
        String a1 = inputA1.getText().substring(inputA1.getText().lastIndexOf(": ")+2);
        String a2 = inputA2.getText().substring(inputA2.getText().lastIndexOf(": ")+2);
        String a3 = inputA3.getText().substring(inputA3.getText().lastIndexOf(": ")+2);
        String a4 = inputA4.getText().substring(inputA4.getText().lastIndexOf(": ")+2);

        //Updates the name of the topic by extracting the text content from the inputTopicName field.
        topic.setName(inputTopicName.getText().substring(inputTopicName.getText().lastIndexOf(": ")+2));
        //
        String correctAnswerString = ((ToggleButton) correctToggleGroup.getSelectedToggle()).getId().replaceAll("\\D", "");
        int correctAnswer = Integer.parseInt(correctAnswerString);
        //Creates a new Question Object
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
        //Sets the Answer UI elements with the answer options from the current topic
        inputA1.setText("A1: " + topic.getQuestions().get(currentIndex).getA1());
        inputA2.setText("A2: " + topic.getQuestions().get(currentIndex).getA2());
        inputA3.setText("A3: " + topic.getQuestions().get(currentIndex).getA3());
        inputA4.setText("A4: " + topic.getQuestions().get(currentIndex).getA4());
        //Sets the Text of current Question + sets the number
        inputQuestion.setText("Question number " + (currentIndex+1) + ": " + topic.getQuestions().get(currentIndex).getQuestion());
        //Sets the text of the inputTopicName field with the name of the current topic.
        inputTopicName.setText("Topic name: " + topic.getName());
    }
}
