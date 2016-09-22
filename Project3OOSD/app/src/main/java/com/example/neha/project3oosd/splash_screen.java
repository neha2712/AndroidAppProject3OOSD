package com.example.neha.project3oosd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread startTimer = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                    Intent i = new Intent(splash_screen.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

        };
        startTimer.start();
    }
}
