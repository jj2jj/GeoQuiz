package com.example.jessica0906zjj.geographicquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String TAG = "CheatActivity";
    public static final String EXTRA_ANSWER_IS_TRUE = "answer is true";
    public static final String EXTRA_ANSWER_SHOWN = "answer_show";
    private static final String KEY_INDEX1 = "answerRevealed";
    private static final String MYPREFERNCES="MyPrefs";

    SharedPreferences mPreferences;

    private boolean mAnswerIsTrue;
    private TextView mTx;
    private TextView mApiTx;
    private Button mShowAnswer;
    boolean mShowAnswerPressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        int currentapiVersion= Build.VERSION.SDK_INT;

        Log.i("AAAAAA","currentapiVersion");

        mApiTx = (TextView) findViewById(R.id.apiTextView);
        mApiTx.setText("API Level:"+ String.valueOf(currentapiVersion));

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);
        mTx = (TextView) findViewById(R.id.answerTextView);
        //SharedPreferences本身是一个接口，无法直接创建实例，通过Context的getSharedPreferences(String name, int  mode)方法来获取实例。
        mPreferences = getSharedPreferences(MYPREFERNCES,MODE_PRIVATE);

        mShowAnswer = (Button) findViewById(R.id.show_answer_btn);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //调用edit()方法获取SharedPreferences.Editor;
                SharedPreferences.Editor editor = mPreferences.edit();
                //通过SharedPreferences.Editor接口提供的put()方法对SharedPreferences进行更新；
                editor.putInt(KEY_INDEX1,1);
                //调用SharedPreferences.Editor的commit()方法，将更新提交到SharedPreferences中。
                editor.commit();
                showAnswer();
                setAnswerShowResult(true);
                mShowAnswerPressed = setAnswerShowResult(true);
            }
        });
        if (savedInstanceState !=null){
            int click = mPreferences.getInt(KEY_INDEX1,0);
            if (click !=0){//不等于0，说明按钮被按了
                setAnswerShowResult(true);
                showAnswer();
            }
        }
    }

    private void showAnswer() {
        if (mAnswerIsTrue){
            mTx.setText(R.string.true_btn);
        }
        else {
            mTx.setText(R.string.false_btn);
        }
    }

    //将是否点击show answer按钮的结果返回给GeoQuizActivity
    private boolean setAnswerShowResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
        return isAnswerShown;
    }

    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        saveInstanceState.putBoolean(KEY_INDEX1, mShowAnswerPressed);

    }

}