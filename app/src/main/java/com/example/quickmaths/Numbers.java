package com.example.quickmaths;

import java.util.Random;

public class Numbers {
    private int questionNum1;
    private int questionNum2;
    private int answerProduct;
    private int otherChoice;

    public Numbers(){
        this.questionNum1 = 0;
        this.questionNum2 = 0;
        this.otherChoice = 0;
        this.answerProduct = 0;
    }


    Random rand= new Random();


    public void setQuestionNum1(int questionNum1) {
        this.questionNum1 = questionNum1;
    }

    public void setQuestionNum2(int questionNum2) {
        this.questionNum2 = questionNum2;
    }

    public void setAnswerProduct(int answerProduct) {
        this.answerProduct = answerProduct;
    }

    public void setAnswerChoice(int answerChoice) {
        this.otherChoice = answerChoice;
    }

    public int getQuestionNum1() {
        questionNum1 = 1+ rand.nextInt(12);
        return questionNum1;
    }

    public int getQuestionNum2() {
        questionNum2 = 1+ rand.nextInt(12);
        return questionNum2;
    }

    public int getAnswerProduct() {
        answerProduct = questionNum1 * questionNum2;
        return answerProduct;
    }

    public int getOtherChoice() {
        otherChoice = 1+ rand.nextInt(getAnswerProduct()+15 ) ;
        if(otherChoice == getAnswerProduct()){
            otherChoice++;
        }
        return otherChoice;
    }


}
