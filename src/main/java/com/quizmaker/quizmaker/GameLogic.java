package com.quizmaker.quizmaker;

import java.util.List;

public class GameLogic {

    public int zahl;
    public List<Question> questionList;

    public GameLogic(int zahl, List<Question> questionList) {
        this.zahl = zahl;
        this.questionList = questionList;
    }

}
