package com.ryzen.contacts;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }
    @Override
    protected void onStart() {
        super.onStart();
        new CountDownTimer(5000,100) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent next = new Intent(getApplicationContext(),DisplayContact.class);
                startActivity(next);
                finish();
            }
        }.start();
    }
}
