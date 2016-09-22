package com.example.jessica0906zjj.geographicquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GeoQuiz1 extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private TextView mTx;
    public TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_americas,true),
            new TrueFalse(R.string.question_oceans,true),
            new TrueFalse(R.string.question_turkey,false),
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_quiz);

        mTx = (TextView) findViewById(R.id.question_text);
        updateQuestion();
        mTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex+1)%mQuestionBank.length;
                updateQuestion();
            }
        });


        mTrueButton = (Button) findViewById(R.id.true_btn);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });
        mFalseButton = (Button) findViewById(R.id.false_btn);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });


    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getmQuestion();
        mTx.setText(question);
    }
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].ismTrueQuestion();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue){
            messageResId = R.string.correct_toast;
        }else {
           messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

}
