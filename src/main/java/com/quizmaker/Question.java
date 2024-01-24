package com.quizmaker;

import java.util.Scanner;

public class Question {

    public String question, a1, a2, a3, a4;
    //correctAnswer is an integer indicating the Index of the correct Answer choice
    public int correctAnswer;

    //Initializes the field with the Question and Answer values
    public Question(String question, String a1, String a2, String a3, String a4, int correctAnswer) {
        this.question = question;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.correctAnswer = correctAnswer;
    }
    //Initializes the field with default values
    public Question(){
        this.question = "";
        this.a1 = "";
        this.a2 = "";
        this.a3 = "";
        this.a4 = "";
        this.correctAnswer = 1;
    }

    //Getter Methods
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

    public int getCorrectAnswer() {
        return this.correctAnswer;
    }

}
