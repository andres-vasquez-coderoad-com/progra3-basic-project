package com.andresvasquez.demoproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final String LOG = SplashActivity.class.getSimpleName();
    private Context context;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_splash);
        initViews();

        simulateLoading();
    }

    private void initViews() {
        progressBar = findViewById(R.id.progressBar);
    }

    private void openNextScreen() {
        Intent loginIntent = new Intent(context, LoginActivity.class);
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Que la carta se elimine
        startActivity(loginIntent);
    }

    private void simulateLoading() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //Esperamos y actualizamos
                    Thread.sleep(500);
                    updateProgress(20);

                    Thread.sleep(500);
                    updateProgress(40);

                    Thread.sleep(500);
                    updateProgress(60);

                    Thread.sleep(500);
                    updateProgress(80);

                    Thread.sleep(1000);
                    updateProgress(99);
                    openNextScreen();
                } catch (InterruptedException ex) {
                    Log.e(LOG, "InterruptedException." + ex.getMessage());
                }
            }
        }).start();
    }

    private void updateProgress(final int progress) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setProgress(progress);
            }
        });
    }
}
