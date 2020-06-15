package com.andresvasquez.demoproject;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String LOG = LoginActivity.class.getSimpleName();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(render());

        addEvents();
    }

    private void addEvents() {

    }

    private View render() {
        RelativeLayout parentRelativeLayout = new RelativeLayout(context);
        return parentRelativeLayout;
    }
}
