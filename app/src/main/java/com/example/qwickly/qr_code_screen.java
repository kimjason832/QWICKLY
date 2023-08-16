package com.example.qwickly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class qr_code_screen extends AppCompatActivity {

    private ImageButton qrCodeScreenTohomeScreen;
    private ImageButton qrCodeScreenTostatusScreen;
    private ImageButton qrCodeScreenTohourLogScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_code_screen);

        //changes screen from QR Code Screen to Menu Screen
        qrCodeScreenTohomeScreen = (ImageButton) findViewById(R.id.qrCodeScreen_homeIcon);
        qrCodeScreenTohomeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(qr_code_screen.this, menu_screen.class);
                startActivity(intent);
            }
        });

        //changes screen from QR Code Screen to Status Screen
        qrCodeScreenTostatusScreen = (ImageButton) findViewById(R.id.qrCodeScreen_statusIcon);
        qrCodeScreenTostatusScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(qr_code_screen.this, status_screen.class);
                startActivity(intent);
            }
        });

        //changes screen from QR Code Screen to Hour Log Screen
        qrCodeScreenTohourLogScreen = (ImageButton) findViewById(R.id.qrCodeScreen_hourLogIcon);
        qrCodeScreenTohourLogScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(qr_code_screen.this, hour_log_screen.class);
                startActivity(intent);
            }
        });

    }
}