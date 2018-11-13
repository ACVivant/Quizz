package com.vivant.annecharlotte.quizz.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vivant.annecharlotte.quizz.R;
import com.vivant.annecharlotte.quizz.model.Question;
import com.vivant.annecharlotte.quizz.model.QuestionBank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mQuestionText;
    private Button mAnswer1Button;
    private Button mAnswer2Button;
    private Button mAnswer3Button;
    private Button mAnswer4Button;

    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;

    private int mScore;
    private int mNumberOfQuestions;

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_STATE_SCORE = "currentScore";
    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";

    private boolean mEnableToucheEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mQuestionBank = this.generateQuestions();

        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mNumberOfQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        } else {
            mScore = 0;
            mNumberOfQuestions = 5;
        }

        mEnableToucheEvents = true;

        mQuestionText = (TextView) findViewById(R.id.activity_game_question_text);
        mAnswer1Button = (Button) findViewById(R.id.activity_game_answer1_btn);
        mAnswer2Button = (Button) findViewById(R.id.activity_game_answer2_btn);
        mAnswer3Button = (Button) findViewById(R.id.activity_game_answer3_btn);
        mAnswer4Button = (Button) findViewById(R.id.activity_game_answer4_btn);



        mAnswer1Button.setOnClickListener(this);
        mAnswer2Button.setOnClickListener(this);
        mAnswer3Button.setOnClickListener(this);
        mAnswer4Button.setOnClickListener(this);

        mAnswer1Button.setTag(0);
        mAnswer2Button.setTag(1);
        mAnswer3Button.setTag(2);
        mAnswer4Button.setTag(3);

        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_STATE_SCORE, mScore);
        outState.putInt(BUNDLE_STATE_QUESTION, mNumberOfQuestions);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {

        int mAnswerClickButton;

        mAnswerClickButton = (int) v.getTag();
        int answerOK = mCurrentQuestion.getAnswerIndex();

        if (mAnswerClickButton == answerOK) {
            Toast.makeText(GameActivity.this, "Bravo!", Toast.LENGTH_SHORT).show();
            mScore++;
        } else {
            Toast.makeText(GameActivity.this, "Perdu...", Toast.LENGTH_SHORT).show();
        }

        --mNumberOfQuestions;

        mEnableToucheEvents = false;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEnableToucheEvents = true;

                if (mNumberOfQuestions ==0) {
                    endGame();
                } else {
                    mCurrentQuestion= mQuestionBank.getQuestion();
                    displayQuestion(mCurrentQuestion);
                }
            }

        }, 2000);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableToucheEvents && super.dispatchTouchEvent(ev);
    }

    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("La partie est terminée!")
                .setMessage("Votre score est de  " + mScore)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    private void displayQuestion(final Question question) {
        // Set the text for the question text view and the four buttons

        String textCurrentQuestion = question.getQuestion();
        mQuestionText.setText(textCurrentQuestion);

        List<String> textCurrentAnswers = question.getChoiceList();
        mAnswer1Button.setText(textCurrentAnswers.get(0));
        mAnswer2Button.setText(textCurrentAnswers.get(1));
        mAnswer3Button.setText(textCurrentAnswers.get(2));
        mAnswer4Button.setText(textCurrentAnswers.get(3));
    }

    private QuestionBank generateQuestions() {

        Question question1 = new Question("What is the house number of The Simpsons?",
                Arrays.asList("42", "101", "666", "742"),
                3);

        Question question2 = new Question("comment dit-on bonjour en polonais?",
                Arrays.asList("Do widzenia", "Dzien dobry", "Dzienkuje", "Spij dobrze"),
                1);

        Question question3 = new Question("Who is the creator of the Android operating system?",
                Arrays.asList("Andy Rubin", "Steve Wozniak", "Jake Wharton", "Paul Smith"),
                0);

        Question question4 = new Question("When did the first man land on the moon?",
                Arrays.asList("1958", "1962", "1967", "1969"),
                3);

        Question question5 = new Question("What is the capital of Romania?",
                Arrays.asList("Bucarest", "Warsaw", "Budapest", "Berlin"),
                0);

        Question question6 = new Question("Who did the Mona Lisa paint?",
                Arrays.asList("Michelangelo", "Leonardo Da Vinci", "Raphael", "Carravagio"),
                1);

        Question question7 = new Question("In which city is the composer Frédéric Chopin buried?",
                Arrays.asList("Strasbourg", "Warsaw", "Paris", "Moscow"),
                2);

        Question question8 = new Question("What is the country top-level domain of Belgium?",
                Arrays.asList(".bg", ".bm", ".bl", ".be"),
                3);

        Question question9 = new Question("What is the house number of The Simpsons?",
                Arrays.asList("42", "101", "666", "742"),
                3);

        Question question10 = new Question("How many countries are there in the European Union?",
                Arrays.asList("15", "24", "28", "32"),
                2);

        List<Question> questionsAll= new ArrayList<Question>();
        questionsAll.add(question1);
        questionsAll.add(question2);
        questionsAll.add(question3);
        questionsAll.add(question4);
        questionsAll.add(question5);
        questionsAll.add(question6);
        questionsAll.add(question7);
        questionsAll.add(question8);
        questionsAll.add(question9);
        questionsAll.add(question10);

        QuestionBank questionsList = new QuestionBank(questionsAll);

        return questionsList;
    }

}
