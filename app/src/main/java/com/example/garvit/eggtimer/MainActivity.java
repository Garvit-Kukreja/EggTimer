package com.example.garvit.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView timerTextView;
    SeekBar timerSeekBar;
    Boolean CounterActive=false;
    Button goButton;
    CountDownTimer countDownTimer;
    public void resetTimer()
    {
        timerTextView.setText("0:30");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("GO!");
        CounterActive=false;

    }
    public void ButtonClicked(View view) {
        if(CounterActive){
           resetTimer();
        }
       else{
            CounterActive = true;
            timerSeekBar.setEnabled(false);
            goButton.setText("STOP");
             countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                public void onFinish() {
                    Log.i("Finished", "Timer all done");
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.at);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        }
    }
    public void updateTimer(int SecondsLeft){
        int minutes =SecondsLeft/60;
        int seconds =SecondsLeft-(minutes*60);

        String SecondString = Integer.toString(seconds);
        if(seconds<=9){
            SecondString="0"+seconds;
        }
        timerTextView.setText(Integer.toString(minutes)+":"+SecondString );


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.timerSeekBar);
         timerTextView = findViewById(R.id.timerTextView);
         goButton = findViewById(R.id.timerButton);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);
         timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             @Override
             public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                 updateTimer(i);
                 }

             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {

             }

             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {

             }
         });
    }
}
