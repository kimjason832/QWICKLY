package com.example.qwickly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class hour_log_screen extends AppCompatActivity {

    private ImageButton hourLogScreenTomenuScreen;
    private ImageButton hourLogScreenToqrCodeScreen;
    private ImageButton hourLogScreenTostatusScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hour_log_screen);

        //changes screen from Status Screen to Menu Screen
        hourLogScreenTomenuScreen = (ImageButton) findViewById(R.id.hourLogScreen_homeIcon);
        hourLogScreenTomenuScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hour_log_screen.this, menu_screen.class);
                startActivity(intent);
            }
        });

        //changes screen from Status Screen to QR Code Screen
        hourLogScreenToqrCodeScreen = (ImageButton) findViewById(R.id.hourLogScreen_QRCodeIcon);
        hourLogScreenToqrCodeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hour_log_screen.this, qr_code_screen.class);
                startActivity(intent);
            }
        });

        //changes screen from Status Screen to Status Screen
        hourLogScreenTostatusScreen = (ImageButton) findViewById(R.id.hourLogScreen_statusIcon);
        hourLogScreenTostatusScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hour_log_screen.this, status_screen.class);
                startActivity(intent);
            }
        });

    }
}