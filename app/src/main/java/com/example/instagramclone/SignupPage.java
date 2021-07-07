package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupPage extends AppCompatActivity {

    Button signupBtn;
    TextView emailTxt;
    TextView usernameTxt;
    TextView passwordTxt;
    public static final String TAG = "SignupPage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        signupBtn = findViewById(R.id.login);
        usernameTxt = findViewById(R.id.username);
        passwordTxt = findViewById(R.id.password);
        emailTxt = findViewById(R.id.email);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "login click success");
                String username = usernameTxt.getText().toString();
                String password = passwordTxt.getText().toString();
                String email = emailTxt.getText().toString();
                signupUser(username, password, email);
            }
        });

    }

    private void signupUser(String username, String password, String email) {
        ParseUser user = new ParseUser();

        // Set core properties
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    Toast.makeText(SignupPage.this, "Congrats, you have made an account!", Toast.LENGTH_SHORT).show();
                    goMainActivity();

                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Log.e(TAG, "issue with login", e);
                    Toast.makeText(SignupPage.this,"issue with signup", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });


    }
    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();

    }
}