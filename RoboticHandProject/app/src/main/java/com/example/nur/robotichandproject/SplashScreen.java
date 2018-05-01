package com.example.nur.robotichandproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_splash_screen);
        Thread t = new Thread(){
                public void run(){
                    try {
                        Thread.sleep(4000);  // change the time according to your needs(its in milliseconds)
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);     // change the activity you want to load
                        startActivity(i);
                        finish();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
        t.start();
    }
}
