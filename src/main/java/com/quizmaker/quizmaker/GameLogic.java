package com.quizmaker.quizmaker;

import com.quizmaker.quizmaker.model.QuestionOK;

import java.util.List;

public class GameLogic {

    public int zahl;
    public List<QuestionOK> questionList;

    public GameLogic(int zahl, List<QuestionOK> questionList) {
        this.zahl = zahl;
        this.questionList = questionList;
    }

}
