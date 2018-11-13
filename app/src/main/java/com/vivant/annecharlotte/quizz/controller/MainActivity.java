package com.vivant.annecharlotte.quizz.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.vivant.annecharlotte.quizz.R;
import com.vivant.annecharlotte.quizz.model.User;

public class MainActivity extends AppCompatActivity {

    private TextView mGreetingText;
    private EditText mFirstnameEditText;
    private Button mPlayBtn;

    private User mUser;

    private static final int GAME_ACTIVITY_REQUEST_CODE = 42;

    private SharedPreferences mPreferences;
    private String KEY_FIRSTNAME;
    private  String KEY_SCORE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUser = new User();

        mPreferences = getPreferences(MODE_PRIVATE);

        mGreetingText = (TextView) findViewById(R.id.activity_main_greeting_text);
        mFirstnameEditText = (EditText) findViewById(R.id.activity_main_firstname_editview);
        mPlayBtn = (Button) findViewById(R.id.activity_main_play_btn);

        mPlayBtn.setEnabled(false);

        greetUser();


        mFirstnameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayBtn.setEnabled(s.toString().length()!=0);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        }
        );

        mPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = mFirstnameEditText.getText().toString();
                mUser.setFirstName(fname);

                // Stockage du prenom
                mPreferences.edit().putString(KEY_FIRSTNAME, mUser.getFirstName()).apply();

                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            // Fetch the score from the Intent
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);

            // Stockage du score
            mPreferences.edit().putInt(KEY_SCORE, score).apply();

            greetUser();
        }
    }

    private void greetUser() {
        String firstname = mPreferences.getString(KEY_FIRSTNAME, null);

        if (firstname!=null) {
            int score = mPreferences.getInt(KEY_SCORE, 0);

            String fullText = "Bonjour " + firstname + "\n ton dernier score est de " +score + ". Quel sera ton score cette fois-ci?";
            mGreetingText.setText(fullText);
            mFirstnameEditText.setText(firstname);
            mFirstnameEditText.setSelection(firstname.length());
            mPlayBtn.setEnabled(true);
        }
    }

}
