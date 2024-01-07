package com.quizmaker.quizmaker;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Topic {
        public String name;
        public List<Question> questions;

    public Topic(String name, List<Question> questions) {
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

    public void addQuestion(Question question) { //add question to List of questions
        this.questions.add(question);
    }
    public void removeQuestion(int pos) {
        this.questions.remove(pos);
    }

    public String getName() { //get name of Topic
        return this.name;
    }
    public List<Question> getQuestions() { //get List of Questions in Topic
        return questions;
    }
    public int getNumberOfQuestions(){
        return this.questions.size();
    }
}
