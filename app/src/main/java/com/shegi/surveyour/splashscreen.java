package com.shegi.surveyour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(2000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    startActivity(new Intent(splashscreen.this, MainActivity.class));
                    finish();
                }
    }
        };
        thread.start();
    }
}
