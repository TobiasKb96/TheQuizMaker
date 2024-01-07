package com.quizmaker.quizmaker;

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
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

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
            if (input == 1) newTopic();
            else if (input == 2) modifyTopic();
            else if (input == 3) break;
            else System.out.println("Invalid Input");
        }
    }
    public static void newTopic() {
        List<Question> questions = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose Topic Name:");
        String topicName = scanner.next();
        int input = 0;
        while (true) {
            System.out.println("1: Add question");
            System.out.println("2: Return");

            input = scanner.nextInt();

            if (input == 1) {
                System.out.println("Enter the question:");
                String questionText = scanner.next();
                System.out.println("Enter answer choice 1:");
                String a1 = scanner.next();
                System.out.println("Enter answer choice 2:");
                String a2 = scanner.next();
                System.out.println("Enter answer choice 3:");
                String a3 = scanner.next();
                System.out.println("Enter answer choice 4:");
                String a4 = scanner.next();

                System.out.println("Answer choices:");
                System.out.println("1. " + a1);
                System.out.println("2. " + a2);
                System.out.println("3. " + a3);
                System.out.println("4. " + a4);

                System.out.println("Enter the correct answer (1, 2, 3, or 4):");
                int correctAnswer = scanner.nextInt();

                Question newQuestion = new Question(questionText, a1, a2, a3, a4, correctAnswer);
                questions.add(newQuestion);

                System.out.println("Question added successfully!");
                }
            else if (input == 2) {

                Topic newTopic = new Topic(topicName, questions);
                JsonRWC.toFile(newTopic);
                System.out.println("Topic created successfully!");
                break;
            }
            else {
                System.out.println("Invalid Input. Please try again.");
            }
        }
    }
    public static void modifyTopic() {
        Scanner scanner = new Scanner(System.in);

        // List all available topics
        List<String> topicNames = JsonRWC.getFileNames();
        System.out.println("Available Topics:");
        for (int i = 0; i < topicNames.size(); i++) {
            System.out.println((i + 1) + ": " + topicNames.get(i));
        }

        System.out.println("Choose a Topic to Modify (or press 0 to return):");
        int topicChoice = scanner.nextInt();

        if (topicChoice == 0) {
            return; // Return to the main menu
        }

        if (topicChoice > 0 && topicChoice <= topicNames.size()) {
            String topicName = topicNames.get(topicChoice - 1);

            // Load the existing topic
            Topic existingTopic = JsonRWC.fromFile(topicName);

            if (existingTopic == null) {
                System.out.println("Topic not found.");
                return;
            }

            List<Question> questions = existingTopic.getQuestions();

            while (true) {
                System.out.println("Current Questions in " + topicName + ":");
                for (int i = 0; i < questions.size(); i++) {
                    System.out.println((i + 1) + ": " + questions.get(i).getQuestion());
                }

                System.out.println("Enter the question number to modify (or press 0 to return):");
                int editIndex = scanner.nextInt();

                if (editIndex == 0) {
                    // Return to the main menu
                    JsonRWC.toFile(existingTopic);
                    System.out.println("Topic modified successfully!");
                    break;
                }

                if (editIndex == 0) {
                    // Return to the main menu
                    JsonRWC.toFile(existingTopic);
                    System.out.println("Topic modified successfully!");
                    break;
                }

                if (editIndex > 0 && editIndex <= questions.size()) {
                    Question editedQuestion = questions.get(editIndex - 1);
                    System.out.println("Editing question:");
                    System.out.println("Question: " + editedQuestion.getQuestion());
                    System.out.println("1. " + editedQuestion.getA1());
                    System.out.println("2. " + editedQuestion.getA2());
                    System.out.println("3. " + editedQuestion.getA3());
                    System.out.println("4. " + editedQuestion.getA4());
                    System.out.println("Correct answer: " + editedQuestion.getCorrectAnswer());

                    System.out.println("Choose an option:");
                    System.out.println("1: Edit question text");
                    System.out.println("2: Edit answer choices");
                    System.out.println("3: Edit correct answer");
                    System.out.println("4: Delete this question");
                    System.out.println("5: Return");

                    int input = scanner.nextInt();

                    switch (input) {
                        case 1:
                            // Edit question text
                            System.out.println("Enter the new question text:");
                            String newQuestionText = scanner.next();
                            editedQuestion.setQuestion(newQuestionText);
                            System.out.println("Question text edited successfully!");
                            break;
                        case 2:
                            // Edit answer choices
                            System.out.println("Enter new answer choice 1:");
                            editedQuestion.setA1(scanner.next());
                            System.out.println("Enter new answer choice 2:");
                            editedQuestion.setA2(scanner.next());
                            System.out.println("Enter new answer choice 3:");
                            editedQuestion.setA3(scanner.next());
                            System.out.println("Enter new answer choice 4:");
                            editedQuestion.setA4(scanner.next());
                            System.out.println("Answer choices edited successfully!");
                            break;
                        case 3:
                            // Edit correct answer
                            System.out.println("Enter the new correct answer (1, 2, 3, or 4):");
                            editedQuestion.setCorrectAnswer(scanner.nextInt());
                            System.out.println("Correct answer edited successfully!");
                            break;
                        case 4:
                            // Delete this question
                            questions.remove(editIndex - 1);
                            System.out.println("Question deleted successfully!");
                            break;
                        case 5:
                            // Return to the main menu
                            JsonRWC.toFile(existingTopic);
                            System.out.println("Topic modified successfully!");
                            return;
                        default:
                            System.out.println("Invalid Input. Please try again.");
                    }
                } else {
                    System.out.println("Invalid question number. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid topic choice. Please try again.");
        }
    }

    public static String chooseTopic() {
        List<String> topicNames = new ArrayList();
        topicNames = JsonRWC.getFileNames();
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
            if (input == 1){
                String topic = chooseTopic();
                playGame(topic);
            }
            else if (input == 2) topicManager();
            else if (input == 3) return;
            else System.out.println("Invalid Input");
        }


    }



    public static void main(String[] args) {
        gameLoop();
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


       // launch();

    }
}