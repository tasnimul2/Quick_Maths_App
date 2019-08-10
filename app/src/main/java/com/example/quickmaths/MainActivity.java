package com.example.quickmaths;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //______________________ Declaration ________________________//
    Numbers number = new Numbers();
    ImageView correctIV;
    ImageView incorrectIV;
    ImageView gameOver;
    ImageView quickMathsLogo;
    ImageView twoPlusTwo;
    ImageView add;
    ImageView subtract;
    ImageView divide;
    ImageView multiply;
    ImageView integral;
    Button startButton;
    TextView questionTV;
    TextView resultsTV;
    android.support.v7.widget.GridLayout grid ;
    ImageView countDownCircle;
    TextView countDownText;
    ImageView playAgain;
    int questionNum1;
    int questionNum2;
    int buttonNumber;
    Button answerButton0;
    Button answerButton1;
    Button answerButton2;
    Button answerButton3;
    boolean startButtonPressed = false;
    boolean answerButtonPressed = false;
    boolean gameInProgress = false;
    int answerSpot ;
    int correct;
    int attempted;
    static int highScore = 0;
    MediaPlayer violinMusic;
    MediaPlayer correctMusic;
    MediaPlayer incorrectMusic;
    MediaPlayer clockMusic;
    MediaPlayer gameOverMusic;

    //------------------------------------------------------------FUNCTIONS/METHODS-------------------------------------------------//


   //_______________________________countdown and displaying questions_____________________________//
    public void timer(){
        if(gameInProgress) {
            new CountDownTimer(30000, 1000) {
                public void onTick(long millisecondsTillDone) {
                    countDownText.setText("" + millisecondsTillDone / 1000);
                    generateQuestion();
                }

                public void onFinish() {
                    clockMusic.pause();
                    gameOverMusic.seekTo(0);
                    gameOverMusic.start();
                    correctIV.animate().alpha(0f);
                    incorrectIV.animate().alpha(0f);
                    playAgain.animate().alpha(1f);
                    gameOver.animate().alpha(1f).setDuration(2000);
                    highScore();
                    resultsTV.setText("Correct : " + correct + "\n Attempted : " + attempted + "\n High Score : " + highScore);
                    resultsTV.animate().alpha(1f).setDuration(2000);
                    gameInProgress = false;
                }
            }.start();
        }
    }

    //_________________________generates which button the correct answer is placed____________________-//
    public int answerSpotGenerator(){
        Random rand= new Random();
        int spot = rand.nextInt(4);
        return spot;
    }

    //_______________________ generates answers options for  the 4 buttons _______________________//
    public void generateQuestion(){

        Log.i("Answer spot is : "+answerSpot , "inside generateQuestion() ");

        if(answerButtonPressed){
            answerSpot = answerSpotGenerator();
        }
        if (startButtonPressed || answerButtonPressed) {
            questionNum1 = number.getQuestionNum1();
            questionNum2 = number.getQuestionNum2();
            questionTV.setText("" + questionNum1 + " X " + questionNum2 + " = ?");

            if (answerSpot == 0) {
                answerButton0.setText("" + number.getAnswerProduct());
                answerButton1.setText("" + number.getOtherChoice());
                answerButton2.setText("" + number.getOtherChoice());
                answerButton3.setText("" + number.getOtherChoice());

            }

            if (answerSpot == 1) {
                answerButton1.setText("" + number.getAnswerProduct());
                answerButton0.setText("" + number.getOtherChoice());
                answerButton2.setText("" + number.getOtherChoice());
                answerButton3.setText("" + number.getOtherChoice());


            }



            if (answerSpot == 2) {
                answerButton2.setText("" + number.getAnswerProduct());
                answerButton1.setText("" + number.getOtherChoice());
                answerButton0.setText("" + number.getOtherChoice());
                answerButton3.setText("" + number.getOtherChoice());


            }


            if (answerSpot == 3) {
                answerButton3.setText("" + number.getAnswerProduct());
                answerButton1.setText("" + number.getOtherChoice());
                answerButton2.setText("" + number.getOtherChoice());
                answerButton0.setText("" + number.getOtherChoice());


            }
            startButtonPressed = false;
            answerButtonPressed = false;

        }
    }

    //___________4 buttons with answer options_______________________________//
    public void answerOnClick (View view){
        Button accessTheView = (Button) view;
        buttonNumber = Integer.parseInt(accessTheView.getTag().toString());
        answerButtonPressed = true;
        correctOrIncorrect();
    }

    //_____________________checks if the answer is correct or incorrect________________-//
    public void correctOrIncorrect(){

        if((buttonNumber == answerSpot) && gameInProgress){
            correctMusic.start();
            incorrectIV.setVisibility(View.INVISIBLE);
            correctIV.setVisibility(View.VISIBLE);
            correctIV.animate().rotationXBy(360);
            correct+=1;
            attempted+=1;

        }
        else {
            incorrectMusic.start();
            correctIV.setVisibility(View.INVISIBLE);
            incorrectIV.setVisibility(View.VISIBLE);
            incorrectIV.animate().rotationXBy(360);
            attempted+=1;
        }
    }

    //_____________________High Score _________________________________________________________//

    public void highScore(){
        if(correct > highScore){
            highScore = correct;
        }
    }

    //______________ Start count down timer when "Start" button is pressed" _____________________ //

    public void startOnClick(View View){
        violinMusic.stop();
        clockMusic.start();
        questionTV.setVisibility(View.VISIBLE);
        grid.setVisibility(View.VISIBLE);
        countDownCircle.setVisibility(View.VISIBLE);
        countDownText.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.INVISIBLE);
        quickMathsLogo.setAlpha(0f);
        twoPlusTwo.setAlpha(0f);
        add.setAlpha(0f);
        subtract.setAlpha(0f);
        multiply.setAlpha(0f);
        divide.setAlpha(0f);
        integral.setAlpha(0f);
        startButtonPressed = true;
        gameInProgress = true;
        answerSpot = answerSpotGenerator();
        generateQuestion();
        timer();
    }



    //____________________________________ PLAY AGAIN BUTTON _____________________///
    public void playAgainOnClick(View view){
        gameOverMusic.pause();
        clockMusic.seekTo(0);
        clockMusic.start();
        resultsTV.animate().alpha(0f).setDuration(2000);
        gameOver.animate().alpha(0f).setDuration(2000);
        correctIV.setVisibility(View.INVISIBLE);
        incorrectIV.setVisibility(View.INVISIBLE);
        correctIV.animate().alpha(1f);
        incorrectIV.animate().alpha(1f);
        playAgain.animate().alpha(0f);
        gameInProgress = true;
        attempted = 0;
        correct = 0;
        generateQuestion();
        timer();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //_________________________ Initialization _______________________________//

        correctIV = (ImageView) findViewById(R.id.correctIV);
        incorrectIV = (ImageView) findViewById(R.id.incorrectIV);
        startButton = (Button) findViewById(R.id.startBTn);
        questionTV = (TextView) findViewById(R.id.questionTV);
        grid = findViewById(R.id.gridLayout);
        countDownCircle = (ImageView) findViewById(R.id.countDownCircleIV);
        countDownText = (TextView) findViewById(R.id.countDownTextView);
        playAgain = (ImageView) findViewById(R.id.playAgainIV);
        gameOver = (ImageView) findViewById(R.id.gameOverIV);
        resultsTV = (TextView) findViewById(R.id.resultsTV);
        quickMathsLogo = (ImageView) findViewById(R.id.quickMathsIV);
        twoPlusTwo = (ImageView) findViewById(R.id.twoplustwoIV);
        add = (ImageView) findViewById(R.id.plusIV);
        subtract = (ImageView) findViewById(R.id.minusIV);
        divide = (ImageView) findViewById(R.id.divideIV);
        multiply = (ImageView) findViewById(R.id.multiplyIV);
        integral = (ImageView) findViewById(R.id.integralIV);

        answerButton0 = (Button) findViewById(R.id.answer1);
        answerButton1 = (Button) findViewById(R.id.answer2);
        answerButton2 = (Button) findViewById(R.id.answer3);
        answerButton3 = (Button) findViewById(R.id.answer4);

        violinMusic = MediaPlayer.create(this,R.raw.cellomusic);
        correctMusic = MediaPlayer.create(this,R.raw.correct);
        incorrectMusic = MediaPlayer.create(this,R.raw.incorrectaudio);
        clockMusic = MediaPlayer.create(this,R.raw.clock);
        gameOverMusic = MediaPlayer.create(this,R.raw.gameover);

        //________________________Setting Visibility_________________//
        correctIV.setVisibility(View.INVISIBLE);
        incorrectIV.setVisibility(View.INVISIBLE);
        startButton.setVisibility(View.VISIBLE);
        questionTV.setVisibility(View.INVISIBLE);
        grid.setVisibility(View.INVISIBLE);
        countDownCircle.setVisibility(View.INVISIBLE);
        countDownText.setVisibility(View.INVISIBLE);


        //_________________ Animate __________//
        startButton.animate().alpha(1f).setDuration(2000);
        quickMathsLogo.setTranslationY(-500);
        quickMathsLogo.setAlpha(1f);
        quickMathsLogo.animate().translationYBy(500).setDuration(2000);
        twoPlusTwo.setTranslationY(500);
        twoPlusTwo.animate().translationYBy(-500).setDuration(2000);
        multiply.setTranslationX(-200);
        multiply.animate().translationXBy(200).setDuration(2000);
        add.setTranslationX(-200);
        add.animate().translationXBy(200).setDuration(2000);
        subtract.setTranslationX(200);
        subtract.animate().translationXBy(-200).setDuration(2000);
        divide.setTranslationX(200);
        divide.animate().translationXBy(-200).setDuration(2000);
        integral.setTranslationX(500);
        integral.animate().translationXBy(-500).setDuration(2000);
        integral.animate().rotation(3600).setDuration(2000);




        //________________________Sound___________________________-//
        violinMusic.start();

    }
}
