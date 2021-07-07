package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.parse.ParseUser;

public class FrontActivity extends AppCompatActivity {

    Button loginBtn;
    Button signupBtn;
    public static final String TAG = "FrontActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);


        if(ParseUser.getCurrentUser() != null){
            goMainActivity();
        }

        loginBtn = findViewById(R.id.login);
        signupBtn = findViewById(R.id.signup);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FrontActivity.this, LoginActivity.class);
                startActivity(i);
                //finish();
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FrontActivity.this, SignupPage.class);
                startActivity(i);
            }
        });



    }


    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();

    }
}