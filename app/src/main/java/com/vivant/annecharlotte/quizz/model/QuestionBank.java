package com.vivant.annecharlotte.quizz.model;

import java.util.Collections;
import java.util.List;

/**
 * Created by Anne-Charlotte Vivant on 11/11/2018.
 */
public class QuestionBank {
    private List<Question> mQuestionList;
    private int mNextQuestionIndex;

    public QuestionBank(List<Question> questionList) {
        mQuestionList = questionList;
        Collections.shuffle(mQuestionList);
        // Shuffle the question list before storing it
        mNextQuestionIndex = 0;
    }


    public Question getQuestion() {

        if (mNextQuestionIndex == mQuestionList.size()) {
            mNextQuestionIndex = 0;
        }
        return mQuestionList.get(mNextQuestionIndex++);
        // Loop over the questions and return a new one at each call
    }

}
