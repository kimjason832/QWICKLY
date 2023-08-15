package com.example.qwickly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sign_up_screen extends AppCompatActivity {

    private Button signUpScreenTosignInScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_screen);

        //changes screen from Sign In Screen to Sign Up Screen
        signUpScreenTosignInScreen = (Button) findViewById(R.id.signUpScreen_signInButton);
        signUpScreenTosignInScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sign_up_screen.this, signIn_screen.class);
                startActivity(intent);
            }
        });

    }
}