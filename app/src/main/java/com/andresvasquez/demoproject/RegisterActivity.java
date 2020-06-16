package com.andresvasquez.demoproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.andresvasquez.demoproject.model.User;
import com.andresvasquez.demoproject.repository.UserRepository;

public class RegisterActivity extends AppCompatActivity {

    private static final String LOG = RegisterActivity.class.getSimpleName();
    private Context context;

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText password2EditText;
    private Spinner countrySpinner;
    private Button registerButton;
    private AppCompatSeekBar demoSeekBar;

    private String countrySelected = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_register);

        initViews();
        addEvents();
    }

    private void initViews() {
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditTExt);
        password2EditText = findViewById(R.id.password2EditTExt);
        countrySpinner = findViewById(R.id.countrySpinner);
        demoSeekBar = findViewById(R.id.demoSeekBar);
        registerButton = findViewById(R.id.registerButton);
    }

    private void addEvents() {
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                countrySelected = adapterView.getItemAtPosition(position).toString();
                Log.d(LOG, "onItemSelected:" + countrySelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        demoSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int value, boolean b) {
                Log.d(LOG, "onProgressChanged:" + value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateRegister();
            }
        });
    }

    private void validateRegister() {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String password2 = password2EditText.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty() || countrySelected.isEmpty()) {
            Toast.makeText(context, getString(R.string.error_fields), Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidEmail(email)) {
            Toast.makeText(context, getString(R.string.error_email), Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(password2)) {
            Toast.makeText(context, getString(R.string.error_password), Toast.LENGTH_SHORT).show();
            return;
        }

        User newUser = new User(name, email, password, countrySelected);
        UserRepository.getInstance().register(newUser);
        finish();
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
