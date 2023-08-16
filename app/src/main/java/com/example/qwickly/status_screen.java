package com.example.qwickly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class status_screen extends AppCompatActivity {

    private ImageButton statusScreenTohomeScreen;
    private ImageButton statusScreenToqrCodeScreen;
    private ImageButton statusScreenTohourLogScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status_screen);

        //changes screen from Status Screen to Menu Screen
        statusScreenTohomeScreen = (ImageButton) findViewById(R.id.statusScreen_homeIcon);
        statusScreenTohomeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(status_screen.this, menu_screen.class);
                startActivity(intent);
            }
        });

        //changes screen from Status Screen to QR Code Screen
        statusScreenToqrCodeScreen = (ImageButton) findViewById(R.id.statusScreen_QRCodeIcon);
        statusScreenToqrCodeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(status_screen.this, qr_code_screen.class);
                startActivity(intent);
            }
        });

        //changes screen from Status Screen to Hour Log Screen
        statusScreenTohourLogScreen = (ImageButton) findViewById(R.id.statusScreen_hourLogIcon);
        statusScreenTohourLogScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(status_screen.this, hour_log_screen.class);
                startActivity(intent);
            }
        });

    }
}