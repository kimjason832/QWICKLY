package com.example.qwickly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class start_screen extends AppCompatActivity {

    private Button startScreenTosignInScreen;
    private Button startScreenTosignUpScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        //changes screen from Start Screen to Sign In Screen
        startScreenTosignInScreen = (Button) findViewById(R.id.startScreen_signInButton);
        startScreenTosignInScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(start_screen.this, signIn_screen.class);
                startActivity(intent);
            }
        });

        //changes screen from Start Screen to Sign Up Screen
        startScreenTosignUpScreen = (Button) findViewById(R.id.startScreen_signUpButton);
        startScreenTosignUpScreen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(start_screen.this, sign_up_screen.class);
                startActivity(intent);
            }
        });


    }
}