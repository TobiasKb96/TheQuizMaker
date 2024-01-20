package com.quizmaker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.Override;
import java.lang.String;
import java.lang.System;
import java.util.*;


public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("frontMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();

    }
    /*
    public static void playGame(String topicName){
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        int score = 0;
        Topic gameTopic = JsonRWC.fromFile(topicName);
        List<Question> questions = gameTopic.getQuestions();
        int index = 1;
        Collections.shuffle(questions);
        for(Question currentQuestion: questions) {
            System.out.println("Question " + (index) + ": " + currentQuestion.getQuestion());
            System.out.println("1. " + currentQuestion.getA1());
            System.out.println("2. " + currentQuestion.getA2());
            System.out.println("3. " + currentQuestion.getA3());
            System.out.println("4. " + currentQuestion.getA4());
            System.out.print("Enter your answer (1, 2, 3, or 4): ");
            input = scanner.nextInt();
            if (input == currentQuestion.getCorrectAnswer()) {
                System.out.println("Correct!");
                score++;
            }
            else if (index == 15) break;
            else {
                System.out.println("Incorrect. The correct answer is " + currentQuestion.getCorrectAnswer() + ".");
                System.out.println("You lost!");
                System.out.println("Your score is " + score + ".");
                break;
            }
            index++;
        }
        if(index == questions.size() + 1) System.out.println("You won your score is "+ questions.size());
        System.out.println("");
    }
    public static void topicManager() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Choose one of the following options:");
            System.out.println("1: Create new Topic");
            System.out.println("2: Modify existing Topic");
            System.out.println("3: Return");

            int input = scanner.nextInt();
            if (input == 1) Topic.newTopicScreen();
            else if (input == 2) Topic.chooseTopicToModify();
            else if (input == 3) break;
            else System.out.println("Invalid Input");
        }
    }


    //User chooses a Topic to play or return
    public static String chooseTopicToPlay() {
        List<String> topicNames = JsonRWC.getFileNames();
        int topicNumber = 1;
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Choose one of the following Topics:");
            for(String name : topicNames) {
                System.out.println("Topic " + topicNumber +": " + name);
                topicNumber++;
            }
            System.out.println("or press 0 to return!");
            int input = scanner.nextInt();
            if (input >= 1 && input <= topicNumber) return topicNames.get(input-1);
            else if (input == 0) {
                return null;
            }
            else System.out.println("Invalid input!");
        }
    }

    public static void gameLoop(){
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Choose one of the following:");
            System.out.println("1: Play game");
            System.out.println("2: Change or create a new Topic");
            System.out.println("3: Abort");
            int input = scanner.nextInt();
            //play game
            if (input == 1){
                String topic = chooseTopicToPlay();
                playGame(topic);
            }
            //Modify topics or add new one
            else if (input == 2) topicManager();
            //Return
            else if (input == 3) return;
            else System.out.println("Invalid Input");
        }


    }
    */


    public static void main(String[] args) {
        //gameLoop();
        /*
        Question testQuestion1 = new Question("Was ist 3", "4", "3", "2", "1", 4);
        Question testQuestion2 = new Question("Was ist 2", "4", "3", "2", "1", 3);
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(testQuestion1);
        questions.add(testQuestion2);
        Topic testTopic = new Topic("was_ist4",questions);
        Topic testReadTopic;
        */
        //Topic readtopic = new Topic(JsonRWC.fromFile("was_ist1"));
        //readtopic = JsonRWC.fromFile("was_ist1");

        //List fileNames = new ArrayList();
        //fileNames = JsonRWC.getFileNames();


        System.out.println("test");


        launch();

    }
}