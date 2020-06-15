package com.andresvasquez.demoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

public class RegisterActivity extends AppCompatActivity {

    private static final String LOG = RegisterActivity.class.getSimpleName();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_register);

        initViews();
        addEvents();
    }

    private void initViews() {

    }

    private void addEvents() {

    }
}
