package com.vivant.annecharlotte.quizz.model;

import java.util.List;

/**
 * Created by Anne-Charlotte Vivant on 11/11/2018.
 */
public class Question {
    private String mQuestion;
    private List<String> mChoiceList;
    private int mAnswerIndex;

    public Question(String question, List<String> choiceList, int answerIndex) {
        this.mQuestion = question;
        this.mChoiceList = choiceList;
        this.mAnswerIndex = answerIndex;

    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public List<String> getChoiceList() {
        return mChoiceList;
    }

    public void setChoiceList(List<String> choiceList) {
        if (mChoiceList == null) {
            throw new IllegalArgumentException("Le tableau de réponses est vide.");
        }
        mChoiceList = choiceList;
    }

    public int getAnswerIndex() {
        return mAnswerIndex;
    }

    public void setAnswerIndex(int answerIndex) {
        if (mAnswerIndex<0 || mAnswerIndex>= mChoiceList.size()) {
            throw new IllegalArgumentException(("L'index est hors des limites."));}
        mAnswerIndex = answerIndex;
    }

}
