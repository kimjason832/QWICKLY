package com.example.qwickly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class signIn_screen extends AppCompatActivity {

    private Button signInScreenTosignUpScreen;
    private Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_screen);

        //changes screen from Sign In Screen to Sign Up Screen
        signInScreenTosignUpScreen = (Button) findViewById(R.id.signInScreen_signUpButton);
        signInScreenTosignUpScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signIn_screen.this, sign_up_screen.class);
                startActivity(intent);
            }
        });

        //changes screen from Sign In Screen to Menu Screen
        signInButton = (Button) findViewById(R.id.signInScreen_signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signIn_screen.this, menu_screen.class);
                startActivity(intent);
            }
        });

    }
}