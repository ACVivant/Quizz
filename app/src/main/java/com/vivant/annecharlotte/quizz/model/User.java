package com.vivant.annecharlotte.quizz.model;

/**
 * Created by Anne-Charlotte Vivant on 11/11/2018.
 */
public class User {
    private String mFirstName;

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    @Override
    public String toString() {
        return "User{" +
                "mFirstName='" + mFirstName + '\'' +
                '}';
    }
}
