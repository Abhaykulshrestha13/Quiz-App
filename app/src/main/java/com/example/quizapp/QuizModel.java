package com.example.quizapp;

public class QuizModel {

    private int mQuestion;
    private boolean mAnswer;

    public QuizModel(int question,boolean answer){

        mQuestion = question;
        mAnswer = answer;

    }

    public int getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(int question) {
        mQuestion = question;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setmAnswer(boolean answer) {
        mAnswer = answer;
    }

}
