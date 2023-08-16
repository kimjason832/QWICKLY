package com.example.qwickly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class menu_screen extends AppCompatActivity {

    private Button scanQRCodeButton;
    private Button seeCurrentStatusButton;
    private Button viewTotalHoursButton;
    private ImageButton menuScreenToqrCodeScreen;
    private ImageButton menuScreenTostatusScreen;
    private ImageButton menuScreenToHoursScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_screen);

        //changes screen from Menu Screen to QR Code Screen
        scanQRCodeButton = (Button) findViewById(R.id.menuScreen_scanQRCodeButton);
        scanQRCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu_screen.this, qr_code_screen.class);
                startActivity(intent);
            }
        });

        //changes screen from Menu Screen to Status Screen
        seeCurrentStatusButton = (Button) findViewById(R.id.menuScreen_seeCurrentStatusButton);
        seeCurrentStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu_screen.this, status_screen.class);
                startActivity(intent);
            }
        });

        //changes screen from Menu Screen to Hour Log Screen
        viewTotalHoursButton = (Button) findViewById(R.id.menuScreen_viewTotalHoursButton);
        viewTotalHoursButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu_screen.this, hour_log_screen.class);
                startActivity(intent);
            }
        });

        //changes screen from Menu Screen to QR Code Screen
        menuScreenToqrCodeScreen = (ImageButton) findViewById(R.id.menuScreen_QRCodeIcon);
        menuScreenToqrCodeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu_screen.this, qr_code_screen.class);
                startActivity(intent);
            }
        });

        //changes screen from Menu Screen to Status Screen
        menuScreenTostatusScreen = (ImageButton) findViewById(R.id.menuScreen_statusIcon);
        menuScreenTostatusScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu_screen.this, status_screen.class);
                startActivity(intent);
            }
        });

        //changes screen from Menu Screen to Hour Log Screen
        menuScreenToHoursScreen = (ImageButton) findViewById(R.id.menuScreen_hourLogIcon);
        menuScreenToHoursScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu_screen.this, hour_log_screen.class);
                startActivity(intent);
            }
        });

    }
}