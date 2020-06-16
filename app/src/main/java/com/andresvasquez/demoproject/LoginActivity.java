package com.andresvasquez.demoproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;

import com.andresvasquez.demoproject.model.User;
import com.andresvasquez.demoproject.repository.UserRepository;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {

    private static final String LOG = LoginActivity.class.getSimpleName();
    private Context context;

    private Button loginButton;
    private Button registerButton;

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginActionButton;

    private RelativeLayout popupRelativeLayout;

    private static final int FOOTER_ID = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(render());

        addEvents();
    }

    private void addEvents() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(context, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupRelativeLayout.setVisibility(View.VISIBLE);
            }
        });

        popupRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupRelativeLayout.setVisibility(View.GONE);
            }
        });

        loginActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateLogin();
            }
        });
    }

    private View render() {
        RelativeLayout parentRelativeLayout = new RelativeLayout(context);
        parentRelativeLayout.setBackgroundColor(getResources().getColor(android.R.color.white));

        LinearLayout footerLinearLayout = new LinearLayout(context);
        footerLinearLayout.setId(FOOTER_ID);
        footerLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

        RelativeLayout.LayoutParams footerLayoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        footerLayoutParams.setMargins(20, 20, 20, 40);
        footerLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        footerLinearLayout.setLayoutParams(footerLayoutParams);

        //Botones
        LinearLayout.LayoutParams buttonsLayoutParams = new LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1);

        loginButton = new Button(context);
        loginButton.setText(getString(R.string.button_login));
        loginButton.setLayoutParams(buttonsLayoutParams);
        loginButton.setBackgroundResource(R.color.colorPrimary);
        loginButton.setTextColor(getResources().getColor(android.R.color.white));
        footerLinearLayout.addView(loginButton);

        registerButton = new Button(context);
        registerButton.setText(getString(R.string.button_register));
        registerButton.setLayoutParams(buttonsLayoutParams);
        footerLinearLayout.addView(registerButton);

        parentRelativeLayout.addView(footerLinearLayout);

        //Image background
        ImageView backgroundImage = new ImageView(context);
        RelativeLayout.LayoutParams imageLayoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        imageLayoutParams.addRule(RelativeLayout.ABOVE, FOOTER_ID);
        backgroundImage.setImageResource(R.drawable.background);
        parentRelativeLayout.addView(backgroundImage);

        popupRelativeLayout = addLoginPopup();
        popupRelativeLayout.setVisibility(View.INVISIBLE);
        parentRelativeLayout.addView(popupRelativeLayout);

        return parentRelativeLayout;
    }

    private RelativeLayout addLoginPopup() {
        RelativeLayout transparentRelativeLayout = new RelativeLayout(context);
        RelativeLayout.LayoutParams transparentLp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        transparentRelativeLayout.setLayoutParams(transparentLp);
        transparentRelativeLayout.setBackgroundColor(getResources().getColor(R.color.semi_transparent));
        transparentRelativeLayout.setPadding(100, 0, 100, 0);

        LinearLayout loginFormLinearLayout = new LinearLayout(context);
        loginFormLinearLayout.setOrientation(LinearLayout.VERTICAL);
        loginFormLinearLayout.setBackground(getDrawable(R.drawable.login_popup));

        RelativeLayout.LayoutParams formLp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        formLp.addRule(RelativeLayout.CENTER_IN_PARENT);
        loginFormLinearLayout.setLayoutParams(formLp);

        //Label
        TextView emailTextView = new TextView(context);
        emailTextView.setText(getString(R.string.register_email));
        loginFormLinearLayout.addView(emailTextView);
        //Field
        emailEditText = new EditText(context);
        loginFormLinearLayout.addView(emailEditText);

        //Label
        TextView passwordTextView = new TextView(context);
        passwordTextView.setText(getString(R.string.register_password));
        loginFormLinearLayout.addView(passwordTextView);
        //Field
        passwordEditText = new EditText(context);
        passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        loginFormLinearLayout.addView(passwordEditText);

        loginActionButton = new Button(context);
        loginActionButton.setBackgroundResource(R.color.colorPrimary);
        loginActionButton.setTextColor(getResources().getColor(android.R.color.white));
        loginActionButton.setText(getString(R.string.button_login));

        LinearLayout.LayoutParams loginLp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        loginLp.setMargins(0, 50, 0, 0);
        loginActionButton.setLayoutParams(loginLp);
        loginFormLinearLayout.addView(loginActionButton);

        transparentRelativeLayout.addView(loginFormLinearLayout);
        return transparentRelativeLayout;
    }

    private void validateLogin() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, getString(R.string.error_fields), Toast.LENGTH_SHORT).show();
            return;
        }

        User userLogged = UserRepository.getInstance().login(email, password);
        if (userLogged == null) {
            Toast.makeText(context, getString(R.string.error_login), Toast.LENGTH_SHORT).show();
            return;
        }

        Intent mainActivityIntent = new Intent(context, MainActivity.class);
        mainActivityIntent.putExtra("user", new Gson().toJson(userLogged));
        startActivity(mainActivityIntent);
    }
}
