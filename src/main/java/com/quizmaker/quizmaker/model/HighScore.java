package com.quizmaker.quizmaker.model;

public class HighScore {
    private String name;
    private String score;
    private int lives;

    public HighScore(String playerName, String score){
        this.name = playerName;
        this.score = score;
    }

    public HighScore( String score){
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString(){
        return score;
    }
}
