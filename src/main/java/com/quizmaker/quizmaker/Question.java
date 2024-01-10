package com.quizmaker.quizmaker;

import java.util.Scanner;

public class Question {

    public String question, a1, a2, a3, a4;
    public int correctAnswer;

    public Question(String question, String a1, String a2, String a3, String a4, int correctAnswer) {
        this.question = question;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getA1() {
        return this.a1;
    }

    public String getA2() {
        return this.a2;
    }

    public String getA3() {
        return this.a3;
    }

    public String getA4() {
        return this.a4;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    public void setA4(String a4) {
        this.a4 = a4;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getCorrectAnswer() {
        return this.correctAnswer;
    }

    //guides user through question creation
    public static Question newQuestionScreen(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the question:");
        String questionText = scanner.nextLine();
        System.out.println("Enter answer choice 1:");
        String a1 = scanner.nextLine();
        System.out.println("Enter answer choice 2:");
        String a2 = scanner.nextLine();
        System.out.println("Enter answer choice 3:");
        String a3 = scanner.nextLine();
        System.out.println("Enter answer choice 4:");
        String a4 = scanner.nextLine();

        System.out.println("Answer choices:");
        System.out.println("1. " + a1);
        System.out.println("2. " + a2);
        System.out.println("3. " + a3);
        System.out.println("4. " + a4);

        System.out.println("Enter the correct answer (1, 2, 3, or 4):");
        int correctAnswer = scanner.nextInt();
        scanner.nextLine();

        return new Question(questionText, a1, a2, a3, a4, correctAnswer);
    }

    public void editQuestionScreen() {
        while (true) {
            System.out.println("Editing question: " + this.getQuestion());
            System.out.println("1. " + this.getA1());
            System.out.println("2. " + this.getA2());
            System.out.println("3. " + this.getA3());
            System.out.println("4. " + this.getA4());
            System.out.println("Correct answer: " + this.getCorrectAnswer());
            System.out.println("Choose an option:");
            System.out.println();
            System.out.println("1: Edit question text");
            System.out.println("2: Edit answer choices");
            System.out.println("3: Edit correct answer");
            System.out.println("0: Return");
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            scanner.nextLine();
            switch (input) {
                case 0:
                    System.out.println("Question modified successfully!");
                    return;
                case 1:
                    // Edit question text
                    System.out.println("Enter the new question text:");
                    String newQuestionText = scanner.nextLine();
                    this.setQuestion(newQuestionText);
                    System.out.println("Question text edited successfully!");
                    break;
                case 2:
                    // Edit answer choices
                    System.out.println("Enter new answer choice 1:");
                    this.setA1(scanner.nextLine());
                    System.out.println("Enter new answer choice 2:");
                    this.setA2(scanner.nextLine());
                    System.out.println("Enter new answer choice 3:");
                    this.setA3(scanner.nextLine());
                    System.out.println("Enter new answer choice 4:");
                    this.setA4(scanner.nextLine());
                    System.out.println("Answer choices edited successfully!");
                    break;
                case 3:
                    // Edit correct answer
                    System.out.println("Enter the new correct answer (1, 2, 3, or 4):");
                    this.setCorrectAnswer(scanner.nextInt());
                    System.out.println("Correct answer edited successfully!");
                    break;
                default:
                    System.out.println("Invalid Input. Please try again.");
            }
            System.out.println();
        }
    }
}
