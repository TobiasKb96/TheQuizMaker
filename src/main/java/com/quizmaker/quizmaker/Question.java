package com.quizmaker.quizmaker;

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
}
