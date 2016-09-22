package com.example.jessica0906zjj.geographicquiz;

/**
 * Created by Jessica0906zjj on 2016-09-20.
 */

public class TrueFalse {
    private int mQuestion;
    private boolean mTrueQuestion;

    public int getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
    }

    public TrueFalse(int question, boolean trueQuestion){
        mQuestion = question;
        mTrueQuestion = trueQuestion;
    }
    public boolean ismTrueQuestion() {
        return mTrueQuestion;
    }

    public void setmTrueQuestion(boolean mTrueQuestion) {
        this.mTrueQuestion = mTrueQuestion;
    }
}
