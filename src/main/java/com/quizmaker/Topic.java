package com.quizmaker;

import com.quizmaker.dataManagment.JsonRWC;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Topic {
        public String name;
        public List<Question> questions;

    //Initializes the name and questions fields
    public Topic(String name, List<Question> questions) {
        this.name = name;
        this.questions = questions;
    }

    //Initializes the name field and creates
    //an empty list for questions
    public Topic(String name){
        this.name = name;
        this.questions = new ArrayList<>();

    }
    //Creates a new Topic Object by copying the name and questions
    //from an existing Topic Object
    public Topic(Topic existingTopic) {
        this.name = existingTopic.getName();
        this.questions = new ArrayList<>(existingTopic.getQuestions());
    }
    //Initializes the name field to an empty string
    //Creates an empty list for questions
    //Adds a Question Object to the list
    public Topic() {
        this.name = "";
        this.questions = new ArrayList<>();
        this.questions.add(new Question());
    }

    //Adds a Question Object to the list
    public void addQuestion(Question question) { //add question to List of questions
        this.questions.add(question);
    }
    //Removes a Question object at the specified position in the list
    public void removeQuestion(int pos) {
        this.questions.remove(pos);
    }
    //Sets a Question Object at the specified position in the list
    public void setQuestion(int questionNr, Question question){
        this.questions.set(questionNr, question);
    }

    //Returns the name of the topic
    public String getName() { //get name of Topic
        return this.name;
    }

    //Sets the name of the topic
    public void setName(String name) {
        this.name = name;
    }

    //Returns the list of questions associated with the topic
    public List<Question> getQuestions() { //get List of Questions in Topic
        return questions;
    }

    //Returns the number of questions in the list
    public int getNumberOfQuestions(){
        return this.questions.size();
    }

}
