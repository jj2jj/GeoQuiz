package com.example.jessica0906zjj.geographicquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GeoQuiz extends AppCompatActivity {
    private static final String TAG = "GQ";
    private static final String KEY_INDEX="index";
    private static final String KEY_INDEX2="index2";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    private TextView mTx;
    private boolean mIsCheater;
    private int mCurrentIndex = 0;

    public TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_americas,true),
            new TrueFalse(R.string.question_oceans,true),
            new TrueFalse(R.string.question_turkey,false),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
            mIsCheater = savedInstanceState.getBoolean(KEY_INDEX2,false);
        }
        setContentView(R.layout.activity_geo_quiz4);
        mTx = (TextView) findViewById(R.id.question_text);
        updateQuestion();

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
        mNextButton = (Button) findViewById(R.id.next_btn);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex+1)%mQuestionBank.length;
               // mIsCheater = false;//查看下一题时要将其置为false
                switch (view.getId()) {
                    case R.id.true_btn:
                        mIsCheater = false;
                        break;
                    case R.id.false_btn:
                        mIsCheater = false;
                        break;
                }
                //mIsCheater = false;
                updateQuestion();
            }
        });
        mCheatButton = (Button) findViewById(R.id.cheat_btn);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GeoQuiz.this,CheatActivity.class);
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].ismTrueQuestion();
                intent.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE,answerIsTrue);
                //startActivity(intent);
                startActivityForResult(intent,0);
            }
        });
    }
    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        Log.i(TAG,"onSaveInstanceState");
        saveInstanceState.putInt(KEY_INDEX,mCurrentIndex);
        saveInstanceState.putBoolean(KEY_INDEX2,mIsCheater);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data ==null){
            return;
        }
        mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN,false);

    }


    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getmQuestion();
        mTx.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].ismTrueQuestion();
        int messageResId = 0;
        if (mIsCheater){
            messageResId = R.string.judgment_toast;
        }else {
            if (userPressedTrue == answerIsTrue){
                messageResId = R.string.correct_toast;
            }else {
                messageResId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

}
