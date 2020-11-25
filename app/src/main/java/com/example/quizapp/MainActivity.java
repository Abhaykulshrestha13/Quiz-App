package com.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    private TextView mTxtQuestion;
    private Button btnTrue;
    private Button btnWrong;
    private int mQuestionIndex = 0;
    private int mQuizQuestion;

    private ProgressBar mProgressBar;
    private TextView mQuizStatsTextView;
    private int mUserScore;

    private QuizModel[] questionCollection = new QuizModel[]{
            new QuizModel(R.string.q1,true),
            new QuizModel(R.string.q2,false),
            new QuizModel(R.string.q3,true),
            new QuizModel(R.string.q4,false),
            new QuizModel(R.string.q5,true),
            new QuizModel(R.string.q6,false),
            new QuizModel(R.string.q7,true),
            new QuizModel(R.string.q8,false),
            new QuizModel(R.string.q9,true),
            new QuizModel(R.string.q10,false),
};

    final int USER_PROGRESS = (int)Math.ceil(100.0/questionCollection.length);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtQuestion = findViewById(R.id.txtQuestion);
        QuizModel q1 = questionCollection[mQuestionIndex];
        mQuizQuestion = q1.getmQuestion();
        mTxtQuestion.setText(mQuizQuestion);

        mQuizStatsTextView = findViewById(R.id.txtQuizStats);
        mProgressBar = findViewById(R.id.quizPB);


        btnTrue = findViewById(R.id.btnTrue);
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evaluateUsersAnswer(true);
             changedQuestionOnButtonCLicked();

            }
        });



        btnWrong = findViewById(R.id.btnWrong);
      //  btnWrong.setOnClickListener(myClickListener);
        btnWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evaluateUsersAnswer(false);
             changedQuestionOnButtonCLicked();
            }
        });
    }
    private void changedQuestionOnButtonCLicked(){
        mQuestionIndex = (mQuestionIndex + 1)%10;
        if (mQuestionIndex == 0){
            AlertDialog.Builder quizAler = new AlertDialog.Builder(this);
            quizAler.setCancelable(false);
            quizAler.setTitle("The Quiz is Finished");
            quizAler.setMessage("Your Score is " + mUserScore);
            quizAler.setPositiveButton("Finish The Quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            quizAler.show();
        }
        mQuizQuestion = questionCollection[mQuestionIndex].getmQuestion();
        mTxtQuestion.setText(mQuizQuestion);

        mProgressBar.incrementProgressBy(USER_PROGRESS);
        mQuizStatsTextView.setText(mUserScore + "");
    }

    private void evaluateUsersAnswer (boolean userGuess){

        boolean currentQuestionAnswer = questionCollection[mQuestionIndex].isAnswer();
        if(currentQuestionAnswer == userGuess){
            Toast.makeText(getApplicationContext(),R.string.correct_toast_message,Toast.LENGTH_SHORT).show();
            mUserScore = mUserScore + 1;        }
        else{
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast_message,Toast.LENGTH_SHORT).show();
        }
    }

}