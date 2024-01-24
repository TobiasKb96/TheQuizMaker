package com.quizmaker.quizmaker.model;

import com.quizmaker.quizmaker.JsonRWC;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Topic {
        public String name;
        public List<QuestionOK> questions;

    public Topic(String name, List<QuestionOK> questions) {
        this.name = name;
        this.questions = questions;
    }

    public Topic(String name){
        this.name = name;
        this.questions = new ArrayList<>();

    }
    public Topic(Topic existingTopic) {
        this.name = existingTopic.getName();
        this.questions = new ArrayList<>(existingTopic.getQuestions());
    }
    public Topic() {
        this.name = null;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(QuestionOK question) { //add question to List of questions
        this.questions.add(question);
    }
    public void removeQuestion(int pos) {
        this.questions.remove(pos);
    }

    public String getName() { //get name of Topic
        return this.name;
    }
    public List<QuestionOK> getQuestions() { //get List of Questions in Topic
        return questions;
    }
    public int getNumberOfQuestions(){
        return this.questions.size();
    }

    public void modifyTopicScreen() {
        Scanner scanner = new Scanner(System.in);
        List<QuestionOK> questions = this.getQuestions();

        while (true) {
            System.out.println();
            System.out.println("Choose one of the following options");
            System.out.println("0: Return");
            System.out.println("1: Add a question");
            System.out.println();
            System.out.println("or edit one of the current Questions in " + this.getName() + ":");

            for (int i = 0; i < questions.size(); i++) {
                System.out.println((i + 2) + ": " + questions.get(i).getQuestion());
            }

            System.out.println("X: Enter number next to the question to modify it");

            int modifyChoice = scanner.nextInt();
            scanner.nextLine();

            // Return to the main menu
            if (modifyChoice == 0) {
                JsonRWC.toFile(this);
                System.out.println("Topic modified successfully!");
                break;
            }
            //adds Question
            else if (modifyChoice == 1) {
                questions.add(QuestionOK.newQuestionScreen());
            }

            //Edit a Question
            else if (modifyChoice > 1 && modifyChoice <= questions.size()+1) {
                QuestionOK modifiedQuestion = questions.get(modifyChoice - 2);
                System.out.println();
                System.out.println("Editing question:");
                System.out.println("Question: " + modifiedQuestion.getQuestion());
                System.out.println("1. " + modifiedQuestion.getA1());
                System.out.println("2. " + modifiedQuestion.getA2());
                System.out.println("3. " + modifiedQuestion.getA3());
                System.out.println("4. " + modifiedQuestion.getA4());
                System.out.println("Correct answer: " + modifiedQuestion.getCorrectAnswer());

                System.out.println("Choose an option:");
                System.out.println("1: Edit question");
                System.out.println("2: Delete this question");
                System.out.println("0: Return");

                int input = scanner.nextInt();
                scanner.nextLine();

                switch (input) {
                    case 0:
                        // Return to the main menu and save topic
                        System.out.println("Question modified successfully!");
                        return;

                    case 1:
                        // Edit question text
                        modifiedQuestion.editQuestionScreen();
                        break;

                    // Delete this question
                    case 2:
                        questions.remove(modifyChoice - 2);
                        System.out.println("Question deleted successfully!");
                        break;
                    default:
                        System.out.println("Invalid Input. Please try again.");
                }
            }
            else {
                System.out.println("Invalid question number. Please try again.");
            }
        }
    }

    //User can modify an existing topic
    public static void chooseTopicToModify() {
        Scanner scanner = new Scanner(System.in);

        // List all available topics
        List<String> topicNames = JsonRWC.getFileNames();
        System.out.println("Available Topics:");
        for (int i = 0; i < topicNames.size(); i++) {
            System.out.println((i + 1) + ": " + topicNames.get(i));
        }
        //Choose a Topic to modify or return
        System.out.println();
        System.out.println("X: Enter Topic number to modify it or press");
        System.out.println("0: Return");
        int topicChoice = scanner.nextInt();
        scanner.nextLine();

        // Return to the main menu
        if (topicChoice == 0) {
            return;
        }

        if (topicChoice > 0 && topicChoice <= topicNames.size()) {
            String topicName = topicNames.get(topicChoice - 1);


            // Load the existing topic
            Topic existingTopic = JsonRWC.fromFile(topicName);
            existingTopic.modifyTopicScreen();
        }
        else {
            System.out.println("Invalid topic choice. Please try again.");
        }
    }

    //User creates a new Topic and adds questions
    public void newTopicScreen(String topicName, TextField questionTextField1, TextField questionTextField2,TextField questionTextField3) {
        List<QuestionOK> questions = new ArrayList<>();
        String question1 = questionTextField1.getText();
                String question2 = questionTextField2.getText();
                String question3 = questionTextField3.getText();

                QuestionOK newQuestion1 = new QuestionOK(question1);
                QuestionOK newQuestion2 = new QuestionOK(question2);
                QuestionOK newQuestion3 = new QuestionOK(question3);
                questions.add(newQuestion1);
                questions.add(newQuestion2);
                questions.add(newQuestion3);

                Topic newTopic = new Topic(topicName, questions);
                JsonRWC.toFile(newTopic);
                System.out.println("Topic created successfully!");
        }
    }
